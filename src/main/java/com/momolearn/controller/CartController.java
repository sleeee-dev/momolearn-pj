package com.momolearn.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.momolearn.exception.MessageException;
import com.momolearn.exception.NotExistException;
import com.momolearn.dto.CartDTO;
import com.momolearn.dto.MembersDTO;
import com.momolearn.dto.PaymentRequestDTO;
import com.momolearn.service.CartService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("cart")
@SessionAttributes({"members"})
@RequiredArgsConstructor
public class CartController {
	
	private final CartService cartService;
	
	@GetMapping(value = "/check-cart/{lecId}", produces = "application/json;charset=UTF-8")
	public String checkCart(Model model, @PathVariable("lecId") int lecId, @ModelAttribute("members") MembersDTO member) {
		log.info(member.getMemId() + "회원이 수강바구니에 " + lecId + "번 강의를 담았는지 검증");
		
		boolean cart = cartService.checkCart(lecId, member.getMemId());
		
		if(cart == true) {
			
			model.addAttribute("msg", "이미 수강바구니에 담겨있습니다. 수강바구니 페이지로 이동하시겠습니까?");
			
			return "cart/success";
		}
		
		return "redirect:/cart/add-cart/" + lecId;
	}
	
	
	@GetMapping(value = "/add-cart/{lecId}", produces = "application/json;charset=UTF-8")
	public String addCart(Model model, @PathVariable("lecId") int lecId, @ModelAttribute("members") MembersDTO member) throws NotExistException {
		log.info(member.getMemId() + "회원이 수강바구니에 " + lecId + "번 강의를 추가");
		
		CartDTO cart = cartService.addCart(lecId, member.getMemId());
		
		if(cart == null) {
			
			model.addAttribute("errMsg", "내부적인 오류로 수강바구니에 담지 못했습니다. 다시 시도해주십시오.");
			
			return "error";
			
		}
		
		model.addAttribute("msg", "성공적으로 수강바구니에 담았습니다. 수강바구니로 이동하시겠습니까?");
		
		return "cart/success";
		
	}
	
	@ApiOperation(value = "수강바구니 담기", notes = "해당 강의 수강바구니에 담기")
	@GetMapping(value = "/get-cart", produces = "application/json;charset=UTF-8")
	public String getCart(Model model, @ModelAttribute("members") MembersDTO member) throws NotExistException {
		log.info(member.getMemId() + "회원의 수강바구니로 이동");
		
		List<CartDTO> cart = cartService.getCart(member.getMemId());
		
		model.addAttribute("cart", cart);
		
		return "cart/cart-view";
	}
	
	@ApiOperation(value = "결제 성공", notes = "결제 후 수강바구니 삭제 및 MyLectures 추가")
	@PostMapping(value = "/success", produces = "application/json;charset=UTF-8")
	public void movePaymentForm(@RequestBody PaymentRequestDTO request) throws NotExistException {
		log.info("결제 성공시 나오는 로직" + request.getCheckedTitles().get(0));
		
		cartService.getMyLectures(request);
		
	}
	
	@ApiOperation(value = "장바구니 삭제", notes = "장바구니에서 체크한 강의 삭제 메소드")
	@DeleteMapping(value = "/delete", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> deleteCart(@RequestBody PaymentRequestDTO request) throws NotExistException {
	    log.info("장바구니 삭제 메소드" + request.getCheckedTitles().get(0));
	    
	    cartService.deleteCart(request);
	    
	    return ResponseEntity.ok().build();
	}
	
	@ExceptionHandler(value = NotExistException.class)
	public String notExistException(NotExistException ne, Model model) {
		
		ne.printStackTrace();
		model.addAttribute("errorMsg", ne.getMessage());
		
		return "error";
		
	}
	
	@ExceptionHandler(value = MessageException.class)
	public String messageExceptio(MessageException ne, Model model) {
		
		ne.printStackTrace();
		model.addAttribute("errorMsg", ne.getMessage());
		
		return "error";
		
	}
	
	@ExceptionHandler(HttpSessionRequiredException.class)
    public String handleSessionRequiredException(HttpSessionRequiredException e, Model model) {
		
		e.printStackTrace();
		model.addAttribute("errorMsg", "로그인 후 이용해주시기 바랍니다.");
		
        return "cart/error";
    }

}
