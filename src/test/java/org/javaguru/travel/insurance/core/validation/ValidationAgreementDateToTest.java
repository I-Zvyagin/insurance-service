package org.javaguru.travel.insurance.core.validation;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
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
class ValidationAgreementDateToTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @InjectMocks
    private ValidationAgreementDateTo validationAgreementDateTo;

    @Mock
    private TravelCalculatePremiumRequest request;

    @Mock
    private ValidationError validationError;

    @Test
    public void AgreementDateToIsNull() {
        when(request.getAgreementDateTo()).thenReturn(null);
        when(validationErrorFactory.buildError("ERROR_CODE_5")).thenReturn(validationError);
        Optional<ValidationError> error = validationAgreementDateTo.validate(request);
        assertTrue(error.isPresent());
        assertSame(validationError, error.get());
    }

    @Test
    public void AgreementDateToIsCorrect() {
        when(request.getAgreementDateTo()).thenReturn(new Date(126, 7, 29));
        Optional<ValidationError> error = validationAgreementDateTo.validate(request);
        assertTrue(error.isEmpty());
    }
}