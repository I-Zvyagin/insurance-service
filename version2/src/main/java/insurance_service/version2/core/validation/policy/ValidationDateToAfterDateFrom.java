package insurance_service.version2.core.validation.policy;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import insurance_service.version2.core.api.dto.PolicyDTO;
import insurance_service.version2.core.api.dto.ValidationErrorDTO;
import insurance_service.version2.core.validation.ValidationErrorFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ValidationDateToAfterDateFrom extends ValidationPolicyImpl {

    private final ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(PolicyDTO request) {
        return (request.getAgreementDateTo() != null &&
                request.getAgreementDateFrom() != null &&
                (request.getAgreementDateFrom().after(request.getAgreementDateTo()) ||
                        request.getAgreementDateTo().getTime() == request.getAgreementDateFrom().getTime()))
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_7"))
                : Optional.empty();
    }
}