package com.example.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
@TableName("device_info")
public class DeviceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 设备编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 设备类别
     */
    private String deviceType;

    /**
     * 设备归属部门id
     */
    private Integer deptId;

    /**
     * 设备状态
     */
    private String deviceStatus;

    /**
     * 设备描述信息
     */
    private String deviceDescription;

    /**
     * 设备维修时间
     */
    private LocalDate fixedTime;

    /**
     * 设备信息创建时间
     */
    private LocalDateTime createTime;

    /**
     * 设备信息更新时间
     */
    private LocalDateTime updateTime;


}
