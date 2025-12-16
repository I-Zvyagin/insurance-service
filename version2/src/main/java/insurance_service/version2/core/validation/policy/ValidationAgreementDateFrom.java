package insurance_service.version2.core.validation.policy;

import insurance_service.version2.core.api.dto.PolicyDTO;
import insurance_service.version2.core.validation.ValidationErrorFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import insurance_service.version2.core.api.dto.ValidationErrorDTO;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ValidationAgreementDateFrom extends ValidationPolicyImpl {

    private final ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(PolicyDTO request) {
        return (request.getAgreementDateFrom() == null)
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_3"))
                : Optional.empty();
    }
}
