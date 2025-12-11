package org.javaguru.travel.insurance.core.validation.policy;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import org.javaguru.travel.insurance.core.validation.ValidationErrorFactory;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ValidationCountry extends ValidationPolicyImpl {

    private final ClassifierValueRepository classifierValueRepository;
    private final ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        return (checkCountryIsFilled(request)
                && checkRiskListContainTravelMedical(request))
                && !checkCountryIsSupported(request)
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_15"))
                : Optional.empty();
    }

    private boolean checkCountryIsFilled(TravelCalculatePremiumRequestV1 request) {
        return request.getCountry() != null && !request.getCountry().isBlank();
    }

    private boolean checkRiskListContainTravelMedical(TravelCalculatePremiumRequestV1 request) {
        return request.getSelectedRisks() != null &&
                request.getSelectedRisks().contains("TRAVEL_MEDICAL");
    }

    private boolean checkCountryIsSupported(TravelCalculatePremiumRequestV1 request) {
        return classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", request.getCountry()).isPresent();
    }
}
