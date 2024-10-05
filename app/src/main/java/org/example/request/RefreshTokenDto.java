package org.example.request;

import lombok.*;
import org.checkerframework.checker.units.qual.N;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenDto {
    private String token;
}
