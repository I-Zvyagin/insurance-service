package insurance_service.version1.core.underwriting.calculators.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import insurance_service.version1.core.domain.AgeCoefficient;
import insurance_service.version1.core.repositories.AgeCoefficientRepository;
import insurance_service.version1.core.util.AgeCalculatorUtil;
import insurance_service.version1.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class AgeCoefficientCalculator {

    private  final AgeCoefficientRepository ageCoefficient;
    private  final AgeCalculatorUtil ageCalculatorUtil;

    BigDecimal getAgeCoefficient(TravelCalculatePremiumRequestV1 request){
        return ageCoefficient.findCoefficient(ageCalculatorUtil.getAge(request.getBirthDate()))
                .map(AgeCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Age coefficient not found by birth date = " + request.getBirthDate()));
    }
}
