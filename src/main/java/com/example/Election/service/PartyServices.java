package com.example.Election.service;

import com.example.Election.Repository.PartyRepository;
import com.example.Election.models.Party;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service

public class PartyServices {

    @Autowired
    private PartyRepository partyRepository;

    //TO CREATE PARTY
    public Party createParty(Party party) {
        return partyRepository.save(party);
    }

    //TO SHOW ALL PARTY
    public List<Party> showAllParty() {
        return partyRepository.findAll();
    }

    //TO SHOW ONE PARTY
    public Optional<Party> showOneParty(UUID partyId) {
        return partyRepository.findById(partyId);
    }

    //TO DELETE PARTY
    public String deleteParty(UUID partyId) {
        partyRepository.deleteById(partyId);
        return "Party Deleted";
    }

    //TO UPDATE PARTY
    public Party updateParty(Party party) {
        AtomicReference<Party> partyAtomicReference = new AtomicReference<>();
        Optional<Party> existingParty = partyRepository.findById(party.getPartyId());
        existingParty.ifPresentOrElse(p->{
            p.setPartyName(party.getPartyName() != null ? party.getPartyName(): p.getPartyName());
            p.setPartyLeaderName(party.getPartyLeaderName() != null ? party.getPartyLeaderName() :p.getPartyLeaderName());
            p.setPartyVotes(party.getPartyVotes() != null ? party.getPartyVotes() : p.getPartyVotes());
            partyAtomicReference.set(partyRepository.save(p));
        },()->{
                    try {
                        throw new Exception("Party not found");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return partyAtomicReference.get();
    }

    //SEARCH BY PARTY NAME
    public List<Party> searchParty(String name) {
        return partyRepository.searchParty(name);}
}
