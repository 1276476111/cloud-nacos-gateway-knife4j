package com.qsn.user.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户表用于测试
 *
 * @author qiusn
 * @date 2021-03-05
 */
@Getter
@Setter
@ToString
@ApiModel(value = "User", description = "用户表用于测试实体")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", position = 1)
    private Long id;

    @ApiModelProperty(value = "名称", position = 1)
    private String name;

    @ApiModelProperty(value = "年龄", position = 1)
    private Integer age;

    @ApiModelProperty(value = "创建时间", position = 1)
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间", position = 1)
    private Date gmtModified;

}