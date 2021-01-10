
package com.example.lostandfoundprovider.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("失物基本信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LostAndFound {
    @ApiModelProperty("Id")
    private Integer lost_ID;
    @ApiModelProperty("用户Id")
    private Integer userId;
    @ApiModelProperty("拾取地点")
    private String location;
    @ApiModelProperty("拾取事件")
    private String time;
    @ApiModelProperty("描述")
    private String information;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("类型")
    private Integer type;//0为失物1为寻物
}
