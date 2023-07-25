package com.example.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author CXK
 * @since 2023-07-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("project_info")
public class ProjectInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String projectName;

    private String projectType;

    private String projectDesc;

    private String projectSchedule;

    private Integer leaderId;


}
