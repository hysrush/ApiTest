package com.jaemin.web.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jaemin.common.Search;
import com.jaemin.web.board.dao.BoardDAO;
import com.jaemin.web.board.model.BoardVO;
import com.jaemin.web.board.model.ReplyVO;

@Service
public class BoardServiceImpl implements BoardService{



	@Inject
	private BoardDAO boardDAO;
	
	//추가
	@Override
	public void insertBoard(BoardVO boardVO) throws Exception {
		boardDAO.insertBoard(boardVO);
	}
	/*
	 * //조회 public List<BoardVO> getBoardList() throws Exception {
	 * 
	 * return boardDAO.getBoardList();
	 * 
	 * }
	 */
	//조회
	@Override
	public List<BoardVO> getBoardList(Search search) throws Exception {
		
		return boardDAO.getBoardList(search);
	}

	
	//상세조회
	@Transactional
	@Override
	public BoardVO getBoardContent(int bid) throws Exception{
		BoardVO boardVO = new BoardVO();
		boardDAO.updateViewCnt(bid);
		boardVO = boardDAO.getBoardContent(bid);

		/*
		 * try { boardVO.setBid(bid);
		 * boardVO.setCate_cd("1111111111111111111111111111111111111");
		 * boardDAO.updateBoard(boardVO); } catch (RuntimeException e) { throw new
		 * NotFoundException(); }
		 */
		return boardVO;

	}

	//수정
	@Override
	public void updateBoard(BoardVO boardVO) throws Exception {
	boardDAO.updateBoard(boardVO);
	}
	
	//삭제
	@Override
	public void deleteBoard(int bid) throws Exception {

		 boardDAO.deleteBoard(bid);

	}

	 //총 게시글 개수 확인
		@Override
		public int getBoardListCnt(Search search) throws Exception {
			return boardDAO.getBoardListCnt(search);
		}

		
		// 댓글 리스트

		@Override
		public List<ReplyVO> getReplyList(int bid) throws Exception {
			return boardDAO.getReplyList(bid);
		}
		@Override
		public int saveReply(ReplyVO replyVO) throws Exception {
			return boardDAO.saveReply(replyVO);
		}
		@Override
		public int updateReply(ReplyVO replyVO) throws Exception {
			return boardDAO.updateReply(replyVO);
		}
		@Override
		public int deleteReply(int rid) throws Exception {
			return boardDAO.deleteReply(rid);
		}

		

}

