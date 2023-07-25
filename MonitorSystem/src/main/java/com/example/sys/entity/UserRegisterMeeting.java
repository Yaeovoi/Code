package com.example.sys.entity;

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
@TableName("user_register_meeting")
public class UserRegisterMeeting implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer userId;

    private Integer registerMeetingId;


}
