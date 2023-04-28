package com.momolearn.dto;

import java.time.LocalDateTime;

import com.momolearn.entity.ApplyTeacher;
import com.momolearn.entity.Members;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApplyTeacherDTO {

    private int applyNo;

//    private  Members member;
    private String membersMemId;
    
    private String phoneNum;
    
    private String hopeField;
    
    private String intro;
    
    private String pfLink;
    
    private LocalDateTime applyRege;
    
    private String approve;

    public ApplyTeacher toEntity(ApplyTeacherDTO applyDTO) {

        return ApplyTeacher.builder()
                .applyNo(applyDTO.getApplyNo())
                .phoneNum(applyDTO.getPhoneNum())
                .pfLink(applyDTO.getPfLink())
                .hopeField(applyDTO.getHopeField())
                .intro(applyDTO.getIntro())
                .approve(applyDTO.getApprove())
                .applyRege(applyDTO.getApplyRege())
                .members(new Members(applyDTO.getMembersMemId()))
                .build();

    }


//    public ApplyTeacherDTO(ApplyTeacher applyTeacher){
//
//        this.applyNo = applyTeacher.getApplyNo();
//        this.member = applyTeacher.getMembers();
//        this.phoneNum = applyTeacher.getPhoneNum();
//        this.hopeField = applyTeacher.getHopeField();
//        this.intro = applyTeacher.getIntro();
//        this.pfLink = applyTeacher.getPfLink();
//        this.applyRege = applyTeacher.getApplyRege();
//        this.approve = applyTeacher.getApprove();
//
//    }




}