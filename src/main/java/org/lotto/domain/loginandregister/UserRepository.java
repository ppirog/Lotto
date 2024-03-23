package org.lotto.domain.loginandregister;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
interface UserRepository extends MongoRepository<User, String> {

        boolean existsByUsername(String username);
        Optional<User> findByUsername(String username);
}
