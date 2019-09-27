package com.sunshine.hardware.controller;

import com.sunshine.hardware.model.BraceletData;
import com.sunshine.hardware.model.ReturnBody;
import com.sunshine.hardware.model.request.BraceletDataRequest;
import com.sunshine.hardware.model.response.BraceletDataResponse;
import com.sunshine.hardware.service.BraceletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("bracelet")
public class BraceletController {

    private static final Logger log = LoggerFactory.getLogger(BraceletController.class);

    @Autowired
    private BraceletService braceletService;

    @RequestMapping("getList")
    @ResponseBody
    public ReturnBody getList(@RequestBody Map<String, Object> request) {
        BraceletDataRequest braceletDataRequest = new BraceletDataRequest();
        braceletDataRequest.setPageSize(Integer.parseInt(request.get("pageSize").toString()));
        braceletDataRequest.setStart(Integer.parseInt(request.get("start").toString()));
        braceletDataRequest.setOrderName(request.get("orderName").toString());
        braceletDataRequest.setOrderType(request.get("orderType").toString());
        if (request.containsKey("probeMac")) {
            braceletDataRequest.setProbeMac(request.get("probeMac").toString());
        }
        if (request.containsKey("braceletMac")) {
            braceletDataRequest.setBraceletMac(request.get("braceletMac").toString());
        }
//        if (request.containsKey("uuid")) {
//            braceletDataRequest.setUuid(request.get("uuid").toString());
//        }
        if (request.containsKey("skipModel")) {
            braceletDataRequest.setSkipModel(Integer.parseInt(request.get("skipModel").toString()));
        }

        List<BraceletData> braceletdataList = braceletService.selectBraceletData(braceletDataRequest);
//        if (braceletdataList.isEmpty()) {
//            return ReturnBody.fail(400, "为查询到任何数据");
//        }
        BraceletDataResponse braceletdataResponse = new BraceletDataResponse();
        braceletdataResponse.setRows(braceletdataList);
        braceletdataResponse.setTotal(braceletService.countBraceletData(braceletDataRequest));
        ReturnBody returnBody = ReturnBody.success();
        returnBody.setData(braceletdataResponse);
        return returnBody;
    }
}
