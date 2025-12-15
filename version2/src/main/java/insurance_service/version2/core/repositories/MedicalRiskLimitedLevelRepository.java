package insurance_service.version2.core.repositories;

import insurance_service.version2.core.domain.MedicalRiskLimitedLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicalRiskLimitedLevelRepository extends JpaRepository<MedicalRiskLimitedLevel, Long> {
    Optional<MedicalRiskLimitedLevel> findByMedicalRiskLimitedLevelIc(String medicalRiskLimitedLevelIc);
}
