package org.yuekeju.sys.user.provider.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yuekeju.common.constants.CommonConstants;
import org.yuekeju.common.entity.user.YuekejuRoleEntity;
import org.yuekeju.common.vo.ResultEnum;
import org.yuekeju.common.vo.ResultVO;
import org.yuekeju.sys.user.provider.dao.YuekejuRoleDAO;
import org.yuekeju.sys.user.provider.dao.YuekejuRolePermissionDAO;
import org.yuekeju.sys.user.provider.service.YuekejuRoleService;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author suzk123
 * @since 2020-07-09
 */
@Service
@Transactional
public class YuekejuRoleServiceImpl extends ServiceImpl<YuekejuRoleDAO, YuekejuRoleEntity> implements YuekejuRoleService {
	@Autowired
	private YuekejuRolePermissionDAO  yuekejuRolePermissionDAO;
	/**
	 * 查询角色列表
	 */
	@SuppressWarnings("all")
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
	@SuppressWarnings("all")
	@Override
	public ResultVO findRoleByCode(Map<String, Object> paramMap) {
		try {
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
	@SuppressWarnings("all")
	@Override
	public ResultVO insertRole(YuekejuRoleEntity yuekejuRoleEntity) {
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
			Integer insert = baseMapper.insert(yuekejuRoleEntity);
			if(yuekejuRoleEntity.getRolePermisionList()!=null && !yuekejuRoleEntity.getRolePermisionList().isEmpty()){
				for (int i = 0; i < yuekejuRoleEntity.getRolePermisionList().size(); i++) {
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
		}
		return  new ResultVO(ResultEnum.SYSTEMERROR.getCode(),CommonConstants.FALSE,ResultEnum.SYSTEMERROR.getMessage(), null);
	}
	
	/**
	 * 根据英文名称和中文名称查询
	 */
	@SuppressWarnings("all")
	@Override
	public ResultVO findRoleNameByCnAndEn(YuekejuRoleEntity yuekejuRoleEntity) {
		
		try {
			Integer findCountBySearch = 0;
			findCountBySearch=baseMapper.findCountBySearch(yuekejuRoleEntity);
			return  new ResultVO(ResultEnum.SELECTSUCCESS.getCode(),CommonConstants.TRUE,CommonConstants.ROLE_NAME+ResultEnum.SELECTSUCCESS.getMessage(), findCountBySearch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  new ResultVO(ResultEnum.SYSTEMERROR.getCode(),CommonConstants.FALSE,ResultEnum.SYSTEMERROR.getMessage(), null);
	}

}
