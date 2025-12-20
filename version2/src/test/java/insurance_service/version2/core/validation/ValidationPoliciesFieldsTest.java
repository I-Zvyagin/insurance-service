package insurance_service.version2.core.validation;

import insurance_service.version2.core.api.dto.PolicyDTO;
import insurance_service.version2.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidationPoliciesFieldsTest {

    @Mock
    private PolicyDTO policyDTO;
    @Mock
    private ValidationPolicy validationPolicy1;
    @Mock
    private ValidationPolicy validationPolicy2;
    @InjectMocks
    private ValidationPoliciesFields validator;

    @Test
    void validationNotThrowExceptions() {
        validator = new ValidationPoliciesFields(List.of(validationPolicy1,validationPolicy2));

        when(validationPolicy1.validate(policyDTO)).thenReturn(Optional.empty());
        when(validationPolicy1.validateList(policyDTO)).thenReturn(List.of());

        when(validationPolicy2.validate(policyDTO)).thenReturn(Optional.empty());
        when(validationPolicy2.validateList(policyDTO)).thenReturn(List.of());

        assertTrue(validator.collectPolicyErrors(policyDTO).isEmpty());
    }

    @Test
    void validationThrowOnePolicyExceptions() {
        when(validationPolicy1.validate(policyDTO)).thenReturn(Optional.of(new ValidationErrorDTO("code", "description")));
        when(validationPolicy2.validate(policyDTO)).thenReturn(Optional.of(new ValidationErrorDTO("code", "description")));

        validator = new ValidationPoliciesFields(List.of(validationPolicy1,validationPolicy2));

        assertEquals(2, validator.collectPolicyErrors(policyDTO).size());
    }

    @Test
    void validationThrowListPolicyExceptions() {
        when(validationPolicy1.validateList(policyDTO)).thenReturn(List.of(new ValidationErrorDTO("code", "description")));
        when(validationPolicy2.validateList(policyDTO)).thenReturn(List.of(new ValidationErrorDTO("code", "description")));

        validator = new ValidationPoliciesFields(List.of(validationPolicy1,validationPolicy2));

        assertEquals(2, validator.collectPolicyErrors(policyDTO).size());
    }
}