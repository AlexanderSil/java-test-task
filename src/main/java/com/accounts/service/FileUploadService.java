package com.accounts.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by alex on 3/10/18.
 */
@Service
public class FileUploadService {

    @Value("${uploaded.folder}")
    private String uploadedFolder;

    public String[] listFiles() {
        File file = new File(uploadedFolder);
        return file.list();
    }


    public String fileUpload(MultipartFile file) {
        try {

            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadedFolder + file.getOriginalFilename());
            Files.write(path, bytes);

            return "successfully uploaded";

        } catch (IOException e) {
            e.printStackTrace();
            return "file no upload";
        }
    }
}
