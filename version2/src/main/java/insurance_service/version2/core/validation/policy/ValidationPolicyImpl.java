package insurance_service.version2.core.validation.policy;

import insurance_service.version2.core.api.dto.PolicyDTO;
import insurance_service.version2.core.api.dto.ValidationErrorDTO;
import insurance_service.version2.core.validation.ValidationPolicy;


import java.util.List;
import java.util.Optional;

abstract class ValidationPolicyImpl implements ValidationPolicy {

    @Override
    public Optional<ValidationErrorDTO> validate(PolicyDTO request) {
        return Optional.empty();
    }

    @Override
    public List<ValidationErrorDTO> validateList(PolicyDTO request) {
        return List.of();
    }
}
