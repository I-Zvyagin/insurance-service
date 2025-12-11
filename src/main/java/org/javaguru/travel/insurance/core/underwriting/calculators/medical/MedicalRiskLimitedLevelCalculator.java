package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.domain.MedicalRiskLimitedLevel;
import org.javaguru.travel.insurance.core.repositories.MedicalRiskLimitedLevelRepository;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
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
