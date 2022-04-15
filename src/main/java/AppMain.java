import command.Command;
import model.AcademicStaff;
import model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import service.LoginService;
import utils.HibernateUtils;

import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class AppMain {
    public static void main(String[] args) {
//        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
//        Session session = sessionFactory.openSession();
//
//        session.close();
//        sessionFactory.close();
//
//        try {
//
//            String sql = "Select e from " + Student.class.getName() + " e ";
//
//            // Tạo đối tượng Query.
//            Query<Student> query = session.createQuery(sql);
//
//            // Thực hiện truy vấn.
//            List<Student> employees = query.getResultList();
//
//            System.out.println(employees);
//
//        }catch (Exception e){
//            System.out.println(e);
//        }
        //Command.addAcademicStaff("thanh", "admin");

//        LoginService loginService = new LoginService();
//
//        AcademicStaff academicStaff = new AcademicStaff();
//
//        academicStaff.setUsername("admin");
//        academicStaff.setPassword("admin");
//
//        System.out.println(loginService.academicStaffLogin(academicStaff));

        Command.addStudent("SV0001", "SV0001", "Nguyễn Văn Thanh");
    }
}
