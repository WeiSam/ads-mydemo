package io.batcloud.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.*;

/**
 * @Description spring线程池,方法上加@Async("asyncServiceExecutor")使用
 * @author longzanzheng
 * @dateTime 2019年1月8日 上午11:36:00
 */
@Configuration
@EnableAsync
@Slf4j
public class ExecutorConfig {

	@Bean
	public Executor asyncServiceExecutor() {
		log.info("start asyncServiceExecutor");
		// 使用VisiableThreadPoolTaskExecutor
		ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
		// 配置核心线程数
		executor.setCorePoolSize(4);
		// 配置最大线程数
		executor.setMaxPoolSize(4);
		// 配置队列大小
		executor.setQueueCapacity(0);
		// 配置线程池中的线程的名称前缀
		executor.setThreadNamePrefix("async-service-");

		// rejection-policy：当pool已经达到max size的时候，如何处理新任务
		// CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
		executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				log.info("");
			}
		});
		// 执行初始化
		executor.initialize();
		return executor;
	}

	@Bean
	public Executor orderExportExecutor() {
		log.info("start orderExportExecutor");
		// 使用VisiableThreadPoolTaskExecutor
		ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
		int cpu = Runtime.getRuntime().availableProcessors();
		// 配置核心线程数
		executor.setCorePoolSize(1);
		// 配置最大线程数
		executor.setMaxPoolSize(2);
		// 配置队列大小
		executor.setQueueCapacity(2);
		// 配置线程池中的线程的名称前缀
		executor.setThreadNamePrefix("async-orderExport-");

		// rejection-policy：当pool已经达到max size的时候，如何处理新任务
		// CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
		executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				log.info("orderExportExecutor.队列已满");
				try {
					executor.getQueue().put(r);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				log.info("orderExportExecutor.拒绝请求");
			}
		});
		// 执行初始化
		executor.initialize();
		return executor;
	}

	@Bean
	public Executor orderExportExecutor1() {
		log.info("start orderExportExecutor");
		// 使用VisiableThreadPoolTaskExecutor
		ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
		int cpu = Runtime.getRuntime().availableProcessors();
		// 配置核心线程数
		executor.setCorePoolSize(1);
		// 配置最大线程数
		executor.setMaxPoolSize(2);
		// 配置队列大小
		executor.setQueueCapacity(12);
		// 配置线程池中的线程的名称前缀
		executor.setThreadNamePrefix("async-orderExport-");

		// rejection-policy：当pool已经达到max size的时候，如何处理新任务
		// CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		// 执行初始化
		executor.initialize();
		return executor;
	}

	public class VisiableThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {
		private static final long serialVersionUID = 1L;

		private void showThreadPoolInfo(String prefix) {
			ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();

			if (null == threadPoolExecutor) {
				return;
			}

			log.info("{}, {},taskCount [{}], completedTaskCount [{}], activeCount [{}], queueSize [{}]", this.getThreadNamePrefix(), prefix, threadPoolExecutor.getTaskCount(),
					threadPoolExecutor.getCompletedTaskCount(), threadPoolExecutor.getActiveCount(), threadPoolExecutor.getQueue().size());
		}

		@Override
		public void execute(Runnable task) {
			showThreadPoolInfo("1. do execute");
			super.execute(task);
		}

		@Override
		public void execute(Runnable task, long startTimeout) {
			showThreadPoolInfo("2. do execute");
			super.execute(task, startTimeout);
		}

		@Override
		public Future<?> submit(Runnable task) {
			showThreadPoolInfo("1. do submit");
			return super.submit(task);
		}

		@Override
		public <T> Future<T> submit(Callable<T> task) {
			showThreadPoolInfo("2. do submit");
			return super.submit(task);
		}

		@Override
		public ListenableFuture<?> submitListenable(Runnable task) {
			showThreadPoolInfo("1. do submitListenable");
			return super.submitListenable(task);
		}

		@Override
		public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
			showThreadPoolInfo("2. do submitListenable");
			return super.submitListenable(task);
		}
	}
}
