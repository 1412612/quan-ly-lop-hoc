package service;

import model.Student;
import model.StudentSubject;
import model.Subject;
import org.apache.commons.lang3.ObjectUtils;
import repository.StudentRepository;
import repository.StudentSubjectRepository;
import repository.SubjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentSubjectService {
    private StudentSubjectRepository studentSubjectRepository = new StudentSubjectRepository();
    private StudentRepository studentRepository = new StudentRepository();
    SubjectRepository subjectRepository = new SubjectRepository();

    public List<Student> getListStudentBySSID(int id){
        List<String> mssvs = Optional.ofNullable(studentSubjectRepository.getBySubjectId(id))
                .orElse(new ArrayList<>())
                .stream()
                .map(StudentSubject::getMssv)
                .collect(Collectors.toList());

        if(ObjectUtils.isEmpty(mssvs)) return new ArrayList<>();

        return Optional.ofNullable(studentRepository.getByListMssv(mssvs)).orElse(new ArrayList<>());
    }

    public List<Student> getNotListStudentBySSID(int id){
        List<String> mssvs = Optional.ofNullable(studentSubjectRepository.getBySubjectId(id))
                .orElse(new ArrayList<>())
                .stream()
                .map(StudentSubject::getMssv)
                .collect(Collectors.toList());

        //if(ObjectUtils.isEmpty(mssvs)) return new ArrayList<>();

        return Optional.ofNullable(studentRepository.getByNotListMssv(mssvs)).orElse(new ArrayList<>());
    }

    public void updateBySubjectIdAndMssv(List<StudentSubject> subjects){
        subjects.forEach(item->{
            studentSubjectRepository.updateBySubjectIdAndMssv(item);
        });
    }

    public List<Subject> getListSubjectByMssv(String mssv){
        List<StudentSubject> studentSubjects = studentSubjectRepository.getByMssv(mssv);
        if(ObjectUtils.isEmpty(studentSubjects)){
            return new ArrayList<>();
        }
        List<Integer> subjectIds = studentSubjects.stream().map(StudentSubject::getSubjectId).collect(Collectors.toList());

        return Optional.ofNullable(subjectRepository.getBySubjectIds(subjectIds)).orElse(new ArrayList<>());


    }
}
