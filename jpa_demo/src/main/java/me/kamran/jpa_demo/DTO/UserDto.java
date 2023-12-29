package me.kamran.jpa_demo.DTO;

import lombok.Data;

@Data
public class UserDto {

        //private Integer id;

        private String name;

        private String surname;

        private String password;
        private String email;

}
