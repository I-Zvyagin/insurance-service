package insurance_service.version2.core.validation;

import insurance_service.version2.core.api.dto.ValidationErrorDTO;
import insurance_service.version2.core.util.ErrorCodeUtil;
import insurance_service.version2.core.util.Placeholder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ValidationErrorFactory {

    private final ErrorCodeUtil errorCodeUtil;

    public ValidationErrorDTO buildError(String errorCode) {
        String errorDescription = errorCodeUtil.getErrorDescription(errorCode);
        return new ValidationErrorDTO(errorCode, errorDescription);
    }

    public ValidationErrorDTO buildError(String errorCode, List<Placeholder> placeholders) {
        String errorDescription = errorCodeUtil.getErrorDescription(errorCode, placeholders);
        return new ValidationErrorDTO(errorCode, errorDescription);
    }
}
