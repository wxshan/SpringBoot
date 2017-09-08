package org.wxshan.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.wxshan.springboot.base.BaseEntity;
import org.wxshan.springboot.utils.serializer.GroupAdd;
import org.wxshan.springboot.utils.serializer.GroupUpdate;
import org.wxshan.springboot.utils.serializer.GroupUpdateForPwd;

import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

@Data
@Table(name = "manager")
public class User extends BaseEntity{

    @NotEmpty(message = "用户名不能为空！", groups = {GroupAdd.class, GroupUpdate.class})
    @Length(min = 4, max = 20, message = "用户名长度为4-20位！",groups = {GroupAdd.class, GroupUpdate.class})
    private String username;

    @JsonIgnore
    @NotEmpty(message = "密码不能为空！", groups = {GroupAdd.class})
    @Length(min =6, max = 50, message = "密码长度为6-50位！",groups = {GroupAdd.class, GroupUpdate.class})
    private String pwd;

    @NotEmpty(message = "姓名不能为空!", groups = {GroupAdd.class, GroupUpdate.class})
    @Length(min = 2, max = 20, message = "姓名长度为2-20位！", groups = {GroupAdd.class, GroupUpdate.class})
    private String name;

    private String phone;

    private String remarks;

    private LocalDateTime createTime;

    private Role role;

    @NotNull(message = "角色不能为空！", groups = {GroupAdd.class,GroupUpdate.class})
    private Integer roleId;

    @Transient
    @NotEmpty(message = "新密码不能为空以及空格！", groups = {GroupUpdateForPwd.class})
    @Length(min = 6, max = 50, message = "密码长度为6-50位！",groups = {GroupUpdateForPwd.class})
    private String newpassword;
}
