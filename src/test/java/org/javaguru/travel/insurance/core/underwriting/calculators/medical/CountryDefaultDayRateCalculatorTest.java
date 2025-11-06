package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import org.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import org.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryDefaultDayRateCalculatorTest {

    @Mock
    private CountryDefaultDayRateRepository countryDefaultDayRateRepository;

    @InjectMocks
    private CountryDefaultDayRateCalculator countryDefaultDayRateCalculator;

    @Mock
    private TravelCalculatePremiumRequest request;

    @Test
    void shouldReturnDayRateWhenExistsCountry() {
        when(request.getCountry()).thenReturn("CANADA");
        BigDecimal expectedRate = new BigDecimal(1.5);
        CountryDefaultDayRate countryDefaultDayRate = mock(CountryDefaultDayRate.class);
        when(countryDefaultDayRate.getDefaultDayRate()).thenReturn(expectedRate);
        when(countryDefaultDayRateRepository.findByCountryIc(request.getCountry()))
                .thenReturn(Optional.of(countryDefaultDayRate));
        assertEquals(expectedRate, countryDefaultDayRateCalculator.getCountryDefaultDayPremium(request));
    }

    @Test
    void shouldThrowExceptionWhenCountryNotSupported() {
        when(request.getCountry()).thenReturn("USA");
        when(countryDefaultDayRateRepository.findByCountryIc(request.getCountry()))
                .thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> countryDefaultDayRateCalculator.getCountryDefaultDayPremium(request));
        assertEquals("Country day rate not found by country ic = " + request.getCountry(), exception.getMessage());
    }
}