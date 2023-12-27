package me.kamran.jpa_demo.Service;

import me.kamran.jpa_demo.DTO.StudentDto;
import me.kamran.jpa_demo.Entity.Student;
import me.kamran.jpa_demo.Mapper.StudentMapper;
import me.kamran.jpa_demo.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private  final StudentMapper studentMapper;
    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public List<StudentDto> GetAll() {
        return studentMapper.toListStudentDto(studentRepository.findAll()) ;
    }

    public StudentDto GetById(Integer id) {
        return studentMapper.toDto(studentRepository.findById(id).orElseThrow());
    }

    public Boolean Create(Student student) {
        studentRepository.save(student);
        return true;
    }

    public void Delete(Integer id) {
        studentRepository.deleteById(id);
    }

    public void Update(Student student) {
        Optional<Student> optionalStudent = studentRepository.findById(student.getId());
        if (optionalStudent.isPresent()) {
            Student st = optionalStudent.get();
            st.setName(student.getName());
            st.setSurname(student.getSurname());
            st.setDateOfBirth(student.getDateOfBirth());
            st.setStudentNumber(student.getStudentNumber());
             studentRepository.save(st);
        }
    }



}
