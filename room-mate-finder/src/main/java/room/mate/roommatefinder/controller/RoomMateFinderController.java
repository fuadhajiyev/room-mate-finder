package room.mate.roommatefinder.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import room.mate.roommatefinder.dto.RoomOrderRequest;
import room.mate.roommatefinder.model.Order;
import room.mate.roommatefinder.model.RoomMate;
import room.mate.roommatefinder.service.OrderService;
import room.mate.roommatefinder.service.RoomMateService;

import java.util.List;

@RestController
@RequestMapping("/v1/roommatefinder")
public class RoomMateFinderController {
    private  final OrderService orderService;
    private  final RoomMateService roomMateService;


    public RoomMateFinderController(OrderService orderService, RoomMateService roomMateService) {
        this.orderService = orderService;
        this.roomMateService = roomMateService;
    }

    @GetMapping
    public ResponseEntity<String> helloTwitch() {
        return ResponseEntity.ok("Hello Twitch");
    }


    @GetMapping("/roommates")
    public ResponseEntity<List<RoomMate>> getAllRoomMate() {
        List<RoomMate> roomMateList = roomMateService.getAllRoomMate();
        return ResponseEntity.ok(roomMateList);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orderList = orderService.getAllOrders();
        return ResponseEntity.ok(orderList);
    }


    @CrossOrigin
    @PostMapping
    public ResponseEntity<Order> putAnOrder(@RequestBody RoomOrderRequest roomOrderRequest) {
        Order order = orderService.putAnOrder(roomOrderRequest.getRoomIdList(), roomOrderRequest.getRoomName());
        return ResponseEntity.ok(order);
    }

}
