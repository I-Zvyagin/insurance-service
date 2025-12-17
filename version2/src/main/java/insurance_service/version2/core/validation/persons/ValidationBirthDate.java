package insurance_service.version2.core.validation.persons;

import insurance_service.version2.core.validation.ValidationErrorFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import insurance_service.version2.core.api.dto.PersonDTO;
import insurance_service.version2.core.api.dto.ValidationErrorDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ValidationBirthDate extends ValidationPersonsImpl {

    private final ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(PersonDTO request) {
        return request.getPersonBirthDate() == null
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_11"))
                : Optional.empty();
    }
}
