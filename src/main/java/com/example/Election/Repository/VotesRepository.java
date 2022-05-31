package com.example.Election.Repository;

import com.example.Election.models.Votes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VotesRepository extends MongoRepository<Votes, UUID> {

    @Query("{'userId':?0}")
    Optional<Votes> findByUserId(UUID userId);

}
