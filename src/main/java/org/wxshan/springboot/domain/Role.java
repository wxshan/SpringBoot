package org.wxshan.springboot.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.wxshan.springboot.base.BaseEntity;
import org.wxshan.springboot.utils.serializer.GroupAdd;
import org.wxshan.springboot.utils.serializer.GroupUpdate;

import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

@Data
@Table(name = "Role")
public class Role extends BaseEntity{

    @NotEmpty(message = "角色名称不能为空！")
    @Length(min = 2, max = 20, message = "角色长度为2-20位！",groups = {GroupUpdate.class, GroupAdd.class})
    private String roleName;

    @Length(max = 100, message = "备注长度不能多于100位！",groups = {GroupAdd.class, GroupUpdate.class})
    private String description;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private List<Permission> permissionList = new ArrayList<>();
}
