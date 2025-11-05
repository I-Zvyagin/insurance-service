package org.javaguru.travel.insurance.core.underwriting.calculators;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.domain.AgeCoefficient;
import org.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import org.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import org.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import org.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import org.javaguru.travel.insurance.core.util.AgeCalculatorUtil;
import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    private final DateTimeUtil dateTimeUtil;
    private final CountryDefaultDayRateRepository dayRate;
    private  final AgeCoefficientRepository ageCoefficient;
    private  final AgeCalculatorUtil ageCalculatorUtil;

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return getDayCount(request)
                .multiply(getCountryDefaultDayPremium(request))
                .multiply(getAgeCoefficient(request))
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal getCountryDefaultDayPremium(TravelCalculatePremiumRequest request) {
        return dayRate.findByCountryIc(request.getCountry())
                .map(CountryDefaultDayRate::getDefaultDayRate)
                .orElseThrow(() -> new RuntimeException("Country day rate not found by country ic = " + request.getCountry()));
    }

    private BigDecimal getDayCount(TravelCalculatePremiumRequest request) {
        return new BigDecimal(dateTimeUtil.getPeriodInDays(request.getAgreementDateFrom(),
                request.getAgreementDateTo()));
    }

    private BigDecimal getAgeCoefficient(TravelCalculatePremiumRequest request){
        return ageCoefficient.findCoefficient(ageCalculatorUtil.getAge(request.getBirthDate()))
                .map(AgeCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Age coefficient not found by birth date = " + request.getBirthDate()));
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }
}