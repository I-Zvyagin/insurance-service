package insurance_service.version2.core.repositories;

import insurance_service.version2.core.domain.AgeCoefficient;
import insurance_service.version2.core.repositories.AgeCoefficientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class AgeCoefficientRepositoryTest {

    @Autowired
    private AgeCoefficientRepository ageCoefficientRepository;

    @Test
    void shouldFindCoefficientByAge() {
        Optional<AgeCoefficient> result = ageCoefficientRepository.findCoefficient(20);
        assertTrue(result.isPresent());
        assertEquals(new BigDecimal("1.10"), result.get().getCoefficient());
    }
}