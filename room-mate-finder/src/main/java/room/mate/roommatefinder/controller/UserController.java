package room.mate.roommatefinder.controller;


import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import room.mate.roommatefinder.auth.token.TokenService;
import room.mate.roommatefinder.config.CurrentUser;
import room.mate.roommatefinder.dto.UserDTO;
import room.mate.roommatefinder.dto.UserRequest;
import room.mate.roommatefinder.dto.UserUpdate;
import room.mate.roommatefinder.error.AuthorizationException;
import room.mate.roommatefinder.model.User;
import room.mate.roommatefinder.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;

    }


    //    @CrossOrigin
//    @PostMapping
//    public GenericMessage createUser(@RequestBody User userRequest) {
//        User user = userService.createUser(userRequest);
//
//        return new  GenericMessage(userRequest.getUsername()+" is created");
//    }
    @CrossOrigin
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserRequest userRequest) {
        User user = userService.createUser(userRequest.toUser());
        return ResponseEntity.ok(user);
    }


    @GetMapping
    Page<UserDTO> getUsers(Pageable page, @AuthenticationPrincipal CurrentUser currentUser) {

        return userService.getUsers(page, currentUser).map(UserDTO::new);
    }


    @GetMapping("/{id}")
    UserDTO getUserById(@PathVariable long id) {
        return new UserDTO(userService.getUser(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("#id == principal.id")
    UserDTO updateUser(@PathVariable long id, @Valid @RequestBody UserUpdate userUpdate) {

        return new UserDTO(userService.updateUser(id, userUpdate));
    }

//    @GetMapping
//    public ResponseEntity<String> helloTwitch() {
//        return ResponseEntity.ok("Hello Twitch");
//    }
//
//
//    @GetMapping("/roommates")
//    public ResponseEntity<List<RoomMate>> getAllRoomMate() {
//        List<RoomMate> roomMateList = roomMateService.getAllRoomMate();
//        return ResponseEntity.ok(roomMateList);
//    }
//
//    @GetMapping("/orders")
//    public ResponseEntity<List<Order>> getAllOrders() {
//        List<Order> orderList = orderService.getAllOrders();
//        return ResponseEntity.ok(orderList);
//    }


}
