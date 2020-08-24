package org.yuekeju.sys.user.provider.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yuekeju.common.constants.CommonConstants;
import org.yuekeju.common.entity.user.YuekejuPermissionEntity;
import org.yuekeju.common.util.SnowflakeId;
import org.yuekeju.common.vo.ResultEnum;
import org.yuekeju.common.vo.ResultVO;
import org.yuekeju.common.vo.user.YuekejuPermissionVo;
import org.yuekeju.sys.user.provider.dao.YuekejuPermissionDAO;
import org.yuekeju.sys.user.provider.service.YuekejuPermissionService;

import java.util.*;

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
@Slf4j
public class YuekejuPermissionServiceImpl extends ServiceImpl<YuekejuPermissionDAO, YuekejuPermissionEntity> implements YuekejuPermissionService {

	@Autowired
	private SnowflakeId snowFlakeId; 
	/**
	 * 根据父id查询
	 */
	@Override
	public ResultVO findAllPermission(String prantId, String tokenId) {
		try {
			log.info("进入权限查询列表method ");
			if(prantId==null){
				prantId="-1";
			}
			List<YuekejuPermissionEntity> findAllPermission = baseMapper.findAllPermission(prantId);
			if(findAllPermission==null || findAllPermission.isEmpty()){
				return new ResultVO(ResultEnum.FINDNULLERROR.getCode(), CommonConstants.FALSE, ResultEnum.FINDNULLERROR.getMessage(), null);
			}
			List<YuekejuPermissionVo> findListPermission = findListPermission(findAllPermission);
			return new ResultVO(ResultEnum.SELECTSUCCESS.getCode(), CommonConstants.TRUE, ResultEnum.SELECTSUCCESS.getMessage(), findListPermission);
		} catch (Exception e) {
			log.error("权限查询失败"+e.getMessage());
			e.printStackTrace();
		}
		return new ResultVO(ResultEnum.SYSTEMERROR.getCode(), CommonConstants.FALSE, ResultEnum.SYSTEMERROR.getMessage(), null);
		
	}
	/**
	 * 根据父id查询 查询menu和目录
	 */
	@Override
	public ResultVO findAllPermissionMenu(String prantId, String tokenId) {
		try {
			log.info("进入权限查询列表method ");
			if(prantId==null){
				prantId="-1";
			}
			List<YuekejuPermissionEntity> findAllPermission = baseMapper.findAllByMenuPermission(prantId);
			if(findAllPermission==null || findAllPermission.isEmpty()){
				return new ResultVO(ResultEnum.FINDNULLERROR.getCode(), CommonConstants.FALSE, ResultEnum.FINDNULLERROR.getMessage(), null);
			}
			List<YuekejuPermissionVo> findListPermission = findListPermissionMenu(findAllPermission);
			return new ResultVO(ResultEnum.SELECTSUCCESS.getCode(), CommonConstants.TRUE, ResultEnum.SELECTSUCCESS.getMessage(), findListPermission);
		} catch (Exception e) {
			log.error("权限查询失败"+e.getMessage());
			e.printStackTrace();
		}
		return new ResultVO(ResultEnum.SYSTEMERROR.getCode(), CommonConstants.FALSE, ResultEnum.SYSTEMERROR.getMessage(), null);
	}

	
	/**
	 * 删除
	 */
	@Override
	@Transactional
	public ResultVO deletePermission(String[] id, String tokenId) {
		try {
			log.info("进入权限删除method");
			Set<String> set = new HashSet<String>();
			Map<String,Object> map =  new HashMap<String, Object>();
			for (int i = 0; i < id.length; i++) {
				List<YuekejuPermissionEntity> findAllPermission = baseMapper.findAllPermission(id[i]);
				if(findAllPermission!=null && !findAllPermission.isEmpty()){
					List<YuekejuPermissionVo> findListPermission = this.findListPermission(findAllPermission);
					for (YuekejuPermissionVo yuekejuPermissionVo : findListPermission) {
						set.add(yuekejuPermissionVo.getYuekejuCode());
					}
				}
				set.add(id[i]);
			}
			map.put("delTabStatus", CommonConstants.DEFAULT_DEL_TAG);
			map.put("yuekejuCode", set);
			baseMapper.updatePermissionDelTag(map);
			return new ResultVO(ResultEnum.DELETESUCCESS.getCode(), CommonConstants.TRUE, CommonConstants.PERMISSION_NAME+ResultEnum.DELETESUCCESS.getMessage(), null);
		} catch (Exception e) {
			log.error("权限删除失败"+e.getMessage());
			e.printStackTrace();
		}
		return new ResultVO(ResultEnum.SYSTEMERROR.getCode(), CommonConstants.FALSE, ResultEnum.SYSTEMERROR.getMessage(), null);
	}

	@Override
	@Transactional
	public ResultVO insertPermission(YuekejuPermissionEntity yuekejuPermissionEntity, String tokenId) {
		try {
			log.info("进入权限新增method");
			yuekejuPermissionEntity.setModified("1");
			yuekejuPermissionEntity.setCreater("1");
			yuekejuPermissionEntity.setYuekejuCode(snowFlakeId.nextIdString());
			Integer insert = baseMapper.insert(yuekejuPermissionEntity);
			if(insert==0){
				return new ResultVO(ResultEnum.INSERTERROR.getCode(), CommonConstants.FALSE, CommonConstants.PERMISSION_NAME+ResultEnum.INSERTERROR.getMessage(), null);
			}
			return new ResultVO(ResultEnum.INSERTSUCCESS.getCode(), CommonConstants.TRUE, CommonConstants.PERMISSION_NAME+ResultEnum.INSERTSUCCESS.getMessage(), null);
		} catch (Exception e) {
			log.error("权限新增失败"+e.getMessage());
			e.printStackTrace();
		}
		return new ResultVO(ResultEnum.SYSTEMERROR.getCode(), CommonConstants.FALSE, ResultEnum.SYSTEMERROR.getMessage(), null);
	}
	
	@Override
	public ResultVO findByidPermission(String yuekejuCode, String tokenId) {
		try {
			log.info("根据唯一code查询权限");
			if(yuekejuCode==null || ("").equals(yuekejuCode)){
				return new ResultVO(ResultEnum.PARAMPEATCN.getCode(), CommonConstants.FALSE, ResultEnum.PARAMPEATCN.getMessage(), null);
			}
			List<YuekejuPermissionEntity> findByYuekejuCode = baseMapper.findByYuekejuCode(yuekejuCode);
			if(findByYuekejuCode==null || findByYuekejuCode.isEmpty()){
				return new ResultVO(ResultEnum.FINDNULLERROR.getCode(), CommonConstants.FALSE, ResultEnum.FINDNULLERROR.getMessage(), null);
			}
			return new ResultVO(ResultEnum.SELECTSUCCESS.getCode(), CommonConstants.TRUE, ResultEnum.SELECTSUCCESS.getMessage(), findByYuekejuCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultVO(ResultEnum.SYSTEMERROR.getCode(), CommonConstants.FALSE, ResultEnum.SYSTEMERROR.getMessage(), null);
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
			 yuekejuPermissionList.add(mapVo);
		}
		return yuekejuPermissionList;
		
	}
	

	/**
	 * 递归查询 menu 
	 * @param findList
	 * @return
	 */
	private List<YuekejuPermissionVo> findListPermissionMenu(List<YuekejuPermissionEntity> findList){
		List<YuekejuPermissionVo> yuekejuPermissionList= new ArrayList<YuekejuPermissionVo>();
		DozerBeanMapper mapper = new DozerBeanMapper();
		for (YuekejuPermissionEntity yuekejuPermissionEntity : findList) {
			 List<YuekejuPermissionEntity> findAllPermission = baseMapper.findAllByMenuPermission(yuekejuPermissionEntity.getYuekejuCode());
			 YuekejuPermissionVo mapVo = mapper.map(yuekejuPermissionEntity, YuekejuPermissionVo.class);
			 mapVo.setList(findListPermissionMenu(findAllPermission));
			 yuekejuPermissionList.add(mapVo);
		}
		return yuekejuPermissionList;
		
	}


}
