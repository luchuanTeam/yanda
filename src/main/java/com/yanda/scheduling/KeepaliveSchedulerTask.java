package com.yanda.scheduling;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@Component
public class KeepaliveSchedulerTask {

	private final static Logger LOG = LoggerFactory.getLogger(KeepaliveSchedulerTask.class);

	@Autowired
	private ThreadPoolTaskScheduler threadPoolTaskScheduler;

	private ScheduledFuture<?> future;
	
	private static final String cron = "* 0/1 * * * ?";
	
	
	public void updateTask() {
		if (future != null) {
			future.cancel(true);//取消任务调度
		}
		initTask();
	}

	@Scheduled(cron = "0 0 0/4 * * ?")
	private void process() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LOG.info("执行定时任务，保持数据库连接不断开，当前时间=[{}]", df.format(new Date()));
	}
	
	//@PostConstruct
	private void initTask() {
		future = threadPoolTaskScheduler.schedule(new Runnable() {

			@Override
			public void run() {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				LOG.info("执行可修改的定时任务，当前时间=[{}]", df.format(new Date()));;
			}
		}, new Trigger() {
			@Override
			public Date nextExecutionTime(TriggerContext triggercontext) {
				CronTrigger trigger = new CronTrigger(cron);// 定时任务触发，可修改定时任务的执行周期
				Date nextExecDate = trigger.nextExecutionTime(triggercontext);
				return nextExecDate;
			}
		});
	}
}
