package com.restfull.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class RegisterUserRequest {

    @NotBlank
    @Size(max=100)
    private String username;

    @NotBlank
    @Size(max=100)
    private String password;

    @NotBlank
    @Size(max=100)
    private String name;

}
