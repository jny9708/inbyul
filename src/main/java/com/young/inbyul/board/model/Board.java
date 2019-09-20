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
	int uno;
	Map<String,MultipartFile> file;
//	List<File> saveFile_list;
	List<String> file_path;
	int likecnt;
	int commentcnt;
	Date b_reg_dt;
	CustomUser user;
}
