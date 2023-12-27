package me.kamran.jpa_demo.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class StudentDto {
    private Integer id;
    private String name;
    private String surname;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;
    private Integer studentNumber;
}
