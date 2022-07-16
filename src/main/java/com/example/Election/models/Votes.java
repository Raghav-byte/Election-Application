package com.example.Election.models;
import org.apache.logging.log4j.core.util.UuidUtil;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.UUID;

@Document
public class Votes {

    @Id
    private UUID voterId;
    private UUID userId;
    private String userName;
    private UUID candidateId;

    //DEFAULT CONSTRUCTOR
    public Votes() {
        this.voterId= UuidUtil.getTimeBasedUuid();
    }

    //GETTER AND SETTER
    public UUID getVoterId() {
        return voterId;
    }
    public void setVoterId(UUID voterId) {
        this.voterId = voterId;
    }
    public UUID getUserId() {
        return userId;
    }
    public void setUserId(UUID userId) {
        this.userId = userId;
    }
    public UUID getCandidateId() {
        return candidateId;
    }
    public void setCandidateId(UUID candidateId) {
        this.candidateId = candidateId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
