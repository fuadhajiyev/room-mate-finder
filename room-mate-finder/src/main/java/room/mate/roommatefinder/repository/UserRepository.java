package room.mate.roommatefinder.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import room.mate.roommatefinder.dto.UserProjection;
import room.mate.roommatefinder.model.Order;
import room.mate.roommatefinder.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query(value = "Select u from User u")
    Page<UserProjection> getAllUserRecords(Pageable page);

    Page<User> findByIdNot(long id, Pageable page);


}
