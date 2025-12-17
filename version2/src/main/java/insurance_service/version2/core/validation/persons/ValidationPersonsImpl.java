package insurance_service.version2.core.validation.persons;

import insurance_service.version2.core.api.dto.PersonDTO;
import insurance_service.version2.core.api.dto.ValidationErrorDTO;
import insurance_service.version2.core.validation.ValidationPersons;

import java.util.List;
import java.util.Optional;

abstract class ValidationPersonsImpl implements ValidationPersons {

    @Override
    public Optional<ValidationErrorDTO> validate(PersonDTO request) {
        return Optional.empty();
    }

    @Override
    public List<ValidationErrorDTO> validateList(PersonDTO request) {
        return List.of();
    }
}
