/**
 * Package: moe.zzy040330.chat138.dto
 * File: PasswordUpdateRequest.java
 * Author: Ziyu ZHOU
 * Date: 20/06/2025
 * Time: 11:10
 * Description: Password update request body.
 */
package moe.zzy040330.chat138.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PasswordUpdateRequest {
    private String newPassword;

    public PasswordUpdateRequest() {}

}
