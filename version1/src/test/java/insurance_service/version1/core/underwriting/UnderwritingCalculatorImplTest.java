package insurance_service.version1.core.underwriting;

import insurance_service.version1.dto.RiskPremium;
import insurance_service.version1.dto.v1.TravelCalculatePremiumRequestV1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UnderwritingCalculatorImplTest {

    @Mock
    private SelectedRisksPremiumCalculator selectedRisksPremiumCalculator;

    @InjectMocks
    private UnderwritingCalculatorImpl underwritingCalculatorImpl;

    @Mock
    private TravelCalculatePremiumRequestV1 request;

    @Test
    void calculateAgreementPrice() {
        List<RiskPremium> riskPremiums = List.of(
                new RiskPremium("TRAVEL_MEDICAL", BigDecimal.ONE),
                new RiskPremium("TRAVEL_EVACUATION", BigDecimal.ONE)
        );
        when(selectedRisksPremiumCalculator.getRiskPremiumForAllSelectedRisk(request)).thenReturn(riskPremiums);
        TravelPremiumCalculationResult premiumCalculationResult = underwritingCalculatorImpl.calculatePremium(request);
        assertEquals(new BigDecimal(2), premiumCalculationResult.totalPremium());
    }
}