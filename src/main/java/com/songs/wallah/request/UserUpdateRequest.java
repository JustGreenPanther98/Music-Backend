package com.songs.wallah.request;

import jakarta.validation.constraints.NotBlank;

public record UserUpdateRequest(@NotBlank String firstName,String middleName,@NotBlank String lastName,int age) {

}
