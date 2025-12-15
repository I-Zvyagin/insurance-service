package insurance_service.version1.core.underwriting.calculators.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import insurance_service.version1.core.domain.MedicalRiskLimitedLevel;
import insurance_service.version1.core.repositories.MedicalRiskLimitedLevelRepository;
import insurance_service.version1.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class MedicalRiskLimitedLevelCalculator {

    private final MedicalRiskLimitedLevelRepository limit;

    BigDecimal getInsuranceLimitCoefficient (TravelCalculatePremiumRequestV1 request) {
        return limit.findByMedicalRiskLimitedLevelIc(request.getMedicalRiskLimitLevel())
                .map(MedicalRiskLimitedLevel::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Coefficient not found by medical risk limited level ic = " + request.getMedicalRiskLimitLevel()));
    }
}
