package insurance_service.version1.core.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Component
public class AgeCalculatorUtil {
    public int getAge(Date birthDate) {
        LocalDate convertedBirthDate = birthDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return Period.between(convertedBirthDate, LocalDate.now()).getYears();
    }
}
