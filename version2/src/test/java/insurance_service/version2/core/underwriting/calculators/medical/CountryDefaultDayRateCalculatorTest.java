package insurance_service.version2.core.underwriting.calculators.medical;

import insurance_service.version2.core.api.dto.PolicyDTO;
import insurance_service.version2.core.domain.CountryDefaultDayRate;
import insurance_service.version2.core.repositories.CountryDefaultDayRateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryDefaultDayRateCalculatorTest {

    @Mock
    private CountryDefaultDayRateRepository countryDefaultDayRateRepository;

    @InjectMocks
    private CountryDefaultDayRateCalculator countryDefaultDayRateCalculator;

    @Mock
    private PolicyDTO policyDTO;

    @Test
    void shouldReturnDayRateWhenExistsCountry() {
        when(policyDTO.getCountry()).thenReturn("CANADA");
        BigDecimal expectedRate = new BigDecimal(1.5);
        CountryDefaultDayRate countryDefaultDayRate = mock(CountryDefaultDayRate.class);
        when(countryDefaultDayRate.getDefaultDayRate()).thenReturn(expectedRate);
        when(countryDefaultDayRateRepository.findByCountryIc(policyDTO.getCountry()))
                .thenReturn(Optional.of(countryDefaultDayRate));
        assertEquals(expectedRate, countryDefaultDayRateCalculator.getCountryDefaultDayPremium(policyDTO));
    }

    @Test
    void shouldThrowExceptionWhenCountryNotSupported() {
        when(policyDTO.getCountry()).thenReturn("USA");
        when(countryDefaultDayRateRepository.findByCountryIc(policyDTO.getCountry()))
                .thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> countryDefaultDayRateCalculator.getCountryDefaultDayPremium(policyDTO));
        assertEquals("Country day rate not found by country ic = " + policyDTO.getCountry(), exception.getMessage());
    }
}