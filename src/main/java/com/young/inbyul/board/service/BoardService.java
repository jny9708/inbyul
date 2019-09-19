package com.young.inbyul.board.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.young.inbyul.board.repository.BoardRepository;
import com.young.inbyul.user.model.CustomUser;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository; 
	
	public int getFollowingCount(int uno) throws Exception {
		return boardRepository.getFollowingCount(uno);
	}
	
	public boolean checkRecommendUser(int uno)  throws Exception {
		int followingcount = boardRepository.getFollowingCount(uno);
		int postcount = boardRepository.getUserBoardCount(uno);
		if(followingcount<1 && postcount<1) {
			System.out.println("reok");
			return true;
		}else {
			return false;
		}
		
	}
	
	public List<CustomUser> getReUser() throws Exception{
		List<Integer> popUserList = boardRepository.getPopUserList();
		return boardRepository.getReUseListr(popUserList);
	}
	
	public void insertBoard(MultipartHttpServletRequest request) throws Exception{
		Map<String, MultipartFile> fileMap = request.getFileMap();
	
			for (MultipartFile multipartFile : fileMap.values()) {
				System.out.println(multipartFile.getOriginalFilename());
				UUID uuid = UUID.randomUUID();
				String originalName = multipartFile.getOriginalFilename();
				String type = originalName.substring(originalName.lastIndexOf("."));
				String now = new SimpleDateFormat("yyyyMMddHmsS").format(new Date()); //현재시간
				String saveName = uuid + "_" + now + type;
				System.out.println(saveName);

				File saveFile = new File(
						request.getSession().getServletContext().getRealPath("/resources/images/postimages"), saveName); // 저장할 폴더 이름, 저장할 파일 이름

				FileCopyUtils.copy(multipartFile.getBytes(), saveFile);
			} 
		
	}
	

	
}
