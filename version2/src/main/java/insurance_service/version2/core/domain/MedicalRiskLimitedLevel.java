package insurance_service.version2.core.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "medical_risk_limit_level")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRiskLimitedLevel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "medical_risk_limit_level_ic", nullable = false, unique = true, length = 200)
    private String medicalRiskLimitedLevelIc;

    @Column(name = "coefficient",precision = 10, scale = 2, nullable = false)
    private BigDecimal coefficient;
}