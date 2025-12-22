package insurance_service.version2.core.underwriting;

import insurance_service.version2.core.api.dto.PersonDTO;
import insurance_service.version2.core.api.dto.PolicyDTO;

public interface UnderwritingCalculator {

    TravelPremiumCalculationResult calculatePremium(PolicyDTO policyDTO, PersonDTO personDTO);

}