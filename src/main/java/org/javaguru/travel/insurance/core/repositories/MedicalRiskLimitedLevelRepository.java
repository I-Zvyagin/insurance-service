package org.javaguru.travel.insurance.core.repositories;

import org.javaguru.travel.insurance.core.domain.MedicalRiskLimitedLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicalRiskLimitedLevelRepository extends JpaRepository<MedicalRiskLimitedLevel, Long> {
    Optional<MedicalRiskLimitedLevel> findByMedicalRiskLimitedLevelIc(String medicalRiskLimitedLevelIc);
}
