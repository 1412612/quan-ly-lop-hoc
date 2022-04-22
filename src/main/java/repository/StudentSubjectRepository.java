package repository;

import model.Room;
import model.Student;
import model.StudentSubject;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.List;

public class StudentSubjectRepository {
    public List<StudentSubject> getBySubjectId(int subjectId){
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();

        String sql = "Select s from " + StudentSubject.class.getName() + " s where s.subjectId=:subjectId";

        Query<StudentSubject> query = session.createQuery(sql);

        query.setParameter("subjectId", subjectId);

        List<StudentSubject> results = query.getResultList();

        session.close();

        if(ObjectUtils.isNotEmpty(results)){
            return results;
        }
        return null;
    }

    public StudentSubject getBySubjectIdAndMssv(int subjectId, String mssv){
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();

        String sql = "Select s from " + StudentSubject.class.getName() + " s where s.subjectId=:subjectId and s.mssv=:mssv";

        Query<StudentSubject> query = session.createQuery(sql);

        query.setParameter("subjectId", subjectId);
        query.setParameter("mssv", mssv);

        List<StudentSubject> results = query.getResultList();

        session.close();

        if(ObjectUtils.isNotEmpty(results)){
            return results.get(0);
        }
        return null;
    }

    public void deleteBySubjectIdAndMssv(int subjectId, String mssv){
        StudentSubject entity = getBySubjectIdAndMssv(subjectId,mssv);
        delete(entity);
    }


    public void delete(StudentSubject entity){
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.delete(entity);
        session.flush();
        session.close();
    }
}
