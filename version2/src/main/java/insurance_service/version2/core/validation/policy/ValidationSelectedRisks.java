package insurance_service.version2.core.validation.policy;

import insurance_service.version2.core.util.Placeholder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import insurance_service.version2.core.api.dto.PolicyDTO;
import insurance_service.version2.core.api.dto.ValidationErrorDTO;
import insurance_service.version2.core.repositories.ClassifierValueRepository;
import insurance_service.version2.core.validation.ValidationErrorFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ValidationSelectedRisks extends ValidationPolicyImpl {

    private final ValidationErrorFactory validationErrorFactory;
    private final ClassifierValueRepository classifierValueRepository;

    @Override
    public List<ValidationErrorDTO> validateList(PolicyDTO request) {
        return (request.getSelectedRisks() != null)
                ? validateSelectedRisks(request)
                : List.of();
    }

    private List<ValidationErrorDTO> validateSelectedRisks(PolicyDTO request) {
        return request.getSelectedRisks().stream()
                .map(this::validateRiskIc)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    private Optional<ValidationErrorDTO> validateRiskIc(String riskIc) {
        return !existInDatabase(riskIc)
                ? Optional.of(buildValidationError(riskIc))
                : Optional.empty();
    }

    private ValidationErrorDTO buildValidationError(String riskIc) {
        Placeholder placeholder = new Placeholder("NOT_EXISTING_RISK_TYPE", riskIc);
        return validationErrorFactory.buildError("ERROR_CODE_9", List.of(placeholder));
    }

    private boolean existInDatabase(String riskIc) {
        return classifierValueRepository.findByClassifierTitleAndIc("RISK_TYPE", riskIc).isPresent();
    }
}
