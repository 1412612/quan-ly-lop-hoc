package repository;

import model.AcademicStaff;
import model.Student;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import utils.HibernateUtils;
import utils.PasswordUtils;

import java.util.List;

public class AcademicRepository {
    public AcademicStaff getByUserName(String username){
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();

        String sql = "Select a from " + AcademicStaff.class.getName() + " a "
                + "where a.username=:username";

        Query<AcademicStaff> query = session.createQuery(sql);

        query.setParameter("username", username);

        List<AcademicStaff> results = query.getResultList();

        session.close();

        if(ObjectUtils.isNotEmpty(results)){
            return results.get(0);
        }
        return null;
    }

    public void update(AcademicStaff academicStaff){
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.update(academicStaff);
        session.flush();
        session.close();
    }
}
