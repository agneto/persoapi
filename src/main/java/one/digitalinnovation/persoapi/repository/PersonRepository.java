package one.digitalinnovation.persoapi.repository;

import one.digitalinnovation.persoapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
