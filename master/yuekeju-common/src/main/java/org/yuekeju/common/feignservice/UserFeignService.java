package org.yuekeju.common.feignservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yuekeju.common.entity.user.UserEntity;
import org.yuekeju.common.feignservice.impl.UserFeignServiceImpl;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * 2020-7-10
 * @author szk
 *  用户服务远程调用接口
 */
@FeignClient(value="yuekeju-user",fallback=UserFeignServiceImpl.class)
public interface UserFeignService {
	/**
	 * 根据条件全查所有用户
	 * @return UserEntity
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	Page<UserEntity> findUser();
	
	
}
