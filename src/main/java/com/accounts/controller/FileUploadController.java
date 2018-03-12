package com.accounts.controller;

import com.accounts.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by alex on 3/10/18.
 */
@RestController
@RequestMapping(value = "/files")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @RequestMapping("")
    public String[] listUploadedFiles() {
        return fileUploadService.listFiles();
    }

    @PostMapping("/upload")
    public String fileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "select file";
        }
        return fileUploadService.fileUpload(file);
    }
}
