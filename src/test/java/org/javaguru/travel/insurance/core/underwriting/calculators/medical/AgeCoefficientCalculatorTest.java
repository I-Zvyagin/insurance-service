package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import org.javaguru.travel.insurance.core.domain.AgeCoefficient;
import org.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import org.javaguru.travel.insurance.core.util.AgeCalculatorUtil;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgeCoefficientCalculatorTest {

    @Mock
    private AgeCalculatorUtil ageCalculatorUtil;

    @Mock
    private AgeCoefficientRepository ageCoefficientRepository;

    @InjectMocks
    private AgeCoefficientCalculator ageCoefficientCalculator;

    @Mock
    private TravelCalculatePremiumRequestV1 request;

    @Test
    void shouldCalculateAgeCoefficientWithCorrectBirthDate() {
        BigDecimal expectedCoefficient = new BigDecimal(1.5);
        Date birthDate = new Date(90, 8, 19);
        int expectedAge = 35;

        when(request.getBirthDate()).thenReturn(birthDate);
        when(ageCalculatorUtil.getAge(birthDate)).thenReturn(expectedAge);

        AgeCoefficient ageCoefficient = mock(AgeCoefficient.class);
        when(ageCoefficient.getCoefficient()).thenReturn(expectedCoefficient);

        when(ageCoefficientRepository.findCoefficient(expectedAge))
                .thenReturn(Optional.of(ageCoefficient));
        assertEquals(expectedCoefficient, ageCoefficientCalculator.getAgeCoefficient(request));
    }
}