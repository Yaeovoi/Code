package com.example.sys.service.impl;

import com.example.sys.entity.ResourceInfo;
import com.example.sys.entity.ResourceRequest;
import com.example.sys.mapper.ResourceRequestMapper;
import com.example.sys.service.IResourceRequestService;
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
public class ResourceRequestServiceImpl extends ServiceImpl<ResourceRequestMapper, ResourceRequest> implements IResourceRequestService {

	//根据ID查询耗材申请信息
	@Override
	public ResourceRequest getResourceRequestById(Integer id) {
		ResourceRequest resourceRequest = this.baseMapper.selectById(id);
		return resourceRequest;
	}

	//新增耗材申请信息
	@Override
	public void addResourceRequest(ResourceRequest resourceRequest) {
		LocalDateTime createTime = LocalDateTime.now();
		resourceRequest.setRequestTime(createTime);
		this.baseMapper.insert(resourceRequest);
	}

	//根据ID删除耗材申请信息
	@Override
	public void deleteResourceRequestById(Integer id) {
		this.baseMapper.deleteById(id);
	}

	//修改耗材申请信息及审核状态
	@Override
	public void updateResourceRequest(ResourceRequest resourceRequest) {
		LocalDateTime approvalTime = LocalDateTime.now();
		Integer managerId = resourceRequest.getManagerId();
		if( managerId != null ){
			resourceRequest.setApprovalTime(approvalTime);
		}
		this.baseMapper.updateById(resourceRequest);
	}
}
