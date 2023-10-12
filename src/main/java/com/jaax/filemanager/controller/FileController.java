package com.jaax.filemanager.controller;

import com.jaax.filemanager.entity.FileEntity;
import com.jaax.filemanager.response.ResponseFile;
import com.jaax.filemanager.response.ResponseMessage;
import com.jaax.filemanager.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/fileManager")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        fileService.store(file);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Archivo subido exitosamente"));
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable UUID id) throws FileNotFoundException {
        FileEntity fileEntity = fileService.getFile(id).get();
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, fileEntity.getType())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName()+"\"")
                .body(fileEntity.getData());
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles(){
        List<ResponseFile> files = fileService.getAllFiles();
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

}
