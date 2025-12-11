package org.javaguru.travel.insurance.core.underwriting;

import org.javaguru.travel.insurance.dto.RiskPremium;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

    private SelectedRisksPremiumCalculator selectedRisksPremiumCalculator;

    @Mock
    private TravelCalculatePremiumRequestV1 request;

    @BeforeEach
    void setUp() {
        selectedRisksPremiumCalculator = new SelectedRisksPremiumCalculator(
                List.of(travelRiskPremiumCalculator1, travelRiskPremiumCalculator2));
    }

    @Test
    void getRiskPremiumForOneRisk() {
        when(travelRiskPremiumCalculator1.getRiskIc()).thenReturn("TRAVEL_MEDICAL");
        when(travelRiskPremiumCalculator1.calculatePremium(any())).thenReturn(BigDecimal.ONE);
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        List<RiskPremium> risks = selectedRisksPremiumCalculator.getRiskPremiumForAllSelectedRisk(request);
        assertFalse(risks.isEmpty());
        assertEquals("TRAVEL_MEDICAL", risks.getFirst().getRiskIc());
        assertEquals(BigDecimal.ONE, risks.getFirst().getPremium());
    }

    @Test
    void getRiskPremiumForTwoRisks() {
        when(travelRiskPremiumCalculator1.getRiskIc()).thenReturn("TRAVEL_MEDICAL");
        when(travelRiskPremiumCalculator1.calculatePremium(any())).thenReturn(BigDecimal.ONE);
        when(travelRiskPremiumCalculator2.getRiskIc()).thenReturn("TRAVEL_CANCELLATION");
        when(travelRiskPremiumCalculator2.calculatePremium(any())).thenReturn(BigDecimal.ONE);
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION"));
        List<RiskPremium> risks = selectedRisksPremiumCalculator.getRiskPremiumForAllSelectedRisk(request);
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
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL", "SOMETHING_STRANGE"));
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> selectedRisksPremiumCalculator.getRiskPremiumForAllSelectedRisk(request));
        assertEquals("Not supported riskIc = SOMETHING_STRANGE", exception.getMessage());
    }
}