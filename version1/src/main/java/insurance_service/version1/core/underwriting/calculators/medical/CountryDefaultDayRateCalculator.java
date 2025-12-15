package insurance_service.version1.core.underwriting.calculators.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import insurance_service.version1.core.domain.CountryDefaultDayRate;
import insurance_service.version1.core.repositories.CountryDefaultDayRateRepository;
import insurance_service.version1.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class CountryDefaultDayRateCalculator {

    private final CountryDefaultDayRateRepository dayRate;

    BigDecimal getCountryDefaultDayPremium(TravelCalculatePremiumRequestV1 request) {
        return dayRate.findByCountryIc(request.getCountry())
                .map(CountryDefaultDayRate::getDefaultDayRate)
                .orElseThrow(() -> new RuntimeException("Country day rate not found by country ic = " + request.getCountry()));
    }
}
