package com.ydq.ihelp.job;

public abstract class Job {

	//job 执行时间 s是最小单位
	public long time = 1;
	//job 名称
	public String jobName;
	//循环次数 ,默认一直跑
	public long count = -1;
	
	protected long runCount = 0;
	//任务到了执行
	public abstract void runJob();
}
