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
@TableName("register_record")
public class RegisterRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 签到会议ID
     */
    private Integer registerMeetingId;

    /**
     * 签到时间
     */
    private LocalDateTime signInTime;

    /**
     * 0表示已签退	1表示已签到
     */
    private Integer status;

    /**
     * user_id 用户ID 外键
     */
    private Integer userId;

    /**
     * 签退时间
     */
    private LocalDateTime signOutTime;

    /**
     * 签到时间-签退时间 存秒
     */
    private Integer times;


}
