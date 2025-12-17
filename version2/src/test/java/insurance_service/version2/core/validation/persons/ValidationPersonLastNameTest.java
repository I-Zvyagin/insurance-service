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
class ValidationPersonLastNameTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @InjectMocks
    private ValidationPersonLastName validationPersonLastName;

    @Mock
    private PersonDTO request;

    @Mock
    private ValidationErrorDTO validationErrorDTO;


    @Test
    public void PersonLastNameIsEmpty() {
        when(request.getPersonLastName()).thenReturn("");
        when(validationErrorFactory.buildError("ERROR_CODE_2")).thenReturn(validationErrorDTO);
        Optional<ValidationErrorDTO> error = validationPersonLastName.validate(request);
        assertTrue(error.isPresent());
        assertSame(validationErrorDTO, error.get());
    }

    @Test
    public void PersonLastNameIsNull() {
        when(request.getPersonLastName()).thenReturn(null);
        when(validationErrorFactory.buildError("ERROR_CODE_2")).thenReturn(validationErrorDTO);
        Optional<ValidationErrorDTO> error = validationPersonLastName.validate(request);
        assertTrue(error.isPresent());
        assertSame(validationErrorDTO, error.get());
    }

    @Test
    public void PersonLastNameIsCorrect() {
        when(request.getPersonLastName()).thenReturn("Lee");
        Optional<ValidationErrorDTO> error = validationPersonLastName.validate(request);
        assertTrue(error.isEmpty());
    }

}