import controller.Login;

import javax.transaction.Transactional;

@Transactional
public class AppMain {
    public static void main(String[] args) {
        Login.start();
//        Command.addAcademicStaff("admin", "admin");
    }
}
