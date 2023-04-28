package com.momolearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.momolearn.exception.MessageException;
import com.momolearn.exception.NotExistException;
import com.momolearn.dto.ApplyTeacherDTO;
import com.momolearn.dto.MembersDTO;
import com.momolearn.entity.ApplyTeacher;
import com.momolearn.service.ApplyTeacherService;
import com.momolearn.service.MembersService;
import com.momolearn.service.TeachersService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("applyteacher")
@SessionAttributes({ "members" })
@RequiredArgsConstructor
public class ApplyTeacherController {

	private final ApplyTeacherService applyTeacherService;
	
	private final MembersService membersService;
	
	private final TeachersService teachersService;


	@GetMapping(value = "/myapplylist")
	public String getMyApplyList(Model model, @ModelAttribute("members") MembersDTO members) throws NotExistException {

		ApplyTeacher applyTeacher = applyTeacherService.getOneApply(members.getMemId());

		if (applyTeacher != null) {
			
			ApplyTeacherDTO apply = applyTeacherService.read(members.getMemId());
			model.addAttribute("apply", apply);
			
		}

		return "teachers/at-mylist";
	}

	@GetMapping(value = "/applylist")
	public String getApplyList(Model model, @ModelAttribute("members") MembersDTO members) throws NotExistException {

		if(members.getGrade().equals("admin")) {
			
			model.addAttribute("list", applyTeacherService.getApplyList());
		}
		
		return "teachers/at-list";

	}

	@GetMapping(value = "/writeform")
	public String writeForm(Model model, @ModelAttribute("members") MembersDTO members) throws NotExistException {

		MembersDTO member = membersService.getOneMember(members.getMemId());
		model.addAttribute("member", member);

		return "teachers/at-form";
	}

	@PostMapping(value = "/write")
	public String writeApplyForm(Model model, @ModelAttribute("members") MembersDTO members, ApplyTeacherDTO apply)
			throws MessageException, NotExistException {

		apply.setMembersMemId(members.getMemId());
		ApplyTeacherDTO newApply = applyTeacherService.write(apply);

		model.addAttribute("apply", newApply);

		return "teachers/at-mylist";
	}

	@GetMapping(value = "/read")
	public String read(Model model, @ModelAttribute("members") MembersDTO members) throws NotExistException {

		ApplyTeacherDTO apply = applyTeacherService.read(members.getMemId());

		model.addAttribute("apply", apply);
		model.addAttribute("member", members);

		return "teachers/at-readform";
	}

	@GetMapping(value = "/read/{applyNo}")
	public String read(Model model, @PathVariable int applyNo, @ModelAttribute("members") MembersDTO members)
			throws NotExistException, MessageException {

		if(members.getGrade().equals("admin")) {
			
			ApplyTeacherDTO apply = applyTeacherService.getOneApplyTeacher(applyNo);
			MembersDTO member = membersService.getOneMember(apply.getMembersMemId());
			
			model.addAttribute("apply", apply);
			model.addAttribute("member", member);
		}

		return "teachers/at-readform";
	}

	@GetMapping(value = "/updateform")
	public String updateForm(Model model, @ModelAttribute("members") MembersDTO members) throws NotExistException {

		ApplyTeacherDTO apply = applyTeacherService.read(members.getMemId());

		model.addAttribute("apply", apply);
		model.addAttribute("member", members);

		return "teachers/at-updateform";
	}

	@PostMapping(value = "/update/{applyNo}")
	public String update(@PathVariable int applyNo, Model model, @ModelAttribute("members") MembersDTO members,
			ApplyTeacherDTO apply) throws NotExistException {

		applyTeacherService.update(applyNo, apply);
		model.addAttribute("apply", apply);
		model.addAttribute("member", members);

		return "teachers/at-readform";
	}

	@PostMapping(value = "/delete/{applyNo}")
	public String delete(@PathVariable int applyNo) throws NotExistException {
		
		applyTeacherService.delete(applyNo);
		return "teachers/at-mylist";
	}

	@PostMapping(value = "/approve/{applyNo}")
	public String approve(Model model, @PathVariable int applyNo) throws NotExistException, MessageException {

		applyTeacherService.approve(applyNo);
		ApplyTeacherDTO apply = applyTeacherService.getOneApplyTeacher(applyNo);

		membersService.updateGrade(apply.getMembersMemId());
		MembersDTO member = membersService.getOneMember(apply.getMembersMemId());
		
		teachersService.saveOneTeacher(apply);
		model.addAttribute("apply", apply);
		model.addAttribute("member", member);

		return "teachers/at-readform";
	}
	
	@ExceptionHandler(value = NotExistException.class)
	public String notExistException(NotExistException e, Model model) {
		
		e.printStackTrace();
		model.addAttribute("errorMsg", e.getMessage());
		
		return "error";
	}

}
