package com.songs.wallah.response;

import java.util.UUID;

import com.songs.wallah.enums.songs.Language;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserProfile(@NotBlank UUID publicId,@NotBlank String firstName, String middleName, @NotBlank String lastName,
		int age, @NotBlank @Email String email){

}
