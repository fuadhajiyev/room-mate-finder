package room.mate.roommatefinder.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Table
@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RoomMate {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String fullName;
    private String roomName;
    private String gender;
    private Double capitation;


}
