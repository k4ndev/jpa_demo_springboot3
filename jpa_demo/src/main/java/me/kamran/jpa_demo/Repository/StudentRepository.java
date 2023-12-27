package me.kamran.jpa_demo.Repository;

import me.kamran.jpa_demo.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {

}
