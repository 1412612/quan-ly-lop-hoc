package service;

import model.Room;
import repository.RoomRepository;

import java.util.List;

public class RoomService {
    private RoomRepository roomRepository = new RoomRepository();

    public List<Room> getAllRoom(){
        return roomRepository.getAll();
    }
}
