package org.javaguru.travel.insurance.core.validation.persons;

import org.javaguru.travel.insurance.core.validation.ValidationPersons;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import java.util.List;
import java.util.Optional;

abstract class ValidationPersonsImpl implements ValidationPersons {

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        return Optional.empty();
    }

    @Override
    public List<ValidationError> validateList(TravelCalculatePremiumRequestV1 request) {
        return List.of();
    }
}
