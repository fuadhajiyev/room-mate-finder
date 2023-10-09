package room.mate.roommatefinder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import room.mate.roommatefinder.model.RoomMate;
import room.mate.roommatefinder.model.User;
import room.mate.roommatefinder.repository.RoomMateRepository;
import room.mate.roommatefinder.repository.UserRepository;

import java.util.Arrays;
//(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
public class Main implements CommandLineRunner {
    private final RoomMateRepository roomMateRepoı;
    private  final UserRepository userRepository;

    public Main(RoomMateRepository roomMateRepoı, UserRepository userRepository) {
        this.roomMateRepoı = roomMateRepoı;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println("workedddd");

        RoomMate roomMate1 = RoomMate.builder().fullName("Shahin Musayev").gender("kisi").capitation(100.00).roomName("telebe").build();
        RoomMate roomMate2 = RoomMate.builder().fullName("Mahir Medetov").gender("kisi").capitation(150.00).roomName("aile").build();
        RoomMate roomMate3 = RoomMate.builder().fullName("Alekber Bagirov").gender("kisi").capitation(200.00).roomName("telebe").build();
        roomMateRepoı.saveAll(Arrays.asList(roomMate1, roomMate2, roomMate3));




        for (var i = 1; i <=20; i++){
            User user = User.builder().username("user "+i).email("user"+i+"@gmail.com").gender("kişi").password(passwordEncoder.encode("123456abc")).build();
            userRepository.save(user);
        }
    }
}
