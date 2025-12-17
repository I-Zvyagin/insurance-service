package insurance_service.version2.core.validation.persons;

import insurance_service.version2.core.api.dto.PersonDTO;
import insurance_service.version2.core.api.dto.ValidationErrorDTO;
import insurance_service.version2.core.validation.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidationPersonFirstNameTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @InjectMocks
    private ValidationPersonFirstName validationPersonFirstName;

    @Mock
    private PersonDTO request;

    @Mock
    private ValidationErrorDTO validationErrorDTO;


    @Test
    public void PersonFirstNameIsEmpty() {
        when(request.getPersonFirstName()).thenReturn("");
        when(validationErrorFactory.buildError("ERROR_CODE_1")).thenReturn(validationErrorDTO);
        Optional<ValidationErrorDTO> error = validationPersonFirstName.validate(request);
        assertTrue(error.isPresent());
        assertSame(validationErrorDTO, error.get());
    }

    @Test
    public void PersonFirstNameIsNull() {
        when(request.getPersonFirstName()).thenReturn(null);
        when(validationErrorFactory.buildError("ERROR_CODE_1")).thenReturn(validationErrorDTO);
        Optional<ValidationErrorDTO> error = validationPersonFirstName.validate(request);
        assertTrue(error.isPresent());
        assertSame(validationErrorDTO, error.get());
    }

    @Test
    public void PersonFirstNameIsCorrect() {
        when(request.getPersonFirstName()).thenReturn("Stan");
        Optional<ValidationErrorDTO> error = validationPersonFirstName.validate(request);
        assertTrue(error.isEmpty());
    }
}