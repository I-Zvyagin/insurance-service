package insurance_service.version1.core.validation.policy;

import insurance_service.version1.core.validation.ValidationErrorFactory;
import insurance_service.version1.dto.ValidationError;
import insurance_service.version1.dto.v1.TravelCalculatePremiumRequestV1;
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
class ValidationAgreementDateFromTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @InjectMocks
    private ValidationAgreementDateFrom validationAgreementDateFrom;

    @Mock
    private TravelCalculatePremiumRequestV1 request;

    @Mock
    private ValidationError validationError;

    @Test
    public void AgreementDateFromIsNull() {
        when(request.getAgreementDateFrom()).thenReturn(null);
        when(validationErrorFactory.buildError("ERROR_CODE_3")).thenReturn(validationError);
        Optional<ValidationError> error = validationAgreementDateFrom.validate(request);
        assertTrue(error.isPresent());
        assertSame(validationError, error.get());
    }

    @Test
    public void AgreementDateFromIsCorrect() {
        when(request.getAgreementDateFrom()).thenReturn(new Date(126, 7, 25));
        Optional<ValidationError> error = validationAgreementDateFrom.validate(request);
        assertTrue(error.isEmpty());
    }
}