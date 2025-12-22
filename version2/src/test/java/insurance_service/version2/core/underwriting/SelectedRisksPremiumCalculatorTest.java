package insurance_service.version2.core.underwriting;

import insurance_service.version2.core.api.dto.PersonDTO;
import insurance_service.version2.core.api.dto.PolicyDTO;
import insurance_service.version2.core.api.dto.RiskDTO;
import org.junit.jupiter.api.BeforeEach;
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
class SelectedRisksPremiumCalculatorTest {

    @Mock
    private TravelRiskPremiumCalculator travelRiskPremiumCalculator1;
    @Mock
    private TravelRiskPremiumCalculator travelRiskPremiumCalculator2;
    @InjectMocks
    private SelectedRisksPremiumCalculator selectedRisksPremiumCalculator;
    @Mock
    private PolicyDTO policyDTO;
    @Mock
    private PersonDTO personDTO;

    @BeforeEach
    void setUp() {
        selectedRisksPremiumCalculator = new SelectedRisksPremiumCalculator(
                List.of(travelRiskPremiumCalculator1, travelRiskPremiumCalculator2));
    }

    @Test
    void getRiskPremiumForOneRisk() {
        when(travelRiskPremiumCalculator1.getRiskIc()).thenReturn("TRAVEL_MEDICAL");
        when(travelRiskPremiumCalculator1.calculatePremium(any(), any())).thenReturn(BigDecimal.ONE);
        when(policyDTO.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        List<RiskDTO> risks = selectedRisksPremiumCalculator.getRiskPremiumForAllSelectedRisk(policyDTO, personDTO);
        assertFalse(risks.isEmpty());
        assertEquals("TRAVEL_MEDICAL", risks.getFirst().getRiskIc());
        assertEquals(BigDecimal.ONE, risks.getFirst().getPremium());
    }

    @Test
    void getRiskPremiumForTwoRisks() {
        when(travelRiskPremiumCalculator1.getRiskIc()).thenReturn("TRAVEL_MEDICAL");
        when(travelRiskPremiumCalculator1.calculatePremium(any(), any())).thenReturn(BigDecimal.ONE);
        when(travelRiskPremiumCalculator2.getRiskIc()).thenReturn("TRAVEL_CANCELLATION");
        when(travelRiskPremiumCalculator2.calculatePremium(any(), any())).thenReturn(BigDecimal.ONE);
        when(policyDTO.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION"));
        List<RiskDTO> risks = selectedRisksPremiumCalculator.getRiskPremiumForAllSelectedRisk(policyDTO, personDTO);
        assertFalse(risks.isEmpty());
        assertEquals("TRAVEL_MEDICAL", risks.getFirst().getRiskIc());
        assertEquals(BigDecimal.ONE, risks.getFirst().getPremium());
        assertEquals("TRAVEL_CANCELLATION", risks.getLast().getRiskIc());
        assertEquals(BigDecimal.ONE, risks.getLast().getPremium());
    }

    @Test
    void getExceptionRiskTypeNotSupported() {
        when(travelRiskPremiumCalculator1.getRiskIc()).thenReturn("TRAVEL_MEDICAL");
        when(travelRiskPremiumCalculator2.getRiskIc()).thenReturn("TRAVEL_CANCELLATION");
        when(policyDTO.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL", "SOMETHING_STRANGE"));
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> selectedRisksPremiumCalculator.getRiskPremiumForAllSelectedRisk(policyDTO, personDTO));
        assertEquals("Not supported riskIc = SOMETHING_STRANGE", exception.getMessage());
    }
}