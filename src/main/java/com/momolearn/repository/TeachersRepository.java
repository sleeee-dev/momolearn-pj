package com.momolearn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.momolearn.entity.Teachers;

@Repository
public interface TeachersRepository extends JpaRepository<Teachers, Integer>{

	@Query("select t from Teachers t join fetch t.applyTeacher at join fetch at.members m where m.memId = :id and at.approve = 'true'")
	Optional<Teachers> findByMemIdAndApprove(@Param("id") String id);
	
	List<Teachers> findAll();
	
	Optional<Teachers> findByApplyTeacherMembersMemId(String memId);

	Optional<Teachers> findByApplyTeacherApplyNo(int applyNo);

}
