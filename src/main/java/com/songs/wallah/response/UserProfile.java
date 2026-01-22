package com.songs.wallah.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserProfile(@NotBlank String firstName, String middleName, @NotBlank String lastName,
		@NotBlank int age, @NotBlank @Email String email){

}
