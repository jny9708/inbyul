package com.young.inbyul;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.young.inbyul.board.service.BoardService;
import com.young.inbyul.config.DatabaseConfig;
import com.young.inbyul.config.RootConfig;
import com.young.inbyul.config.WebConfig;
import com.young.inbyul.config.WebSecurityConfig;
import com.young.inbyul.util.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {RootConfig.class,WebConfig.class,DatabaseConfig.class,WebSecurityConfig.class})
@WebAppConfiguration
public class FileTest {
	
	@Autowired
	BoardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(FileTest.class);
	
	//@Test
	public void test() throws Exception{
		String path = "C:\\javaide\\spring-tool-suite-4-4.3.1.RELEASE-e4.12.0-win32.win32.x86_64\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\inbyul\\resources\\images\\postimages";
		
		
		logger.info(path.substring(path.lastIndexOf("\\resources")));
	
	}
	
	@Test
	public void test6() throws Exception{
		Criteria criteria = new Criteria();
		List<Map<String,Object>> list = boardService.getPersonalBoard("skdud5606", criteria);
		for(Map<String,Object> map : list) {
			logger.info(map.get("bno")+"");
			logger.info(map.get("file_path")+"");
		}
		
	}
	
//	@Test
//	public void test() throws Exception{
////		BoardService boardService = new BoardService();
//		String path = "C:\\javaide\\spring-tool-suite-4-4.3.1.RELEASE-e4.12.0-win32.win32.x86_64\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\inbyul\\resources\\images\\postimages";
//		Board board = new Board();
//		FileVO filevo = new FileVO();
//		board.setFilevo(filevo);
//		List<String> list = new ArrayList<>();
//		list.add("alksdjlakjsd/asdasda/asd123.jpg");
//		list.add("alksdjlakjsd/asdasda/asdasd123123.png");
//		board.getFilevo().setRmvFileArr(list);
//		//boardService.removeFile(board, path);
//		boardService.updateBoard(board, path);
////		logger.info(boardService.removeFile(board, path)+"...");
//
//		
//	}
	
	
	
//	@Transactional
//	public void insertBoard(Board board,String path) throws Exception{
//		//Map<String, MultipartFile> fileMap = request.getFileMap();
//		
//		List<MultipartFile> fileList = new ArrayList<>(board.getFilevo().getFiles().values()); // 사용자가 보내온 멀티파일들 받기 
//		List<File> saveFile_list = new ArrayList<>();  //이름바꾸기위해서 새로만든 file 객체들 담음
//		
//		//리스트로 따로 만들어서 다른 함수에서 파일저장을 하는 이유는 모든 db값이 정상적으로 저장되었을때 마지막으로 저장되게끔하고싶었기 때문이다.
//		//db저장 파일저장 따로 하고싶었던 이유는
//		//예를 들어 3개의 파일이 넘어왔을때 2개는 db값과 파일이  정상적으로 저장되고  1개만 db값이 저장되다 오류를 일으켰을때 저장된 파일을 다시 모두 삭제해야하는데
//		//오류를 일으켰을 때 삭제하는것 보다 그냥 디비저장완료 되었을 때 파일저장하는게 더 나을 것 같다 생각했다.
//		
//		List<String> dbpath_list = new ArrayList<>();
//		
//		
//		boardRepository.insertBoard(board);
//		
//		for (MultipartFile multipartFile : fileList) {
//			String dbpath = "/resources/images/postimages/";
//			System.out.println(multipartFile.getOriginalFilename());
//			
//			File saveFile = makeFile(path,multipartFile);
//			
//			saveFile_list.add(saveFile);
//			dbpath += saveFile.getName();
//			dbpath_list.add(dbpath);
//		} 
//		board.getFilevo().setFile_path(dbpath_list);
//		//board.File_path(dbpath_list);
//		boardRepository.insertImages(board);
//		uploadFile(fileList, saveFile_list);
//	}
//	
//	public void uploadFile(List<MultipartFile> fileList,List<File> saveFile_list) throws Exception {
//		for(int i =0; i<fileList.size(); i++) {
//			FileCopyUtils.copy(fileList.get(i).getBytes(), saveFile_list.get(i));
//		}
//		
//	}
//	
//	public File makeFile(String path, MultipartFile multipartFile) throws Exception{
//		UUID uuid = UUID.randomUUID();
//		String originalName = multipartFile.getOriginalFilename();
//		String type = originalName.substring(originalName.lastIndexOf("."));
//		String now = new SimpleDateFormat("yyyyMMddHmsS").format(new Date()); //현재시간
//		String saveName = uuid + "_" + now + type;
//		File saveFile = new File(path, saveName); // 저장할 폴더 이름, 저장할 파일 이름
//		return saveFile;
//	}
//	
	
	
}
