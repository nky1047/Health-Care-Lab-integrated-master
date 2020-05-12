package org.com.dao;

import org.com.model.User1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User1Repository extends JpaRepository<User1, Integer>{
	
}
