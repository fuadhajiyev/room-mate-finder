package room.mate.roommatefinder.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table
@Data
@Builder
@Entity(name = "roomMateOrder")
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String roomName;

    @ElementCollection(targetClass=Integer.class)
    private List<Integer> roomIdList;
    private Double totalPrice;
}
