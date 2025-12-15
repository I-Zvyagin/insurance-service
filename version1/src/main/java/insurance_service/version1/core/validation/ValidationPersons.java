package insurance_service.version1.core.validation;

import insurance_service.version1.dto.ValidationError;
import insurance_service.version1.dto.v1.TravelCalculatePremiumRequestV1;

import java.util.List;
import java.util.Optional;

public interface ValidationPersons {

    Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request);

    List<ValidationError> validateList(TravelCalculatePremiumRequestV1 request);
}
