package insurance_service.version2.core.underwriting.calculators.medical;

import insurance_service.version2.core.api.dto.PolicyDTO;
import insurance_service.version2.core.domain.MedicalRiskLimitedLevel;
import insurance_service.version2.core.repositories.MedicalRiskLimitedLevelRepository;
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
class MedicalRiskLimitedLevelCalculatorTest {

    @Mock
    private MedicalRiskLimitedLevelRepository medicalRiskLimitedLevelRepository;

    @InjectMocks
    private MedicalRiskLimitedLevelCalculator medicalRiskLimitedLevelCalculator;

    @Mock
    private PolicyDTO policyDTO;

    @Test
    void shouldReturnCoefficientIfMedicalRiskLimitedLevelIcSupported() {
        when(policyDTO.getMedicalRiskLimitLevel()).thenReturn("LIMIT_20000");
        BigDecimal expectedCoefficient = new BigDecimal(1.5);
        MedicalRiskLimitedLevel medicalRiskLimitedLevel = mock(MedicalRiskLimitedLevel.class);
        when(medicalRiskLimitedLevel.getCoefficient()).thenReturn(expectedCoefficient);
        when(medicalRiskLimitedLevelRepository
                .findByMedicalRiskLimitedLevelIc(policyDTO.getMedicalRiskLimitLevel()))
                .thenReturn(Optional.of(medicalRiskLimitedLevel));
        assertEquals(expectedCoefficient, medicalRiskLimitedLevelCalculator.getInsuranceLimitCoefficient(policyDTO));
    }

    @Test
    void shouldThrowExceptionIfMedicalRiskLimitedLevelIcNotSupported() {
        when(policyDTO.getMedicalRiskLimitLevel()).thenReturn("LIMIT_500");
        when(medicalRiskLimitedLevelRepository
                .findByMedicalRiskLimitedLevelIc(policyDTO.getMedicalRiskLimitLevel()))
                .thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> medicalRiskLimitedLevelCalculator.getInsuranceLimitCoefficient(policyDTO));
        assertEquals("Coefficient not found by medical risk limited level ic = " + policyDTO.getMedicalRiskLimitLevel(), exception.getMessage());
    }
}