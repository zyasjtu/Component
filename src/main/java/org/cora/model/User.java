package org.cora.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String mobile;
    private String loginName;
    private String password;
    private Date createTime;
    private Date updateTime;
}
