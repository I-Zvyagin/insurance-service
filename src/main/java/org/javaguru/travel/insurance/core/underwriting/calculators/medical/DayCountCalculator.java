package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class DayCountCalculator {

    private final DateTimeUtil dateTimeUtil;
    BigDecimal getDayCount(TravelCalculatePremiumRequest request) {
        return new BigDecimal(dateTimeUtil.getPeriodInDays(request.getAgreementDateFrom(),
                request.getAgreementDateTo()));
    }
}
