package org.javaguru.travel.insurance.core.validation.persons;

import org.javaguru.travel.insurance.core.validation.ValidationErrorFactory;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
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
    private TravelCalculatePremiumRequestV1 request;

    @Test
    public void birthDateIsNull() {
        when(request.getBirthDate()).thenReturn(null);
        when(validationErrorFactory.buildError("ERROR_CODE_11")).thenReturn(
                new ValidationError("ERROR_CODE_11",
                        "Birth date must not be empty!")
        );
        Optional<ValidationError> error = validationBirthDate.validate(request);
        assertTrue(error.isPresent());
        assertEquals("ERROR_CODE_11", error.get().errorCode());
        assertEquals("Birth date must not be empty!", error.get().description());
    }

    @Test
    public void birthDateFilled() {
        when(request.getBirthDate()).thenReturn(new Date(90, 8, 19));
        Optional<ValidationError> error = validationBirthDate.validate(request);
        assertTrue(error.isEmpty());
    }
}