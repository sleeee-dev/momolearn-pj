package com.momolearn.dto;

import com.momolearn.entity.ApplyTeacher;
import com.momolearn.entity.Members;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplyListDTO {

    private int applyNo;
    
    private Members member;
    
    private LocalDateTime applyRege;
    
    private String approve;

    public ApplyListDTO(ApplyTeacher applyTeacher) {

        this.applyNo = applyTeacher.getApplyNo();
        this.member = applyTeacher.getMembers();
        this.applyRege = applyTeacher.getApplyRege();
        this.approve = applyTeacher.getApprove();

    }
}