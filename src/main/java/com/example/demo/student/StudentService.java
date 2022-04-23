package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void saveStudent(Student student) {
        Optional<Student> studentEmail = studentRepository.findStudentByEmail(student.getEmail());
        if(studentEmail.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        try{
            studentRepository.deleteById(id);
        }
        catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Transactional
    public void updateStudent(Long id, Student student) {
        try{
            Student toUpdate = studentRepository.findById(id).get();

            if(student.getName()!=null) toUpdate.setName(student.getName());
            if(student.getEmail()!=null){
                Optional<Student> studentEmail = studentRepository.findStudentByEmail(student.getEmail());
                if(studentEmail.isPresent()) throw new IllegalStateException("email taken");
                toUpdate.setEmail(student.getEmail());
            }
            if(student.getDob()!=null) toUpdate.setDob(student.getDob());

            studentRepository.save(toUpdate);
        }
        catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }
    }
}
