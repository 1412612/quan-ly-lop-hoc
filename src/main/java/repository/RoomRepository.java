package repository;

import model.Room;
import model.Student;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.List;

public class RoomRepository {

    public List<Room> getAll(){
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();

        String sql = "Select r from " + Room.class.getName() + " r ";

        Query<Room> query = session.createQuery(sql);

        List<Room> results = query.getResultList();

        session.close();

        if(ObjectUtils.isNotEmpty(results)){
            return results;
        }
        return null;
    }
}
