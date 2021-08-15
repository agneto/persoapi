package one.digitalinnovation.persoapi.controller;

import lombok.RequiredArgsConstructor;
import one.digitalinnovation.persoapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.persoapi.entity.Person;
import one.digitalinnovation.persoapi.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private final PersonService personService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Validated Person person) {
        return this.personService.createPerson(person);
    }
}
