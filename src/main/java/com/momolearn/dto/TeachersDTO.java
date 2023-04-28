package com.momolearn.dto;

import com.momolearn.entity.ApplyTeacher;
import com.momolearn.entity.Members;
import com.momolearn.entity.Teachers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeachersDTO  {
	
	private int teacherNo;

	private String memId;
	
	private String phoneNum;
	
	private String hope;
	
	private String intro;
	
	private String pfLink;
	
	private int applyNo;

	public static Teachers toEntity(ApplyTeacherDTO applyDTO) {
		
		return Teachers.builder()
				.members(new Members(applyDTO.getMembersMemId()))
				.phoneNum(applyDTO.getPhoneNum())
				.pfLink(applyDTO.getPfLink())
				.hope(applyDTO.getHopeField())
				.intro(applyDTO.getIntro())
				.applyTeacher(new ApplyTeacher(applyDTO.getApplyNo()))
				.build();
		
	}
	
}