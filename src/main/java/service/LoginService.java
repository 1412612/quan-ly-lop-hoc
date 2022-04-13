package service;

import model.AcademicStaff;
import repository.AcademicRepository;
import utils.PasswordUtils;

public class LoginService {
    private AcademicRepository academicRepository;

    public LoginService(){
        this.academicRepository = new AcademicRepository();
    }

    public boolean academicStaffLogin(AcademicStaff academicStaff){
        AcademicStaff entity = academicRepository.getByUserName(academicStaff.getUsername());
        if (entity==null) return false;

        if(PasswordUtils.passwordEncoder.matches(academicStaff.getPassword(), entity.getPassword())) return true;

        return false;
    }
}
