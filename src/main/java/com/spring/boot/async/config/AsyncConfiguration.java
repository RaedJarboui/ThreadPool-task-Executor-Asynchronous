package com.spring.boot.async.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync // enable asynchronous method execution
public class AsyncConfiguration {

	@Bean
	public Executor asyncTaskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor(); // control threads execution
		taskExecutor.setCorePoolSize(4); // number of threads that are always kept in the pool, even if they are idle.
		taskExecutor.setQueueCapacity(150); // number of tasks inside queue to be managed by thread
		taskExecutor.setMaxPoolSize(4); // maximum number of threads that the pool can create to handle tasks.
		taskExecutor.initialize();

		return taskExecutor;
	}
}