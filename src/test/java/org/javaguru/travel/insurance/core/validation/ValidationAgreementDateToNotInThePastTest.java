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
class ValidationAgreementDateToNotInThePastTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @InjectMocks
    private ValidationAgreementDateToNotInThePast validationAgreementDateToNotInThePast;

    @Mock
    private TravelCalculatePremiumRequest request;

    @Mock
    private ValidationError validationError;


    @Test
    public void AgreementDateToInThePast() {
        when(request.getAgreementDateTo()).thenReturn(new Date(125, 7, 25));
        when(validationErrorFactory.buildError("ERROR_CODE_6")).thenReturn(validationError);
        Optional<ValidationError> error = validationAgreementDateToNotInThePast.validate(request);
        assertTrue(error.isPresent());
        assertSame(validationError, error.get());
    }

    @Test
    public void AgreementDateToInTheFuture() {
        when(request.getAgreementDateTo()).thenReturn(new Date(126, 7, 25));
        Optional<ValidationError> error = validationAgreementDateToNotInThePast.validate(request);
        assertTrue(error.isEmpty());
    }
}