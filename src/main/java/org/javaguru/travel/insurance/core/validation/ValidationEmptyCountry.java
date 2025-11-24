package org.javaguru.travel.insurance.core.validation;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ValidationEmptyCountry extends ValidationServiceImpl{

    private final ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return (checkCountryNotFilled(request)
                && checkRiskListContainTravelMedical(request))
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_10"))
                : Optional.empty();
    }

    private boolean checkCountryNotFilled(TravelCalculatePremiumRequest request) {
        return request.getCountry() == null || request.getCountry().isBlank();
    }

    private boolean checkRiskListContainTravelMedical(TravelCalculatePremiumRequest request) {
        return request.getSelectedRisks() != null;
    }
}
