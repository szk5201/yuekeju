package org.yuekeju.gateway.feiginservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yuekeju.common.entity.user.UserEntity;
import org.yuekeju.gateway.feiginservice.impl.UserFeignServiceImpl;

import com.baomidou.mybatisplus.plugins.Page;

@FeignClient(value="yuekeju-user",fallback=UserFeignServiceImpl.class)
public interface UserFeignService {

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	Page<UserEntity> findUser();
	
	
}
