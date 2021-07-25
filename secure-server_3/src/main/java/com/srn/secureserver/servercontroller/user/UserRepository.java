package com.srn.secureserver.servercontroller.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface UserRepository extends JpaRepository<UserERM, Long>
{
    Optional<UserERM> findByEmail(final String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE UserERM u SET u.enabled = TRUE WHERE u.email = ?1", nativeQuery = true)
    int enableUserERM(final String email);
}
