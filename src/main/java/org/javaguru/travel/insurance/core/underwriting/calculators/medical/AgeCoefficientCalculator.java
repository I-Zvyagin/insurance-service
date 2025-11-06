package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.domain.AgeCoefficient;
import org.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import org.javaguru.travel.insurance.core.util.AgeCalculatorUtil;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class AgeCoefficientCalculator {

    private  final AgeCoefficientRepository ageCoefficient;
    private  final AgeCalculatorUtil ageCalculatorUtil;

    BigDecimal getAgeCoefficient(TravelCalculatePremiumRequest request){
        return ageCoefficient.findCoefficient(ageCalculatorUtil.getAge(request.getBirthDate()))
                .map(AgeCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Age coefficient not found by birth date = " + request.getBirthDate()));
    }
}
