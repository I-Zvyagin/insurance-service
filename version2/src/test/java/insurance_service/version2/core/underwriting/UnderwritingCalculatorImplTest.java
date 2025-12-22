package insurance_service.version2.core.underwriting;

import insurance_service.version2.core.api.dto.PersonDTO;
import insurance_service.version2.core.api.dto.PolicyDTO;
import insurance_service.version2.core.api.dto.RiskDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UnderwritingCalculatorImplTest {

    @Mock
    private SelectedRisksPremiumCalculator selectedRisksPremiumCalculator;
    @InjectMocks
    private UnderwritingCalculatorImpl underwritingCalculatorImpl;
    @Mock
    private PolicyDTO policyDTO;
    @Mock
    private PersonDTO personDTO;

    @Test
    void calculateAgreementPrice() {
        List<RiskDTO> riskPremiums = List.of(
                new RiskDTO("TRAVEL_MEDICAL", BigDecimal.ONE),
                new RiskDTO("TRAVEL_EVACUATION", BigDecimal.ONE)
        );
        when(selectedRisksPremiumCalculator.getRiskPremiumForAllSelectedRisk(policyDTO, personDTO)).thenReturn(riskPremiums);
        TravelPremiumCalculationResult premiumCalculationResult = underwritingCalculatorImpl.calculatePremium(policyDTO, personDTO);
        assertEquals(new BigDecimal(2), premiumCalculationResult.totalPremium());
    }
}