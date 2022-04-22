package repository;

import model.Room;
import model.StudentSubject;
import model.Subject;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.List;

public class SubjectRepository {
    public void save(Subject subject){
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.save(subject);
        session.flush();
        session.close();
    }

    public Subject getBySubjectId(int id){
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();

        String sql = "Select s from " + Subject.class.getName() + " s where s.id=:id";

        Query<Subject> query = session.createQuery(sql);

        query.setParameter("id", id);

        List<Subject> results = query.getResultList();

        session.close();

        if(ObjectUtils.isNotEmpty(results)){
            return results.get(0);
        }
        return null;
    }
}
