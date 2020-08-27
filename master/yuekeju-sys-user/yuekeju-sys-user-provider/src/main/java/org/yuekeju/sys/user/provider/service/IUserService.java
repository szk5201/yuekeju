package org.yuekeju.sys.user.provider.service;

import com.baomidou.mybatisplus.service.IService;
import org.yuekeju.common.entity.user.UserEntity;
import org.yuekeju.common.vo.ResultVO;
import org.yuekeju.common.vo.user.YuekejuUserVo;

/**
 * 2020-07-10
 * @author szk
 * 管理用户数据服务层
 */
public interface IUserService extends IService<UserEntity>{
    /**
     * 根据账号密码查询是否存在此用户
     *
     * @param yuekejuUserVo
     * @return
     */
    ResultVO findUserByLoginName(YuekejuUserVo yuekejuUserVo);

    /**
     * 根据条件查询 全查 并分页
     *
     * @param yuekejuUserVo
     * @return
     */
    ResultVO findUserAllBySearch(YuekejuUserVo yuekejuUserVo);

    /**
     * 修改用户信息
     *
     * @param userEntity
     * @return
     */
    ResultVO updateUser(UserEntity userEntity);

    /**
     * 删除用户信息 批量
     *
     * @param yuekejuCode
     * @return
     */
    ResultVO deleteUser(String[] yuekejuCode);

    /**
     * 新增用户信息
     *
     * @param userEntity
     * @return
     */
    ResultVO insertUser(UserEntity userEntity);

    /**
     * 修改锁定状态 和禁用状态
     *
     * @param userEntity
     * @return
     */
    ResultVO updateDisableAndLock(UserEntity userEntity);

}
