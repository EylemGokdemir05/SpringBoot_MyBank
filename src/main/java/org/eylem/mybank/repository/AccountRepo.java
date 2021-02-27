package org.eylem.mybank.repository;

import org.eylem.mybank.entity.Customer;
import org.eylem.mybank.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepo extends CrudRepository<Account, UUID> {
    Account deleteAccountById(UUID id);
    Optional<Account> findById(UUID id);
    Optional<Account> findByCustomer(Customer customer);
    Optional<Account> getByIban(String iban);
}
