package org.wxshan.springboot.base;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/6 0006.
 */
@Data
public class BaseEntity implements Serializable{

    @Id
    @NotNull(message = "主键id不能为空！")
    private Integer id;

    @Transient
    @JsonIgnore
    private Integer pageNo = 1; //当前页数

    @Transient
    @JsonIgnore
    private Integer pageSize = 10; //每页显示的条数


}
