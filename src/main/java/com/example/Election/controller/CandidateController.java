package com.example.Election.controller;

import com.example.Election.models.Candidate;
import com.example.Election.models.Party;
import com.example.Election.service.CandidateServices;
import com.example.Election.service.PartyServices;
import com.example.Election.service.UserServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/candidates")

public class CandidateController {

    @Autowired
    private CandidateServices candidateServices;

    @Autowired
    private UserServices userServices;

    @Autowired
    private PartyServices partyServices;

    @ApiOperation("Create candidate")
    @PostMapping("/createCandidate")
    public Candidate createCandidate(@RequestBody Candidate candidate){
        return candidateServices.createCandidate(candidate);
    }

    @ApiOperation("Show all candidates")
    @GetMapping("/showAllCandidates")
    public List<Candidate> showAllCandidate(){
        return candidateServices.showAllCandidate();
    }

    @ApiOperation("Show one candidate")
    @GetMapping("/{candidateId}")
    public Optional<Candidate> showOneCandidate(@PathVariable UUID candidateId){
        return candidateServices.showOneCandidate(candidateId);
    }

    @ApiOperation("Deletes candidate")
    @DeleteMapping("/{candidateId}")
    public String deleteCandidate(@PathVariable UUID candidateId){
        return candidateServices.deleteCandidate(candidateId);
    }

    @ApiOperation("Updates candidate")
    @PutMapping("/updateCandidate")
    public Candidate updateCandidate(@RequestBody Candidate candidate){
        return candidateServices.updateCandidate(candidate);
    }

    @ApiOperation("Search by candidate name")
    @GetMapping("/search-candidate")
    public List<Candidate> searchCandidate(@RequestParam String name) {
        return candidateServices.searchCandidate(name);
    }

//    @ApiOperation("Filter by candidate gender")
//    @GetMapping("/candidate-by-gender")
//    public List<Candidate> filterCandidateByGender(@RequestParam String name) {
//        return candidateServices.filterCandidateByGender(name);
//    }
//
//    @ApiOperation("Filter by candidate age")
//    @GetMapping("/candidate-by-age")
//    public List<Candidate> filterCandidateByAge(@RequestParam String name) {
//        return candidateServices.filterCandidateByAge(name);
//    }

    @ApiOperation("Filter")
    @GetMapping("/filter")
    public List<Candidate> filterCandidate(@RequestParam(required = false) Candidate.Candidate_Gender candidateGender,
                                           @RequestParam(required = false) UUID partyId ,
                                           @RequestParam(required = false) Candidate.Candidate_Status candidateStatus,
                                           @RequestParam(required = false) Integer fromAge) {
        return candidateServices.filterCandidate(candidateGender,partyId,candidateStatus,fromAge);
    }



}
