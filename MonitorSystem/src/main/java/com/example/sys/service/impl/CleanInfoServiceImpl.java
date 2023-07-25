package com.example.sys.service.impl;

import com.example.sys.entity.CleanInfo;
import com.example.sys.entity.DeptInfo;
import com.example.sys.mapper.CleanInfoMapper;
import com.example.sys.service.ICleanInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CXK
 * @since 2023-07-23
 */
@Service
public class CleanInfoServiceImpl extends ServiceImpl<CleanInfoMapper, CleanInfo> implements ICleanInfoService {

	//根据ID查询值日信息
	@Override
	public CleanInfo getCleanInfoById(Integer id) {
		CleanInfo cleanInfo = this.baseMapper.selectById(id);
		return cleanInfo;
	}

	//新增值日信息
	@Override
	public void addCleanInfo(CleanInfo cleanInfo) {
		this.baseMapper.insert(cleanInfo);
	}

	//根据ID删除值日信息
	@Override
	public void deleteCleanInfoById(Integer id) {
		this.baseMapper.deleteById(id);
	}

	//修改值日信息
	@Override
	public void updateCleanInfo(CleanInfo cleanInfo) {
		this.baseMapper.updateById(cleanInfo);
	}
}
