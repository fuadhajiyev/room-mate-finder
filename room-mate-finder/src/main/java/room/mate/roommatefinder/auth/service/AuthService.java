package room.mate.roommatefinder.auth.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import room.mate.roommatefinder.auth.dto.AuthResponse;
import room.mate.roommatefinder.auth.dto.Credentials;
import room.mate.roommatefinder.auth.exception.AuthenticationException;
import room.mate.roommatefinder.auth.token.Token;
import room.mate.roommatefinder.auth.token.TokenService;
import room.mate.roommatefinder.dto.UserDTO;
import room.mate.roommatefinder.error.AuthorizationException;
import room.mate.roommatefinder.model.User;
import room.mate.roommatefinder.service.UserService;

@Service
public class AuthService {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public AuthResponse authenticate(Credentials creds) {
        User inDB = userService.findByEmail(creds.email());
        if (inDB == null) throw new AuthenticationException();

        if (!passwordEncoder.matches(creds.password(), inDB.getPassword()))
            throw new AuthenticationException();


        Token token = tokenService.createToken(inDB, creds);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setUser(new UserDTO(inDB));

        return authResponse;

    }


    public void logout(String authorizationHeader) {
        tokenService.logout(authorizationHeader);
    }
}

