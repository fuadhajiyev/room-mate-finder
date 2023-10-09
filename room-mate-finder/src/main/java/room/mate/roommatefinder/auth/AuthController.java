package room.mate.roommatefinder.auth;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import room.mate.roommatefinder.auth.dto.AuthResponse;
import room.mate.roommatefinder.auth.dto.Credentials;
import room.mate.roommatefinder.auth.service.AuthService;
import room.mate.roommatefinder.error.ApiError;
import room.mate.roommatefinder.shared.GenericMessage;

import java.util.stream.Collectors;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/api/v1/auth")
    ResponseEntity<AuthResponse> handleAuthentication(@Valid @RequestBody Credentials creds) {
        var authResponse = authService.authenticate(creds);
        var cookie = ResponseCookie.from("token", authResponse.getToken().getToken()).path("/").httpOnly(true).build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(authResponse);

    }

    @PostMapping("/api/v1/logout")
    ResponseEntity<GenericMessage> handleLogout(@RequestHeader(name = "Authorization", required = false) String authorizationHeader,

                                                @CookieValue(name = "token", required = false) String cookieValue) {
        var tokenWithPrefix = authorizationHeader;
        if (cookieValue != null) {
            tokenWithPrefix = "AnyPrefix " + cookieValue;
        }
        authService.logout(tokenWithPrefix);
        var cookie = ResponseCookie.from("token", "").path("/").maxAge(0).httpOnly(true).build();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(new GenericMessage("Logout Success"));

    }


}
