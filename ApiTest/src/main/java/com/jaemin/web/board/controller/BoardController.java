package com.jaemin.web.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jaemin.common.Search;
import com.jaemin.web.board.model.BoardVO;
import com.jaemin.web.board.model.ReplyVO;
import com.jaemin.web.board.service.BoardService;

@Controller
@RequestMapping(value = "/board")
public class BoardController {


	@Inject
	private BoardService boardService;

	
	/*  따로  com.jaemin.web.error.controller로 빼놨다. 에러에 관련한 컨트롤러
	 * @ExceptionHandler(RuntimeException.class) public String
	 * exceptionHandler(Model model, Exception e){ System.out.println("exception : "
	 * + e.getMessage()); model.addAttribute("exception", e); return
	 * "error/exception"; }
	 */

	//게시글 목록 조회
	@RequestMapping(value = "/getBoardList", method = RequestMethod.GET)
	public String getBoardList(Model model
			, @RequestParam(required = false, defaultValue = "1") int page
			, @RequestParam(required = false, defaultValue = "1") int range
			, @RequestParam(required = false, defaultValue = "title") String searchType
			, @RequestParam(required = false) String keyword
			) throws Exception {
		Search search = new Search();
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		//전체 게시글 수
		int listCnt = boardService.getBoardListCnt(search);
		search.pageInfo(page, range, listCnt);
		model.addAttribute("pagination", search);
		model.addAttribute("boardList", boardService.getBoardList(search));
		return "board/index";
	}


	
	@RequestMapping(value = "/getBoardContent", method = RequestMethod.GET)
	public String getBoardContent(Model model, @RequestParam("bid") int bid) throws Exception {

		model.addAttribute("boardContent", boardService.getBoardContent(bid));
		model.addAttribute("replyVO",new ReplyVO());
		return "board/boardContent";

	}
	@RequestMapping("/boardForm")
	public String boardForm(@ModelAttribute("boardVO") BoardVO vo, Model model) {
		return "board/boardForm";
	}

	
	
	
	@RequestMapping(value = "/saveBoard", method = RequestMethod.POST)
	public String saveBoard(@ModelAttribute("boardVO") BoardVO boardVO
			, @RequestParam("mode") String mode
			, RedirectAttributes rttr) throws Exception {

		if (mode.equals("edit")) {
			boardService.updateBoard(boardVO);
		} else {
			boardService.insertBoard(boardVO);
		}

		return "redirect:/board/getBoardList";
	}


	  @RequestMapping(value = "/editForm", method = RequestMethod.GET)
		public String editForm(@RequestParam("bid") int bid
	, @RequestParam("mode") String mode, Model model) throws Exception {
			model.addAttribute("boardContent", boardService.getBoardContent(bid));
			model.addAttribute("mode", mode);
			model.addAttribute("boardVO", new BoardVO());

			return "board/boardForm";

		}

	  @RequestMapping(value = "/deleteBoard", method = RequestMethod.GET)
		public String deleteBoard(RedirectAttributes rttr, @RequestParam("bid") int bid) throws Exception {

			boardService.deleteBoard(bid);

			return "redirect:/board/getBoardList";
 
		}



}	
