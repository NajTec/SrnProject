package com.srn.secureserver.components.password;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRepository extends JpaRepository<PasswordERM, Long>
{
    Optional<PasswordERM> findByHashMail(String hashmail);
    boolean existsByHashMail(String email);
	void deleteByHashMail(String hashMail);
}
