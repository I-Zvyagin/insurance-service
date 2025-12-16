package insurance_service.version2.core.validation.policy;

import insurance_service.version2.core.repositories.ClassifierValueRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import insurance_service.version2.core.api.dto.PolicyDTO;
import insurance_service.version2.core.api.dto.ValidationErrorDTO;
import insurance_service.version2.core.validation.ValidationErrorFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ValidationCountry extends ValidationPolicyImpl {

    private final ClassifierValueRepository classifierValueRepository;
    private final ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(PolicyDTO request) {
        return (checkCountryIsFilled(request)
                && checkRiskListContainTravelMedical(request))
                && !checkCountryIsSupported(request)
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_15"))
                : Optional.empty();
    }

    private boolean checkCountryIsFilled(PolicyDTO request) {
        return request.getCountry() != null && !request.getCountry().isBlank();
    }

    private boolean checkRiskListContainTravelMedical(PolicyDTO request) {
        return request.getSelectedRisks() != null &&
                request.getSelectedRisks().contains("TRAVEL_MEDICAL");
    }

    private boolean checkCountryIsSupported(PolicyDTO request) {
        return classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", request.getCountry()).isPresent();
    }
}
