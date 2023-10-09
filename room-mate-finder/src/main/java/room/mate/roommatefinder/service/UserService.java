package room.mate.roommatefinder.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import room.mate.roommatefinder.config.CurrentUser;
import room.mate.roommatefinder.dto.UserDTO;
import room.mate.roommatefinder.dto.UserRequest;
import room.mate.roommatefinder.dto.UserUpdate;
import room.mate.roommatefinder.error.NotFoundException;
import room.mate.roommatefinder.error.NotUniqueEmailException;
import room.mate.roommatefinder.model.Order;
import room.mate.roommatefinder.model.RoomMate;
import room.mate.roommatefinder.model.User;
import room.mate.roommatefinder.repository.OrderRepository;
import room.mate.roommatefinder.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User createUser(User userData) {

        try {
            userData.setPassword(passwordEncoder.encode(userData.getPassword()));
//            userData.setActivationToken(UUID.randomUUID().toString());
            return userRepository.save(userData);

        } catch (DataIntegrityViolationException exception) {
            throw new NotUniqueEmailException();
        }


    }


    public Page<User> getUsers(Pageable pageable, CurrentUser currentUser) {
        if (currentUser == null) {
            return userRepository.findAll(pageable);
        }
        return userRepository.findByIdNot(currentUser.getId(), pageable);
    }

    public User getUser(long id) {
        return userRepository.getReferenceById(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User updateUser(long id, UserUpdate userUpdate) {

        User inDB = getUser(id);
        inDB.setUsername(userUpdate.username());
        return userRepository.save(inDB);

    }
}

