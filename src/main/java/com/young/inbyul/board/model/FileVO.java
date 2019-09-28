package com.young.inbyul.board.model;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FileVO {
	
	//Map<String,MultipartFile> files; // dropzone 에서 받아오는 업로드요청 맵 나중에 list로 변형될 것임
//	List<Integer> fnos;
//	List<String> file_path;
//	
	//List<String> rmvFileArr;
	
	int fno;
	String file_path;
	File remakefile;
	//MultipartFile file; 
	
}
