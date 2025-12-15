package insurance_service.version1.core.services;

import insurance_service.version1.dto.v1.TravelCalculatePremiumRequestV1;
import insurance_service.version1.dto.v1.TravelCalculatePremiumResponseV1;

public interface TravelCalculatePremiumService {

    TravelCalculatePremiumResponseV1 calculatePremium(TravelCalculatePremiumRequestV1 request);

}
