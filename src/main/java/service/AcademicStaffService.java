package service;


import model.AcademicStaff;
import repository.AcademicRepository;

public class AcademicStaffService {
    private AcademicRepository academicRepository = new AcademicRepository();

    public AcademicStaff getByUsername(String username) {
        return academicRepository.getByUserName(username);
    }

    public void update(AcademicStaff academicStaff) {
        academicRepository.update(academicStaff);
    }
}

