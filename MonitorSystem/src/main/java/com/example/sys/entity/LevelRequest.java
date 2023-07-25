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
@TableName("level_request")
public class LevelRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 申请人id
     */
    private Integer applicantId;

    /**
     * 申请开始时间
     */
    private LocalDate beginDate;

    /**
     * 请假结束时间
     */
    private LocalDate endDate;

    /**
     * 请假时长
     */
    private Integer levelTime;

    /**
     * 请假描述
     */
    private String levelDesc;

    /**
     * 0待审批(默认) 1通过 2驳回
     */
    private Integer approvalStatus;

    /**
     * 审核时间
     */
    private LocalDateTime approvalTime;

    /**
     * 审核人的id
     */
    private Integer managerId;

    /**
     * 审核描述
     */
    private String approvalDesc;


}
