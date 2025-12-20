package insurance_service.version2.core.validation;

import insurance_service.version2.core.api.dto.PersonDTO;
import insurance_service.version2.core.api.dto.PolicyDTO;
import insurance_service.version2.core.api.dto.ValidationErrorDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelPolicyValidatorImpl {

    private final List<ValidationPersons> validationPersons;
    private final List<ValidationPolicy> validationPolicies;

    public List<ValidationErrorDTO> validate(PolicyDTO policyDTO){
        List<ValidationErrorDTO> list1 = policyDTO
                .getPersons().stream()
                .map(this::collectPersonsError)
                .flatMap(List::stream)
                .toList();

        List<ValidationErrorDTO> list2 = collectPolicyErrors(policyDTO);
        return concatLists(list1, list2);
    }

    private List<ValidationErrorDTO> collectPersonsError(PersonDTO personDTO){
        List<ValidationErrorDTO> list1 = collectOnePersonsError(personDTO);
        List<ValidationErrorDTO> list2 = collectListOfPersonsErrors(personDTO);
        return concatLists(list1, list2);
    }

    private List<ValidationErrorDTO> collectPolicyErrors(PolicyDTO policyDTO){
        List<ValidationErrorDTO> list1 = collectOnePolicyError(policyDTO);
        List<ValidationErrorDTO> list2 = collectListOfPolicyErrors(policyDTO);
        return concatLists(list1, list2);
    }

    private List<ValidationErrorDTO> collectOnePersonsError(PersonDTO personDTO) {
        return validationPersons.stream()
                .map(validation -> validation.validate(personDTO))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    private List<ValidationErrorDTO> collectOnePolicyError(PolicyDTO policyDTO) {
        return validationPolicies.stream()
                .map(validation -> validation.validate(policyDTO))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    private List<ValidationErrorDTO> collectListOfPersonsErrors(PersonDTO personDTO) {
        return validationPersons.stream()
                .map(validation -> validation.validateList(personDTO))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .toList();
    }

    private List<ValidationErrorDTO> collectListOfPolicyErrors(PolicyDTO policyDTO) {
        return validationPolicies.stream()
                .map(validation -> validation.validateList(policyDTO))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .toList();
    }

    private List<ValidationErrorDTO> concatLists(List<ValidationErrorDTO> list1, List<ValidationErrorDTO> list2) {
        return Stream.concat(list1.stream(), list2.stream()).toList();
    }
}
