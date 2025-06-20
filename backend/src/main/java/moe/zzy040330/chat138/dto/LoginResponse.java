/**
 * Package: moe.zzy040330.chat138.dto
 * File: LoginResponse.java
 * Author: Ziyu ZHOU
 * Date: 20/06/2025
 * Time: 15:19
 * Description: DTO for Login response
 */
package moe.zzy040330.chat138.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponse {
    private String token;
    private String userName;
    private Long userId;
    private String userCode;
    private String userRoleCode;

    public LoginResponse() {
    }

    public LoginResponse(String token, String userName, Long userId, String userCode, String userRoleId) {
        this.token = token;
        this.userName = userName;
        this.userId = userId;
        this.userCode = userCode;
        this.userRoleCode = userRoleId;
    }

}
