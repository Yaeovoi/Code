package com.yupi.usercenter.service;

import com.yupi.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户服务
 *
* @author 莜莜
*
*/
public interface UserService extends IService<User> {


	/**
	 * 用户注册
	 * @param userAccount 用户账户
	 * @param userPassword  用户密码
	 * @param checkPassword 校验密码
	 * @return  新用户 Id
	 */
	long userRegister(String userAccount, String userPassword, String checkPassword);

	/**
	 *
	 * @param userAccount   用户账户
	 * @param userPassword  用户密码
	 * @return  脱敏后的用户信息
	 */
	User userLogin(String userAccount, String userPassword, HttpServletRequest request);

	/**
	 * 用户脱敏
	 *
	 * @param originUser
	 * @return
	 */
	User getSafetyUser(User originUser);
}
