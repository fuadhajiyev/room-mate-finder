package room.mate.roommatefinder.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoomOrderRequest {
    private List<Integer> roomIdList;
    private String roomName;

}
