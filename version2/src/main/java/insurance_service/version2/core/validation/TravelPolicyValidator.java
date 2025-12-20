package insurance_service.version2.core.validation;

import insurance_service.version2.core.api.dto.PolicyDTO;
import insurance_service.version2.core.api.dto.ValidationErrorDTO;

import java.util.List;

public interface TravelPolicyValidator {

    List<ValidationErrorDTO> validate(PolicyDTO request);
}
