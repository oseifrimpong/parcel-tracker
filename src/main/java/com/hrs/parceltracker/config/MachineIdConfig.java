package com.hrs.parceltracker.config;

import com.hrs.parceltracker.util.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@Configuration
public class MachineIdConfig {
    @Resource

    @Value("${application.snowflake.data-center}")
    private Long dataCenterId;

    /**
     * get IP address
     *
     * @return
     * @throws UnknownHostException
     */
    private String getIPAddress() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        return address.getHostAddress();
    }

    /**
     * hash machine IP to initialize a machine ID
     *
     * @throws UnknownHostException
     */
    @Bean
    public SnowflakeIdWorker initMachineId() throws UnknownHostException {
        /**
         * local IP address
         */
        String localIp = getIPAddress();

        int ipint = 0;
        try {
            long ipLong = Long.parseLong(localIp.replace(".", ""));
            ipint = new BigDecimal(Long.hashCode(ipLong)).abs().intValue();
        } catch (NumberFormatException e) {
            log.warn("Can't convert ip [{}] to number", localIp);
        }
        int machineId = ipint % ((int) SnowflakeIdWorker.MAX_WORKER_ID + 1);
        log.info("initialize machine_id :{}", machineId);

        return new SnowflakeIdWorker(machineId, dataCenterId);
    }
}
