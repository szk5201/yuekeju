package org.yuekeju.sys.user.provider.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yuekeju.common.constants.CommonConstants;
import org.yuekeju.common.entity.user.YuekejuPermissionEntity;
import org.yuekeju.common.vo.ResultEnum;
import org.yuekeju.common.vo.ResultVO;
import org.yuekeju.common.vo.user.YuekejuPermissionVo;
import org.yuekeju.sys.user.provider.dao.YuekejuPermissionDAO;
import org.yuekeju.sys.user.provider.service.YuekejuPermissionService;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author suzk123
 * @since 2020-07-20
 */
@Service
@SuppressWarnings("all")
@Transactional
public class YuekejuPermissionServiceImpl extends ServiceImpl<YuekejuPermissionDAO, YuekejuPermissionEntity> implements YuekejuPermissionService {

	
	@Override
	public ResultVO findAllPermission(String prantId) {
		if(prantId==null){
			prantId="0";
		}
		List<YuekejuPermissionEntity> findAllPermission = baseMapper.findAllPermission(prantId);
		if(findAllPermission==null || findAllPermission.isEmpty()){
			return new ResultVO(ResultEnum.FINDNULLERROR.getCode(), CommonConstants.FALSE, ResultEnum.FINDNULLERROR.getMessage(), null);
		}
		List<YuekejuPermissionVo> findListPermission = findListPermission(findAllPermission);
		return new ResultVO(ResultEnum.SELECTSUCCESS.getCode(), CommonConstants.TRUE, ResultEnum.SELECTSUCCESS.getMessage(), findListPermission);
	}

	
	
	
	/**
	 * 递归查询
	 * @param findList
	 * @return
	 */
	private List<YuekejuPermissionVo> findListPermission(List<YuekejuPermissionEntity> findList){
		List<YuekejuPermissionVo> yuekejuPermissionList= new ArrayList<YuekejuPermissionVo>();
		DozerBeanMapper mapper = new DozerBeanMapper();
		for (YuekejuPermissionEntity yuekejuPermissionEntity : findList) {
			 List<YuekejuPermissionEntity> findAllPermission = baseMapper.findAllPermission(yuekejuPermissionEntity.getYuekejuCode());
			 YuekejuPermissionVo mapVo = mapper.map(yuekejuPermissionEntity, YuekejuPermissionVo.class);
			 mapVo.setList(findListPermission(findAllPermission));
			// return ;
			 yuekejuPermissionList.add(mapVo);
		}
		return yuekejuPermissionList;
		
	} 
	
	
	
	
	
	
	
}
