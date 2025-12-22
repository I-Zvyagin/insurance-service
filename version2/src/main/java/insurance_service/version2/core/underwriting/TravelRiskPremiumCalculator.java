package insurance_service.version2.core.underwriting;

import insurance_service.version2.core.api.dto.PersonDTO;
import insurance_service.version2.core.api.dto.PolicyDTO;

import java.math.BigDecimal;

public interface TravelRiskPremiumCalculator {
    BigDecimal calculatePremium(PolicyDTO policy, PersonDTO person);
    String getRiskIc();
}