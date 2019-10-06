package com.young.inbyul.board.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.young.inbyul.user.model.CustomUser;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Board {

	int bno;
	String bcontent;
	int likecnt;
	int commentcnt;
	Date b_reg_dt;
	CustomUser user;
	String timestring;
	int heart;
	//Map<String,MultipartFile> files;
	List<MultipartFile> uploadFileArr;
	List<FileVO> rmvFileArr;
	
	List<FileVO> fileArr;
	String folderpath;

}
