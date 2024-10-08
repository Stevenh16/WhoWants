package model.controller;

import lombok.AllArgsConstructor;
import model.dto.RatingDto;
import model.service.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/ratings")
@AllArgsConstructor
public class RatingController {
    private RatingService ratingService;

    @GetMapping
    public ResponseEntity<List<RatingDto>> getRatings(){
        return ResponseEntity.ok(ratingService.findAll());
    }
    @PostMapping()
    public ResponseEntity<RatingDto> createRating(@RequestBody RatingDto ratingDto){
        return createNewRating(ratingDto);
    }

    @PutMapping("/id")
    public ResponseEntity<RatingDto> updateRating(@RequestBody Long id, RatingDto ratingDto){
        Optional<RatingDto> ratingUpdated = ratingService.update(id, ratingDto);
        return ratingUpdated.map(ResponseEntity::ok).orElseGet(() ->createNewRating(ratingDto));
    }

    @DeleteMapping("/id")
    public ResponseEntity<RatingDto> deleteRating(Long id){
        ratingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<RatingDto> createNewRating(RatingDto ratingDto) {
        RatingDto ratingIdDto = ratingService.save(ratingDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/id")
                .buildAndExpand(ratingIdDto.id())
                .toUri();
        return ResponseEntity.created(location).body(ratingIdDto);
    }
}
