package insurance_service.version2.core.validation;

import insurance_service.version2.core.api.dto.PersonDTO;
import insurance_service.version2.core.api.dto.PolicyDTO;
import insurance_service.version2.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.BeforeEach;
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
class TravelPolicyValidatorImplTest {
    @Mock
    private PolicyDTO policyDTO;
    @Mock
    private PersonDTO personDTO;
    @Mock
    private ValidationPersons validationPersons1;
    @Mock
    private ValidationPersons validationPersons2;
    @Mock
    private ValidationPolicy validationPolicy1;
    @Mock
    private ValidationPolicy validationPolicy2;
    @InjectMocks
    private TravelPolicyValidatorImpl validator;

    @BeforeEach
    public void setUp() {

    }

    @Test
    void validationNotThrowExceptions() {
        validator = new TravelPolicyValidatorImpl(
                List.of(validationPersons1, validationPersons2),
                List.of(validationPolicy1,validationPolicy2)
        );

        when(policyDTO.getPersons()).thenReturn(List.of(personDTO));

        when(validationPersons1.validate(personDTO)).thenReturn(Optional.empty());
        when(validationPersons1.validateList(personDTO)).thenReturn(List.of());

        when(validationPolicy1.validate(policyDTO)).thenReturn(Optional.empty());
        when(validationPolicy1.validateList(policyDTO)).thenReturn(List.of());

        when(validationPersons2.validate(personDTO)).thenReturn(Optional.empty());
        when(validationPersons2.validateList(personDTO)).thenReturn(List.of());

        when(validationPolicy2.validate(policyDTO)).thenReturn(Optional.empty());
        when(validationPolicy2.validateList(policyDTO)).thenReturn(List.of());

        assertTrue(validator.validate(policyDTO).isEmpty());
    }

    @Test
    void validationThrowOnePersonsExceptions() {
        when(policyDTO.getPersons()).thenReturn(List.of(personDTO));
        when(validationPersons1.validate(personDTO)).thenReturn(Optional.of(new ValidationErrorDTO("code", "description")));
        when(validationPersons2.validate(personDTO)).thenReturn(Optional.of(new ValidationErrorDTO("code", "description")));

        List<ValidationPersons> validationPersons = List.of(validationPersons1,validationPersons2);
        List<ValidationPolicy> validationPolicies = List.of();

        validator = new TravelPolicyValidatorImpl(
                validationPersons,
                validationPolicies
        );

        assertEquals(2, validator.validate(policyDTO).size());
    }

    @Test
    void validationThrowOnePolicyExceptions() {
        when(validationPolicy1.validate(policyDTO)).thenReturn(Optional.of(new ValidationErrorDTO("code", "description")));
        when(validationPolicy2.validate(policyDTO)).thenReturn(Optional.of(new ValidationErrorDTO("code", "description")));

        List<ValidationPersons> validationPersons = List.of();
        List<ValidationPolicy> validationPolicies = List.of(validationPolicy1,validationPolicy2);

        validator = new TravelPolicyValidatorImpl(
                validationPersons,
                validationPolicies
        );

        assertEquals(2, validator.validate(policyDTO).size());
    }

    @Test
    void validationThrowListPersonsExceptions() {
        when(policyDTO.getPersons()).thenReturn(List.of(personDTO));
        when(validationPersons1.validateList(personDTO)).thenReturn(List.of(new ValidationErrorDTO("code", "description")));
        when(validationPersons2.validateList(personDTO)).thenReturn(List.of(new ValidationErrorDTO("code", "description")));

        List<ValidationPersons> validationPersons = List.of(validationPersons1,validationPersons2);
        List<ValidationPolicy> validationPolicies = List.of();

        validator = new TravelPolicyValidatorImpl(
                validationPersons,
                validationPolicies
        );

        assertEquals(2, validator.validate(policyDTO).size());
    }

    @Test
    void validationThrowListPolicyExceptions() {
        when(validationPolicy1.validateList(policyDTO)).thenReturn(List.of(new ValidationErrorDTO("code", "description")));
        when(validationPolicy2.validateList(policyDTO)).thenReturn(List.of(new ValidationErrorDTO("code", "description")));

        List<ValidationPersons> validationPersons = List.of();
        List<ValidationPolicy> validationPolicies = List.of(validationPolicy1,validationPolicy2);

        validator = new TravelPolicyValidatorImpl(
                validationPersons,
                validationPolicies
        );

        assertEquals(2, validator.validate(policyDTO).size());
    }
}