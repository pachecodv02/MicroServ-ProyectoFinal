package com.report.processfile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification.S3EventNotificationRecord;
import com.amazonaws.services.s3.model.S3Object;

import com.report.processfile.models.FileReport;
import com.report.processfile.models.Report;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HandlerProcessFile implements RequestHandler<S3Event, String> {
    private static Logger logger = LoggerFactory.getLogger(HandlerProcessFile.class);
    private final String CSV_TYPE = (String) "csv";
    private final String SEPARATOR = ",";

    

    @Override
    public String handleRequest(S3Event s3Event, Context context) {
        logger.info("Se detecta evento informado por s3:::V6");
        try{
            S3EventNotificationRecord record =  s3Event.getRecords().get(0);

            //Bucket en el que se disparo el evento
            String srcBucket = record.getS3().getBucket().getName();
            logger.info("Bucket: {}", srcBucket);

            // Obteneiendo el nombre del archivo
            String srcKey = record.getS3().getObject().getUrlDecodedKey();
            logger.info("Nombre de archivo: {}", srcKey);

            // Descargando la imagen del S3 dentro de un input stream
            AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
            S3Object s3Object = s3Client.getObject(new GetObjectRequest(
                    srcBucket, srcKey));
            InputStream objectData = s3Object.getObjectContent();

            logger.info("Objeto recuperado del S3");

            // Validando el tipo de archivo
            Matcher matcher = Pattern.compile(".*\\.([^\\.]*)").matcher(srcKey);
            if (!matcher.matches()) {
                System.out.println("No disponible el tipo de imagen para la llave "
                        + srcKey);
                return "";
            }
            String imageType = matcher.group(1);
            if (CSV_TYPE.equals(imageType)) {
                logger.info("Procesando archivo CSV");
                BufferedReader br = null;
                FileReport file = null;
      
                try {
                    file = new FileReport();
                    file.setIdReport(Integer.parseInt(srcKey.split("_")[0]));
                    
                    br =new BufferedReader(new InputStreamReader(objectData));

                    logger.info("El repositorio es null, se intenta guardar la informacion directo a dynamo");
                    AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
                    logger.info("Obteniendo instancia de Dinamo");
                    DynamoDB dynamoDB = new DynamoDB(client);
                    
                    logger.info("Obteniendo la tabla");
                    Table table = dynamoDB.getTable("filereport");
                    
                    String line = br.readLine();
                    logger.info("Cabeceros no se guardan");
                    
                    Report registro = null;
                    while ((line=br.readLine())!=null) {

                        Item item = new Item();
                        String idKey = getId();
                        logger.info("IdKEy DynamoBD: {}", idKey);
                        if(idKey != null && !idKey.equals("")){
                            item.withPrimaryKey("id",idKey);
                        }else{
                            item.withPrimaryKey("id","u87900-001"+file.getIdReport());
                        }
                        String [] fields = line.split(SEPARATOR);
                        item.withNumber("idReport", file.getIdReport());
                        item.withInt("cantidad", Integer.parseInt(fields[0]));
                        item.withString("descripcion", fields[1]);
                        item.withFloat("precio", Float.parseFloat(fields[2]));
                        item.withFloat("total", Float.parseFloat(fields[3]));
                    
                        logger.info("Agregando item a la tabla");
                        PutItemOutcome  res =table.putItem(item);

                    }
                    logger.info("Ser termino de leer el archivo CSV");
                    
                    logger.info("Informacion del reporte guardada correctamente ");

                
                    
                    
                } catch (Exception e) {
                    logger.info("Incidencia al procesar el archivo ", e);
                } finally {
                    if (null!=br) {
                        br.close();
                    }
                }


            }else{
                logger.info("Tipo de archivo no procesable");
                return "Tipo de archivo no procesable";
            }


        }catch(IOException e){
            throw new RuntimeException(e);
        }
        logger.info("Proceso terminado OK");
        return "Proceso terminado OK";

    }

    

    
    public String getId() {
        String id = genCadena(8)+"-"+genCadena(4)+"-"+genCadena(4)+"-"+genCadena(4)+"-"+genCadena(12);

        return id;
    }

    private String genCadena(int tamanio){
        String  cad ="";
        for(int i =0; i<tamanio; i++){
            String alphabet = "0123456789abcdefghijklmnopqrstuvwxyz";
            Random rnd = new Random();
            
             cad = cad+alphabet.charAt(rnd.nextInt(alphabet.length()));
        }

        return cad;
    }

}
