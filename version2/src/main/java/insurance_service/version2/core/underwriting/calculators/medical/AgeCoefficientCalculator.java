package insurance_service.version2.core.underwriting.calculators.medical;

import insurance_service.version2.core.domain.AgeCoefficient;
import insurance_service.version2.core.repositories.AgeCoefficientRepository;
import insurance_service.version2.core.util.AgeCalculatorUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import insurance_service.version2.core.api.dto.PersonDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class AgeCoefficientCalculator {

    private final AgeCoefficientRepository ageCoefficient;
    private final AgeCalculatorUtil ageCalculatorUtil;

    BigDecimal getAgeCoefficient(PersonDTO person){
        return ageCoefficient.findCoefficient(ageCalculatorUtil.getAge(person.getPersonBirthDate()))
                .map(AgeCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Age coefficient not found by birth date = " + person.getPersonBirthDate()));
    }
}
