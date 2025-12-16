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
class ValidationDateToAfterDateFromTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @InjectMocks
    private ValidationDateToAfterDateFrom validationDateToAfterDateFrom;

    @Mock
    private PolicyDTO request;

    @Mock
    private ValidationErrorDTO validationError;


    @Test
    public void DateToBeforeDateFrom() {
        when(request.getAgreementDateFrom()).thenReturn(new Date(126, 7 , 29));
        when(request.getAgreementDateTo()).thenReturn(new Date(126, 7 , 25));
        when(validationErrorFactory.buildError("ERROR_CODE_7")).thenReturn(validationError);
        Optional<ValidationErrorDTO> error = validationDateToAfterDateFrom.validate(request);
        assertTrue(error.isPresent());
        assertSame(validationError, error.get());
    }

    @Test
    public void DateToAfterDateFrom() {
        when(request.getAgreementDateFrom()).thenReturn(new Date(126, 7 , 25));
        when(request.getAgreementDateTo()).thenReturn(new Date(126, 7 , 29));
        Optional<ValidationErrorDTO> error = validationDateToAfterDateFrom.validate(request);
        assertTrue(error.isEmpty());
    }
}