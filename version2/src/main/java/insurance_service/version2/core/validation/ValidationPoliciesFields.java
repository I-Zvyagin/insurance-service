package insurance_service.version2.core.validation;

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
public class ValidationPoliciesFields {

    private final List<ValidationPolicy> validationPolicies;

    List<ValidationErrorDTO> collectPolicyErrors(PolicyDTO policyDTO){
        List<ValidationErrorDTO> list1 = collectOnePolicyError(policyDTO);
        List<ValidationErrorDTO> list2 = collectListOfPolicyErrors(policyDTO);
        return concatLists(list1, list2);
    }

    private List<ValidationErrorDTO> collectOnePolicyError(PolicyDTO policyDTO) {
        return validationPolicies.stream()
                .map(validation -> validation.validate(policyDTO))
                .filter(Optional::isPresent)
                .map(Optional::get)
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
