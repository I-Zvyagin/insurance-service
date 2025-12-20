package insurance_service.version2.core.validation;

import insurance_service.version2.core.api.dto.PersonDTO;
import insurance_service.version2.core.api.dto.PolicyDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelPolicyValidatorImplTest {

    @Mock
    private PolicyDTO policyDTO;
    @Mock
    private PersonDTO personDTO;
    @Mock
    private ValidationPoliciesFields validationPoliciesFields;
    @Mock
    private ValidationPersonsFields validationPersonsFields;
    @InjectMocks
    private TravelPolicyValidatorImpl validator;

    @Test
    void validationNotThrowExceptions() {
        validator = new TravelPolicyValidatorImpl(
                validationPersonsFields,
                validationPoliciesFields
        );

        when(policyDTO.getPersons()).thenReturn(List.of(personDTO));

        when(validationPersonsFields.collectPersonsError(personDTO)).thenReturn(List.of());
        when(validationPoliciesFields.collectPolicyErrors(policyDTO)).thenReturn(List.of());

        assertTrue(validator.validate(policyDTO).isEmpty());
    }
}