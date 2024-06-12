package com.fer.hr.du.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

public class UserDTO {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String username;

    @Getter @Setter
    private Double overallScore;

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", overallScore=" + overallScore +
                '}';
    }
}
