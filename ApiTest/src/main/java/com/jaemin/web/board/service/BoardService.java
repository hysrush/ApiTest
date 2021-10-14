package com.jaemin.web.board.service;

import java.util.List;

import com.jaemin.common.Search;
import com.jaemin.web.board.model.BoardVO;
import com.jaemin.web.board.model.ReplyVO;

public interface BoardService {

	public List<BoardVO> getBoardList(Search search) throws Exception;
	
	
	public BoardVO getBoardContent(int bid) throws Exception;

	public void insertBoard(BoardVO boardVO) throws Exception;


	public void updateBoard(BoardVO boardVO) throws Exception;

	public void deleteBoard(int bid) throws Exception;

	//�� �Խñ� ���� Ȯ��
	public int getBoardListCnt(Search search) throws Exception;
	
	  // ��� ����Ʈ
		public List<ReplyVO> getReplyList(int bid) throws Exception;
		public int saveReply(ReplyVO replyVO) throws Exception;
		public int updateReply(ReplyVO replyVO) throws Exception;
		public int deleteReply(int rid) throws Exception;


}
