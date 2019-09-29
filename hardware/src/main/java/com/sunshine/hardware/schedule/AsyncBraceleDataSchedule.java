package com.sunshine.hardware.schedule;

import com.google.gson.Gson;
import com.sunshine.hardware.model.BraceletData;
import com.sunshine.hardware.model.request.BraceletDataRequest;
import com.sunshine.hardware.service.BraceletService;
import com.sunshine.hardware.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AsyncBraceleDataSchedule {

    private static final Logger log = LoggerFactory.getLogger(AsyncBraceleDataSchedule.class);

    @Autowired
    private BraceletService braceletService;

    @Scheduled(cron = "0/15 * * * * ?")
    public void exportData(){
        BraceletDataRequest braceletDataRequest = new BraceletDataRequest();
        braceletDataRequest.setBeginTime(System.currentTimeMillis() - 15 * 1000);
        braceletDataRequest.setEndTime(System.currentTimeMillis());
        List<BraceletData> braceletDataList = braceletService.selectBraceletData(braceletDataRequest);

        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("data", gson.toJson(braceletDataList));
        log.info(gson.toJson(map));
        log.info("---------"+gson.toJson(map).getBytes().length);
        HttpUtil.sendPost(map, "http://www.sunshineforce.com:7014/data/data/getData");
        //HttpUtil.sendPost(map, "http://localhost:8888/data/getData");
    }
}
