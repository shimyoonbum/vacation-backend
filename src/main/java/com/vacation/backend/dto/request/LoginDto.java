package com.vacation.backend.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

   @NotNull
   private String username;

   @NotNull
   @Size(min = 4, max = 100)
   private String password;

   private List<Object> roles;

   public LoginDto(String username) {
      this.username = username;
   }

   public LoginDto(String username, List<Object> roles) {
      this.username = username;
      this.roles = roles;
   }
}