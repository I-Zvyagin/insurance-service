package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    private final CountryDefaultDayRateCalculator dayRate;
    private final DayCountCalculator dayCount;
    private final AgeCoefficientCalculator ageCoefficient;
    private final MedicalRiskLimitedLevelCalculator medicalRiskLimitedLevel;

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequestV1 request) {
        return dayCount.getDayCount(request)
                .multiply(dayRate.getCountryDefaultDayPremium(request))
                .multiply(ageCoefficient.getAgeCoefficient(request))
                .multiply(medicalRiskLimitedLevel.getInsuranceLimitCoefficient(request))
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }
}