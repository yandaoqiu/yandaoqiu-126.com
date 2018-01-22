package com.ydq.ihelp.job;

import com.ydq.ihelp.cache.BlackUserCache;

public class BlackUserJob extends Job {

	public BlackUserJob(){
		this.count = -1;
		this.jobName = "黑名单刷新Job";
		this.time = 60;//1分钟
	}
	@Override
	public void runJob() {
		//刷新
		BlackUserCache.getInstance().refush();
	}

}
