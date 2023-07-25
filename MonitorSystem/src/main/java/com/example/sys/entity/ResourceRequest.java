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
@TableName("resource_request")
public class ResourceRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 申请编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 申请资源编号
     */
    private Integer resourceId;

    /**
     * 申请者ID
     */
    private Integer applicantId;

    /**
     * 申请时间
     */
    private LocalDateTime requestTime;

    /**
     * 申请数量
     */
    private Integer count;

    /**
     * 申请描述
     */
    private String requestDesc;

    /**
     * 紧急等级	1级为不紧急	2级为比较紧急，但是还有一定的准备期限	3级为非常紧急，必须很快就要使用,	默认为1
     */
    private Integer emergencyLevel;

    /**
     * 审核人ID
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

}
