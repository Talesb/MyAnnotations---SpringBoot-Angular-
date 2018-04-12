package com.tales.myannotations.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tales.myannotations.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

}
