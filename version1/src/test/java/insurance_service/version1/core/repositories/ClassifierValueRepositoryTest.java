package insurance_service.version1.core.repositories;

import insurance_service.version1.core.domain.ClassifierValue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class ClassifierValueRepositoryTest {

    @Autowired private ClassifierValueRepository classifierValueRepository;

    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(classifierValueRepository);
    }

    @ParameterizedTest
    @CsvSource({
            "RISK_TYPE, TRAVEL_MEDICAL",
            "RISK_TYPE, TRAVEL_CANCELLATION",
            "RISK_TYPE, TRAVEL_LOSS_BAGGAGE",
            "RISK_TYPE, TRAVEL_THIRD_PARTY_LIABILITY",
            "RISK_TYPE, TRAVEL_EVACUATION",
            "RISK_TYPE, TRAVEL_SPORT_ACTIVITIES"
    })
    public void getClassifierValueAndAssert(String classifierTitle, String ic) {    Optional<ClassifierValue> valueOpt = classifierValueRepository.findByClassifierTitleAndIc(
            classifierTitle, ic);
        assertTrue(valueOpt.isPresent());
        assertEquals(ic, valueOpt.get().getIc());
        assertEquals(classifierTitle, valueOpt.get().getClassifier().getTitle());
    }

    @Test
    public void shouldNotFindRiskTypeFAKE() {
        Optional<ClassifierValue> valueOpt = classifierValueRepository.findByClassifierTitleAndIc(
                "RISK_TYPE", "FAKE");
        assertTrue(valueOpt.isEmpty());
    }
}