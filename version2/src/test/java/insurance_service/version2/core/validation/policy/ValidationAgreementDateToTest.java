package insurance_service.version2.core.validation.policy;

import insurance_service.version2.core.api.dto.PolicyDTO;
import insurance_service.version2.core.api.dto.ValidationErrorDTO;
import insurance_service.version2.core.validation.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidationAgreementDateToTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @InjectMocks
    private ValidationAgreementDateTo validationAgreementDateTo;

    @Mock
    private PolicyDTO request;

    @Mock
    private ValidationErrorDTO validationError;

    @Test
    public void AgreementDateToIsNull() {
        when(request.getAgreementDateTo()).thenReturn(null);
        when(validationErrorFactory.buildError("ERROR_CODE_5")).thenReturn(validationError);
        Optional<ValidationErrorDTO> error = validationAgreementDateTo.validate(request);
        assertTrue(error.isPresent());
        assertSame(validationError, error.get());
    }

    @Test
    public void AgreementDateToIsCorrect() {
        when(request.getAgreementDateTo()).thenReturn(new Date(126, 7, 29));
        Optional<ValidationErrorDTO> error = validationAgreementDateTo.validate(request);
        assertTrue(error.isEmpty());
    }
}