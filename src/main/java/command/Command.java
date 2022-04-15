package command;

import model.AcademicStaff;
import model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateUtils;
import utils.PasswordUtils;

import javax.transaction.Transactional;

@Transactional
public class Command {
    public static void addAcademicStaff(String username, String password) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();

        AcademicStaff academicStaff = new AcademicStaff();
        academicStaff.setUsername(username);
        academicStaff.setPassword(PasswordUtils.passwordEncoder.encode(password));

        session.save(academicStaff);
        session.flush();
        session.close();
    }

    public static void addStudent(String mssv, String password, String name) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();

        Student student = new Student()
                .setMssv(mssv)
                .setPassword(PasswordUtils.passwordEncoder.encode(password))
                .setName(name);

        session.save(student);
        session.flush();
        session.close();
    }
}
