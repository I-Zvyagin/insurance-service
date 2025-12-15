package insurance_service.version1.core.validation;

import insurance_service.version1.dto.ValidationError;
import insurance_service.version1.dto.v1.TravelCalculatePremiumRequestV1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumRequestValidatorTest {

    @Mock
    private TravelCalculatePremiumRequestV1 request;
    @Mock
    private ValidationPersons validationPersons1;
    @Mock
    private ValidationPersons validationPersons2;
    @Mock
    private ValidationPolicy validationPolicy1;
    @Mock
    private ValidationPolicy validationPolicy2;
    @InjectMocks
    private TravelCalculatePremiumRequestValidatorImpl validator;

    @Test
    void validationNotThrowExceptions() {
        validator = new TravelCalculatePremiumRequestValidatorImpl(
                List.of(validationPersons1, validationPersons2),
                List.of(validationPolicy1,validationPolicy2)
        );

        when(validationPersons1.validate(request)).thenReturn(Optional.empty());
        when(validationPersons1.validateList(request)).thenReturn(List.of());

        when(validationPolicy1.validate(request)).thenReturn(Optional.empty());
        when(validationPolicy1.validateList(request)).thenReturn(List.of());

        when(validationPersons2.validate(request)).thenReturn(Optional.empty());
        when(validationPersons2.validateList(request)).thenReturn(List.of());

        when(validationPolicy2.validate(request)).thenReturn(Optional.empty());
        when(validationPolicy2.validateList(request)).thenReturn(List.of());

        assertTrue(validator.validate(request).isEmpty());
    }

    @Test
    void validationThrowOnePersonsExceptions() {
        when(validationPersons1.validate(request)).thenReturn(Optional.of(new ValidationError("code", "description")));
        when(validationPersons2.validate(request)).thenReturn(Optional.of(new ValidationError("code", "description")));

        List<ValidationPersons> validationPersons = List.of(validationPersons1,validationPersons2);
        List<ValidationPolicy> validationPolicies = List.of();

        validator = new TravelCalculatePremiumRequestValidatorImpl(
                validationPersons,
                validationPolicies
        );

        assertEquals(2, validator.validate(request).size());
    }

    @Test
    void validationThrowOnePolicyExceptions() {
        when(validationPolicy1.validate(request)).thenReturn(Optional.of(new ValidationError("code", "description")));
        when(validationPolicy2.validate(request)).thenReturn(Optional.of(new ValidationError("code", "description")));

        List<ValidationPersons> validationPersons = List.of();
        List<ValidationPolicy> validationPolicies = List.of(validationPolicy1,validationPolicy2);

        validator = new TravelCalculatePremiumRequestValidatorImpl(
                validationPersons,
                validationPolicies
        );

        assertEquals(2, validator.validate(request).size());
    }

    @Test
    void validationThrowListPersonsExceptions() {
        when(validationPersons1.validateList(request)).thenReturn(List.of(new ValidationError("code", "description")));
        when(validationPersons2.validateList(request)).thenReturn(List.of(new ValidationError("code", "description")));

        List<ValidationPersons> validationPersons = List.of(validationPersons1,validationPersons2);
        List<ValidationPolicy> validationPolicies = List.of();

        validator = new TravelCalculatePremiumRequestValidatorImpl(
                validationPersons,
                validationPolicies
        );

        assertEquals(2, validator.validate(request).size());
    }

    @Test
    void validationThrowListPolicyExceptions() {
        when(validationPolicy1.validateList(request)).thenReturn(List.of(new ValidationError("code", "description")));
        when(validationPolicy2.validateList(request)).thenReturn(List.of(new ValidationError("code", "description")));

        List<ValidationPersons> validationPersons = List.of();
        List<ValidationPolicy> validationPolicies = List.of(validationPolicy1,validationPolicy2);

        validator = new TravelCalculatePremiumRequestValidatorImpl(
                validationPersons,
                validationPolicies
        );

        assertEquals(2, validator.validate(request).size());
    }
}