package com.young.inbyul.socket.handler;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.young.inbyul.notice.model.NoticeVO;
import com.young.inbyul.notice.repository.NoticeRepository;

@Component
public class EchoHandler  extends TextWebSocketHandler{
	
	@Autowired
	NoticeRepository noticeRepository;
	
	Map<String,WebSocketSession> Sessions = new HashMap<>();
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception{
		System.out.println("afterConnectionEstablished:" + session);
		//String senderId = getId(session);
		Sessions.put(getId(session), session);
	}
	
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("handleTextMessage:" + session + " : " + message);
		String senderId = getId(session);
		//protocol: cmd,sender,recipient,bno  (ex: reply,user2,user1,234)
		String msg = message.getPayload();
		if(msg!=null) {
			NoticeVO noticeVO = setNoticeVO(msg);
			noticeRepository.insertNotice(noticeVO);
			int cnt = noticeRepository.getUnreadCnt(noticeVO.getRecipient());
			WebSocketSession recipientSession = Sessions.get(noticeVO.getRecipient()); 
			if(recipientSession != null) {
				TextMessage tmpMsg = new TextMessage(msg);
				recipientSession.sendMessage(tmpMsg);
			}
		}
	}
	
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("afterConnectionClosed:" + session + ":" + status);
		Sessions.remove(getId(session));
	}
	
	private String getId(WebSocketSession session) {
		return session.getPrincipal().getName(); 
	}
	
	private NoticeVO setNoticeVO(String msg) throws Exception{
		NoticeVO noticeVO = new NoticeVO();
		JSONParser jsonParse = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParse.parse(msg);
		String cmd = jsonObj.get("cmd").toString();
		String sender = jsonObj.get("sender").toString();
		String recipient = jsonObj.get("recipient").toString();
		int target = Integer.parseInt(jsonObj.get("target").toString()); // bno

		noticeVO.setCmd(cmd);
		noticeVO.setRecipient(recipient);
		noticeVO.setSender(sender);
		noticeVO.setTarget(target);
		
		return noticeVO;
	}
	
//	private String makeNewMsg(NoticeVO noticeVO, int cnt) throws Exception{
//		String newMsg = "";
//		newMsg+="{\"notice\":{\"cmd\":\""+ noticeVO.getCmd() +"\",\"sender\":\""+ noticeVO.getSender() +"\", \"recipient\":\"" + noticeVO.getRecipient()+"\",\"target\":\""+ noticeVO.getTarget() +"\"},";
//		newMsg+="\"cnt\":\""+cnt +"\"}";
//		return newMsg;
//	}
	
	
}
	