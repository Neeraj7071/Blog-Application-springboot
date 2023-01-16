package com.neebal.service.imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.neebal.service.FileService;

@Service
public class FileServiceImp implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		//getname
		String name=file.getOriginalFilename();
		
		
		System.out.println(name);
		//randome name genrater
		String randomId=UUID.randomUUID().toString();
		String fileName=randomId.concat(name.substring(name.lastIndexOf(".")));
		
		
		//full path
		String filePath=path+File.separator+fileName;
		
		
		
		//create folder
		File f=new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		System.out.println(filePath);
		System.out.println();
		//copy file with change name
		Files.copy(file.getInputStream(), Paths.get(filePath));
		return fileName;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullpath=path+File.separator+fileName;
		InputStream is=new FileInputStream(fullpath);
		return is;
	}

}
