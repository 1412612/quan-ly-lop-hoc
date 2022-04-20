package service;

import model.Student;
import org.apache.commons.lang3.ObjectUtils;
import repository.StudentRepository;
import utils.PasswordUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Student> getAllStudent(){
        return studentRepository.getAll();
    }

    public void managerUpdate(List<Student> students){
        List<Student> oldStudent = getAllStudent();
        if(ObjectUtils.isEmpty(oldStudent)){
            students.stream().forEach(item->{
                item.setPassword(PasswordUtils.passwordEncoder.encode(item.getMssv()));
                studentRepository.save(item);
            });
            return;
        }

        students.stream().forEach(item->{
            Optional<Student> optionalStudent = oldStudent.stream().filter(olditem->olditem.getId()==item.getId()).findFirst();
            if(!optionalStudent.isEmpty()){
                item.setPassword(optionalStudent.get().getPassword());
            }
        });

        List<Student> deleteStudent = oldStudent.stream().filter(item->!students.contains(item)).collect(Collectors.toList());

        List<Student> updateStudent = oldStudent.stream().filter(item->students.contains(item)).collect(Collectors.toList());

        List<Student> addStudent = students.stream().filter(student -> !oldStudent.contains(student)).collect(Collectors.toList());

        deleteStudent.stream().forEach(item->studentRepository.deleteById(item.getId()));
        updateStudent.stream().forEach(item->studentRepository.update(item));
        addStudent.stream().forEach(item->{
            item.setPassword(PasswordUtils.passwordEncoder.encode(item.getMssv()));
            studentRepository.save(item);
        });
    }

    public void delete(Student student){
        studentRepository.deleteById(student.getId());
    }
}
