package insurance_service.version2.core.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "classifiers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Classifier {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false, unique = true, length = 200)
    private String title;

    @Column(name = "description", nullable = false, unique = true, length = 100)
    private String description;
}
