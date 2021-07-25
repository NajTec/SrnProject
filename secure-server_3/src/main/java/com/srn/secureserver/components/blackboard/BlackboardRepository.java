package com.srn.secureserver.components.blackboard;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackboardRepository extends JpaRepository<BlackboardERM, Long>
{
    void deleteByOwnerHashMail(final String hashMail);   
    Optional<BlackboardERM> findByOwnerHashMail(final String hashMail);
    List<Optional<BlackboardERM>> findAllByOwnerHashMail(final String hashMail);
    void deleteAllByOwnerHashMail(String ownerMail);
    Optional<BlackboardERM> findByEnquirerHashMail(String enquirerHashMail);
}
