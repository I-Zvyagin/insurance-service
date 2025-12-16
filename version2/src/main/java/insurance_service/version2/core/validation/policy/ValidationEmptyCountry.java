package insurance_service.version2.core.validation.policy;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import insurance_service.version2.core.api.dto.PolicyDTO;
import insurance_service.version2.core.api.dto.ValidationErrorDTO;
import insurance_service.version2.core.validation.ValidationErrorFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ValidationEmptyCountry extends ValidationPolicyImpl {

    private final ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(PolicyDTO request) {
        return (checkCountryNotFilled(request)
                && checkRiskListContainTravelMedical(request))
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_10"))
                : Optional.empty();
    }

    private boolean checkCountryNotFilled(PolicyDTO request) {
        return request.getCountry() == null || request.getCountry().isBlank();
    }

    private boolean checkRiskListContainTravelMedical(PolicyDTO request) {
        return request.getSelectedRisks() != null;
    }
}
