package com.example.analytic_service.entity.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {

    Long userId;
    String content;
    PostStatus status;
    RejectionReason rejectionReason;
}
