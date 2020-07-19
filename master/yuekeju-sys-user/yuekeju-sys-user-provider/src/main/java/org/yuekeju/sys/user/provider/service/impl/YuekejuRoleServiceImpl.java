package org.yuekeju.sys.user.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.yuekeju.common.constants.CommonConstants;
import org.yuekeju.common.entity.system.YuekejuAppBannerEntity;
import org.yuekeju.common.entity.user.YuekejuRoleEntity;
import org.yuekeju.common.vo.ResultEnum;
import org.yuekeju.common.vo.ResultVO;
import org.yuekeju.sys.user.provider.dao.YuekejuRoleDAO;
import org.yuekeju.sys.user.provider.service.YuekejuRoleService;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author suzk123
 * @since 2020-07-09
 */
@Service
public class YuekejuRoleServiceImpl extends ServiceImpl<YuekejuRoleDAO, YuekejuRoleEntity> implements YuekejuRoleService {
	
	/**
	 * 查询角色列表
	 */
	@Override
	public ResultVO findAllBySearch(Map<String, Object> paramMap) {
		try {
			if(paramMap.get("curryPage")==null || paramMap.get("curryPage").equals("")){
				paramMap.put("curryPage", CommonConstants.DEFAULT_CURRY_PAGE);
			}
			if(paramMap.get("size")==null || paramMap.get("size").equals("")){
				paramMap.put("size", CommonConstants.DEFAULT_SIZE);
			}
			Page<YuekejuRoleEntity> page = new Page<YuekejuRoleEntity>(Integer.parseInt(paramMap.get("curryPage").toString()), Integer.parseInt(paramMap.get("size").toString()));
			List<YuekejuRoleEntity> findAllBySearch = baseMapper.findAllBySearch(paramMap);
			if(findAllBySearch!=null && !findAllBySearch.isEmpty()){
				Page<YuekejuRoleEntity> setRecords = page.setRecords(findAllBySearch);
				return  new ResultVO(ResultEnum.SELECTSUCCESS.getCode(),CommonConstants.TRUE,CommonConstants.ROLE_NAME+ResultEnum.SELECTSUCCESS.getMessage(), setRecords);
			}
			return  new ResultVO(ResultEnum.FINDNULLERROR.getCode(),CommonConstants.FALSE,ResultEnum.FINDNULLERROR.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  new ResultVO(ResultEnum.SYSTEMERROR.getCode(),CommonConstants.FALSE,ResultEnum.SYSTEMERROR.getMessage(), null);
	}
	/**
	 * 根据id 或者不分页查询
	 */
	@Override
	public ResultVO findRoleByCode(Map<String, Object> paramMap) {
		try {
			
			if(paramMap.get("curryPage")==null || paramMap.get("curryPage").equals("")){
				paramMap.put("curryPage", CommonConstants.DEFAULT_CURRY_PAGE);
			}
			if(paramMap.get("size")==null || paramMap.get("size").equals("")){
				paramMap.put("size", CommonConstants.DEFAULT_SIZE);
			}
			
			List<YuekejuRoleEntity> findAllBySearch = baseMapper.findAllBySearch(paramMap);
			if(findAllBySearch!=null && !findAllBySearch.isEmpty()){
				return  new ResultVO(ResultEnum.SELECTSUCCESS.getCode(),CommonConstants.TRUE,CommonConstants.ROLE_NAME+ResultEnum.SELECTSUCCESS.getMessage(), findAllBySearch);
			}
			return  new ResultVO(ResultEnum.FINDNULLERROR.getCode(),CommonConstants.FALSE,ResultEnum.FINDNULLERROR.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  new ResultVO(ResultEnum.SYSTEMERROR.getCode(),CommonConstants.FALSE,ResultEnum.SYSTEMERROR.getMessage(), null);
	}
	/**
	 * 新增
	 */
	@Override
	public ResultVO insertRole(YuekejuRoleEntity yuekejuRoleEntity) {
		try {
			yuekejuRoleEntity.setCreater("1");
			yuekejuRoleEntity.setModified("1");
			yuekejuRoleEntity.setYuekejuCode(System.currentTimeMillis()+"");
			Integer insert = baseMapper.insert(yuekejuRoleEntity);
			if(insert>0){
				return  new ResultVO(ResultEnum.INSERTSUCCESS.getCode(),CommonConstants.TRUE,CommonConstants.ROLE_NAME+ResultEnum.INSERTSUCCESS.getMessage(), null);
			}
			return  new ResultVO(ResultEnum.INSERTERROR.getCode(),CommonConstants.TRUE,CommonConstants.ROLE_NAME+ResultEnum.INSERTERROR.getMessage(), null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  new ResultVO(ResultEnum.SYSTEMERROR.getCode(),CommonConstants.FALSE,ResultEnum.SYSTEMERROR.getMessage(), null);
	}

}
