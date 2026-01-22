package com.songs.wallah.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserSignupRequest(@NotBlank String firstName, String middleName, @NotBlank String lastName,
		@NotBlank int age, @NotBlank @Email String email, @NotBlank String encryptedPassword) {

}
