package com.example.Election.controller;

import com.example.Election.models.Party;
import com.example.Election.models.Votes;
import com.example.Election.service.VotesServices;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vote")
public class VotesController {

    @Autowired
    private VotesServices votesServices;

    @PostMapping("/givingVote")
    public Votes addVoteForCandidate(@RequestBody Votes votes) {
        return votesServices.addVoteForCandidate(votes);
    }

    @GetMapping("/winner")
    public Optional<Party> findWinner() {
        return votesServices.findWinner();
    }


//    @GetMapping("/declare/winner")
//    public Party declareWinner(){
//        return votesServices.declareWinner();
//    }
}
