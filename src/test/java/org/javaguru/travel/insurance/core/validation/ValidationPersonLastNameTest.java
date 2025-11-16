package org.javaguru.travel.insurance.core.validation;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
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
    private TravelCalculatePremiumRequest request;

    @Mock
    private ValidationError validationError;


    @Test
    public void PersonLastNameIsEmpty() {
        when(request.getPersonLastName()).thenReturn("");
        when(validationErrorFactory.buildError("ERROR_CODE_2")).thenReturn(validationError);
        Optional<ValidationError> error = validationPersonLastName.validate(request);
        assertTrue(error.isPresent());
        assertSame(validationError, error.get());
    }

    @Test
    public void PersonLastNameIsNull() {
        when(request.getPersonLastName()).thenReturn(null);
        when(validationErrorFactory.buildError("ERROR_CODE_2")).thenReturn(validationError);
        Optional<ValidationError> error = validationPersonLastName.validate(request);
        assertTrue(error.isPresent());
        assertSame(validationError, error.get());
    }

    @Test
    public void PersonLastNameIsCorrect() {
        when(request.getPersonLastName()).thenReturn("Lee");
        Optional<ValidationError> error = validationPersonLastName.validate(request);
        assertTrue(error.isEmpty());
    }

}