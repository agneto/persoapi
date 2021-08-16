package one.digitalinnovation.persoapi.service;

import one.digitalinnovation.persoapi.dto.request.PersonDTO;
import one.digitalinnovation.persoapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.persoapi.entity.Person;
import one.digitalinnovation.persoapi.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSavedMessage() {
        PersonDTO dto = PersonDTO
                .builder()
                .firstName("Barizon")
                .lastName("Metaleiro")
                .cpf("391.229.210-87")
                .birthDate("04-04-1990")
                .build();

        Person person = Person
                .builder()
                .id(1L)
                .firstName("Barizon")
                .lastName("Metaleiro")
                .cpf("391.229.210-87")
                .build();

        Mockito.when(this.personRepository.save(any(Person.class))).thenReturn(person);

        MessageResponseDTO expectedSuccessMessage = MessageResponseDTO
                .builder()
                .message("Created person with ID " + person.getId())
                .build();

        MessageResponseDTO success = this.personService.createPerson(dto);

        Assertions.assertEquals(expectedSuccessMessage, success);
    }
}