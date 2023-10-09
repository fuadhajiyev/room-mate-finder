package room.mate.roommatefinder.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import room.mate.roommatefinder.model.User;
import room.mate.roommatefinder.validation.UniqueEmail;



public record UserRequest(@NotBlank
                          @Size(min = 3, max = 88)
                          String username,

                          @NotBlank
                          @UniqueEmail
                          @Email
                          String email,
                          @Size(min = 8, max = 88)
                          @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")
                          String password,
                          String gender) {
    public User toUser() {

        User user = new User().builder().username(username).email(email).password(password).gender(gender).build();
        return user;
    }


}
