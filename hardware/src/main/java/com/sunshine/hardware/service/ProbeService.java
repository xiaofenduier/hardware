package com.sunshine.hardware.service;

import com.sunshine.hardware.dao.ProbeDao;
import com.sunshine.hardware.enums.IsNormalCode;
import com.sunshine.hardware.enums.StatusCode;
import com.sunshine.hardware.model.BraceletData;
import com.sunshine.hardware.model.Probe;
import com.sunshine.hardware.model.request.BraceletDataRequest;
import com.sunshine.hardware.model.response.ProbeResponse;
import com.sunshine.hardware.util.Constants;
import com.sunshine.hardware.util.TimeUtil;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProbeService {

    @Autowired
    private ProbeDao probeDao;

    @Autowired
    private BraceletService braceletService;

    public List<ProbeResponse> selectProbes(Probe probe) {
        List<ProbeResponse>  probeResponseList = new ArrayList<>();
        List<Probe> probeList = probeDao.selectProbe(probe);
        //计算吞吐量，每分钟收到的数据条数
        probeList.stream().forEach(probeObj -> {
            ProbeResponse probeResponse = new ProbeResponse();
            BeanUtils.copyProperties(probeObj, probeResponse);
            String probeMac = probeResponse.getProbeMac();
            long currentTime = System.currentTimeMillis();
            //long currentTime = 1536885977836l;
            long startTime = currentTime - 60 * 1000;
            BraceletDataRequest braceletdataRequest = new BraceletDataRequest(startTime, currentTime, probeMac);
            //计算10s内收到数据条数
            long regularThroughput = braceletService.countBraceletData(braceletdataRequest);
            probeResponse.setRegularThroughput(regularThroughput);
            if(regularThroughput > 0 ){
                probeResponse.setStatusStr(StatusCode.WORK.getStatus());
                probeObj.setStatus(StatusCode.WORK.getId());
                if(regularThroughput > 12){
                    probeObj.setIsNormal(IsNormalCode.HEIGHT.getId());
                    probeResponse.setIsNormalStr(IsNormalCode.HEIGHT.getIsMormal());
                }else if(regularThroughput < 8){
                    probeObj.setIsNormal(IsNormalCode.LOW.getId());
                    probeResponse.setIsNormalStr(IsNormalCode.LOW.getIsMormal());
                }else{
                    probeObj.setIsNormal(IsNormalCode.REGULAR.getId());
                    probeResponse.setIsNormalStr(IsNormalCode.REGULAR.getIsMormal());
                }
            }else{
                probeObj.setStatus(StatusCode.WAIT.getId());
                probeResponse.setStatusStr(StatusCode.WAIT.getStatus());
            }
            //查询设备是否在线
            ChannelHandlerContext ctx= Constants.ctxMap.get("tcp_"+probeMac);
            if(ctx==null){
                probeObj.setOnLine(StatusCode.OFFLINE.getId());
                probeResponse.setOnLineStr(StatusCode.OFFLINE.getStatus());
            }else{
                probeObj.setOnLine(StatusCode.ONLINE.getId());
                probeResponse.setOnLineStr(StatusCode.ONLINE.getStatus());
            }
            probeResponseList.add(probeResponse);

            probeObj.setRegularThroughput(regularThroughput);
            probeObj.setId(probeObj.getId());
            probeDao.updateProbe(probeObj);
        });
        return probeResponseList;
    }

    public void updateProbe(Probe probe){
        probeDao.updateProbe(probe);
    }

    public long countProbes(Probe probe) {
        return probeDao.countProbe(probe);
    }

    public int addProbe(Probe probe) {
        probe.setIsNormal(IsNormalCode.REGULAR.getId());
        probe.setRegularThroughput(0L);
        probe.setStatus(StatusCode.NORMAL.getId());
        int result = probeDao.insertProbeData(probe);
        return result;
    }

    @Transactional
    public ProbeResponse deleteProbeByIds(String ids) {
        List<String> failList = new ArrayList<>();
        List<String> successList = new ArrayList<>();
        Arrays.asList(ids.split(",")).stream().forEach(id -> {
            Probe probe = probeDao.getProbeById(Integer.parseInt(id));
            int result = 0;
            try{
                result = probeDao.deleteProbeData(Integer.parseInt(id));
            }catch(RuntimeException e){
               System.out.println(e);
            }

            if(result <= 0){
                failList.add(probe.getProbeMac());
            }else{
                successList.add(probe.getProbeMac());
            }
        });
        ProbeResponse probeResponse = new ProbeResponse();
        probeResponse.setFailList(failList);
        probeResponse.setSuccessList(successList);
        return probeResponse;
    }

//    以1分钟为单位查询吞吐量
    public ProbeResponse getProbeThroughtoutListByMac(String probeMac) {
        ProbeResponse probeResponse = new ProbeResponse();
        List<List<String>> result = new ArrayList<>();
        long currentTime = System.currentTimeMillis();
        long regularTime = currentTime;
        while(regularTime >= currentTime - 1000 * 3600 * 24){
            long beforeTime = regularTime - 1000 * 60;
            BraceletDataRequest braceletdataRequest = new BraceletDataRequest(beforeTime, regularTime, probeMac);
            //计算1min内收到数据条数
            long regularThroughput = braceletService.countBraceletData(braceletdataRequest);
            List<String> throughoutList = new ArrayList<>();
            throughoutList.add(TimeUtil.getTimeString(new Date(regularTime), TimeUtil.dataSecondString));
            throughoutList.add(regularThroughput + "");
            result.add(throughoutList);
            regularTime = beforeTime;
        }
        Collections.reverse(result);
        regularTime = currentTime - 3600 * 1000;
        probeResponse.setThroughoutList(result);
        probeResponse.setRegularTime(TimeUtil.getTimeString(new Date(regularTime), TimeUtil.dataSecondString));
        return probeResponse;
    }

    public ProbeResponse getProbeThroughtoutListByTime(String endTime){
        Probe probe = new Probe();
        List<String> probeMacList = new ArrayList<>();
        List<Map<String, Object>> probeValueList = new ArrayList<>();
        List<Probe> probeList = probeDao.selectProbe(probe);
        probeList.stream().forEach(probeObj -> {
            try{
                long startTime = Long.parseLong(endTime) - 60 * 1000;
                BraceletDataRequest braceletdataRequest = new BraceletDataRequest(startTime, Long.parseLong(endTime), probeObj.getProbeMac());
                //计算1分钟内收到数据条数
                long regularThroughput = braceletService.countBraceletData(braceletdataRequest);
                probeMacList.add(probeObj.getProbeMac());
                Map<String, Object> map = new HashMap<>();
                map.put("value", regularThroughput);
                map.put("name", probeObj.getProbeMac());
                probeValueList.add(map);
            }catch(Exception e){
                System.out.println(e);
            }
        });
        ProbeResponse probeResponse = new ProbeResponse();
        probeResponse.setProbeMacList(probeMacList);
        probeResponse.setProbeValueList(probeValueList);
        probeResponse.setRegularTime(TimeUtil.getTimeString(new Date(Long.parseLong(endTime)), TimeUtil.dataMinuteString));
        return probeResponse;
    }

    public ProbeResponse getProbeThroughtoutListByTimeWithMac(String startTime, String endTime) {
        Probe probe = new Probe();
        List<String> probeMacList = new ArrayList<>();
        List<Long> probeThroughtoutList = new ArrayList<>();
        List<Probe> probeList = probeDao.selectProbe(probe);
        probeList.stream().forEach(probeObj -> {
            try{
                BraceletDataRequest braceletdataRequest = new BraceletDataRequest(Long.parseLong(startTime), Long.parseLong(endTime), probeObj.getProbeMac());
                //计算收到数据条数
                long regularThroughput = braceletService.countBraceletData(braceletdataRequest);
                probeMacList.add(probeObj.getProbeMac());
                probeThroughtoutList.add(regularThroughput);
            }catch(Exception e){
                System.out.println(e);
            }
        });
        ProbeResponse probeResponse = new ProbeResponse();
        probeResponse.setProbeMacList(probeMacList);
        probeResponse.setProbeThroughtoutList(probeThroughtoutList);
        return probeResponse;
    }

    public List<String> initProbe() {
        Probe probe = new Probe();
        probe.setStatus(StatusCode.NORMAL.getId());
        List<Probe> probeList = probeDao.selectProbe(probe);
        List<String> list = probeList.stream().map(obj -> obj.getProbeMac()).collect(Collectors.toList());
        return list;
    }

    public double getLocation(String braceletMac) {
        long endTime = System.currentTimeMillis();
        long startTime = endTime - 60 * 1000;
        BraceletDataRequest braceletdataRequest = new BraceletDataRequest(startTime, endTime);
        braceletdataRequest.setBraceletMac(braceletMac);
        //计算1分钟内收到数据条数
        double result = 0;
        List<BraceletData> braceletdataList = braceletService.selectBraceletData(braceletdataRequest);
        for(BraceletData braceletdata : braceletdataList){
            int signal = Math.abs(braceletdata.getSignalValue() != null ? braceletdata.getSignalValue() : 0);
            double power = (signal-59)/(10*2.0);
            double distance = Math.pow(10, power);
            result = result + distance;
        }
        return result/braceletdataList.size();
    }

    public List<ProbeResponse> selectProbesLocation(Probe probe) {
        List<ProbeResponse>  probeResponseList = new ArrayList<>();
        List<Probe> probeList = probeDao.selectProbe(probe);
        probeList.stream().forEach(probeObj -> {
            ProbeResponse probeResponse = new ProbeResponse();
            BeanUtils.copyProperties(probeObj, probeResponse);
            probeResponseList.add(probeResponse);
        });
        return probeResponseList;
    }

    public List<ProbeResponse> selectProbesThroughtout(Probe probe) {
        List<ProbeResponse>  probeResponseList = new ArrayList<>();
        List<Probe> probeList = probeDao.selectProbe(probe);
        //计算吞吐量，每分钟收到的数据条数
        probeList.stream().forEach(probeObj -> {
            ProbeResponse probeResponse = new ProbeResponse();
            BeanUtils.copyProperties(probeObj, probeResponse);

            String probeMac = probeResponse.getProbeMac();
            long currentTime = System.currentTimeMillis();
            long startTime = currentTime - 60 * 1000;
            BraceletDataRequest braceletdataRequest = new BraceletDataRequest(startTime, currentTime, probeMac);
            //计算10s内收到数据条数
            long regularThroughput = braceletService.countBraceletData(braceletdataRequest);
            probeResponse.setRegularThroughput(regularThroughput);
            probeResponseList.add(probeResponse);

        });
        return probeResponseList;
    }

    public List<ProbeResponse> getProbeConfig(){
        Probe probe = new Probe();
        List<ProbeResponse>  probeResponseList = new ArrayList<>();
        List<Probe> probeList = probeDao.selectProbe(probe);
        probeList.stream().forEach(obj -> {
            ProbeResponse probeResponse = new ProbeResponse();
            BeanUtils.copyProperties(obj, probeResponse);
            //查询设备是否在线
            ChannelHandlerContext ctx= Constants.ctxMap.get("tcp_"+obj.getProbeMac());
            if(ctx==null){
                probeResponse.setOnLineStr(StatusCode.OFFLINE.getStatus());
            }else{
                probeResponse.setOnLineStr(StatusCode.ONLINE.getStatus());
            }
            probeResponseList.add(probeResponse);
        });
        return probeResponseList;
    }
}
