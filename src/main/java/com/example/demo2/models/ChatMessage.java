package com.example.demo2.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder @AllArgsConstructor @NoArgsConstructor
@Data
public class ChatMessage {

    private String sender;
    private String content;
    private LocalDateTime timestamp;
    
}
