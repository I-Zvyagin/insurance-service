package insurance_service.version2.core.underwriting.calculators.medical;

import insurance_service.version2.core.domain.MedicalRiskLimitedLevel;
import insurance_service.version2.core.repositories.MedicalRiskLimitedLevelRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import insurance_service.version2.core.api.dto.PolicyDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class MedicalRiskLimitedLevelCalculator {

    private final MedicalRiskLimitedLevelRepository limit;

    BigDecimal getInsuranceLimitCoefficient (PolicyDTO policy) {
        return limit.findByMedicalRiskLimitedLevelIc(policy.getMedicalRiskLimitLevel())
                .map(MedicalRiskLimitedLevel::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Coefficient not found by medical risk limited level ic = " + policy.getMedicalRiskLimitLevel()));
    }
}
