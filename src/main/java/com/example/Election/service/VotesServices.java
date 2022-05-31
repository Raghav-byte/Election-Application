package com.example.Election.service;

import com.example.Election.Repository.CandidateRepository;
import com.example.Election.Repository.PartyRepository;
import com.example.Election.Repository.UserRepository;
import com.example.Election.Repository.VotesRepository;
import com.example.Election.models.Candidate;
import com.example.Election.models.Party;
import com.example.Election.models.User;
import com.example.Election.models.Votes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class VotesServices {

    @Autowired
    private VotesRepository votesRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private UserRepository userRepository;

    public Votes addVoteForCandidate(Votes votes) {
        AtomicReference<Candidate> candidateAtomicReference = new AtomicReference<>();
        AtomicReference<Party> partyAtomicReference = new AtomicReference<>();
        Optional<User> user = userRepository.findById(votes.getUserId());
        user.ifPresentOrElse(u->{
            //IF USER ALREADY VOTED
            Optional<Votes> temp = votesRepository.findByUserId(votes.getUserId());
            temp.ifPresent(v->{
                try {
                    throw new Exception("User Already Voted, Can't Vote Twice");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            //ADDS VOTES TO CANDIDATE
            Optional<Candidate> candidate = candidateRepository.findById(votes.getCandidateId());
            candidate.ifPresent(c->{
                //ADDS VOTES TO PARTY

                Optional<Party> party = partyRepository.findById(c.getPartyId());
                party.ifPresent(p->{
                    p.setPartyVotes(p.getPartyVotes() + 1);
                    partyAtomicReference.set(partyRepository.save(p));
                });

                c.setCandidateVotes(c.getCandidateVotes() + 1);
                candidateAtomicReference.set(candidateRepository.save(c));
            });

            //set name of user
            votes.setUserName(u.getUserName());
            votesRepository.save(votes);
        },()->{
            try {
                throw new Exception("User Not Found To Vote!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return votesRepository.findById(votes.getVoterId()).get();
    }

    //FIND WINNER
    public Optional<Party> findWinner() {
        Party party = partyRepository.findAll().stream().sorted(Comparator.comparingInt(Party::getPartyVotes).reversed()).collect(Collectors.toList()).get(0);
        Candidate candidate = candidateRepository.finAllByPartyId(party.getPartyId()).stream().sorted(Comparator.comparingInt(Candidate::getCandidateVotes).reversed()).collect(Collectors.toList()).get(0);

        AtomicReference<Candidate> candidateAtomicReference = new AtomicReference<>();
        if (candidate!=null) {
            candidate.setCandidateStatus(Candidate.Candidate_Status.WON);
            candidateAtomicReference.set(candidateRepository.save(candidate));
        }
        //making others candidate status as CANDIDATE
        candidateRepository.findAll().stream().filter(candidate1 -> !candidate1.getCandidateId().equals(candidate.getCandidateId())).forEach(candidate1 ->{
            AtomicReference<Candidate> candidateAtomicReference1 = new AtomicReference<>();
            candidate1.setCandidateStatus(Candidate.Candidate_Status.CANDIDATE);
            candidateAtomicReference1.set(candidateRepository.save(candidate1));
        });

        AtomicReference<Party> partyAtomicReference = new AtomicReference<>();
        if (candidate!=null){
            if (party!=null){
                party.setPartyLeaderId(candidate.getCandidateId());
                party.setPartyLeaderName(candidate.getCandidateName());
                partyAtomicReference.set(partyRepository.save(party));
            }
        }
        return partyRepository.findById(party.getPartyId());
    }


//    public Party declareWinner() {
//        AtomicReference<Candidate> candidateAtomicReference = new AtomicReference<>();
//        candidateRepository.findAll().forEach(candidate -> {
//            if (candidate.getCandidateVotes() == Candidate.qualifyingCount) {
//
//            } else if (candidate.getCandidateVotes() > Candidate.qualifyingCount) {
//
//            }else {
//
//            }
//        });
//    }
}


