package com.hmall.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hmall.user.domain.po.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 用户表 Mapper 接口
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 扣减用户余额
     *
     * @param userId   用户id
     * @param totalFee 扣减金额（分）
     */
    @Update("update user set balance = balance - ${totalFee} where id = #{userId}")
    void updateMoney(@Param("userId") Long userId, @Param("totalFee") Integer totalFee);
}
