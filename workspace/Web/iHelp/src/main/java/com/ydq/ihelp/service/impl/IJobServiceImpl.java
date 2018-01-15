package com.ydq.ihelp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ydq.ihelp.dao.db.JobMapper;
import com.ydq.ihelp.model.db.Job;
import com.ydq.ihelp.pojo.IJobDetail;
import com.ydq.ihelp.pojo.SelfRequest;
import com.ydq.ihelp.service.IJobService;

@Service("mJobService")
public class IJobServiceImpl extends BaseService implements IJobService {

	private JobMapper mJobMapper;

	@Autowired
	public void setmJobMapper(JobMapper JobMapper) {
		this.mJobMapper = JobMapper;
	}

	@Override
	public List<Job> getItem(SelfRequest request) {
		List<Job> jobs = mJobMapper.selectAll();
		List<Job> list = new ArrayList<Job>();
		list.addAll(jobs);
		return list;
	}

	@Override
	public IJobDetail getItemDetail(SelfRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateItem(SelfRequest request) {
		// TODO Auto-generated method stub
		return false;
	}

}
