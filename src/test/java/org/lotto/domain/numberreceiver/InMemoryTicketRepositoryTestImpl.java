package org.lotto.domain.numberreceiver;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

class InMemoryTicketRepositoryTestImpl implements TicketRepository {

    private final Map<String, Ticket> inMemoryDatabase = new ConcurrentHashMap<>();

    @Override
    public Ticket save(final Ticket ticket) {
        inMemoryDatabase.put(ticket.id(), ticket);
        return ticket;
    }

    @Override
    public List<Ticket> findAll(final LocalDateTime date) {
        return inMemoryDatabase
                .values()
                .stream()
                .filter(ticket -> ticket.date().toLocalDate().equals(date.toLocalDate()))
                .toList();
    }

    @Override
    public Optional<Ticket> findById(final String ticketId) {
        return Optional.ofNullable(inMemoryDatabase.get(ticketId));
    }

    @Override
    public boolean existsById(final String s) {
        return false;
    }

    @Override
    public <S extends Ticket> S insert(final S entity) {
        return null;
    }

    @Override
    public <S extends Ticket> List<S> insert(final Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends Ticket> Optional<S> findOne(final Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Ticket> List<S> findAll(final Example<S> example) {
        return null;
    }

    @Override
    public <S extends Ticket> List<S> findAll(final Example<S> example, final Sort sort) {
        return null;
    }

    @Override
    public <S extends Ticket> Page<S> findAll(final Example<S> example, final Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Ticket> long count(final Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Ticket> boolean exists(final Example<S> example) {
        return false;
    }

    @Override
    public <S extends Ticket, R> R findBy(final Example<S> example, final Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Ticket> List<S> saveAll(final Iterable<S> entities) {
        return null;
    }

    @Override
    public List<Ticket> findAll() {
        return null;
    }

    @Override
    public List<Ticket> findAllById(final Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(final String s) {

    }

    @Override
    public void delete(final Ticket entity) {

    }

    @Override
    public void deleteAllById(final Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(final Iterable<? extends Ticket> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Ticket> findAll(final Sort sort) {
        return null;
    }

    @Override
    public Page<Ticket> findAll(final Pageable pageable) {
        return null;
    }
}
