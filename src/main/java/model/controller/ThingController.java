package model.controller;

import lombok.AllArgsConstructor;
import model.dto.ThingDto;
import model.service.ThingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/things")
@AllArgsConstructor
public class ThingController {
    private ThingService thingService;

    @GetMapping
    public ResponseEntity<List<ThingDto>> getThings() {
        return ResponseEntity.ok(thingService.findAll());
    }

    @PostMapping()
    public ResponseEntity<ThingDto> createThing(@RequestBody ThingDto thing) {
        return createNewThing(thing);
    }

    @PutMapping("/id")
    public ResponseEntity<ThingDto> updateThing(@RequestBody Long id, ThingDto thing) {
        Optional<ThingDto> thingUpdated = thingService.update(id, thing);
        return thingUpdated.map(ResponseEntity::ok).orElseGet(() -> createNewThing(thing));
    }

    @DeleteMapping("/id")
    public ResponseEntity<ThingDto> deleteThing(Long id) {
        thingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<ThingDto> createNewThing(ThingDto thing) {
        ThingDto thingIdDto = thingService.save(thing);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/id")
                .buildAndExpand(thingIdDto.id())
                .toUri();
        return ResponseEntity.created(location).body(thingIdDto);
    }

}
