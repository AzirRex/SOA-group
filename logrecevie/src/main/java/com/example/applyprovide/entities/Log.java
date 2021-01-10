package com.example.applyprovide.entities;

import antlr.CSharpCodeGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Log implements Serializable {

    private Integer log_id;
    private String log_information;

 public String getLog_information()
 {
     return  this.log_information;
 }

    public Integer getLog_id()
    {
        return  this.log_id;
    }
}
