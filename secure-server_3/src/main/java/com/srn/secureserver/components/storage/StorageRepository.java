package com.srn.secureserver.components.storage;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends JpaRepository<StorageERM, Long>
{
    Optional<StorageERM> findStorageByHashMail(final String email); 
    void deleteByHashMail(String hashMail);
    boolean existsByHashMail(String email);
    Optional<StorageERM> findByHashMail(String hashMail);    
}
