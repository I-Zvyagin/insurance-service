package insurance_service.version2.core.repositories;

import insurance_service.version2.core.domain.ClassifierValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClassifierValueRepository extends JpaRepository<insurance_service.version2.core.domain.ClassifierValue, Long> {

    @Query("SELECT cv from ClassifierValue cv " +
            "left join cv.classifier c " +
            "where c.title = :classifierTitle " +
            "and cv.ic = :ic")
    Optional<insurance_service.version2.core.domain.ClassifierValue> findByClassifierTitleAndIc(
            @Param("classifierTitle") String classifierTitle,
            @Param("ic") String ic
    );

}
