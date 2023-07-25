package com.example.sys.service.impl;

import com.example.sys.entity.ProjectInfo;
import com.example.sys.entity.ResourceInfo;
import com.example.sys.mapper.ResourceInfoMapper;
import com.example.sys.service.IResourceInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CXK
 * @since 2023-07-23
 */
@Service
public class ResourceInfoServiceImpl extends ServiceImpl<ResourceInfoMapper, ResourceInfo> implements IResourceInfoService {

	//根据ID查询采购信息
	@Override
	public ResourceInfo getResourceInfoById(Integer id) {
		ResourceInfo resourceInfo = this.baseMapper.selectById(id);
		return resourceInfo;
	}

	//新增采购信息
	@Override
	public void addResourceInfo(ResourceInfo resourceInfo) {
		LocalDateTime createTime = LocalDateTime.now();
		resourceInfo.setCreateTime(createTime);
		this.baseMapper.insert(resourceInfo);
	}

	//根据ID删除采购信息
	@Override
	public void deleteResourceInfoById(Integer id) {
		this.baseMapper.deleteById(id);
	}

	//修改采购信息信息
	@Override
	public void updateResourceInfo(ResourceInfo resourceInfo) {
		this.baseMapper.updateById(resourceInfo);
	}


}
