package com.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.BoardVO;
import com.board.domain.PageDTO;
import com.board.domain.Paging;
import com.board.service.BoardService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")	// 기본주소 설정	
public class BoardContoller {
	
	@Autowired		// 또는 class에 @AllArgsConstructor 써도됨
	private BoardService boardService;
	
	@GetMapping("/list")
	public void list(Model model, Paging paging) {
		log.info("전체레코드 조회");
		List<BoardVO> list = boardService.getList(paging);
		
		int total = boardService.getTotal(paging);
		
		model.addAttribute("list", list);			// 페이지출력 레코드들
		model.addAttribute("pageInfo", new PageDTO(paging, total));
	}
	
	@GetMapping("/add")
	public void addPage() {
		// 입력 화면으로 전환
	}
	
	@PostMapping("/add")
	public String add(BoardVO board, RedirectAttributes attr) {	// RedirectAttributes attr 리다이랙트에 데이터 저장해서 보냄
		boardService.add(board);		
		attr.addFlashAttribute("result", board.getBno());	// 화면에 출력할 게시물번호만 저장
		return "redirect:/board/list";	// 서비스를 타고 화면 이동
	}
	
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno")Long bno, Model model) {	// 클라이언트가 빈bno를 보낼수도 있으므로 Long으로 하는게 좋음
		model.addAttribute("board", boardService.get(bno));
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes attr) {
		if(boardService.modify(board)) {
			attr.addFlashAttribute("result", "수정 완료");
		} else {
			attr.addFlashAttribute("result", "수정 실패");
		}
		return "redirect:/board/list";
	}
	
	@GetMapping("/remove")
	public String remove(Long bno, RedirectAttributes attr) {
		if(boardService.remove(bno)) {
			attr.addFlashAttribute("result","삭제 성공");
		} else {
			attr.addFlashAttribute("result","삭제 실패");
		}
		
		return "redirect:/board/list";
	}
	
}