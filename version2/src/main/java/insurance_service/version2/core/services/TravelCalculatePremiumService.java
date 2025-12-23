package insurance_service.version2.core.services;

import insurance_service.version2.core.api.command.TravelCalculatePremiumCoreCommand;
import insurance_service.version2.core.api.command.TravelCalculatePremiumCoreResult;

public interface TravelCalculatePremiumService {

    TravelCalculatePremiumCoreResult calculatePremium(TravelCalculatePremiumCoreCommand command);

}
