package com.jam.pocket.utils;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Jam on 2017/1/10.
 */
public class FileUtils {

    private final static Logger logger = Logger.getLogger(FileUtils.class);

    public static File saveTempFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile == null || multipartFile.isEmpty()) {
            throw new IllegalArgumentException("Save file failed, file can not null or empty.");
        }

        String name = "temp.csv";
        byte[] bytes = multipartFile.getBytes();

        String rootPath = getTempFolderPath();
        File dir = new File(rootPath + File.separator + "tmpFiles");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Create the file on server
        File serverFile = new File(dir.getAbsolutePath()
                + File.separator + name);
        BufferedOutputStream stream = new BufferedOutputStream(
                new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.close();

        logger.info("Server File Location="
                + serverFile.getAbsolutePath());
        return serverFile;
}

    private static String getTempFolderPath() {
        // Creating the directory to store file
//                String rootPath = System.getProperty("catalina.home");
        String rootPath = "tempData";
        return rootPath;
    }

}
