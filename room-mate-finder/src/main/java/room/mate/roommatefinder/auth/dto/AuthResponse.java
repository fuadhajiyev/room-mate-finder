package room.mate.roommatefinder.auth.dto;

import lombok.Data;
import room.mate.roommatefinder.auth.token.Token;
import room.mate.roommatefinder.dto.UserDTO;

@Data
public class AuthResponse {

    UserDTO user;

    Token token;

    public  UserDTO getUser(){
        return user;
    };

    public void setUser(UserDTO user){
        this.user = user;

    }

}
