package com.sunshine.hardware.netty;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.sunshine.hardware.netty.coder.ByteArrayNewDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
/**
 * TODO: netty配置
 * @author liuyicong
 * @Date 2018年11月26日 上午9:54:46
 */
@Configuration  
public class nettyConfig {
    
    @Value("${boss.thread.count}")  
    private int bossCount;  
  
    @Value("${worker.thread.count}")  
    private int workerCount;  
  
    @Value("${tcp.port}")  
    private int tcpPort;  
  
    @Value("${so.keepalive}")  
    private boolean keepAlive;  
  
    @Value("${so.backlog}")  
    private int backlog;  
      
    @Autowired  
    @Qualifier("stringInitializer")  
    private StringInitializer stringInitializer;  
  
    @SuppressWarnings("unchecked")  
    @Bean(name = "serverBootstrap")  
    public ServerBootstrap bootstrap() {  
        ServerBootstrap b = new ServerBootstrap();  
        b.group(bossGroup(), workerGroup())  
                .channel(NioServerSocketChannel.class)  
                .childHandler(stringInitializer)
                .option(ChannelOption.SO_BACKLOG, 512)          // (5)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.SO_LINGER, 1)
                .option(ChannelOption.SO_RCVBUF, 10 * 1024)
                .option(ChannelOption.SO_SNDBUF, 10 * 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)  // (6)
                .childOption(ChannelOption.TCP_NODELAY, true);  
        Map<ChannelOption<?>, Object> tcpChannelOptions = tcpChannelOptions();  
        Set<ChannelOption<?>> keySet = tcpChannelOptions.keySet();  
        for (@SuppressWarnings("rawtypes")  
        ChannelOption option : keySet) {  
            b.option(option, tcpChannelOptions.get(option));  
        }  
        return b;  
    }  
  
    @Bean(name = "bossGroup", destroyMethod = "shutdownGracefully")  
    public NioEventLoopGroup bossGroup() {  
        return new NioEventLoopGroup(bossCount);  
    }  
  
    @Bean(name = "workerGroup", destroyMethod = "shutdownGracefully")  
    public NioEventLoopGroup workerGroup() {  
        return new NioEventLoopGroup(workerCount);  
    }  
  
    @Bean(name = "tcpSocketAddress")  
    public InetSocketAddress tcpPort() {  
        return new InetSocketAddress(tcpPort);  
    }  
  
    @Bean(name = "tcpChannelOptions")  
    public Map<ChannelOption<?>, Object> tcpChannelOptions() {  
        Map<ChannelOption<?>, Object> options = new HashMap<ChannelOption<?>, Object>();  
        options.put(ChannelOption.SO_KEEPALIVE, keepAlive);  
        options.put(ChannelOption.SO_BACKLOG, backlog);  
        return options;  
    }  
  
    @Bean(name = "byteArrayEncoder")  
    public ByteArrayEncoder byteArrayEncoder() {  
        return new ByteArrayEncoder();  
    }  
  
    @Bean(name = "byteArrayNewDecoder")  
    public ByteArrayNewDecoder byteArrayNewDecoder() {
        return new ByteArrayNewDecoder();  
    }  
  
    /** 
     * Necessary to make the Value annotations work. 
     *   
     * @return 
     */  
    @Bean  
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {  
        return new PropertySourcesPlaceholderConfigurer();  
    }     
}
