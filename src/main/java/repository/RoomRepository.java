package repository;

import model.AcademicStaff;
import model.Room;
import model.Student;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import javax.swing.*;
import java.util.List;

public class RoomRepository {

    public List<Room> getAll(){
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();

        String sql = "Select r from " + Room.class.getName() + " r order by r.id";

        Query<Room> query = session.createQuery(sql);

        List<Room> results = query.getResultList();

        session.close();

        if(ObjectUtils.isNotEmpty(results)){
            return results;
        }
        return null;
    }

    public Room getByCode(String code){
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();

        String sql = "Select r from " + Room.class.getName() + " r where r.code =:code";

        Query<Room> query = session.createQuery(sql);

        query.setParameter("code",code);

        List<Room> results = query.getResultList();

        session.close();

        if(ObjectUtils.isNotEmpty(results)){
            return results.get(0);
        }
        return null;
    }

    public void update(Room room){
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.update(room);
        session.flush();
        session.close();
    }

    public boolean delete(Room room){
        try{
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.delete(room);
        session.flush();
        session.close();
        return true;
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Phòng học đang được sử dụng, không thể xóa!");
        return false;
        }
    }

    public void save(Room room){
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.save(room);
        session.flush();
        session.close();
    }
}
