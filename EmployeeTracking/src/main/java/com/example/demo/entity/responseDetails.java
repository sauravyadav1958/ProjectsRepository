package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class responseDetails {
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateAndTime;
    private String responseCode;
    private String responseMessage;
    private Object responseData;
}
