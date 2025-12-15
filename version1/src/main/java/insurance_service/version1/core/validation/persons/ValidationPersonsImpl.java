package insurance_service.version1.core.validation.persons;

import insurance_service.version1.core.validation.ValidationPersons;
import insurance_service.version1.dto.ValidationError;
import insurance_service.version1.dto.v1.TravelCalculatePremiumRequestV1;

import java.util.List;
import java.util.Optional;

abstract class ValidationPersonsImpl implements ValidationPersons {

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        return Optional.empty();
    }

    @Override
    public List<ValidationError> validateList(TravelCalculatePremiumRequestV1 request) {
        return List.of();
    }
}
