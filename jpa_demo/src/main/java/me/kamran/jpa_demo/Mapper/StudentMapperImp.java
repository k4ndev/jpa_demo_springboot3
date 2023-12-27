package me.kamran.jpa_demo.Mapper;

import me.kamran.jpa_demo.DTO.StudentDto;
import me.kamran.jpa_demo.Entity.Student;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class StudentMapperImp implements StudentMapper{
    @Override
    public StudentDto toDto(Student student) {
        if ( student == null ) {
            return null;
        }
        StudentDto st = new StudentDto();
        st.setId(student.getId());
        st.setName(student.getName());
        st.setSurname(student.getSurname());
        st.setStudentNumber(student.getStudentNumber());
        st.setDateOfBirth(student.getDateOfBirth());
        return st;
    }

    @Override
    public Student toStudent(StudentDto dto) {
        if ( dto == null ) {
            return null;
        }
        Student st = new Student();
        st.setId(dto.getId());
        st.setName(dto.getName());
        st.setSurname(dto.getSurname());
        st.setStudentNumber(dto.getStudentNumber());
        st.setDateOfBirth(dto.getDateOfBirth());
        return st;
    }

    @Override
    public List<StudentDto> toListStudentDto(List<Student> students) {
        if (students.isEmpty())
        {
            return null;
        }
        List<StudentDto> studentDtos = new ArrayList<>(students.size());
        for (Student st : students)
            studentDtos.add(toDto(st));

        return studentDtos;
    }
}
