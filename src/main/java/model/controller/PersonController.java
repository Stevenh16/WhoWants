package model.controller;

import lombok.AllArgsConstructor;
import model.dto.PersonDto;
import model.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/persons")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PersonController {
    private PersonService personService;

    @GetMapping()
    public ResponseEntity<List<PersonDto>> getPersons(){
        return ResponseEntity.ok(personService.findAll());
    }
    @GetMapping("/id")
    public ResponseEntity<PersonDto> getPersonById(@RequestParam Long id){
        return personService.findById(id)
                .map(p->ResponseEntity.ok().body(p))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping()
    public ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto personDto){
        return createNewPerson(personDto);
    }

    private ResponseEntity<PersonDto> createNewPerson(PersonDto personDto) {
        PersonDto personIdDto = personService.save(personDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/id")
                .buildAndExpand(personIdDto.id())
                .toUri();
        return ResponseEntity.created(location).body(personIdDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> updatePerson(@PathVariable Long id, @RequestBody PersonDto personDto){
        Optional<PersonDto> personUpdated = personService.update(id, personDto);
        return personUpdated.map(ResponseEntity::ok).orElseGet(()->createNewPerson(personDto));
    }

    @DeleteMapping("/id")
    public ResponseEntity<PersonDto> deletePersonById(@RequestBody Long id){
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
