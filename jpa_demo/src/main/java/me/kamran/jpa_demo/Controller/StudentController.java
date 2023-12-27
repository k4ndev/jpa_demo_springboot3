package me.kamran.jpa_demo.Controller;

import me.kamran.jpa_demo.DTO.StudentDto;
import me.kamran.jpa_demo.Entity.Student;
import me.kamran.jpa_demo.Service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/auth")
    public List<StudentDto> GetAllStudent(){
        return studentService.GetAll();
    }

    @GetMapping("/{id}")
    public StudentDto GetByIdStudent(@PathVariable Integer id){
        return studentService.GetById(id);
    }

    @PostMapping("/create")
    public Boolean Create(@RequestBody Student student){
        return studentService.Create(student);
    }

    @DeleteMapping("/delete/{id}")
    public void Delete (@PathVariable Integer id){
        studentService.Delete(id);
    }

    @PutMapping("/update")
    public void Update (@RequestBody Student student){
        studentService.Update(student);
    }
}
