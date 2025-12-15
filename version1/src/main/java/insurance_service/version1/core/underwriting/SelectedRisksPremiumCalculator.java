package insurance_service.version1.core.underwriting;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import insurance_service.version1.dto.RiskPremium;
import insurance_service.version1.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class SelectedRisksPremiumCalculator {

    private  final List<TravelRiskPremiumCalculator> travelRiskPremiumCalculators;

    public List<RiskPremium> getRiskPremiumForAllSelectedRisk(TravelCalculatePremiumRequestV1 request) {
        List<RiskPremium> riskPremiums = request.getSelectedRisks().stream()
                .map(riskIc -> new RiskPremium(riskIc, getRiskPremiumForOneSelectedRisk(riskIc, request)))
                .toList();
        return riskPremiums;
    }

    private BigDecimal getRiskPremiumForOneSelectedRisk(String riskIc, TravelCalculatePremiumRequestV1 request) {
        TravelRiskPremiumCalculator riskPremiumCalculator = getTravelRiskPremiumCalculator(riskIc);
        return riskPremiumCalculator.calculatePremium(request);
    }

    private TravelRiskPremiumCalculator getTravelRiskPremiumCalculator(String riskIc) {
        return travelRiskPremiumCalculators.stream()
                .filter(riskCalculator -> riskCalculator.getRiskIc().equals(riskIc))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not supported riskIc = " + riskIc));
    }
}
