package com.sunshine.hardware.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class ThreadPoolConfig {

    @Bean
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.setCorePoolSize(5);
        threadPoolTaskExecutor.setThreadNamePrefix("insert-bracelet-task");
        threadPoolTaskExecutor.setMaxPoolSize(15);
        threadPoolTaskExecutor.setKeepAliveSeconds(60);
        //缓冲队列，用来缓冲执行任务的队列
        threadPoolTaskExecutor.setQueueCapacity(200);
        //用来设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        //该方法用来设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住。
        threadPoolTaskExecutor.setAwaitTerminationSeconds(60);
        return threadPoolTaskExecutor;
    }
}
