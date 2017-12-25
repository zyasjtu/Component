package org.cora.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserForm {
    @NotBlank(message = "mobile is blank", groups = {AddUser.class})
    @Pattern(regexp = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$", message = "invalid mobile", groups = {AddUser.class})
    private String mobile;

    @NotBlank(message = "loginName is blank", groups = {AddUser.class, CheckUser.class})
    @Size(min = 3, max = 30, message = "invalid loginName", groups = {AddUser.class, CheckUser.class})
    private String loginName;

    @NotBlank(message = "password is blank", groups = {AddUser.class, CheckUser.class})
    @Size(min = 3, max = 30, message = "invalid password", groups = {AddUser.class, CheckUser.class})
    private String password;

    public interface AddUser {
    }

    public interface CheckUser {
    }
}
