package model.controller;

import lombok.AllArgsConstructor;
import model.dto.DonationDto;
import model.service.DonationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/donations")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class DonationController {
    private DonationService donationService;

    @GetMapping
    public ResponseEntity<List<DonationDto>> getDonations(){
        return ResponseEntity.ok(donationService.findAll());
    }
    @GetMapping("/id")
    public ResponseEntity<DonationDto> getDonationById(@RequestParam Long id){
        return donationService.findById(id)
                .map(d->ResponseEntity.ok().body(d))
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/thingName")
    public ResponseEntity<List<DonationDto>> getDonationByThingName(@RequestParam String thingName){
        return ResponseEntity.ok(donationService.findByThingName(thingName));
    }
    @GetMapping("/personId")
    public ResponseEntity<List<DonationDto>> getDonationByPersonId(@RequestParam Long personId){
        return ResponseEntity.ok(donationService.findByPersonId(personId));
    }
    @PostMapping()
    public ResponseEntity<DonationDto> createDonation(@RequestBody DonationDto donation){
        return createNewDonation(donation);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DonationDto> updateDonation(@PathVariable Long id, @RequestBody DonationDto donation){
        Optional<DonationDto> donationUpdated = donationService.update(id, donation);
        return donationUpdated.map(ResponseEntity::ok).orElseGet(()->createNewDonation(donation));
    }
    @DeleteMapping("/id")
    public ResponseEntity<DonationDto> deleteDonationById(@RequestBody Long id){
        donationService.delete(id);
        return ResponseEntity.noContent().build();
    }
    private ResponseEntity<DonationDto> createNewDonation(DonationDto donationDto) {
        DonationDto donationIdDto = donationService.save(donationDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/id")
                .buildAndExpand(donationIdDto.id())
                .toUri();
        return ResponseEntity.created(location).body(donationIdDto);
    }
}
