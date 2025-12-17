package insurance_service.version2.core.validation;

import insurance_service.version2.core.api.dto.PersonDTO;
import insurance_service.version2.core.api.dto.ValidationErrorDTO;

import java.util.List;
import java.util.Optional;

public interface ValidationPersons {

    Optional<ValidationErrorDTO> validate(PersonDTO request);

    List<ValidationErrorDTO> validateList(PersonDTO request);
}
