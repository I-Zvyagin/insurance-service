package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.domain.AgeCoefficient;
import org.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import org.javaguru.travel.insurance.core.util.AgeCalculatorUtil;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
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
