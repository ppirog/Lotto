package org.lotto.domain.numberannouncer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Repository
interface ResultRepository extends MongoRepository<Result, String> {

    Optional<Result> findByTicketId(String ticketId);

    boolean existsByTicketId(String ticketId);

    Optional<Result> deleteByTicketId(String ticketId);

    List<Result> findByDrawDateBefore(LocalDateTime date);

    List<Result> findAll();
}
