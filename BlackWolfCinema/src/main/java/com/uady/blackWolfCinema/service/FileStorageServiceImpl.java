package com.uady.blackWolfCinema.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.uady.blackWolfCinema.exceptions.FileNotFoundException;
import com.uady.blackWolfCinema.exceptions.StorageException;
import jakarta.annotation.PostConstruct;

@Service
public class FileStorageServiceImpl  implements FilesStorageService{

    @Value("${storage.location}")
    private String storageLocation;
    
    //esta sirve para indicar que este metodo se va a ejecutar cada vez que halla una nueva instancia de esta esta clase
	@PostConstruct
    @Override
    public void startFileStorage() {
        try {
			Files.createDirectories(Paths.get(storageLocation));
		}catch (IOException excepcion) {
			throw new StorageException("Error al inicializar la ubicación en el almacen de archivos");
		}
    }

    @Override
    public String saveFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
		if(file.isEmpty()) {
			throw new StorageException("No se puede almacenar un archivo vacio");
		}
		try {
			InputStream inputStream  = file.getInputStream();
			Files.copy(inputStream,Paths.get(storageLocation).resolve(fileName),StandardCopyOption.REPLACE_EXISTING);
		}catch (IOException excepcion) {
			throw new StorageException("Error al almacenar el archivo " + fileName,excepcion);
		}
		return fileName;
    }

    @Override
    public Path loadFile(String fileName) {
        return Paths.get(storageLocation).resolve(fileName);

    }

    @Override
    public Resource loadAsResource(String fileName) {
        try {
			Path archivo = loadFile(fileName);
			Resource recurso = new UrlResource(archivo.toUri());
			
			if(recurso.exists() || recurso.isReadable()) {
				return recurso;
			}else {
				throw new FileNotFoundException("No se pudo encontrar el archivo " + fileName);
			}
		
		}catch (MalformedURLException excepcion) {
			throw new FileNotFoundException("No se pudo encontrar el archivo " + fileName,excepcion);
		}
    }

    @Override
    public void deleteFile(String fileName) {
        Path archivo = loadFile(fileName);
		try {
			FileSystemUtils.deleteRecursively(archivo);
		}catch (Exception excepcion) {
			System.out.println(excepcion);
		}
    }
    
}
