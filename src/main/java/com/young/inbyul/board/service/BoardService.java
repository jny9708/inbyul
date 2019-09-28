package com.young.inbyul.board.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.young.inbyul.board.model.Board;
import com.young.inbyul.board.model.FileVO;
import com.young.inbyul.board.repository.BoardRepository;
import com.young.inbyul.user.model.CustomUser;
import com.young.inbyul.util.FileProcess;
import com.young.inbyul.util.TimeAgo;

@Service
public class BoardService extends FileProcess{
	
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
	public void insertBoard(Board board) throws Exception{
		//boardRepository.insertBoard(board);
		insertFile(board);
	}
	
	public void insertFile(Board board) throws Exception{
		
		for(MultipartFile originFile : board.getUploadFileArr()) {
			File remakeFile = remakeFile(originFile,getDestinationLocation());
			saveFileToLocalDisk(originFile, remakeFile);

			//saveFileToDatabase(remakeFile.getAbsolutePath(),board.getBno());
		}
	}
	
	public void saveFileToDatabase(String path, int bno) throws Exception{
		Map<String,Object> imageMap = new HashMap<>();
		imageMap.put("filepath", path.substring(path.lastIndexOf("\\resources")));
		imageMap.put("bno",bno);
		boardRepository.insertImage(imageMap);
	}
	
	private String getDestinationLocation() {
        return "C:\\javaide\\spring-tool-suite-4-4.3.1.RELEASE-e4.12.0-win32.win32.x86_64\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\inbyul\\resources\\images\\postimages";
	}
	
	
	@Transactional
	public List<Board> getBoardList(int uno) throws Exception{
		List<Integer> followerList = boardRepository.getFollowerList(uno);
		List<Board> boardList = boardRepository.getBoardList(uno, followerList);
		TimeAgo timeAgo = new TimeAgo();
		for(Board board : boardList) {
			String timestring = timeAgo.timeString(board.getB_reg_dt());
			board.setTimestring(timestring);
		}
		return boardList;
	}
	
	public int boardDelete(int bno) throws Exception{
		return boardRepository.deleteBoard(bno);
	}

	public Board getBoard(int bno) throws Exception{
		Board board = boardRepository.getBoard(bno);
		return board;
	}
	
	@Transactional
	public void updateBoard(Board board) throws Exception {
		
		boardRepository.updateBoard(board);
		updateFile(board);
	}
	
	public void updateFile(Board board) throws Exception{
		
		if(!board.getUploadFileArr().isEmpty()) {
			insertFile(board);
		}
		
		if(board.getRmvFileArr().size() > 0) {
			boardRepository.deleteImages(board.getRmvFileArr());
			for(FileVO rmvFile : board.getFileArr() ) {
				removeFileToLocalDisk(rmvFile.getFile_path(),getDestinationLocation());
			}
		}
		
		
		

	}
	
	
	
}
