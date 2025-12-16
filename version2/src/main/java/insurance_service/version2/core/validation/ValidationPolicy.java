package insurance_service.version2.core.validation;

import insurance_service.version2.core.api.dto.PolicyDTO;
import insurance_service.version2.core.api.dto.ValidationErrorDTO;

import java.util.List;
import java.util.Optional;

public interface ValidationPolicy {

    Optional<ValidationErrorDTO> validate(PolicyDTO request);

    List<ValidationErrorDTO> validateList(PolicyDTO request);
}
