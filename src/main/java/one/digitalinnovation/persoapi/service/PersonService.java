package one.digitalinnovation.persoapi.service;

import lombok.RequiredArgsConstructor;
import one.digitalinnovation.persoapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.persoapi.entity.Person;
import one.digitalinnovation.persoapi.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public MessageResponseDTO createPerson(Person person) {
        Person savedPerson = this.personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message(String.format("Created person with ID %s", savedPerson.getId()))
                .build();

    }
}
