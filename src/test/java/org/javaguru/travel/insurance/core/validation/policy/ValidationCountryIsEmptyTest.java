package org.javaguru.travel.insurance.core.validation.policy;

import org.javaguru.travel.insurance.core.validation.ValidationErrorFactory;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class ValidationCountryIsEmptyTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    private ValidationEmptyCountry validationEmptyCountry;

    @Mock
    private TravelCalculatePremiumRequestV1 request;

    @Test
    public void countryIsNull() {
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION"));
        when(request.getCountry()).thenReturn(null);
        when(validationErrorFactory.buildError("ERROR_CODE_10")).thenReturn(
                new ValidationError("ERROR_CODE_10",
                        "Country must be provided when TRAVEL_MEDICAL is selected")
        );
        Optional<ValidationError> error = validationEmptyCountry.validate(request);
        assertTrue(error.isPresent());
        assertEquals("ERROR_CODE_10", error.get().errorCode());
        assertEquals("Country must be provided when TRAVEL_MEDICAL is selected", error.get().description());
    }

    @Test
    public void countryIsEmpty() {
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION"));
        when(request.getCountry()).thenReturn("");
        when(validationErrorFactory.buildError("ERROR_CODE_10")).thenReturn(
                new ValidationError("ERROR_CODE_10",
                        "Country must be provided when TRAVEL_MEDICAL is selected")
        );
        Optional<ValidationError> error = validationEmptyCountry.validate(request);
        assertTrue(error.isPresent());
        assertEquals("ERROR_CODE_10", error.get().errorCode());
        assertEquals("Country must be provided when TRAVEL_MEDICAL is selected", error.get().description());
    }

    @Test
    public void countryFilled() {
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION"));
        when(request.getCountry()).thenReturn("JAPAN");
        Optional<ValidationError> error = validationEmptyCountry.validate(request);
        assertTrue(error.isEmpty());
    }
}