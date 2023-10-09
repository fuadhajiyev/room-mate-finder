package room.mate.roommatefinder.service;


import org.springframework.stereotype.Service;
import room.mate.roommatefinder.model.RoomMate;
import room.mate.roommatefinder.repository.RoomMateRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomMateService {
    private  final RoomMateRepository roomMateRepo;


    public RoomMateService(RoomMateRepository roomMateRepo) {
        this.roomMateRepo = roomMateRepo;
    }


    public Optional<RoomMate> findRoomMateById(Integer roomMateId){
        return roomMateRepo.findById(roomMateId);
    }

    public List<RoomMate>  getAllRoomMate(){
        return  roomMateRepo.findAll();
    }


}
