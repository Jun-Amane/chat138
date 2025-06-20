/**
 * Package: moe.zzy040330.chat138.controller.dto
 * File: ErrorResponse.java
 * Author: Ziyu ZHOU
 * Date: 20/06/2025
 * Time: 11:02
 * Description: General error response body.
 */
package moe.zzy040330.chat138.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse {
    private Integer status;
    private String message;

    public ErrorResponse() {
    }

    public ErrorResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

}
