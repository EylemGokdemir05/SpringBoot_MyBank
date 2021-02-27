package org.eylem.mybank.repository;

import org.eylem.mybank.entity.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface CardRepo extends CrudRepository<Card, UUID> {
    Optional<Card> findById(UUID id);
    Optional<Card> findByDebtOrderById(UUID id);
}
