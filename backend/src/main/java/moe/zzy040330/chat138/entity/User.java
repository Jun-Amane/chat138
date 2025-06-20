/**
 * Package: moe.zzy040330.chat138.entity
 * File: User.java
 * Author: Ziyu ZHOU
 * Date: 19/06/2025
 * Time: 12:34
 * Description: This class represents a User entity with basic information.
 */

package moe.zzy040330.chat138.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;


@Setter
@Getter
public class User {
    private Long id;
    private String code;
    private String name;
    private String password;
    private Integer gender;
    private Date birthday;
    private String phone;
    private String address;
    private Integer role;
    private Integer age;
    private User createdBy;
    private Date creationDate;
    private User modifiedBy;
    private Date modificationDate;

    public User() {
        this.calculateAge();
    }

    public User(Long id, String code, String name, String password, Integer gender, Date birthday, String phone, String address, Integer role, Integer age, User createdBy, Date creationDate, User modifiedBy, Date modificationDate) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.role = role;
        this.age = age;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifiedBy = modifiedBy;
        this.modificationDate = modificationDate;
        this.calculateAge();
    }

    private void calculateAge() {
        if (this.birthday == null) {
            this.age = null;
            return;
        }

        Calendar birth = Calendar.getInstance();
        birth.setTime(this.birthday);
        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);

        if (today.get(Calendar.MONTH) < birth.get(Calendar.MONTH) ||
                (today.get(Calendar.MONTH) == birth.get(Calendar.MONTH) &&
                        today.get(Calendar.DAY_OF_MONTH) < birth.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }

        this.age = age;
    }

}
