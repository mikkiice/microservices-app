package com.example.post_service.Model.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PostRequest {

    @NotBlank(message = "Заголовок обязательно нужно заполнить.")
    private String title;
    @NotBlank(message = "Содержимое обязательно")
    private String content;
    @NotNull(message = "заполните userId")
    private Long userId;
}
