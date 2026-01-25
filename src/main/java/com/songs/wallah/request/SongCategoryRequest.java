package com.songs.wallah.request;

import com.songs.wallah.enums.songs.Category;

import jakarta.validation.constraints.NotBlank;

public record SongCategoryRequest (@NotBlank Category category){

}
