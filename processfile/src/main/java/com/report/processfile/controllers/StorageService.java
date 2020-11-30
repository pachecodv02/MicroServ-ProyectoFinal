package com.report.processfile.controllers;

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStream;
// import java.io.InputStreamReader;
// import java.util.ArrayList;
// import java.util.Date;
// import java.util.List;
// import java.util.regex.Matcher;
// import java.util.regex.Pattern;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;

// import com.amazonaws.services.s3.model.S3Object;
// import com.report.processfile.models.FileReport;
// import com.report.processfile.models.Report;
// import com.report.processfile.repositories.FileReportRepository;
// import com.amazonaws.services.s3.AmazonS3;
// import com.amazonaws.services.s3.model.GetObjectRequest;

//@Service
public class StorageService {
    /*
    private static Logger logger = LoggerFactory.getLogger(StorageService.class);
    private final String CSV_TYPE = (String) "csv";
    private final String XLS_TYPE = (String) "xls";
    private final String SEPARATOR = ",";

    @Value("${amazon_aws_bucket}")
    private String bucket;

    @Autowired
    AmazonS3 s3;

    @Autowired
    FileReportRepository repository;

    public String getFile(String fileName)  {
        

        logger.info("Se detecta evento informado por s3");
        try{
            
            

            
            S3Object object = s3.getObject(new GetObjectRequest(bucket, fileName));
         
            InputStream objectData = object.getObjectContent();

            logger.info("Objeto recuperado del S3");

            // Infer the image type.
            Matcher matcher = Pattern.compile(".*\\.([^\\.]*)").matcher(fileName);
            if (!matcher.matches()) {
                System.out.println("No disponible el tipo de imagen para la llave "
                        + fileName);
                return "";
            }
            String imageType = matcher.group(1);
            if (CSV_TYPE.equals(imageType)) {
                logger.info("Procesando archivo CSV");
                BufferedReader br = null;
                FileReport file = null;
      
                try {
                    file = new FileReport();
                    List<Report> registros = new ArrayList<>();
                    file.setFechaProcces(new Date());
                    file.setIdReport(Integer.parseInt(fileName.split("_")[0]));
                    file.setNameFile(fileName);
                    
                    br =new BufferedReader(new InputStreamReader(objectData));

                    
                    String line = br.readLine();
                    logger.info("Cabeceros no se guardan");
                    Report registro = null;
                    while ((line=br.readLine())!=null) {
                        
                        String [] fields = line.split(SEPARATOR);
                        registro =  new Report(Integer.parseInt(fields[0]), fields[1], Float.parseFloat(fields[2]), Float.parseFloat(fields[3]));
                        logger.info("Agregando registro {}", fields.toString());
                        registros.add(registro);

                    }
                    logger.info("Ser termino de leer el archivo CSV");
                    file.setContentFile(registros);
                    
                if(this.repository != null){
                    logger.info("Guardando la informacion en dynamo..");
                    this.repository.save(file);
                }else{
                    logger.info("El repositorio es null");
                }
                    
                    
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
    */
}


