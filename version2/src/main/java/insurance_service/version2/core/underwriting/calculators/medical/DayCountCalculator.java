package insurance_service.version2.core.underwriting.calculators.medical;

import insurance_service.version2.core.util.DateTimeUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import insurance_service.version2.core.api.dto.PolicyDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class DayCountCalculator {

    private final DateTimeUtil dateTimeUtil;
    BigDecimal getDayCount(PolicyDTO policy) {
        return new BigDecimal(dateTimeUtil.getPeriodInDays(policy.getAgreementDateFrom(),
                policy.getAgreementDateTo()));
    }
}
