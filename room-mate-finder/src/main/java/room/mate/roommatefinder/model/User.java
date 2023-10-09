package room.mate.roommatefinder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import room.mate.roommatefinder.validation.UniqueEmail;

@Data
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String email;
//    @JsonIgnore
    private String password;
    private String gender;
//
//    boolean active = false;
//    private String activationToken;



}
