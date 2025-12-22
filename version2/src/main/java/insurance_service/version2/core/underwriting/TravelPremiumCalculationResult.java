package insurance_service.version2.core.underwriting;

import insurance_service.version2.core.api.dto.RiskDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public record TravelPremiumCalculationResult(BigDecimal totalPremium, List<RiskDTO> riskPremiums){
    public TravelPremiumCalculationResult {
        Objects.requireNonNull(totalPremium);
        Objects.requireNonNull(riskPremiums);

        riskPremiums = List.copyOf(riskPremiums);
    }
}