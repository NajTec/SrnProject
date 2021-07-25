package com.srn.secureserver.components.customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerERM, Long>
{
    //@Query(value = "SELECT c FROM CustomerModel c WHERE c.email = ?1", nativeQuery = true)
    Optional<CustomerERM> findCustomerByEmail(final String email);
    boolean existsByEmail(final String email);
    boolean existsByHashMail(final String customerHash);
    void deleteByHashMail(final String customerHash);
    Optional<CustomerERM> findByEmail(final String email);
    Optional<CustomerERM> findByHashMail(final String hashMail);
}
