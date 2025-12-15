package insurance_service.version1.core.underwriting;

import insurance_service.version1.dto.v1.TravelCalculatePremiumRequestV1;

public interface UnderwritingCalculator {

    TravelPremiumCalculationResult calculatePremium(TravelCalculatePremiumRequestV1 request);

}
