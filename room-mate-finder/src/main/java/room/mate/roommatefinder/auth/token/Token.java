package room.mate.roommatefinder.auth.token;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.Data;
import room.mate.roommatefinder.model.User;


@Data
@Entity
public class Token{


    @Transient
    String prefix = "Bearer";

    @Id
    String token;

    @JsonIgnore
    @ManyToOne
    User user;

    public Token() {
    }

    public Token(String prefix, String token) {
        this.prefix = prefix;
        this.token = token;
    }
}
