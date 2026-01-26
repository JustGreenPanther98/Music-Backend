package com.songs.wallah.request;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DeleteUserRequest(@NotBlank UUID publicId,@NotBlank @Email String email) {

}