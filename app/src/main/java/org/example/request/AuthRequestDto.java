package org.example.request;


import lombok.*;

@AllArgsConstructor
@Data
@Builder

@NoArgsConstructor
public class AuthRequestDto {
    private String username;
    private String password;
}
