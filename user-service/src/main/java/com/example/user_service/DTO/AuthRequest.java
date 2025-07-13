package com.example.user_service.DTO;

import lombok.*;

@Data
@RequiredArgsConstructor
@Getter
@Setter
public class AuthRequest {

    private String login;
    private String password;


}
