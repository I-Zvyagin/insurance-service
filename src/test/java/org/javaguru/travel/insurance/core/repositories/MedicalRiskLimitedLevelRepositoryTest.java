package org.javaguru.travel.insurance.core.repositories;

import org.javaguru.travel.insurance.core.domain.MedicalRiskLimitedLevel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class MedicalRiskLimitedLevelRepositoryTest {

    @Autowired
    private MedicalRiskLimitedLevelRepository medicalRiskLimitedLevelRepository;

    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(medicalRiskLimitedLevelRepository);
    }

    @Test
    public void shouldFindForLimit10000() {searchCoefficient("LEVEL_10000", new BigDecimal("1.00"));}

    @Test
    public void shouldFindForLimit15000() {searchCoefficient("LEVEL_15000", new BigDecimal("1.20"));}

    @Test
    public void shouldFindForLimit20000() {searchCoefficient("LEVEL_20000", new BigDecimal("1.50"));}

    @Test
    public void shouldFindForLimit50000() {searchCoefficient("LEVEL_50000", new BigDecimal("2.00"));}

    private void searchCoefficient(String medicalRiskLimitedLevelIc, BigDecimal coefficient) {
        Optional<MedicalRiskLimitedLevel> valueOpt = medicalRiskLimitedLevelRepository
                .findByMedicalRiskLimitedLevelIc(medicalRiskLimitedLevelIc);
        assertTrue(valueOpt.isPresent());
        assertEquals(medicalRiskLimitedLevelIc, valueOpt.get().getMedicalRiskLimitedLevelIc());
        assertEquals(coefficient, valueOpt.get().getCoefficient());
        assertEquals(valueOpt.get().getCoefficient().stripTrailingZeros(), coefficient.stripTrailingZeros());
    }
}