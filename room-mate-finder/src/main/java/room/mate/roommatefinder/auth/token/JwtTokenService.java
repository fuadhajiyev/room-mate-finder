package room.mate.roommatefinder.auth.token;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import room.mate.roommatefinder.auth.dto.Credentials;
import room.mate.roommatefinder.model.User;

import javax.crypto.SecretKey;


@Service
@Primary
public class JwtTokenService implements TokenService {

    SecretKey key = Keys.hmacShaKeyFor("fuad-elmir-sehriyar-rufat-resad-odlar-yurdu-uni".getBytes());

    @Override
    public Token createToken(User user, Credentials creds) {

        String token = Jwts.builder().setSubject(Long.toString(user.getId())).signWith(key).compact();

        return new Token("Bearer", token);

    }

    @Override
    public User verifyToken(String authorizationHeader) {
        if (authorizationHeader == null) return null;
        var token = authorizationHeader.split(" ")[1];
        JwtParser parser = Jwts.parserBuilder().setSigningKey(key).build();
        try {
            Jws<Claims> claims = parser.parseClaimsJws(token);
            long userId = Long.valueOf(claims.getBody().getSubject());
            User user = new User();
            user.setId(userId);
            return user;
        } catch (JwtException e) {
            e.printStackTrace();
        }

        return null;


    }

    @Override
    public void logout(String authorizationHeader) {

    }
}
