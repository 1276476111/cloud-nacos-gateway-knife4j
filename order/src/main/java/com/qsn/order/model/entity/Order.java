package com.qsn.order.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


/**
 * @author qiusn
 * @date 2021-03-05
 */
@Getter
@Setter
@ToString
//@ApiModel(value = "Order", description = "实体")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    //    @ApiModelProperty(value = "", position = 1)
    private Long id;

    //    @ApiModelProperty(value = "", position = 1)
    private Integer userId;

    //    @ApiModelProperty(value = "", position = 1)
    private String commodityCode;

    //    @ApiModelProperty(value = "", position = 1)
    private Integer count;

    //    @ApiModelProperty(value = "", position = 1)
    private Integer money;

}