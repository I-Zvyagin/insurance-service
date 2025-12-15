package insurance_service.version1.core.validation.persons;

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
class ValidationBirthDateNotInThePastTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @InjectMocks
    private ValidationBirthDateNotInThePast validationBirthDateNotInThePast;

    @Mock
    private TravelCalculatePremiumRequestV1 request;

    @Mock
    private ValidationError validationError;


    @Test
    public void BirthDateInTheFuture() {
        when(request.getBirthDate()).thenReturn(new Date(130, 8, 19));
        when(validationErrorFactory.buildError("ERROR_CODE_12")).thenReturn(validationError);
        Optional<ValidationError> error = validationBirthDateNotInThePast.validate(request);
        assertTrue(error.isPresent());
        assertSame(validationError, error.get());
    }

    @Test
    public void BirthDateInThePast() {
        when(request.getBirthDate()).thenReturn(new Date(90, 8, 19));
        Optional<ValidationError> error = validationBirthDateNotInThePast.validate(request);
        assertTrue(error.isEmpty());
    }
}