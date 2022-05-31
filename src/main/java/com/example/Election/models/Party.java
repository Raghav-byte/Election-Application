package com.example.Election.models;

import org.apache.logging.log4j.core.util.UuidUtil;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document
public class Party {

    @Id
    private UUID partyId;
    private String partyName;
    private Integer partyVotes;
    private String partyLeaderName;
    private UUID partyLeaderId;

    //CONSTRUCTOR TO SET PARTY ID
    public Party() {
        this.partyId= UuidUtil.getTimeBasedUuid();
    }

    //GETTER & SETTER
    public String getPartyLeaderName() {
        return partyLeaderName;
    }
    public void setPartyLeaderName(String partyLeaderName) {
        this.partyLeaderName = partyLeaderName;
    }
    public UUID getPartyLeaderId() {
        return partyLeaderId;
    }
    public void setPartyLeaderId(UUID partyLeaderId) {
        this.partyLeaderId = partyLeaderId;
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
    public Integer getPartyVotes() {
        return partyVotes;
    }
    public void setPartyVotes(Integer partyVotes) {
        this.partyVotes = partyVotes;
    }

}
