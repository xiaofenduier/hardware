package com.sunshine.hardware.service;

import com.sunshine.hardware.dao.BraceletDao;
import com.sunshine.hardware.model.BraceletData;
import com.sunshine.hardware.model.request.BraceletDataRequest;
import com.sunshine.hardware.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BraceletService {

    @Autowired
    private BraceletDao braceletDao;

    @Value("${BRACELET_TABLENAME}")
    private String braceletTableName;

    @Async
    public void insertBraceletData(BraceletData braceletData){
        braceletDao.insertBraceletData(braceletData, getBraceletTableName());
    }

    private String getBraceletTableName(){
        StringBuilder builder = new StringBuilder();
        builder.append(braceletTableName).append(TimeUtil.getTimeString(TimeUtil.getCurrentMonth().getTime(), TimeUtil.dataMonthStringNotSign));
        return builder.toString();
    }

    public List<BraceletData> selectBraceletData(BraceletDataRequest braceletDataRequest){
        return braceletDao.selectBraceletData(getBraceletTableName(), braceletDataRequest);
    }

    public Long countBraceletData(BraceletDataRequest braceletDataRequest){
        return braceletDao.countBraceletData(getBraceletTableName(), braceletDataRequest);
    }
}
