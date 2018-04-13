package com.tales.myannotations.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tales.myannotations.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

@Modifying
@Transactional
@Query("update User u SET u.name =:name where u.id =:id")
public void updatename(@Param("id") Integer id,@Param("name") String name);

@Modifying
@Transactional
@Query("update User u SET u.cpf =:cpf where u.id =:id")
public void updatecpf(@Param("id") Integer id,@Param("cpf") String cpf);

@Modifying
@Transactional
@Query("update User u SET u.email =:email where u.id =:id")
public void updateemail(@Param("id") Integer id,@Param("email") String email);

@Modifying
@Transactional
@Query("update User u SET u.password =:password where u.id =:id")
public void updatepassword(@Param("id") Integer id,@Param("password") String password);

 }
