package org.eylem.mybank.repository;

import org.eylem.mybank.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepo extends CrudRepository<Customer, UUID> {
    Page<Customer> findAll(Pageable pageable);
    Optional<Customer> findById(UUID id);
}
