package insurance_service.version2.core.underwriting.calculators.medical;

import insurance_service.version2.core.domain.CountryDefaultDayRate;
import insurance_service.version2.core.repositories.CountryDefaultDayRateRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import insurance_service.version2.core.api.dto.PolicyDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class CountryDefaultDayRateCalculator {

    private final CountryDefaultDayRateRepository dayRate;

    BigDecimal getCountryDefaultDayPremium(PolicyDTO policy) {
        return dayRate.findByCountryIc(policy.getCountry())
                .map(CountryDefaultDayRate::getDefaultDayRate)
                .orElseThrow(() -> new RuntimeException("Country day rate not found by country ic = " + policy.getCountry()));
    }
}
