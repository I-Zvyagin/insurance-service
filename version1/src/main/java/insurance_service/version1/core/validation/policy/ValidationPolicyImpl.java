package insurance_service.version1.core.validation.policy;

import insurance_service.version1.core.validation.ValidationPolicy;
import insurance_service.version1.dto.ValidationError;
import insurance_service.version1.dto.v1.TravelCalculatePremiumRequestV1;

import java.util.List;
import java.util.Optional;

abstract class ValidationPolicyImpl implements ValidationPolicy {

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        return Optional.empty();
    }

    @Override
    public List<ValidationError> validateList(TravelCalculatePremiumRequestV1 request) {
        return List.of();
    }
}
