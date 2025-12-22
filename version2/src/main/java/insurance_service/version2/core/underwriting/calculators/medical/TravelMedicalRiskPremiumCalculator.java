package insurance_service.version2.core.underwriting.calculators.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import insurance_service.version2.core.api.dto.PersonDTO;
import insurance_service.version2.core.api.dto.PolicyDTO;
import insurance_service.version2.core.underwriting.TravelRiskPremiumCalculator;
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
    public BigDecimal calculatePremium(PolicyDTO policy, PersonDTO person) {
        return dayCount.getDayCount(policy)
                .multiply(dayRate.getCountryDefaultDayPremium(policy))
                .multiply(ageCoefficient.getAgeCoefficient(person))
                .multiply(medicalRiskLimitedLevel.getInsuranceLimitCoefficient(policy))
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }
}