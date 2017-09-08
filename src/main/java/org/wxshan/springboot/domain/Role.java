package org.wxshan.springboot.domain;

import lombok.Data;
import org.wxshan.springboot.base.BaseEntity;

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

    private String roleName;

    private String description;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private List<Permission> permissionList = new ArrayList<>();
}
