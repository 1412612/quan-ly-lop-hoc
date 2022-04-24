package repository;

import model.Room;
import model.Student;
import model.StudentSubject;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.SQLQuery;
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

    public List<StudentSubject> getByMssv(String mssv){
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();

        String sql = "Select s from " + StudentSubject.class.getName() + " s where s.mssv=:mssv";

        Query<StudentSubject> query = session.createQuery(sql);

        query.setParameter("mssv", mssv);

        List<StudentSubject> results = query.getResultList();

        session.close();

        if(ObjectUtils.isNotEmpty(results)){
            return results;
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

    public void save(StudentSubject entity){
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.save(entity);
        session.flush();
        session.close();
    }

    public void deleteByMssvAndSID(int sid, String mssv){
        StudentSubject entity = getBySubjectIdAndMssv(sid, mssv);
        delete(entity);
    }

    public void updateBySubjectIdAndMssv(StudentSubject subject){
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();

       // session.load(StudentSubject.class, );

//        String query = "update "+ StudentSubject.class.getName() +" s SET  = s.'" +"' WHERE ";
        String sql = "update student_subject" + " s set "+
                "s.session_one=:sessionOne," +
                "s.session_two=:sessionTwo," +
                "s.session_three=:sessionThree," +
                "s.session_four=:sessionFour," +
                "s.session_five=:sessionFive," +
                "s.session_six=:sessionSix," +
                "s.session_seven=:sessionSeven," +
                "s.session_eight=:sessionEight," +
                "s.session_nine=:sessionNine," +
                "s.session_ten=:sessionTen," +
                "s.session_eleven=:sessionEleven," +
                "s.session_twelve=:sessionTwelve," +
                "s.session_thirteen=:sessionThirteen," +
                "s.session_fourteen=:sessionFourteen," +
                "s.session_fifteen=:sessionFifteen " +
                "where (s.mssv=:mssv and s.subject_id=:subjectId)";

        SQLQuery query = session.createSQLQuery(sql);

        query.setParameter("subjectId", subject.getSubjectId());
        query.setParameter("mssv",subject.getMssv());
        query.setParameter("sessionOne",subject.isSessionOne());
        query.setParameter("sessionTwo",subject.isSessionTwo());
        query.setParameter("sessionThree",subject.isSessionThree());
        query.setParameter("sessionFour",subject.isSessionFour());
        query.setParameter("sessionFive",subject.isSessionFive());
        query.setParameter("sessionSix",subject.isSessionSix());
        query.setParameter("sessionSeven",subject.isSessionSeven());
        query.setParameter("sessionEight",subject.isSessionEight());
        query.setParameter("sessionNine",subject.isSessionNine());
        query.setParameter("sessionTen",subject.isSessionTen());
        query.setParameter("sessionEleven",subject.isSessionEleven());
        query.setParameter("sessionTwelve",subject.isSessionTwelve());
        query.setParameter("sessionThirteen",subject.isSessionThirteen());
        query.setParameter("sessionFourteen",subject.isSessionFourteen());
        query.setParameter("sessionFifteen",subject.isSessionFifteen());

        query.executeUpdate();

        session.close();
    }
}
