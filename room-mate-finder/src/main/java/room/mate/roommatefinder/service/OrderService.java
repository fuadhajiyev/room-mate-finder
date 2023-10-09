package room.mate.roommatefinder.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import room.mate.roommatefinder.model.Order;
import room.mate.roommatefinder.model.RoomMate;
import room.mate.roommatefinder.repository.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {

    private final RoomMateService roomMateService;
    private final OrderRepository orderRepo;

    public OrderService(RoomMateService roomMateService, OrderRepository orderRepo) {
        this.roomMateService = roomMateService;
        this.orderRepo = orderRepo;
    }

    public List<Order> getAllOrders(){
        return orderRepo.findAll();
    }

    public Order putAnOrder(List<Integer> roomIdList, String roomName) {
        List<Optional<RoomMate>> roomMateList = roomIdList.stream().map(roomMateService::findRoomMateById).collect(Collectors.toList());

        Double totalPrice = roomMateList.stream()
                .map(optionalRoomMate -> optionalRoomMate.map(RoomMate::getCapitation).orElse(0.0)).reduce(0.0, Double::sum);

        Order order = Order.builder()
                .totalPrice(totalPrice)
                .roomName(roomName)
                .build();

        return orderRepo.save(order);
    }


}

