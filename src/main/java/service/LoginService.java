package service;

import model.AcademicStaff;
import model.Student;
import repository.AcademicRepository;
import repository.StudentRepository;
import utils.PasswordUtils;

public class LoginService {
    private AcademicRepository academicRepository;
    private StudentRepository studentRepository;

    public LoginService(){
        this.academicRepository = new AcademicRepository();
        this.studentRepository = new StudentRepository();
    }

    public boolean academicStaffLogin(AcademicStaff academicStaff){
        AcademicStaff entity = academicRepository.getByUserName(academicStaff.getUsername());
        if (entity==null) return false;

        if(PasswordUtils.passwordEncoder.matches(academicStaff.getPassword(), entity.getPassword())) return true;

        return false;
    }

    public boolean studentLogin(Student student){
        Student entity = studentRepository.getByMssv(student.getMssv());
        if (entity==null) return false;

        if(PasswordUtils.passwordEncoder.matches(student.getPassword(), entity.getPassword())) return true;

        return false;
    }
}
