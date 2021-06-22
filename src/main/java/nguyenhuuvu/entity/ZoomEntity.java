package nguyenhuuvu.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "zoom")
public class ZoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Accept to send
    private boolean active;

    @OneToMany(mappedBy = "zoom", cascade = CascadeType.ALL)
    private List<JoinEntity> joins;
}
