package insurance_service.version2.core.underwriting;

import insurance_service.version2.core.api.dto.PersonDTO;
import insurance_service.version2.core.api.dto.PolicyDTO;
import insurance_service.version2.core.api.dto.RiskDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class UnderwritingCalculatorImpl implements  UnderwritingCalculator{

    private final SelectedRisksPremiumCalculator selectedRisksPremiumCalculator;

    @Override
    public TravelPremiumCalculationResult calculatePremium(PolicyDTO policyDTO, PersonDTO personDTO) {
        List<RiskDTO> riskPremiums = calculateSelectedRisksPremium(policyDTO, personDTO);
        BigDecimal totalPremium = calculateTotalPremium(riskPremiums);
        return new TravelPremiumCalculationResult(totalPremium, riskPremiums);
    }

    private List<RiskDTO> calculateSelectedRisksPremium(PolicyDTO policyDTO, PersonDTO personDTO) {
        return selectedRisksPremiumCalculator.getRiskPremiumForAllSelectedRisk(policyDTO, personDTO);
    }

    private static BigDecimal calculateTotalPremium(List<RiskDTO> riskPremiums) {
        return riskPremiums.stream()
                .map(RiskDTO::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}