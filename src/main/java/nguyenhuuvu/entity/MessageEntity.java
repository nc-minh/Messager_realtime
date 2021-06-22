package nguyenhuuvu.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "message")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private Date date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "join_id")
    private JoinEntity join;
}
