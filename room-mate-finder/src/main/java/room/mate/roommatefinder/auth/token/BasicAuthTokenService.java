package room.mate.roommatefinder.auth.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import room.mate.roommatefinder.auth.dto.Credentials;
import room.mate.roommatefinder.model.User;
import room.mate.roommatefinder.service.UserService;

import java.util.Base64;


@Service
public class BasicAuthTokenService implements TokenService {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public Token createToken(User user, Credentials credentials) {
        String emailColonPassword = credentials.email() + ":" + credentials.password();

        String token = Base64.getEncoder().encodeToString(emailColonPassword.getBytes());

        return new Token("Basic", token);

    }

    @Override
    public User verifyToken(String authorizationHeader) {

        if (authorizationHeader == null) return null;
        var base64Encoded = authorizationHeader.split("Basic ")[1];
        var decoded = new String(Base64.getDecoder().decode(base64Encoded));
        var cred = decoded.split(":");
        var email = cred[0];
        var password = cred[1];

        User inDB = userService.findByEmail(email);
        if (inDB == null) return null;
        if (!passwordEncoder.matches(password, inDB.getPassword())) return null;

        return inDB;
    }

    @Override
    public void logout(String authorizationHeader) {

    }
}
