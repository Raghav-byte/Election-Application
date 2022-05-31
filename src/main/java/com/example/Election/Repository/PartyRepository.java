package com.example.Election.Repository;

import com.example.Election.models.Party;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import javax.servlet.http.Part;
import java.util.List;
import java.util.UUID;

@Repository

public interface PartyRepository extends MongoRepository<Party, UUID> {

   @Query("{'partyName':'?0'}")
    List<Party> searchParty(String name);
}
