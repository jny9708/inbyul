package com.young.inbyul.board.service;

import java.io.File;
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
import com.young.inbyul.util.Criteria;
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
		boardRepository.insertBoard(board);
		insertFile(board);
	}
	
	public void insertFile(Board board) throws Exception{
		
		for(MultipartFile originFile : board.getUploadFileArr()) {
			File remakeFile = remakeFile(originFile,getDestinationLocation());
			saveFileToLocalDisk(originFile, remakeFile);

			saveFileToDatabase(remakeFile.getName(),board.getBno());
		}
	}
	
	public void saveFileToDatabase(String filename, int bno) throws Exception{
		String path = getDestinationLocation() + "/" + filename;
		Map<String,Object> imageMap = new HashMap<>();
		imageMap.put("file_path", path.substring(path.lastIndexOf("/resources")));
		imageMap.put("bno",bno);
		boardRepository.insertImage(imageMap);
	}
	
	private String getDestinationLocation() {
        return "C:/javaide/spring-tool-suite-4-4.3.1.RELEASE-e4.12.0-win32.win32.x86_64/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/inbyul/resources/images/postimages";
	}
	
	  
	@Transactional
	public List<Board> getBoardList(int uno,Criteria criteria) throws Exception{
		
		List<Board> boardList = boardRepository.getBoardList(uno,criteria);
		TimeAgo timeAgo = new TimeAgo();
		for(Board board : boardList) {
			String timestring = timeAgo.timeString(board.getB_reg_dt());
			board.setTimestring(timestring);
		}
		return boardList;
	}
	
	@Transactional
	public void deleteBoard(int bno) throws Exception{
		List<FileVO> rmvFileList = boardRepository.getFilePaths(bno);
		for(FileVO rmvFile : rmvFileList ) {
			removeFileToLocalDisk(rmvFile.getFile_path(),getDestinationLocation());
		}
		boardRepository.deleteBoard(bno);
	}

	public Board getBoard(int bno, int uno) throws Exception{
		Board board = boardRepository.getBoard(bno,uno);
		return board;
	}
	
	@Transactional
	public void updateBoard(Board board) throws Exception {
		boardRepository.updateBoard(board);
		updateFile(board);
	}
	
	public void updateFile(Board board) throws Exception{
		
		if(board.getUploadFileArr()!=null) {
			insertFile(board);
		}
		
		if(board.getRmvFileArr()!=null) {
			boardRepository.deleteImages(board.getRmvFileArr());
			for(FileVO rmvFile : board.getRmvFileArr() ) {
				removeFileToLocalDisk(rmvFile.getFile_path(),getDestinationLocation());
			}
		}
	}
	
	public List<Map<String,Object>> getPersonalBoard(String uid, Criteria criteria) throws Exception{
	   return boardRepository.getPersonalBoard(uid, criteria);	
	}

	
}
