package com.ivan.spring.zuul.utils;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserContext {
    private String correlationId = new String();
    private String authToken = new String();
    private String userId = new String();
    private String orgId = new String();
}
