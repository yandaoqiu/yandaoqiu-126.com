package com.ydq.ihelp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ydq.ihelp.common.Address;
import com.ydq.ihelp.common.PublicUtil;
import com.ydq.ihelp.dao.db.JobMapper;
import com.ydq.ihelp.model.db.Job;
import com.ydq.ihelp.pojo.IJobDetail;
import com.ydq.ihelp.pojo.SelfRequest;
import com.ydq.ihelp.pojo.job.JobResponse;
import com.ydq.ihelp.service.IJobService;

@Service("mJobService")
public class IJobServiceImpl extends BaseService implements IJobService {

	private JobMapper mJobMapper;

	@Autowired
	public void setmJobMapper(JobMapper JobMapper) {
		this.mJobMapper = JobMapper;
	}

	@Override
	public JobResponse getItem(SelfRequest request,String location,int from,int length) {
		List<Job> jobs = getUserRangeJobs(request, location, from, length);
		JobResponse response = new JobResponse();
		response.setCoentent(jobs);
		return response;
	}

	@Override
	public IJobDetail getItemDetail(SelfRequest request,String jobid) {
	
		return null;
	}

	@Override
	public boolean updateItem(SelfRequest request) {
	
		return false;
	}

	
	/**
	 * 获取范围内的数据
	 * @param request
	 * @param location 坐标值";"
	 * @param from
	 * @param length
	 * @return
	 */
	private List<Job> getUserRangeJobs(SelfRequest request,String location,int from,int length){
		//1.根据当前所在坐标，如果为空 用IP定位，拉取用户 范围内的job
		//2.排序 置顶
		//TODO 3.结合大数据 
		JSONObject json = null;
		try {
			json = PublicUtil.readJsonFromUrl(Address.GD_IP);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		if(josn != null) {
//			MapIP
//		}
		logger.info("getUserRangeJobs-> "+json.toJSONString());
		List<Job> list = new ArrayList<Job>();
		
		return list;
	}
}
