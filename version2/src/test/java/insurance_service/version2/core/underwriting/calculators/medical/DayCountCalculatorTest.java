package insurance_service.version2.core.underwriting.calculators.medical;

import insurance_service.version2.core.api.dto.PolicyDTO;
import insurance_service.version2.core.util.DateTimeUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DayCountCalculatorTest {

    @Mock
    private DateTimeUtil dateTimeUtil;

    @InjectMocks
    private DayCountCalculator dayCountCalculator;

    @Mock
    private PolicyDTO policyDTO;

    @Test
    void shouldFindDayCountBetweenTwoDifferentDates(){
        when(policyDTO.getAgreementDateFrom()).thenReturn(new Date(130,7,20));
        when(policyDTO.getAgreementDateTo()).thenReturn(new Date(130,7,25));
        when(dateTimeUtil.getPeriodInDays(policyDTO.getAgreementDateFrom(),policyDTO.getAgreementDateTo()))
                .thenReturn(5L);
        assertEquals(new BigDecimal(5), dayCountCalculator.getDayCount(policyDTO));
    }
}