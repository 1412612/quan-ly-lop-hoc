package repository;

import model.AcademicStaff;
import model.Student;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.List;

public class StudentRepository {
    public Student getByMssv(String mssv){
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();

        String sql = "Select s from " + Student.class.getName() + " s "
                + "where s.mssv=:mssv";

        Query<Student> query = session.createQuery(sql);

        query.setParameter("mssv", mssv);

        List<Student> results = query.getResultList();

        session.close();

        if(ObjectUtils.isNotEmpty(results)){
            return results.get(0);
        }
        return null;
    }

    public void update(Student student){
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.update(student);
        session.flush();
        session.close();
    }
}
