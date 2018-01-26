package com.ydq.ihelp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydq.ihelp.common.Address;
import com.ydq.ihelp.common.PublicUtil;
import com.ydq.ihelp.dao.db.JobMapper;
import com.ydq.ihelp.model.db.Job;
import com.ydq.ihelp.pojo.IJobDetail;
import com.ydq.ihelp.pojo.SelfRequest;
import com.ydq.ihelp.pojo.job.JobResponse;
import com.ydq.ihelp.pojo.map.MapIP;
import com.ydq.ihelp.service.IJobService;

@Service("mJobService")
public class IJobServiceImpl extends BaseService implements IJobService {

	private JobMapper mJobMapper;

	@Autowired
	public void setmJobMapper(JobMapper JobMapper) {
		this.mJobMapper = JobMapper;
	}

	@Override
	public JobResponse getItem(SelfRequest request,String city,String fromId,int length) {
		List<Job> jobs = getUserRangeJobs(request, city, fromId, length);
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
	 * @param city 
	 * @param from
	 * @param length
	 * @return
	 */
	private List<Job> getUserRangeJobs(SelfRequest request,String city,String fromId,int length){
		//1.根据当前所在坐标，如果为空 用IP定位，拉取用户 范围内的job
		//2.排序 置顶
		//TODO 3.结合大数据 
	
		if(!StringUtils.isEmpty(city)) {
			
		}else {
			//查询城市为空 查询全国
		}
//			MapIP mapip = null;
//			try {
//				mapip = PublicUtil.readJsonFromUrl(Address.GD_IP,MapIP.class);
//				logger.info("getUserRangeJobs-> "+mapip.city+ " "+mapip.info);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//			if(mapip != null && "10000".equals(mapip.infocode)) {
//				String province = mapip.province;
//				String city = mapip.city;
//				
//			}
		
		
		
		List<Job> list = new ArrayList<Job>();
		
		return list;
	}
}
