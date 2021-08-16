package one.digitalinnovation.persoapi.service;

import lombok.RequiredArgsConstructor;
import one.digitalinnovation.persoapi.dto.request.PersonDTO;
import one.digitalinnovation.persoapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.persoapi.entity.Person;
import one.digitalinnovation.persoapi.exception.PersonNotFoundException;
import one.digitalinnovation.persoapi.mapper.PersonMapper;
import one.digitalinnovation.persoapi.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        Person personToSave = PersonMapper.INSTANCE.toModel(personDTO);
        Person savedPerson = this.personRepository.save(personToSave);
        return createMessageResponse(savedPerson.getId(), "Created person with ID %s");
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = this.personRepository.findAll();
        return allPeople.stream()
                .map(people -> PersonMapper.INSTANCE.toDTO(people))
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = this.verifyIfExists(id);
        return PersonMapper.INSTANCE.toDTO(person);
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return this.personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    public void delete(Long id) throws PersonNotFoundException {
        this.verifyIfExists(id);
        this.personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        this.verifyIfExists(id);

        Person personToUpdate = PersonMapper.INSTANCE.toModel(personDTO);
        Person updatedPerson = this.personRepository.save(personToUpdate);
        return createMessageResponse(updatedPerson.getId(), "Updated person with ID %s");
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(String.format(message, id))
                .build();
    }
}
