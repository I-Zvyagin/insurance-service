package insurance_service.version1.core.underwriting.calculators.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import insurance_service.version1.core.util.DateTimeUtil;
import insurance_service.version1.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class DayCountCalculator {

    private final DateTimeUtil dateTimeUtil;
    BigDecimal getDayCount(TravelCalculatePremiumRequestV1 request) {
        return new BigDecimal(dateTimeUtil.getPeriodInDays(request.getAgreementDateFrom(),
                request.getAgreementDateTo()));
    }
}
