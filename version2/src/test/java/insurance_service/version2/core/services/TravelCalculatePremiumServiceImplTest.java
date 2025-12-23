package insurance_service.version2.core.services;

import insurance_service.version2.core.api.command.TravelCalculatePremiumCoreCommand;
import insurance_service.version2.core.api.command.TravelCalculatePremiumCoreResult;
import insurance_service.version2.core.api.dto.PersonDTO;
import insurance_service.version2.core.api.dto.PolicyDTO;
import insurance_service.version2.core.api.dto.RiskDTO;
import insurance_service.version2.core.api.dto.ValidationErrorDTO;
import insurance_service.version2.core.underwriting.TravelPremiumCalculationResult;
import insurance_service.version2.core.underwriting.UnderwritingCalculator;
import insurance_service.version2.core.validation.TravelPolicyValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @InjectMocks
    private TravelCalculatePremiumServiceImpl travelCalculatePremiumServiceImpl;
    @Mock
    private TravelPolicyValidator travelPolicyValidator;
    @Mock
    private UnderwritingCalculator underwritingCalculator;
    @Test
    public void shouldCalculateForTwoPersons() {
        PolicyDTO policyDTO = new PolicyDTO();
        PersonDTO personDTO1 = new PersonDTO();
        PersonDTO personDTO2 = new PersonDTO();
        policyDTO.setPersons(List.of(personDTO1,personDTO2));

        when(travelPolicyValidator.validate(policyDTO)).thenReturn(Collections.emptyList());

        TravelCalculatePremiumCoreCommand command = new TravelCalculatePremiumCoreCommand(policyDTO);

        RiskDTO riskDTO1 = new RiskDTO("riscIc", BigDecimal.ONE);
        RiskDTO riskDTO2 = new RiskDTO("riscIc", BigDecimal.ONE);
        when(underwritingCalculator.calculatePremium(policyDTO, personDTO1))
                .thenReturn(new TravelPremiumCalculationResult(BigDecimal.ONE, List.of(riskDTO1)));
        when(underwritingCalculator.calculatePremium(policyDTO, personDTO2))
                .thenReturn(new TravelPremiumCalculationResult(BigDecimal.ONE, List.of(riskDTO2)));

        TravelCalculatePremiumCoreResult result = travelCalculatePremiumServiceImpl.calculatePremium(command);

        assertEquals(BigDecimal.TWO, result.getPolicyDTO().getAgreementPremium());
        verify(underwritingCalculator, times(2)).calculatePremium(eq(policyDTO), any(PersonDTO.class));
    }

    @Test
    public void shouldCalculateForOnePerson() {
        PolicyDTO policyDTO = new PolicyDTO();
        PersonDTO personDTO = new PersonDTO();
        policyDTO.setPersons(List.of(personDTO));

        when(travelPolicyValidator.validate(policyDTO)).thenReturn(Collections.emptyList());

        TravelCalculatePremiumCoreCommand command = new TravelCalculatePremiumCoreCommand(policyDTO);

        RiskDTO riskDTO = new RiskDTO("riscIc", BigDecimal.ONE);
        when(underwritingCalculator.calculatePremium(policyDTO, personDTO))
                .thenReturn(new TravelPremiumCalculationResult(BigDecimal.ONE, List.of(riskDTO)));

        TravelCalculatePremiumCoreResult result = travelCalculatePremiumServiceImpl.calculatePremium(command);

        assertEquals(BigDecimal.ONE, result.getPolicyDTO().getAgreementPremium());
        verify(underwritingCalculator, times(1)).calculatePremium(eq(policyDTO), any(PersonDTO.class));
    }

    @Test
    public void shouldReturnError() {
        PolicyDTO policyDTO = new PolicyDTO();
        ValidationErrorDTO error = new ValidationErrorDTO("errorCode", "description");

        when(travelPolicyValidator.validate(policyDTO)).thenReturn(List.of(error));

        TravelCalculatePremiumCoreCommand command = new TravelCalculatePremiumCoreCommand(policyDTO);
        TravelCalculatePremiumCoreResult result = travelCalculatePremiumServiceImpl.calculatePremium(command);

        assertEquals(1, result.getErrors().size());
        assertEquals("errorCode", result.getErrors().get(0).getErrorCode());
        assertEquals("description", result.getErrors().get(0).getDescription());
        verify(underwritingCalculator, never()).calculatePremium(any(), any());
    }
}