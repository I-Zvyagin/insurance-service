package insurance_service.version2.core.validation;

import insurance_service.version2.core.api.dto.PolicyDTO;
import insurance_service.version2.core.api.dto.ValidationErrorDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelPolicyValidatorImpl {

    private final ValidationPersonsFields validationPersonsFields;
    private final ValidationPoliciesFields validationPoliciesFields;

    public List<ValidationErrorDTO> validate(PolicyDTO policyDTO){
        List<ValidationErrorDTO> list1 = policyDTO
                .getPersons().stream()
                .map(validationPersonsFields::collectPersonsError)
                .flatMap(Collection::stream)
                .toList();

        List<ValidationErrorDTO> list2 = validationPoliciesFields.collectPolicyErrors(policyDTO);
        return concatLists(list1, list2);
    }

    private List<ValidationErrorDTO> concatLists(List<ValidationErrorDTO> list1, List<ValidationErrorDTO> list2) {
        return Stream.concat(list1.stream(), list2.stream()).toList();
    }
}
