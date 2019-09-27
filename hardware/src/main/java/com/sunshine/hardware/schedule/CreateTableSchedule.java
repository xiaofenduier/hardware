package com.sunshine.hardware.schedule;

import com.sunshine.hardware.dao.TableDao;
import com.sunshine.hardware.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CreateTableSchedule {

    @Autowired
    private TableDao tableDao;

    @Value("${BRACELET_TABLENAME}")
    private String braceletTableName;

    private static final Logger log = LoggerFactory.getLogger(CreateTableSchedule.class);

    @Scheduled(cron = "0 0 23 28 * ?")
    public void scheduledCreateTable(){
        String braceletTable = braceletTableName + TimeUtil.getTimeString(TimeUtil.getNextMonth().getTime(), TimeUtil.dataMonthStringNotSign);
        tableDao.createBraceletTable(braceletTable);
        log.info("scheduledCreateBraceletTable successed");
    }
}
