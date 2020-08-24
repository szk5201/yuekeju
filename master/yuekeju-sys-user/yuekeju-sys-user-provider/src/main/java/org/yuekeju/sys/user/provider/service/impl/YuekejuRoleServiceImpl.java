package org.yuekeju.sys.user.provider.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.yuekeju.common.constants.CommonConstants;
import org.yuekeju.common.entity.user.YuekejuRoleEntity;
import org.yuekeju.common.util.SnowflakeId;
import org.yuekeju.common.vo.ResultEnum;
import org.yuekeju.common.vo.ResultVO;
import org.yuekeju.sys.user.provider.dao.YuekejuRoleDAO;
import org.yuekeju.sys.user.provider.dao.YuekejuRolePermissionDAO;
import org.yuekeju.sys.user.provider.service.YuekejuRoleService;

import java.util.List;
import java.util.Map;

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
	@Autowired
	private YuekejuRolePermissionDAO  yuekejuRolePermissionDAO;
	@Autowired
	private SnowflakeId snowFlakeId;

	/**
	 * 查询角色列表
	 */
	@SuppressWarnings("all")
	@Override
	public ResultVO findAllBySearch(Map<String, Object> paramMap, String tokenId) {
		try {
			if(paramMap.get("curryPage")==null || paramMap.get("curryPage").equals("")){
				paramMap.put("curryPage", CommonConstants.DEFAULT_CURRY_PAGE);
			}
			if (paramMap.get("pageSize") == null || paramMap.get("pageSize").equals("")) {
				paramMap.put("pageSize", CommonConstants.DEFAULT_SIZE);
			}
			Page<YuekejuRoleEntity> page = new Page<YuekejuRoleEntity>(Integer.parseInt(paramMap.get("curryPage").toString()), Integer.parseInt(paramMap.get("pageSize").toString()));

			List<YuekejuRoleEntity> findAllBySearch = baseMapper.findAllBySearch(page, paramMap);
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
	@SuppressWarnings("all")
	@Override
	public ResultVO findRoleByCode(Map<String, Object> paramMap, String tokenId) {
		try {
			List<YuekejuRoleEntity> findAllBySearch = baseMapper.findAllBySearch(null, paramMap);
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
	@SuppressWarnings("all")
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultVO insertRole(YuekejuRoleEntity yuekejuRoleEntity, String tokenId) {
		try {
			//查询名字是否重复
			YuekejuRoleEntity yuekejuRole=null;
			yuekejuRole = new YuekejuRoleEntity();
			yuekejuRole.setRoleEnName(yuekejuRoleEntity.getRoleEnName());
			Integer findCountBySearch = 0;
			findCountBySearch=baseMapper.findCountBySearch(yuekejuRole);
			if(findCountBySearch>0){
				return  new ResultVO(ResultEnum.INSERTREPEATEN.getCode(),CommonConstants.FALSE,CommonConstants.ROLE_NAME+ResultEnum.INSERTREPEATEN.getMessage(), null);
			}
			yuekejuRole = new YuekejuRoleEntity();
			yuekejuRole.setRoleCnName(yuekejuRoleEntity.getRoleCnName());
			findCountBySearch = baseMapper.findCountBySearch(yuekejuRole);
			if(findCountBySearch>0){
				return  new ResultVO(ResultEnum.INSERTREPEATCN.getCode(),CommonConstants.FALSE,CommonConstants.ROLE_NAME+ResultEnum.INSERTREPEATCN.getMessage(), null);
			}
			//新增赋值
			yuekejuRoleEntity.setCreater("1");
			yuekejuRoleEntity.setModified("1");
			yuekejuRoleEntity.setYuekejuCode(snowFlakeId.nextIdString());
			Integer insert = baseMapper.insert(yuekejuRoleEntity);
			if(yuekejuRoleEntity.getRolePermisionList()!=null && !yuekejuRoleEntity.getRolePermisionList().isEmpty()){
				for (int i = 0; i < yuekejuRoleEntity.getRolePermisionList().size(); i++) {
					yuekejuRoleEntity.getRolePermisionList().get(i).setYuekejuCode(snowFlakeId.nextIdString());
					yuekejuRoleEntity.getRolePermisionList().get(i).setRoleId(yuekejuRoleEntity.getYuekejuCode());
					yuekejuRolePermissionDAO.insert(yuekejuRoleEntity.getRolePermisionList().get(i));
				}
			}
			if(insert>0){
				return  new ResultVO(ResultEnum.INSERTSUCCESS.getCode(),CommonConstants.TRUE,CommonConstants.ROLE_NAME+ResultEnum.INSERTSUCCESS.getMessage(), null);
			}
			return  new ResultVO(ResultEnum.INSERTERROR.getCode(),CommonConstants.FALSE,CommonConstants.ROLE_NAME+ResultEnum.INSERTERROR.getMessage(), null);

		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return  new ResultVO(ResultEnum.SYSTEMERROR.getCode(),CommonConstants.FALSE,ResultEnum.SYSTEMERROR.getMessage(), null);
	}

	/**
	 * 根据英文名称和中文名称查询
	 */
	@SuppressWarnings("all")
	@Override
	public ResultVO findRoleNameByCnAndEn(YuekejuRoleEntity yuekejuRoleEntity, String tokenId) {

		try {
			Integer findCountBySearch = 0;
			findCountBySearch=baseMapper.findCountBySearch(yuekejuRoleEntity);
			return  new ResultVO(ResultEnum.SELECTSUCCESS.getCode(),CommonConstants.TRUE,CommonConstants.ROLE_NAME+ResultEnum.SELECTSUCCESS.getMessage(), findCountBySearch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  new ResultVO(ResultEnum.SYSTEMERROR.getCode(),CommonConstants.FALSE,ResultEnum.SYSTEMERROR.getMessage(), null);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultVO deleteRole(String[] ids, String tokenId) {
		try {
			EntityWrapper entityWrapper = new EntityWrapper();
			Integer deleteRole = baseMapper.delete(entityWrapper.in("yuekeju_code", ids));
			entityWrapper = new EntityWrapper();
			Integer role_id = yuekejuRolePermissionDAO.delete(entityWrapper.in("role_id", ids));
			if (deleteRole > 0) {
				return new ResultVO(ResultEnum.DELETESUCCESS.getCode(), CommonConstants.TRUE, CommonConstants.ROLE_NAME + ResultEnum.DELETESUCCESS.getMessage(), null);
			}
			return new ResultVO(ResultEnum.DELETEERROR.getCode(), CommonConstants.FALSE, CommonConstants.ROLE_NAME + ResultEnum.DELETEERROR.getMessage(), null);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return new ResultVO(ResultEnum.SYSTEMERROR.getCode(), CommonConstants.FALSE, ResultEnum.SYSTEMERROR.getMessage(), null);
	}

}
