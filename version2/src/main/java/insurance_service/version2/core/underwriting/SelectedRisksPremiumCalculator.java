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
public class SelectedRisksPremiumCalculator {

    private  final List<TravelRiskPremiumCalculator> travelRiskPremiumCalculators;

    public List<RiskDTO> getRiskPremiumForAllSelectedRisk(PolicyDTO policyDTO, PersonDTO personDTO) {
        return policyDTO.getSelectedRisks().stream()
                .map(riskIc -> new RiskDTO(riskIc, getRiskPremiumForOneSelectedRisk(riskIc, policyDTO, personDTO)))
                .toList();
    }

    private BigDecimal getRiskPremiumForOneSelectedRisk(String riskIc, PolicyDTO policyDTO, PersonDTO personDTO) {
        TravelRiskPremiumCalculator riskPremiumCalculator = getTravelRiskPremiumCalculator(riskIc);
        return riskPremiumCalculator.calculatePremium(policyDTO, personDTO);
    }

    private TravelRiskPremiumCalculator getTravelRiskPremiumCalculator(String riskIc) {
        return travelRiskPremiumCalculators.stream()
                .filter(riskCalculator -> riskCalculator.getRiskIc().equals(riskIc))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not supported riskIc = " + riskIc));
    }
}
