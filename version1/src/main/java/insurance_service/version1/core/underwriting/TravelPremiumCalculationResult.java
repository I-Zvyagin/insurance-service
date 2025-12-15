package insurance_service.version1.core.underwriting;

import insurance_service.version1.dto.RiskPremium;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public record TravelPremiumCalculationResult(BigDecimal totalPremium, List<RiskPremium> riskPremiums){
    public TravelPremiumCalculationResult {
        Objects.requireNonNull(totalPremium);
        Objects.requireNonNull(riskPremiums);

        riskPremiums = List.copyOf(riskPremiums);
    }
}
