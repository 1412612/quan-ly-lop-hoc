package service;

import model.Student;
import model.StudentSubject;
import org.apache.commons.lang3.ObjectUtils;
import repository.StudentRepository;
import repository.StudentSubjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentSubjectService {
    private StudentSubjectRepository studentSubjectRepository = new StudentSubjectRepository();
    private StudentRepository studentRepository = new StudentRepository();

    public List<Student> getListStudentBySSID(int id){
        List<String> mssvs = Optional.ofNullable(studentSubjectRepository.getBySubjectId(id))
                .orElse(new ArrayList<>())
                .stream()
                .map(StudentSubject::getMssv)
                .collect(Collectors.toList());

        if(ObjectUtils.isEmpty(mssvs)) return new ArrayList<>();

        return Optional.ofNullable(studentRepository.getByListMssv(mssvs)).orElse(new ArrayList<>());
    }
}
