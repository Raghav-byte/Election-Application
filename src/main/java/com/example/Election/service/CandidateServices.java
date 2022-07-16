package com.example.Election.service;

import com.example.Election.Repository.CandidateRepository;
import com.example.Election.Repository.PartyRepository;
import com.example.Election.models.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.print.attribute.standard.PageRanges;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CandidateServices {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    //CREATING CANDIDATE
    public Candidate createCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    //SHOWS ALL CANDIDATE
    public Page<Candidate> showAllCandidate(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return candidateRepository.findAll(pageable);
    }

    //SHOWS ONE CANDIDATE
    public Optional<Candidate> showOneCandidate(UUID candidateId) {
        return candidateRepository.findById(candidateId);
    }

    //DELETES CANDIDATE
    public String deleteCandidate(UUID candidateId) {
        candidateRepository.deleteById(candidateId);
        return "Candidate Deleted";
    }

    //UPDATES CANDIDATE
    public Candidate updateCandidate(Candidate candidate) {
        AtomicReference<Candidate> candidateAtomicReference = new AtomicReference<>();
        Optional<Candidate> existingCandidate = candidateRepository.findById(candidate.getCandidateId());
        existingCandidate.ifPresentOrElse(c->{
            c.setCandidateAge(candidate.getCandidateAge() > 0 ? candidate.getCandidateAge() : c.getCandidateAge());
            c.setCandidateNumber(candidate.getCandidateNumber() != null ? candidate.getCandidateNumber() : c.getCandidateNumber());
            c.setCandidateName(candidate.getCandidateName() != null ? candidate.getCandidateName() : c.getCandidateName());
            c.setCandidateGender(candidate.getCandidateGender() != null ? candidate.getCandidateGender() : c.getCandidateGender());
            candidateAtomicReference.set(candidateRepository.save(c));
        },()->{
            try {
                throw new Exception("Candidate not found");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return candidateAtomicReference.get();
    }

    //TO SEARCH CANDIDATE NAME
    public List<Candidate> searchCandidate(String name) {
          return candidateRepository.searchCandidate(name);
//        Query query = new Query();
//        query.addCriteria(Criteria.where("candidateName").regex(name));
//        return mongoTemplate.find(query,Candidate.class);
    }

    //TO FILTER CANDIDATE BY GENDER
//    public List<Candidate> filterCandidateByGender(String gender) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("candidateGender").is(gender.toLowerCase()));
//        return mongoTemplate.find(query,Candidate.class);
//    }
//
//    //TO FILTER CANDIDATE BY AGE
//    public List<Candidate> filterCandidateByAge(String age) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("candidateAge").gt(age));
//        return mongoTemplate.find(query,Candidate.class);
//
//    }

    public List<Candidate> filterCandidate(Candidate.Candidate_Gender candidateGender,
                                           UUID partyId,
                                           Candidate.Candidate_Status candidateStatus,
                                           int fromAge
                                           ) {
        Query query = new Query();

        //FILTERING BY GENDER
        List<Criteria> criteriaList = new ArrayList<>();

        if (candidateGender != null) {
            criteriaList.add(Criteria.where("candidateGender").is(candidateGender));
        }

        if (fromAge > 0) {
            criteriaList.add(Criteria.where("candidateAge").gt(fromAge));
        }
        if (partyId != null) {
            criteriaList.add(Criteria.where("partyId").is(partyId));
        }
        if (candidateStatus != null) {
            criteriaList.add(Criteria.where("candidateStatus").is(candidateStatus));
        }

        if (!CollectionUtils.isEmpty(criteriaList)){
            query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[criteriaList.size()])));
            return mongoTemplate.find(query,Candidate.class).stream().sorted(Comparator.comparingInt(Candidate::getCandidateAge)).collect(Collectors.toList());
        }
        return null;
    }


}
