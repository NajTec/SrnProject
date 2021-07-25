package com.srn.secureserver.servercontroller.token;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationTokenERM, Long>
{
    Optional<ConfirmationTokenERM> findByToken(final String token);

    @Transactional
    @Modifying
    @Query(value = "UPDATE ConfirmationTokenERM c SET c.confirmedAt = ?2 WHERE c.token = ?1", nativeQuery = true)
    int updateConfirmedAt(String token, LocalDateTime confirmedAt);
}
