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
@TableName("user_info")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id,根据用户id的开头来判断权限 1为管理员 2为普通用户
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户所属部门编号
     */
    private Integer deptId;

    /**
     * 用户职称编号
     */
    private Integer rankId;

    /**
     * 用户值日Id
     */
    private Integer cleanId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户电话号码
     */
    private String userPhone;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 用户创建时间
     */
    private LocalDateTime createTime;


}
