package com.hmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hmall.domain.dto.LoginFormDTO;
import com.hmall.domain.po.User;
import com.hmall.domain.vo.UserLoginVO;

/**
 * 用户 服务类
 */
public interface IUserService extends IService<User> {
    /**
     * 用户登录
     * @param loginFormDTO 登录参数
     * @return 登录结果
     */
    UserLoginVO login(LoginFormDTO loginFormDTO);

    /**
     * 扣减余额
     * @param pw 支付密码
     * @param totalFee 支付金额
     */
    void deductMoney(String pw, Integer totalFee);
}
