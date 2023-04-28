package com.momolearn.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.momolearn.entity.Members;

@Repository
public interface MembersRepository extends JpaRepository<Members, String>{
	
	Members findByMemId(String memId);
    
	Members findByMemIdAndPw(String memId, String pw);
    
	Members findByEmail(String email);
    
	Members findByMemIdAndEmail(String memId, String email);

}