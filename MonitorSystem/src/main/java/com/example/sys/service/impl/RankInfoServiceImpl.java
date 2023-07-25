package com.example.sys.service.impl;

import com.example.sys.entity.DeptInfo;
import com.example.sys.entity.RankInfo;
import com.example.sys.mapper.RankInfoMapper;
import com.example.sys.service.IRankInfoService;
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
public class RankInfoServiceImpl extends ServiceImpl<RankInfoMapper, RankInfo> implements IRankInfoService {

	//根据ID查询职称
	@Override
	public RankInfo getRankById(Integer id) {
		RankInfo rank = this.baseMapper.selectById(id);
		return rank;
	}

	//新增职称
	@Override
	public void addRankInfo(RankInfo rankInfo) {
		this.baseMapper.insert(rankInfo);
	}

	//删除职称
	@Override
	public void deleteRankById(Integer id) {
		this.baseMapper.deleteById(id);
	}

	//修改职称
	@Override
	public void updateRank(RankInfo rankInfo) {
		this.baseMapper.updateById(rankInfo);
	}
}
