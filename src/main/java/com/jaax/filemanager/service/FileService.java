package com.jaax.filemanager.service;

import com.jaax.filemanager.entity.FileEntity;
import com.jaax.filemanager.response.ResponseFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FileService {
    // Permite almacenar o cargar archivos a la base de datos
    FileEntity store(MultipartFile file) throws IOException;

    // Permite descargar archivos de nuestra base de datos
    Optional<FileEntity> getFile (UUID id) throws FileNotFoundException;

    // Permite consultar la lista de archivos cargados a nuestra base de datos
    List<ResponseFile> getAllFiles();
}
