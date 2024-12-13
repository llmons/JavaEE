package cn.edu.xmu.javaee.order.config;

import cn.edu.xmu.javaee.core.util.SnowFlakeIdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class OrderConfig {

    private final static Logger logger = LoggerFactory.getLogger(OrderConfig.class);

    @Value("${oomall.datacenter:0}")
    private Long dataCenterId;


    @Bean
    public SnowFlakeIdWorker snowFlakeIdWorker(){
        if (this.dataCenterId > SnowFlakeIdWorker.maxDatacenterId){
            throw new IllegalArgumentException("oomall.datacenter大于最大值"+SnowFlakeIdWorker.maxDatacenterId);
        }

        InetAddress ip = null;
        try {
            ip = Inet4Address.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        String ipAddress = ip.getHostAddress();
        logger.debug("snowFlakeIdWorker: ip = {}",ipAddress);
        Long ipLong = this.ipToLong(ipAddress);
        Long workerId = ipLong % SnowFlakeIdWorker.maxWorkerId;
        return new SnowFlakeIdWorker(workerId, this.dataCenterId);
    }

    // 将127.0.0.1形式的IP地址转换成十进制整数，这里没有进行任何错误处理
    private Long ipToLong(String strIp) {
        Long[] ip = new Long[4];
        // 先找到IP地址字符串中.的位置
        int position1 = strIp.indexOf(".");
        int position2 = strIp.indexOf(".", position1 + 1);
        int position3 = strIp.indexOf(".", position2 + 1);
        // 将每个.之间的字符串转换成整型
        ip[0] = Long.parseLong(strIp.substring(0, position1));
        ip[1] = Long.parseLong(strIp.substring(position1 + 1, position2));
        ip[2] = Long.parseLong(strIp.substring(position2 + 1, position3));
        ip[3] = Long.parseLong(strIp.substring(position3 + 1));
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
    }
}
