package one.digitalinnovation.persoapi.mapper;

import one.digitalinnovation.persoapi.dto.request.PersonDTO;
import one.digitalinnovation.persoapi.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class PersonMapper {

    public static final PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    public abstract Person toModel(PersonDTO personDTO);

    public abstract PersonDTO toDTO(Person person);
}
