package room.mate.roommatefinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import room.mate.roommatefinder.model.RoomMate;

public interface RoomMateRepository extends JpaRepository<RoomMate, Integer> {
}
