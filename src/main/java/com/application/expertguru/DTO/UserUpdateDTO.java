package com.application.expertguru.DTO;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private String userId;
    private String username;
    private String DOB;
    private String emailId;
}
