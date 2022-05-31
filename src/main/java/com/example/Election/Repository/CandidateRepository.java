package com.example.Election.Repository;

import com.example.Election.models.Candidate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CandidateRepository extends MongoRepository<Candidate, UUID> {

   @Query("{'partyId':?0}")
   List<Candidate> finAllByPartyId(UUID partyId);

   @Query("{ $or : [{'candidateName':{'$regex':'(?i)?0'}}, {'partyName':{'$regex':'(?i)?0'}}]}")
   List<Candidate> searchCandidate(String name);

}
