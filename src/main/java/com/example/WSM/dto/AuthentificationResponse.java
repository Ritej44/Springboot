package com.example.WSM.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthentificationResponse {


    String status;
    private String token;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class Builder {
        private AuthentificationResponse response = new AuthentificationResponse();

        public Builder status(String status) {
            response.status = status;
            return this;
        }

        public Builder token(String token) {
            response.token = token;
            return this;
        }

        public AuthentificationResponse build() {
            return response;
        }
    }

}

