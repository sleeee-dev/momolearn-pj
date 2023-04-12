package com.momolearn.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.momolearn.exception.NotExistException;
import com.momolearn.model.BoardRepository;
import com.momolearn.model.LikesRepository;
import com.momolearn.model.MembersRepository;
import com.momolearn.model.dto.LikesDTO;
import com.momolearn.model.entity.Board;
import com.momolearn.model.entity.Likes;
import com.momolearn.model.entity.Members;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LikesService {
	
	private final LikesRepository likesRepository;
	
	private final MembersRepository membersRepository;
	
	private final BoardRepository boardRepository;

	public int likePost(String memId, int comNo) throws NotExistException {

		Members member = membersRepository.findById(memId).orElseThrow(()->new NotExistException("다시 로그인 해 주세요."));
		Board board = boardRepository.findById(comNo).orElseThrow(()->new NotExistException("해당 게시글이 존재하지 않습니다."));
		Likes like = likesRepository.save(new Likes(board, member));
		
		return like.getLikeNo();
	}

	public void cancelLike(String memId, int comNo) throws NotExistException{

		Members member = membersRepository.findById(memId).orElseThrow(()->new NotExistException("다시 로그인 해 주세요."));
		Board board = boardRepository.findById(comNo).orElseThrow(()->new NotExistException("해당 게시글이 존재하지 않습니다."));
		Likes like = likesRepository.findByMembersAndBoard(member, board).get();
		likesRepository.delete(like);
	}
	

	public long countLike(int comNo) throws NotExistException {

		long likesCount = likesRepository.countByComNo(comNo);
		
		return likesCount;
	}

	public List<LikesDTO> getLikesList(int comNo) throws NotExistException {

		Board board = boardRepository.findById(comNo).orElseThrow(()->new NotExistException("해당 게시글이 존재하지 않습니다."));
		List<Likes> entityList = likesRepository.findAllByBoard(board);
		List<LikesDTO> likesList = entityList.stream().map(LikesDTO::new).collect(Collectors.toList());
		
		return likesList;
	}
	
}
