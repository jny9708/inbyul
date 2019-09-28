package com.young.inbyul.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.young.inbyul.board.model.FileVO;


public class FileProcess {

	public File remakeFile(MultipartFile originFile,String path) throws Exception{
		
			System.out.println(originFile.getOriginalFilename());
			UUID uuid = UUID.randomUUID();
			String originalName = originFile.getOriginalFilename();
			String type = originalName.substring(originalName.lastIndexOf("."));
			String now = new SimpleDateFormat("yyyyMMddHmsS").format(new Date()); //현재시간
			String saveName = uuid + now + type;
			File remakeFile = new File(path, saveName); // 저장할 폴더 이름, 저장할 파일 이름
			
		return remakeFile;
	}
	

	public void saveFileToLocalDisk(MultipartFile originFile,File remakeFile) throws Exception {
			FileCopyUtils.copy(originFile.getBytes(), remakeFile);
	}
	
	public void removeFileToLocalDisk(String file_path, String folderPath) throws Exception{
		
		file_path = file_path.substring(file_path.lastIndexOf("/")); //파일 이름만 담게끔..
		File rmvFile = new File(folderPath,file_path);
		if(rmvFile.exists()) {
			rmvFile.delete();
		}	
	}
	
	
}
