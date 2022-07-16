package com.example.Election.controller;
import com.example.Election.models.Candidate;
import com.example.Election.service.CandidateServices;
import com.example.Election.service.PartyServices;
import com.example.Election.service.UserServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @ApiOperation("Creates candidate")
    @PostMapping("/createCandidate")
    public Candidate createCandidate(@RequestBody Candidate candidate){
        return candidateServices.createCandidate(candidate);
    }

    @ApiOperation("Show all candidates")
    @GetMapping("/showAllCandidates")
    public Page<Candidate> showAllCandidate(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "3") int pageSize){
        return candidateServices.showAllCandidate(pageNo,pageSize);
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
    @PutMapping("/update-Candidate")
    public Candidate updateCandidate(@RequestBody Candidate candidate){
        return candidateServices.updateCandidate(candidate);
    }

    @ApiOperation("Search ")
    @GetMapping("/search")
    public List<Candidate> searchCandidate(@RequestParam String name) {
        return candidateServices.searchCandidate(name);
    }


    @ApiOperation("Filter")
    @GetMapping("/filter")
    public List<Candidate> filterCandidate(@RequestParam(required = false) Candidate.Candidate_Gender candidateGender,
                                           @RequestParam(required = false) UUID partyId ,
                                           @RequestParam(required = false) Candidate.Candidate_Status candidateStatus,
                                           @RequestParam(required = false) Integer fromAge) {
        return candidateServices.filterCandidate(candidateGender,partyId,candidateStatus,fromAge);
    }



}
