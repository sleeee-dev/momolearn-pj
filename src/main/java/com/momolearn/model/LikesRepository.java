package com.momolearn.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.Board;
import com.momolearn.model.entity.Likes;
import com.momolearn.model.entity.Members;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Integer>{
	
	Optional<Likes> findByMembersAndBoard(Members member, Board board);
	
	@Query(value="select count(*) from likes l where l.com_no=:com_no", nativeQuery = true)
	Long countByComNo(@Param("com_no") int comNo);

	List<Likes> findAllByBoard(Board board);
	
	@Query(value="select l.com_no from Likes l group by l.com_no order by count(l.com_no) desc limit 5", nativeQuery = true)
	List<Integer> findTop5ByOrderByLikesDesc();

}
