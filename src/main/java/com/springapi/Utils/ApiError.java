package com.springapi.Utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private HttpStatus status;
    private LocalDateTime timestamp = LocalDateTime.now();
    private String message;
    private String debugMessage;

}
