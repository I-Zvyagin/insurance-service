package insurance_service.version2.core.validation.policy;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import insurance_service.version2.core.api.dto.PolicyDTO;
import insurance_service.version2.core.api.dto.ValidationErrorDTO;
import insurance_service.version2.core.validation.ValidationErrorFactory;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ValidationAgreementDateToNotInThePast extends ValidationPolicyImpl {

    private final ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(PolicyDTO request) {
        Date now = Date.from(java.time.ZonedDateTime.now(ZoneId.systemDefault()).toInstant());
        return (request.getAgreementDateTo() != null &&
                request.getAgreementDateTo().before(now))
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_6"))
                : Optional.empty();
    }
}