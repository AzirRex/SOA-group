
package com.webconsumer.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LostAndFound {
    private Integer lost_ID;
    private Integer userId;
    private String location;
    private String time;
    private String information;
    private String name;
    private Integer type;//0为失物1为寻物
}
