package org.wxshan.springboot.domain;

import lombok.Data;
import org.wxshan.springboot.base.BaseEntity;

import javax.persistence.Table;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

@Data
@Table(name = "permission")
public class Permission extends BaseEntity{

    //权限所属
    private String category;
    //权限名称
    private String permissionName;
    //用于显示权限的名称
    private String permissionDisplayName;
}
