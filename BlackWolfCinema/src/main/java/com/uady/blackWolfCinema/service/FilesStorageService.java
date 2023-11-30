package com.uady.blackWolfCinema.service;

import java.nio.file.Path;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
    public void startFileStorage();

	public String saveFile(MultipartFile file);

	public Path loadFile(String fileName);

	public Resource loadAsResource(String fileName);

	public void deleteFile(String fileName);
}
