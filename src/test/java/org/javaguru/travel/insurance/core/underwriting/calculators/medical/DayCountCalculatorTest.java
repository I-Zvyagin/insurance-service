package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DayCountCalculatorTest {

    @Mock
    private DateTimeUtil dateTimeUtil;

    @InjectMocks
    private DayCountCalculator dayCountCalculator;

    @Mock
    private TravelCalculatePremiumRequest request;

    @Test
    void shouldFindDayCountBetweenTwoDifferentDates(){
        when(request.getAgreementDateFrom()).thenReturn(new Date(130,7,20));
        when(request.getAgreementDateTo()).thenReturn(new Date(130,7,25));
        when(dateTimeUtil.getPeriodInDays(request.getAgreementDateFrom(),request.getAgreementDateTo()))
                .thenReturn(5L);
        assertEquals(new BigDecimal(5), dayCountCalculator.getDayCount(request));
    }
}