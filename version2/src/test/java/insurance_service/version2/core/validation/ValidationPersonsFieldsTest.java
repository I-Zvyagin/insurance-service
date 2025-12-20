package insurance_service.version2.core.validation;

import insurance_service.version2.core.api.dto.PersonDTO;
import insurance_service.version2.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidationPersonsFieldsTest {

    @Mock
    private PersonDTO personDTO;
    @Mock
    private ValidationPersons validationPersons1;
    @Mock
    private ValidationPersons validationPersons2;
    @InjectMocks
    private ValidationPersonsFields validator;

    @Test
    void validationNotThrowExceptions() {
        validator = new ValidationPersonsFields(List.of(validationPersons1,validationPersons2));

        when(validationPersons1.validate(personDTO)).thenReturn(Optional.empty());
        when(validationPersons1.validateList(personDTO)).thenReturn(List.of());

        when(validationPersons2.validate(personDTO)).thenReturn(Optional.empty());
        when(validationPersons2.validateList(personDTO)).thenReturn(List.of());

        assertTrue(validator.collectPersonsError(personDTO).isEmpty());
    }

    @Test
    void validationThrowOnePersonsExceptions() {
        when(validationPersons1.validate(personDTO)).thenReturn(Optional.of(new ValidationErrorDTO("code", "description")));
        when(validationPersons2.validate(personDTO)).thenReturn(Optional.of(new ValidationErrorDTO("code", "description")));

        validator = new ValidationPersonsFields(List.of(validationPersons1,validationPersons2));

        assertEquals(2, validator.collectPersonsError(personDTO).size());
    }

    @Test
    void validationThrowListPersonsExceptions() {
        when(validationPersons1.validateList(personDTO)).thenReturn(List.of(new ValidationErrorDTO("code", "description")));
        when(validationPersons2.validateList(personDTO)).thenReturn(List.of(new ValidationErrorDTO("code", "description")));

        validator = new ValidationPersonsFields(List.of(validationPersons1,validationPersons2));

        assertEquals(2, validator.collectPersonsError(personDTO).size());
    }
}