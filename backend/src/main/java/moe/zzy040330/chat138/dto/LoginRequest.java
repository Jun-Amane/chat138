/**
 * Package: moe.zzy040330.chat138.dto
 * File: LoginRequest.java
 * Author: Ziyu ZHOU
 * Date: 20/06/2025
 * Time: 15:14
 * Description: DTO for Login request
 */
package moe.zzy040330.chat138.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {
    private String username;
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
