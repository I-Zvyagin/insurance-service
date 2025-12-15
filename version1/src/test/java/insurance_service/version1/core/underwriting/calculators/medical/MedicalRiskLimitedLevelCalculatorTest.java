package insurance_service.version1.core.underwriting.calculators.medical;

import insurance_service.version1.core.domain.MedicalRiskLimitedLevel;
import insurance_service.version1.core.repositories.MedicalRiskLimitedLevelRepository;
import insurance_service.version1.dto.v1.TravelCalculatePremiumRequestV1;
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
class MedicalRiskLimitedLevelCalculatorTest {

    @Mock
    private MedicalRiskLimitedLevelRepository medicalRiskLimitedLevelRepository;

    @InjectMocks
    private MedicalRiskLimitedLevelCalculator medicalRiskLimitedLevelCalculator;

    @Mock
    private TravelCalculatePremiumRequestV1 request;

    @Test
    void shouldReturnCoefficientIfMedicalRiskLimitedLevelIcSupported() {
        when(request.getMedicalRiskLimitLevel()).thenReturn("LIMIT_20000");
        BigDecimal expectedCoefficient = new BigDecimal(1.5);
        MedicalRiskLimitedLevel medicalRiskLimitedLevel = mock(MedicalRiskLimitedLevel.class);
        when(medicalRiskLimitedLevel.getCoefficient()).thenReturn(expectedCoefficient);
        when(medicalRiskLimitedLevelRepository
                .findByMedicalRiskLimitedLevelIc(request.getMedicalRiskLimitLevel()))
                .thenReturn(Optional.of(medicalRiskLimitedLevel));
        assertEquals(expectedCoefficient, medicalRiskLimitedLevelCalculator.getInsuranceLimitCoefficient(request));
    }

    @Test
    void shouldThrowExceptionIfMedicalRiskLimitedLevelIcNotSupported() {
        when(request.getMedicalRiskLimitLevel()).thenReturn("LIMIT_500");
        when(medicalRiskLimitedLevelRepository
                .findByMedicalRiskLimitedLevelIc(request.getMedicalRiskLimitLevel()))
                .thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> medicalRiskLimitedLevelCalculator.getInsuranceLimitCoefficient(request));
        assertEquals("Coefficient not found by medical risk limited level ic = " + request.getMedicalRiskLimitLevel(), exception.getMessage());
    }
}