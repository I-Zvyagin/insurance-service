package insurance_service.version2.core.validation.policy;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import insurance_service.version2.core.api.dto.PolicyDTO;
import insurance_service.version2.core.api.dto.ValidationErrorDTO;
import insurance_service.version2.core.validation.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ValidationEmptyMedicalRiskLimitLevel extends ValidationPolicyImpl {

    @Value( "${medical.risk.limit.level.enabled:true}" )
    private Boolean medicalRiskLimitLevelEnabled;

    private final ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(PolicyDTO request) {
        return (isMedicalRiskLimitLevelEnabled()
                && isMedicalRiskLimitLevelIsNullOrBlank(request))
                && containsTravelMedical(request)
                ? Optional.of(errorFactory.buildError("ERROR_CODE_13"))
                : Optional.empty();
    }

    private boolean isMedicalRiskLimitLevelEnabled() {
        return medicalRiskLimitLevelEnabled;
    }

    private boolean isMedicalRiskLimitLevelIsNullOrBlank(PolicyDTO request) {
        return request.getMedicalRiskLimitLevel() == null || request.getMedicalRiskLimitLevel().isBlank();
    }

    private boolean containsTravelMedical(PolicyDTO request) {
        return request.getSelectedRisks() != null
                && request.getSelectedRisks().contains("TRAVEL_MEDICAL");
    }

}
