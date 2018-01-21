package com.ydq.ihelp.job;

import java.util.ArrayList;
import java.util.List;

public class JobManager extends Thread{

	private boolean isRunning = false;
	
	private static JobManager jobManager = null;
	private List<Job> jobs = new ArrayList<Job>();
	private JobManager() {}
	public synchronized static JobManager getInstance(){
		if(jobManager == null){
			jobManager = new JobManager();
		}
		return jobManager;
	}
	
	public boolean isRuning(){
		return isRunning;
	}
	
	
	public void begin(){
		isRunning = true;
		this.start();
	}
	
	public void shut(){
		isRunning = false;
	}
	
	public void addJob(Job job){
		synchronized (jobs) {
			jobs.add(job);
		}
	}
	
	@Override
	public void run() {
		super.run();
		try {
			synchronized (jobs) {
				List<Job> needRemoveJobs = new ArrayList();
				while (isRunning) {
					for (Job job : jobs) {
						job.runCount ++;
						if(job.runCount >= job.count){
							job.runJob();
							//重至
							job.runCount = 0;
							//停止 job
							if(job.count != -1){
								needRemoveJobs.add(job);
							}
						}
					}
					if(!needRemoveJobs.isEmpty()){
						jobs.remove(needRemoveJobs);
					}
					
					sleep(1000);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
