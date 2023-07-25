package com.example.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
@TableName("device_request")
public class DeviceRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 申请编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 申请人ID
     */
    private Integer applicantId;

    /**
     * 申请时间
     */
    private LocalDateTime requestTime;

    /**
     * 申请设备ID
     */
    private Integer deviceId;

    /**
     * 申请描述
     */
    private String requestDesc;

    /**
     * 审核人id
     */
    private Integer managerId;

    /**
     * 审核时间
     */
    private LocalDateTime approvalTime;

    /**
     * 审核状态
     */
    private Integer approvalStatus;

    /**
     * 审核描述
     */
    private String approvalDesc;


}
