package com.momolearn.dto;

import com.momolearn.entity.Likes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LikesDTO {
	
	private int likeNo;
	
	private int boardcomNo;
	
	private String membersMemId;
	
	public LikesDTO(Likes likes) {
		
		this.likeNo = likes.getLikeNo();
		this.boardcomNo = likes.getBoard().getComNo();
		this.membersMemId = likes.getMembers().getMemId();
		
	}
	
}
