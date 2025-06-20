/**
 * Package: moe.zzy040330.chat138.dto
 * File: UserRequest.java
 * Author: Ziyu ZHOU
 * Date: 20/06/2025
 * Time: 11:30
 * Description: General User Entity is request bodies.
 */
package moe.zzy040330.chat138.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    private Long id;
    private String code;
    private String name;
    private String password;
    private Integer gender;
    private String birthday;
    private String phone;
    private String address;
    private Integer roleId;
    private Integer age;

    public UserDto() {
    }

    public UserDto(Long id, String code, String name, String password, Integer gender, String birthday, String phone, String address, Integer roleId, Integer age) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.roleId = roleId;
        this.age = age;
    }

}
