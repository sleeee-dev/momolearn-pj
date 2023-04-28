package com.momolearn.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.momolearn.exception.MessageException;
import com.momolearn.exception.NotExistException;
import com.momolearn.repository.ApplyTeacherRepository;
import com.momolearn.repository.MembersRepository;
import com.momolearn.dto.ApplyTeacherDTO;
import com.momolearn.entity.ApplyTeacher;
import com.momolearn.entity.Members;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplyTeacherService {

	private final ApplyTeacherRepository applyTeacherRepository;
	
	private final MembersRepository membersRepository;
	
	private final ModelMapper mapper = new ModelMapper();

	public List<ApplyTeacherDTO> getApplyList() {

		List<ApplyTeacher> applylists = applyTeacherRepository.findAll();

		return Arrays.asList(mapper.map(applylists, ApplyTeacherDTO[].class));

	}
	
	public ApplyTeacherDTO write(ApplyTeacherDTO apply) throws MessageException{
		
		ApplyTeacher applyTeachers = apply.toEntity(apply);
		
		try {
			
			ApplyTeacher applyTeacher = applyTeacherRepository.save(applyTeachers);
			
			return mapper.map(applyTeacher, ApplyTeacherDTO.class);
			
		} catch (Exception e) {
			
			throw new MessageException("신청서 등록에 실패했습니다.");
			
		}
		
	}	
	
	public ApplyTeacherDTO read(String membersMemId) throws NotExistException {

		Optional<ApplyTeacher> applyteacher = applyTeacherRepository.findByMembersMemId(membersMemId);
		ApplyTeacher applyTeacher = applyteacher
				.orElseThrow(() -> new NotExistException("신청서가 존재하지 않습니다."));

		return mapper.map(applyTeacher, ApplyTeacherDTO.class);
	}
	
	@Transactional
	public void update(int applyNo, ApplyTeacherDTO applyDTO) throws NotExistException {
		
		ApplyTeacher applyTeacher = applyTeacherRepository.findById(applyNo)
				.orElseThrow(() -> new NotExistException("신청서가 존재하지 않습니다."));
		applyTeacher.setApplyForm(applyDTO.getPhoneNum(), applyDTO.getHopeField(), applyDTO.getPfLink(), applyDTO.getIntro());
	}

	@Transactional
	public void delete(int applyNo) throws NotExistException {
		
		ApplyTeacher applyTeacher = applyTeacherRepository.findById(applyNo)
				.orElseThrow(()->new NotExistException("신청서가 존재하지 않습니다."));
		applyTeacherRepository.delete(applyTeacher);
	}
	
	@Transactional
	public void approve(int applyNo) throws NotExistException {
		
		ApplyTeacher applyTeacher = applyTeacherRepository.findById(applyNo)
				.orElseThrow(()->new NotExistException("신청서가 존재하지 않습니다."));
		applyTeacher.setApprove("true");
	}

	public ApplyTeacherDTO getOneApplyTeacher(int applyNo) throws NotExistException {
		
		System.out.println("service.getOneApplyTeacher() : 신청 번호로 1명 조회" );
		ApplyTeacher applyteacher = applyTeacherRepository.findById(applyNo)
				.orElseThrow(() -> new NotExistException("현재 등록된 신청서가 없습니다."));
		
		return mapper.map(applyteacher, ApplyTeacherDTO.class);
	}

	public ApplyTeacherDTO getOneTeacher(String id) throws NotExistException {
		
		ApplyTeacher applyTeacher = applyTeacherRepository.findByMembersMemIdAndApprove(id)
				.orElseThrow(() -> new NotExistException("현재 강사로 등록되어 있지 않습니다."));
		
		return mapper.map(applyTeacher, ApplyTeacherDTO.class);
	}
	
	public ApplyTeacher getOneApply(String id) throws NotExistException {
		
		Members member = membersRepository.findById(id)
				.orElseThrow(() -> new NotExistException("해당 회원을 찾을 수 없습니다."));
		return member.getApplyTeacher();
	}

}
