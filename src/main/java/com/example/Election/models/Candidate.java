package com.example.Election.models;
import org.apache.logging.log4j.core.util.UuidUtil;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.UUID;

@Document
public class Candidate {

    public enum Candidate_Status {
        CANDIDATE, NOMINATED, WON;
    }
    public enum Candidate_Gender {
        MALE,FEMALE;
    }
    public static final int qualifyingCount = 4;


    @Id
    private UUID candidateId;
    private String candidateName;
    private int candidateAge;
    private Candidate_Gender candidateGender;
    private String candidateNumber;
    private UUID partyId;
    private String partyName;
    private int candidateVotes;
    private Candidate_Status candidateStatus;

    public Candidate() {
        this.candidateId= UuidUtil.getTimeBasedUuid();
        this.candidateStatus = Candidate_Status.CANDIDATE;
    }

    //GETTER & SETTER
    public Candidate_Gender getCandidateGender() {
        return candidateGender;
    }

    public void setCandidateGender(Candidate_Gender candidateGender) {
        this.candidateGender = candidateGender;
    }

    public void setCandidateVotes(int candidateVotes) {
        this.candidateVotes = candidateVotes;
    }

    public int getCandidateVotes() {
        return candidateVotes;
    }

    public UUID getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(UUID candidateId) {
        this.candidateId = candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public int getCandidateAge() {
        return candidateAge;
    }

    public void setCandidateAge(int candidateAge) {
        this.candidateAge = candidateAge;
    }

    public String getCandidateNumber() {
        return candidateNumber;
    }

    public void setCandidateNumber(String candidateNumber) {
        this.candidateNumber = candidateNumber;
    }

    public UUID getPartyId() {
        return partyId;
    }

    public void setPartyId(UUID partyId) {
        this.partyId = partyId;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public Candidate_Status getCandidateStatus() {
        return candidateStatus;
    }

    public void setCandidateStatus(Candidate_Status candidateStatus) {
        this.candidateStatus = candidateStatus;
    }
}
