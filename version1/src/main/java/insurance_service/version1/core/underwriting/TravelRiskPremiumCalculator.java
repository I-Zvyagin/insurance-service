package insurance_service.version1.core.underwriting;

import insurance_service.version1.dto.v1.TravelCalculatePremiumRequestV1;

import java.math.BigDecimal;

public interface TravelRiskPremiumCalculator {
    BigDecimal calculatePremium(TravelCalculatePremiumRequestV1 request);
    String getRiskIc();
}
