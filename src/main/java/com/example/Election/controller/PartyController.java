package com.example.Election.controller;
import com.example.Election.models.Party;
import com.example.Election.service.PartyServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/party")
public class PartyController {

    @Autowired
    private PartyServices partyServices;

    @ApiOperation("Creates Party")
    @PostMapping("/createParty")
    public Party createParty(@RequestBody Party party){
        return partyServices.createParty(party);
    }

    @ApiOperation("Show all Party")
    @GetMapping("/showParty")
    public List<Party> showAllParty(){
        return partyServices.showAllParty();
    }

    @ApiOperation("Shows One Party")
    @GetMapping("/{partyId}")
    public Optional<Party> showOneParty(@PathVariable UUID partyId){
        return partyServices.showOneParty(partyId);
    }

    @ApiOperation("Deletes Party")
    @DeleteMapping("/{partyId}")
    public String deleteParty(@PathVariable UUID partyId){
        return partyServices.deleteParty(partyId);
    }

    @ApiOperation("Updates Party")
    @PutMapping("/update-party")
    public Party updateParty(@RequestBody Party party){
        return partyServices.updateParty(party);
    }

    @ApiOperation("Search by party name")
    @GetMapping("/search-party")
    public List<Party> searchParty(@RequestParam String name) {
        return partyServices.searchParty(name);
    }
}
