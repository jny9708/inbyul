package com.young.inbyul.board.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.young.inbyul.board.model.Board;
import com.young.inbyul.board.repository.BoardRepository;
import com.young.inbyul.user.model.CustomUser;

@Service
public class BoardService {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardService.class);
	
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
	
	public List<CustomUser> getReUser(int uno) throws Exception{
		return boardRepository.getReUseList(uno);
	}
	
	@Transactional
	public void insertBoard(Board board,String path) throws Exception{
		//Map<String, MultipartFile> fileMap = request.getFileMap();
		
		List<MultipartFile> fileList = new ArrayList<>(board.getFile().values()); // 사용자가 보내온 멀티파일들 받기 
		List<File> saveFile_list = new ArrayList<>();  //이름바꾸기위해서 새로만든 file 객체들 담음
		
		//리스트로 따로 만들어서 다른 함수에서 파일저장을 하는 이유는 모든 db값이 정상적으로 저장되었을때 마지막으로 저장되게끔하고싶었기 때문이다.
		//db저장 파일저장 따로 하고싶었던 이유는
		//예를 들어 3개의 파일이 넘어왔을때 2개는 db값과 파일이  정상적으로 저장되고  1개만 db값이 저장되다 오류를 일으켰을때 저장된 파일을 다시 모두 삭제해야하는데
		//오류를 일으켰을 때 삭제하는것 보다 그냥 디비저장완료 되었을 때 파일저장하는게 더 나을 것 같다 생각했다.
		
		List<String> dbpath_list = new ArrayList<>();
		
		
		boardRepository.insertBoard(board);
		
		for (MultipartFile multipartFile : fileList) {
			String dbpath = "/resources/images/postimages/";
			System.out.println(multipartFile.getOriginalFilename());
			
			File saveFile = makeFile(path,multipartFile);
			
			saveFile_list.add(saveFile);
			dbpath += saveFile.getName();
			dbpath_list.add(dbpath);
		} 
		board.setFile_path(dbpath_list);
		boardRepository.insertImages(board);
		uploadFile(fileList, saveFile_list);
	}
	
	public void uploadFile(List<MultipartFile> fileList,List<File> saveFile_list) throws Exception {
		for(int i =0; i<fileList.size(); i++) {
			FileCopyUtils.copy(fileList.get(i).getBytes(), saveFile_list.get(i));
		}
		
	}
	
	public File makeFile(String path, MultipartFile multipartFile) throws Exception{
		UUID uuid = UUID.randomUUID();
		String originalName = multipartFile.getOriginalFilename();
		String type = originalName.substring(originalName.lastIndexOf("."));
		String now = new SimpleDateFormat("yyyyMMddHmsS").format(new Date()); //현재시간
		String saveName = uuid + "_" + now + type;
		File saveFile = new File(path, saveName); // 저장할 폴더 이름, 저장할 파일 이름
		return saveFile;
	}
	
//	public List<Board> getBoardList(int uno) throws Exception{
//		List<Integer> followerList = boardRepository.getFollowerList(uno);
//		List<Board> boardList = boardRepository.getBoardList(uno, followerList);
//		
//		return boardList;
//	}
//
	
}
