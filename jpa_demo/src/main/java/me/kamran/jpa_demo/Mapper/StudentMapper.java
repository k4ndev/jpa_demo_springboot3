package me.kamran.jpa_demo.Mapper;

import me.kamran.jpa_demo.DTO.StudentDto;
import me.kamran.jpa_demo.Entity.Student;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDto toDto(Student student);
    Student toStudent(StudentDto dto);
    List<StudentDto> toListStudentDto(List<Student> students);
}
