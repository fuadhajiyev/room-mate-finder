package room.mate.roommatefinder.dto;

import lombok.Data;
import room.mate.roommatefinder.model.User;


@Data
public class UserDTO {

    private long id;

    private String username;
    private String email;
    private String gender;

    public UserDTO(User user) {

        setId(user.getId());
        setUsername(user.getUsername());
        setEmail(user.getEmail());
        setGender(user.getGender());

    }
}
