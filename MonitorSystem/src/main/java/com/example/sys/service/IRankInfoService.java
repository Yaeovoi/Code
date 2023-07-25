package com.example.sys.service;

import com.example.sys.entity.RankInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CXK
 * @since 2023-07-23
 */
public interface IRankInfoService extends IService<RankInfo> {

	//根据ID查询职称
	RankInfo getRankById(Integer id);

	//新增职称
	void addRankInfo(RankInfo rankInfo);

	//删除职称
	void deleteRankById(Integer id);

	//修改职称
	void updateRank(RankInfo rankInfo);
}
