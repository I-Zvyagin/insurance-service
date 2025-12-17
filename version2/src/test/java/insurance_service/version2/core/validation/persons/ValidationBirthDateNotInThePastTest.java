package insurance_service.version2.core.validation.persons;

import insurance_service.version2.core.api.dto.PersonDTO;
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
class ValidationBirthDateNotInThePastTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @InjectMocks
    private ValidationBirthDateNotInThePast validationBirthDateNotInThePast;

    @Mock
    private PersonDTO request;

    @Mock
    private ValidationErrorDTO validationErrorDTO;


    @Test
    public void BirthDateInTheFuture() {
        when(request.getPersonBirthDate()).thenReturn(new Date(130, 8, 19));
        when(validationErrorFactory.buildError("ERROR_CODE_12")).thenReturn(validationErrorDTO);
        Optional<ValidationErrorDTO> error = validationBirthDateNotInThePast.validate(request);
        assertTrue(error.isPresent());
        assertSame(validationErrorDTO, error.get());
    }

    @Test
    public void BirthDateInThePast() {
        when(request.getPersonBirthDate()).thenReturn(new Date(90, 8, 19));
        Optional<ValidationErrorDTO> error = validationBirthDateNotInThePast.validate(request);
        assertTrue(error.isEmpty());
    }
}