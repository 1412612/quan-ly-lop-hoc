package service;

import model.Room;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.cache.spi.support.DirectAccessRegionTemplate;
import repository.RoomRepository;

import java.util.List;
import java.util.stream.Collectors;

public class RoomService {
    private RoomRepository roomRepository = new RoomRepository();

    public List<Room> getAllRoom(){
        return roomRepository.getAll();
    }

    public void managerUpdate(List<Room> rooms){
        List<Room> oldRoom = getAllRoom();
        if(ObjectUtils.isEmpty(oldRoom)){
            rooms.stream().forEach(room -> roomRepository.save(room));
            return;
        }
        List<Room> deleteRoom = oldRoom.stream().filter(item->!rooms.contains(item)).collect(Collectors.toList());

        List<Room> updateRoom = oldRoom.stream().filter(item->rooms.contains(item)).collect(Collectors.toList());

        List<Room> addRoom = rooms.stream().filter(room -> !oldRoom.contains(room)).collect(Collectors.toList());

        deleteRoom.stream().forEach(item->roomRepository.delete(item));
        updateRoom.stream().forEach(item->roomRepository.update(item));
        addRoom.stream().forEach(item->roomRepository.save(item));
    }

    public void delete(Room room){
        roomRepository.delete(room);
    }

    public Room getByCode(String code){
        return  roomRepository.getByCode(code);
    }
}
