package com.srn.secureserver.components.signature;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignatureRepository extends JpaRepository<SignatureERM, Long>
{
    boolean existsByHashMail(String email);
    void deleteByHashMail(String email);
    Optional<SignatureERM> findByHashMail(String hashMail);  
}
