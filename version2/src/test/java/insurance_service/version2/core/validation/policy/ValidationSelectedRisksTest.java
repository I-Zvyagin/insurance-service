package insurance_service.version2.core.validation.policy;

import insurance_service.version2.core.api.dto.PolicyDTO;
import insurance_service.version2.core.api.dto.ValidationErrorDTO;
import insurance_service.version2.core.domain.ClassifierValue;
import insurance_service.version2.core.repositories.ClassifierValueRepository;
import insurance_service.version2.core.validation.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidationSelectedRisksTest {

    @Mock
    private ClassifierValueRepository classifierValueRepository;
    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @InjectMocks
    private ValidationSelectedRisks validationSelectedRisks;

    @Mock
    private PolicyDTO request;

    @Test
    public void ValidationSelectedRisksIsNull() {
        when(request.getSelectedRisks()).thenReturn(null);
        assertTrue(validationSelectedRisks.validateList(request).isEmpty());
        verifyNoInteractions(classifierValueRepository, validationErrorFactory);
    }

    @Test
    public void shouldValidateWithoutErrors() {
        PolicyDTO request = mock(PolicyDTO.class);
        when(request.getSelectedRisks()).thenReturn(List.of("RISK_IC_1", "RISK_IC_2"));
        when(classifierValueRepository.findByClassifierTitleAndIc("RISK_TYPE", "RISK_IC_1"))
                .thenReturn(Optional.of(mock(ClassifierValue.class)));
        when(classifierValueRepository.findByClassifierTitleAndIc("RISK_TYPE", "RISK_IC_2"))
                .thenReturn(Optional.of(mock(ClassifierValue.class)));
        assertTrue(validationSelectedRisks.validateList(request).isEmpty());
    }

    @Test
    public void shouldValidateWithErrors() {
        PolicyDTO request = mock(PolicyDTO.class);
        when(request.getSelectedRisks()).thenReturn(List.of("RISK_IC_1", "RISK_IC_2"));
        when(classifierValueRepository.findByClassifierTitleAndIc("RISK_TYPE", "RISK_IC_1"))
                .thenReturn(Optional.empty());
        when(classifierValueRepository.findByClassifierTitleAndIc("RISK_TYPE", "RISK_IC_2"))
                .thenReturn(Optional.empty());

        ValidationErrorDTO error = mock(ValidationErrorDTO.class);
        when(validationErrorFactory.buildError(eq("ERROR_CODE_9"), anyList())).thenReturn(error);

        assertEquals(2, validationSelectedRisks.validateList(request).size());
    }
}