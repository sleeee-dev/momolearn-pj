package com.momolearn.dto;

import java.time.LocalDateTime;

import com.momolearn.entity.Board;
import com.momolearn.entity.Comment;
import com.momolearn.entity.Members;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CommentSaveDTO {
	
	private int cmtNo;
	
	private Board board;
	
	private Members members;
	
	private String cmtContent;
	
	private LocalDateTime cmtRegdate;
	
	public Comment toEntity() {
		
		Comment comment = Comment.builder()
				.cmtNo(cmtNo)
				.board(board)
				.members(members)
				.cmtContent(cmtContent)
				.cmtRegdate(cmtRegdate)
				.build();
		
		return comment;
		
	}
	
}
