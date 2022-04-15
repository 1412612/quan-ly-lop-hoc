package service;

import model.Student;
import repository.StudentRepository;

public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(){
        this.studentRepository = new StudentRepository();
    }

    public Student getByMssv(String mssv){
       return studentRepository.getByMssv(mssv);
    }

    public void update(Student student){
        studentRepository.update(student);
    }
}
