package room.mate.roommatefinder.auth.token;

import room.mate.roommatefinder.auth.dto.Credentials;
import room.mate.roommatefinder.model.User;

public interface TokenService {

    public Token createToken(User user, Credentials credentials);

    public User verifyToken(String authorizationHeader);

    public void logout(String authorizationHeader);

}
