package insurance_service.version1.core.validation;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import insurance_service.version1.dto.ValidationError;
import insurance_service.version1.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatePremiumRequestValidatorImpl implements TravelCalculatePremiumRequestValidator{

    private final List<ValidationPersons> validationPersons;
    private final List<ValidationPolicy> validationPolicies;

    public List<ValidationError> validate(TravelCalculatePremiumRequestV1 request){
        List<ValidationError> list1 = collectPersonsError(request);
        List<ValidationError> list2 = collectPolicyErrors(request);
        return concatLists(list1, list2);

    }

    public List<ValidationError> collectPersonsError(TravelCalculatePremiumRequestV1 request){
        List<ValidationError> list1 = collectOnePersonsError(request);
        List<ValidationError> list2 = collectListOfPersonsErrors(request);
        return concatLists(list1, list2);

    }

    public List<ValidationError> collectPolicyErrors(TravelCalculatePremiumRequestV1 request){
        List<ValidationError> list1 = collectOnePolicyError(request);
        List<ValidationError> list2 = collectListOfPolicyErrors(request);
        return concatLists(list1, list2);

    }
    public List<ValidationError> collectOnePersonsError(TravelCalculatePremiumRequestV1 request) {
        return validationPersons.stream()
                .map(validation -> validation.validate(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    public List<ValidationError> collectOnePolicyError(TravelCalculatePremiumRequestV1 request) {
        return validationPolicies.stream()
                .map(validation -> validation.validate(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    public List<ValidationError> collectListOfPersonsErrors(TravelCalculatePremiumRequestV1 request) {
        return validationPersons.stream()
                .map(validation -> validation.validateList(request))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .toList();
    }

    public List<ValidationError> collectListOfPolicyErrors(TravelCalculatePremiumRequestV1 request) {
        return validationPolicies.stream()
                .map(validation -> validation.validateList(request))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .toList();
    }

    public List<ValidationError> concatLists(List<ValidationError> list1, List<ValidationError> list2) {
        return Stream.concat(list1.stream(), list2.stream()).toList();
    }
}