package org.lotto.domain.numberannouncer;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

class InMemoryResultRepositoryTestImpl implements ResultRepository {

    private final Map<String, Result> results = new ConcurrentHashMap<>();
        @Override
    public <S extends Result> S save(final S entity) {
        results.put(entity.ticketId(), entity);
        return entity;
    }

    @Override
    public Optional<Result> findByTicketId(final String ticketId) {
        return Optional.ofNullable(results.get(ticketId));
    }

    @Override
    public boolean existsByTicketId(final String ticketId) {
        return results.containsKey(ticketId);
    }

    @Override
    public Optional<Result> deleteByTicketId(final String ticketId) {
        return Optional.ofNullable(results.remove(ticketId));
    }

    @Override
    public List<Result> findByDrawDateBefore(LocalDateTime date) {
        return results.values().stream().filter(result -> result.drawDate().isBefore(date.minusMonths(1))).toList();

    }


    @Override
    public <S extends Result> List<S> saveAll(final Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Result> findById(final String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(final String s) {
        return false;
    }

    @Override
    public List<Result> findAll() {
        return results.values().stream().toList();
    }

    @Override
    public List<Result> findAllById(final Iterable<String> strings) {
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
    public void delete(final Result entity) {

    }

    @Override
    public void deleteAllById(final Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(final Iterable<? extends Result> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Result> S insert(final S entity) {
        return null;
    }

    @Override
    public <S extends Result> List<S> insert(final Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends Result> Optional<S> findOne(final Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Result> List<S> findAll(final Example<S> example) {
        return null;
    }

    @Override
    public <S extends Result> List<S> findAll(final Example<S> example, final Sort sort) {
        return null;
    }

    @Override
    public <S extends Result> Page<S> findAll(final Example<S> example, final Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Result> long count(final Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Result> boolean exists(final Example<S> example) {
        return false;
    }

    @Override
    public <S extends Result, R> R findBy(final Example<S> example, final Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<Result> findAll(final Sort sort) {
        return null;
    }

    @Override
    public Page<Result> findAll(final Pageable pageable) {
        return null;
    }
}
