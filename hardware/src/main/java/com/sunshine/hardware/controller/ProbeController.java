package com.sunshine.hardware.controller;

import com.cositea.gateway.message.type.UpdateTime;
import com.cositea.gateway.service.CositeaMethod;
import com.cositea.gateway.util.ByteOps;
import com.sunshine.hardware.controller.timeout.TimeOutWork;
import com.sunshine.hardware.model.ErrorCode;
import com.sunshine.hardware.model.Location;
import com.sunshine.hardware.model.Probe;
import com.sunshine.hardware.model.ReturnBody;
import com.sunshine.hardware.model.response.LocationResponse;
import com.sunshine.hardware.model.response.ProbeResponse;
import com.sunshine.hardware.service.ProbeService;
import com.sunshine.hardware.util.Constants;
import com.sunshine.hardware.util.TimeUtil;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("probe")
public class ProbeController {

	@Autowired
	private ProbeService probeService;

	private static final Logger log = LoggerFactory.getLogger(ProbeController.class);

	//实例化方法
	CositeaMethod cositeaMethod=CositeaMethod.getInstance();

	@RequestMapping("initList")
	@ResponseBody
	public ReturnBody initProbe(){
		ReturnBody responseData = ReturnBody.success();
		List<String> list = probeService.initProbe();
		ProbeResponse probeResponse = new ProbeResponse();
		probeResponse.setProbeMacList(list);
		responseData.setData(probeResponse);
		return responseData;
	}

	@RequestMapping("getList")
	@ResponseBody
	public ReturnBody getList(@RequestBody Map<String, Object> request){
		ReturnBody returnBody = ReturnBody.success();
		Probe probe = new Probe();
		probe.setPageSize(Integer.parseInt(request.get("pageSize").toString()));
		probe.setStart(Integer.parseInt(request.get("start").toString()));
		probe.setOrderName(request.get("orderName").toString());
		probe.setOrderType(request.get("orderType").toString());
		if(request.containsKey("probeMac")){
            probe.setProbeMac(request.get("probeMac").toString());
        }
		if(request.containsKey("location")){
            probe.setLocation(request.get("location").toString());
        }

		List<ProbeResponse> probeResponseList = probeService.selectProbes(probe);
		ProbeResponse probeResponse = new ProbeResponse();
		probeResponse.setRows(probeResponseList);
		probeResponse.setTotal(probeService.countProbes(probe));
		returnBody.setData(probeResponse);
		return returnBody;
	}

	@RequestMapping("getListLocation")
	@ResponseBody
	public ReturnBody getListLocation(@RequestBody Map<String, Object> request){
		ReturnBody returnBody = ReturnBody.success();
		Probe probe = new Probe();
		probe.setPageSize(Integer.parseInt(request.get("pageSize").toString()));
		probe.setStart(Integer.parseInt(request.get("start").toString()));
		probe.setOrderName(request.get("orderName").toString());
		probe.setOrderType(request.get("orderType").toString());
		if(request.containsKey("probeMac")){
			probe.setProbeMac(request.get("probeMac").toString());
		}
		if(request.containsKey("location")){
			probe.setLocation(request.get("location").toString());
		}

		List<ProbeResponse> probeList = probeService.selectProbesLocation(probe);
		ProbeResponse probeResponse = new ProbeResponse();
		probeResponse.setRows(probeList);
		probeResponse.setTotal(probeService.countProbes(probe));
		returnBody.setData(probeResponse);
		return returnBody;
	}

	@RequestMapping("getListThroughtout")
	@ResponseBody
	public ReturnBody getListThroughtout(@RequestBody Map<String, Object> request){
		ReturnBody responseData = ReturnBody.success();
		Probe probe = new Probe();
		probe.setPageSize(Integer.parseInt(request.get("pageSize").toString()));
		probe.setStart(Integer.parseInt(request.get("start").toString()));
		probe.setOrderName(request.get("orderName").toString());
		probe.setOrderType(request.get("orderType").toString());
		if(request.containsKey("probeMac")){
			probe.setProbeMac(request.get("probeMac").toString());
		}
		if(request.containsKey("location")){
			probe.setLocation(request.get("location").toString());
		}

		List<ProbeResponse> probeResponseList = probeService.selectProbesThroughtout(probe);
		ProbeResponse probeResponse = new ProbeResponse();
		probeResponse.setRows(probeResponseList);
		probeResponse.setTotal(probeService.countProbes(probe));
		responseData.setData(probeResponse);
		return responseData;
	}

    @RequestMapping("addProbe")
    @ResponseBody
	public ReturnBody addProbe(Probe probe){
        int result = probeService.addProbe(probe);
        if(result <= 0){
            return ReturnBody.fail(401, "新增探针失败");
        }
        return ReturnBody.success();
    }

    @RequestMapping("deleteProbeByIds")
    @ResponseBody
    public ReturnBody deleteProbeByIds(String ids){
		ReturnBody returnBody = ReturnBody.success();
	    ProbeResponse probeResponse = probeService.deleteProbeByIds(ids);
		returnBody.setData(probeResponse);
        return returnBody;
    }

	@RequestMapping("getTime")
	@ResponseBody
	public String getTime(){
		return TimeUtil.getTimeString(new Date(), TimeUtil.dataSecondString);
	}

	@RequestMapping("getProbeThroughtoutListByMac")
	@ResponseBody
	public ReturnBody getProbeThroughtoutListById(String probeMac){
		ReturnBody returnBody = ReturnBody.success();
		ProbeResponse probeResponse = probeService.getProbeThroughtoutListByMac(probeMac);
		returnBody.setData(probeResponse);
		return returnBody;
	}

	@RequestMapping("getProbeThroughtoutListByTime")
	@ResponseBody
	public ReturnBody getProbeThroughtoutListByTime(String endTime){
		ReturnBody returnBody = ReturnBody.success();
		ProbeResponse probeResponse = probeService.getProbeThroughtoutListByTime(endTime);
		returnBody.setData(probeResponse);
		return returnBody;
	}

	@RequestMapping("getProbeThroughtoutListByTimeWithMac")
	@ResponseBody
	public ReturnBody getProbeThroughtoutListByTimeWithMac(String beginTime, String endTime){
		ReturnBody returnBody = ReturnBody.success();
		ProbeResponse probeResponse = probeService.getProbeThroughtoutListByTimeWithMac(beginTime, endTime);
		returnBody.setData(probeResponse);
		return returnBody;
	}

	@RequestMapping("getProbeLocation")
	@ResponseBody
	public ReturnBody getProbeLocation(String braceletMac){
		//braceletMac = "e5d41c33b7cf";
		ReturnBody responseData = ReturnBody.success();
		double location = probeService.getLocation(braceletMac);
		DecimalFormat format = new DecimalFormat("0.00");
		String l = format.format(location);

		Location location1 = new Location();
		location1.setBraceletMac(braceletMac);
		location1.setLocation(l);
		List<Location> locations = new ArrayList<>();
		LocationResponse locationResponse = new LocationResponse();
		locations.add(location1);
		locationResponse.setRows(locations);
		locationResponse.setTotal(1);
		responseData.setData(locationResponse);
		return responseData;
	}

	@RequestMapping("getProbeConfig")
	@ResponseBody
	public ReturnBody getProbeConfig(){
		ReturnBody responseData = ReturnBody.success();
		List<ProbeResponse> probeList = probeService.getProbeConfig();
		ProbeResponse probeResponse = new ProbeResponse();
		probeResponse.setRows(probeList);
		Probe probe = new Probe();
		probeResponse.setTotal(probeService.countProbes(probe));
		responseData.setData(probeResponse);
		return responseData;
	}
}
