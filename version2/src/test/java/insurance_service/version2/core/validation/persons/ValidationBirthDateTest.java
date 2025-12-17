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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidationBirthDateTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    private ValidationBirthDate validationBirthDate;

    @Mock
    private PersonDTO request;

    @Test
    public void birthDateIsNull() {
        when(request.getPersonBirthDate()).thenReturn(null);
        when(validationErrorFactory.buildError("ERROR_CODE_11")).thenReturn(
                new ValidationErrorDTO("ERROR_CODE_11",
                        "Birth date must not be empty!")
        );
        Optional<ValidationErrorDTO> error = validationBirthDate.validate(request);
        assertTrue(error.isPresent());
        assertEquals("ERROR_CODE_11", error.get().getErrorCode());
        assertEquals("Birth date must not be empty!", error.get().getDescription());
    }

    @Test
    public void birthDateFilled() {
        when(request.getPersonBirthDate()).thenReturn(new Date(90, 8, 19));
        Optional<ValidationErrorDTO> error = validationBirthDate.validate(request);
        assertTrue(error.isEmpty());
    }
}