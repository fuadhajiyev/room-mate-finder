package room.mate.roommatefinder.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import room.mate.roommatefinder.model.User;
import room.mate.roommatefinder.service.UserService;

import java.util.Collection;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User inDB = userService.findByEmail(email);

        if (inDB == null){
            throw  new UsernameNotFoundException(email +" is not found");
        }
        return new CurrentUser(inDB);

    }
}
