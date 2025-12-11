package org.javaguru.travel.insurance.core.validation.policy;

import org.javaguru.travel.insurance.core.validation.ValidationPolicy;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

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
