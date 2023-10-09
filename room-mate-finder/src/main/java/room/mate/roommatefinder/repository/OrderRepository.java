package room.mate.roommatefinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import room.mate.roommatefinder.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
