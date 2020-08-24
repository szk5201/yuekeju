package org.yuekeju.sys.user.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.yuekeju.common.constants.CommonConstants;
import org.yuekeju.common.entity.user.UserEntity;
import org.yuekeju.common.entity.user.YuekejuLoginTypeRelationEntity;
import org.yuekeju.common.entity.user.YuekejuRoleUserEntity;
import org.yuekeju.common.util.GenerateUuid;
import org.yuekeju.common.util.JwtUtil;
import org.yuekeju.common.util.RedisUtil;
import org.yuekeju.common.vo.ResultEnum;
import org.yuekeju.common.vo.ResultVO;
import org.yuekeju.common.vo.user.YuekejuUserVo;
import org.yuekeju.sys.user.provider.dao.UserDAO;
import org.yuekeju.sys.user.provider.dao.YuekejuLoginTypeRelationDAO;
import org.yuekeju.sys.user.provider.dao.YuekejuRoleUserDAO;
import org.yuekeju.sys.user.provider.service.IUserService;

import java.util.Date;
import java.util.List;

/**
 * 2020-07-10
 * @author szk
 * 用户数据服务层实现
 */
@Service
@Slf4j
@SuppressWarnings("all")
public class IUserServiceImpl extends ServiceImpl<UserDAO, UserEntity> implements IUserService {


    @Autowired
    private YuekejuRoleUserDAO yuekejuRoleUserDAO;
    @Autowired
    private YuekejuLoginTypeRelationDAO yuekejuLoginTypeRelationDAO;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public ResultVO findUserByLoginName(YuekejuUserVo yuekejuUserVo) {
        try {
            //转化vo和do对象
            DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
            UserEntity userEntity = dozerBeanMapper.map(yuekejuUserVo, UserEntity.class);
            //根据账号密码判断是否存在
            List<UserEntity> userByLoginName = baseMapper.findUserByLoginName(userEntity);
            if (userByLoginName != null && !userByLoginName.isEmpty()) {
                //查询登陆类型
                EntityWrapper entityWrapper = new EntityWrapper();
                entityWrapper.eq("user_id", userByLoginName.get(0).getYuekejuCode());
                List<YuekejuLoginTypeRelationEntity> list = yuekejuLoginTypeRelationDAO.selectList(entityWrapper);
                Boolean isLoginType = false;
                //判断登陆类型是否存在
                for (YuekejuLoginTypeRelationEntity yuekejuLoginTypeRelation : list) {
                    if (yuekejuLoginTypeRelation.getTypeId() == yuekejuUserVo.getLoginType()) {
                        isLoginType = true;
                    }
                }
                String jwt = JwtUtil.createJWT(System.currentTimeMillis() + "", JSON.toJSONString(userByLoginName.get(0)), null);
                if (isLoginType) {
                    redisUtil.set(jwt, userByLoginName.get(0));
                    return new ResultVO(ResultEnum.LOGINGSUCCESS.getCode(), CommonConstants.TRUE, ResultEnum.LOGINGSUCCESS.getMessage(), jwt);
                }

                return new ResultVO(ResultEnum.LOGINGTYPEROOR.getCode(),
                        CommonConstants.FALSE, ResultEnum.LOGINGTYPEROOR.getMessage(), null);
            }
            return new ResultVO(ResultEnum.LOGINGERROR.getCode(), CommonConstants.FALSE, ResultEnum.LOGINGERROR.getMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultVO(ResultEnum.SYSTEMERROR.getCode(), CommonConstants.FALSE, ResultEnum.SYSTEMERROR.getMessage(), null);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO updateDisableAndLock(UserEntity userEntity, String tokenId) {
        try {
            int updateUser = baseMapper.updateUser(userEntity);
            return new ResultVO(ResultEnum.UPDATESUCCESS.getCode(), CommonConstants.TRUE, ResultEnum.UPDATESUCCESS.getMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return new ResultVO(ResultEnum.UPDATEERROR.getCode(), CommonConstants.FALSE, ResultEnum.UPDATEERROR.getMessage(), null);
    }

    @Override
    public ResultVO findUserAllBySearch(YuekejuUserVo yuekejuUserVo, String tokenId) {
        try {
            if (yuekejuUserVo.getCurrentPage() == 0) {
                yuekejuUserVo.setCurrentPage(CommonConstants.DEFAULT_CURRY_PAGE);
            }
            if (yuekejuUserVo.getPageSize() == 0) {
                yuekejuUserVo.setPageSize(CommonConstants.DEFAULT_SIZE);
            }
            Page<UserEntity> page = new Page<UserEntity>(yuekejuUserVo.getCurrentPage(), yuekejuUserVo.getPageSize());
            List<UserEntity> userAllBySearch = baseMapper.findUserAllBySearch(page, yuekejuUserVo);
            return new ResultVO(ResultEnum.SELECTSUCCESS.getCode(), CommonConstants.TRUE, ResultEnum.SELECTSUCCESS.getMessage(), userAllBySearch);

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

        return new ResultVO(ResultEnum.SELECTERROR.getCode(), CommonConstants.FALSE, ResultEnum.SELECTERROR.getMessage(), null);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO updateUser(UserEntity userEntity, String tokenId) {
        try {
            int updateUser = baseMapper.updateUser(userEntity);
            if (updateUser > 0) {
                EntityWrapper entityWrapper = new EntityWrapper();
                yuekejuRoleUserDAO.delete(entityWrapper.eq("user_id", userEntity.getYuekejuCode()));
                for (YuekejuRoleUserEntity list : userEntity.getYuekejuRoleUserEntityList()) {
                    list.setUserId(userEntity.getYuekejuCode());
                    yuekejuRoleUserDAO.insert(list);
                }
                return new ResultVO(ResultEnum.UPDATESUCCESS.getCode(), CommonConstants.TRUE, ResultEnum.UPDATESUCCESS.getMessage(), null);
            }
            return new ResultVO(ResultEnum.UPDATEERROR.getCode(), CommonConstants.FALSE, ResultEnum.UPDATEERROR.getMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultVO(ResultEnum.SYSTEMERROR.getCode(), CommonConstants.FALSE, ResultEnum.SYSTEMERROR.getMessage(), null);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO deleteUser(String[] yuekejuCode, String tokenId) {
        try {
            EntityWrapper entityWrapper = new EntityWrapper();
            Integer delete = baseMapper.delete(entityWrapper.in("yuekeju_code", yuekejuCode));
            yuekejuRoleUserDAO.delete(entityWrapper.in("user_id", yuekejuCode));
            return new ResultVO(ResultEnum.DELETESUCCESS.getCode(), CommonConstants.TRUE, ResultEnum.DELETESUCCESS.getMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return new ResultVO(ResultEnum.DELETEERROR.getCode(), CommonConstants.FALSE, ResultEnum.DELETEERROR.getMessage(), null);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO insertUser(UserEntity userEntity, String tokenId) {
        try {
            UserEntity entity = JwtUtil.parseJwtAsObject(tokenId);
            userEntity.setCreater(userEntity.getYuekejuCode());
            userEntity.setCreateTime(new Date());
            userEntity.setSalf(GenerateUuid.randomUuid());
            YuekejuUserVo yuekejuUserVo = new YuekejuUserVo();
            yuekejuUserVo.setLoginName(userEntity.getLoginName());
            List<UserEntity> userAllBySearch = baseMapper.findUserAllBySearch(null, yuekejuUserVo);
            if (userAllBySearch != null && !userAllBySearch.isEmpty()) {
                return new ResultVO(ResultEnum.LOGINNAMEREPEATENT.getCode(), CommonConstants.FALSE, ResultEnum.LOGINNAMEREPEATENT.getMessage(), null);
            }
            Integer insert = baseMapper.insert(userEntity);
            for (YuekejuRoleUserEntity list : userEntity.getYuekejuRoleUserEntityList()) {
                list.setUserId(userEntity.getYuekejuCode());
                yuekejuRoleUserDAO.insert(list);
            }
            return new ResultVO(ResultEnum.INSERTSUCCESS.getCode(), CommonConstants.TRUE, ResultEnum.INSERTSUCCESS.getMessage(), insert);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

        }
        return new ResultVO(ResultEnum.INSERTERROR.getCode(), CommonConstants.FALSE, ResultEnum.INSERTERROR.getMessage(), null);
    }
}
