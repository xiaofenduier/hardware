package com.sunshine.hardware.dao;

import com.sunshine.hardware.model.BraceletData;
import com.sunshine.hardware.model.request.BraceletDataRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

public interface BraceletDao {

    @Insert("insert ignore into ${tableName} (bracelet_mac, heart_rate, step, active, sleep, skip_model, skip_num, skip_time, version, utc, static_heart_rate, probe_mac, uuid, add_time, battery, signal_value) values (#{braceletData.braceletMac}, #{braceletData.heartRate}, #{braceletData.step}, #{braceletData.active}, #{braceletData.sleep}, #{braceletData.skipModel}, #{braceletData.skipNum}, #{braceletData.skipTime}, #{braceletData.version}, #{braceletData.utc}, #{braceletData.staticHeartRate}, #{braceletData.probeMac}, #{braceletData.uuid}, #{braceletData.addTime},#{braceletData.battery}, #{braceletData.signalValue})")
    int insertBraceletData(@Param("braceletData") BraceletData braceletData, @Param("tableName") String tableName);

    @SelectProvider(type = BraceletDaoProvider.class, method = "selectBraceletData")
    List<BraceletData> selectBraceletData(@Param("tableName") String tableName, @Param("braceletDataRequest") BraceletDataRequest braceletDataRequest);

    @SelectProvider(type = BraceletDaoProvider.class, method = "countBraceletData")
    Long countBraceletData(@Param("tableName") String tableName, @Param("braceletDataRequest") BraceletDataRequest braceletDataRequest);

    class BraceletDaoProvider {
        public String selectBraceletData(@Param("tableName") String tableName, @Param("braceletDataRequest") BraceletDataRequest braceletDataRequest){
            StringBuilder pageStr = new StringBuilder();
            if(braceletDataRequest.getPageSize() != 0 && StringUtils.isNotBlank(braceletDataRequest.getOrderType()) ){
                int start = braceletDataRequest.getStart();
                pageStr.append(" ORDER BY ").
                        append(braceletDataRequest.getOrderName()).
                        append(" ").append(braceletDataRequest.getOrderType()).
                        append(" limit ").
                        append(start).
                        append(",").
                        append(braceletDataRequest.getPageSize());
            }
            return new SQL(){{
                SELECT("id, bracelet_mac as braceletMac, heart_rate as heartRate, step, active, sleep, skip_model as skipModel, skip_num as skipNum, skip_time as skipTime, version, utc, static_heart_rate as staticHeartRate, probe_mac as probeMac, uuid, add_time as addTime, battery, signal_value as signalValue ");
                FROM(tableName);
                if(StringUtils.isNotBlank(braceletDataRequest.getProbeMac())){
                    WHERE("probe_mac = #{braceletData.probeMac}");
                }
                if(StringUtils.isNotBlank(braceletDataRequest.getBraceletMac())){
                    WHERE("bracelet_mac = #{braceletData.braceletMac}");
                }
                if(braceletDataRequest.getSkipModel() != null){
                    WHERE("skip_model = #{braceletData.skipModel}");
                }
                if(braceletDataRequest.getBeginTime() != null && braceletDataRequest.getEndTime() != null){
                    WHERE("add_time > #{braceletData.startTime} and add_time < #{braceletData.endTime}");
                }
            }}.toString() + pageStr.toString();
        }

        public String countBraceletData(@Param("tableName") String tableName, @Param("braceletDataRequest") BraceletDataRequest braceletDataRequest){
            return new SQL(){{
                SELECT("count(*)");
                FROM(tableName);
                if(StringUtils.isNotBlank(braceletDataRequest.getProbeMac())){
                    WHERE("probe_mac = #{braceletDataRequest.probeMac}");
                }
                if(StringUtils.isNotBlank(braceletDataRequest.getBraceletMac())){
                    WHERE("bracelet_mac = #{braceletDataRequest.braceletMac}");
                }
                if(braceletDataRequest.getSkipModel() != null){
                    WHERE("skip_model = #{braceletDataRequest.skipModel}");
                }
                if(braceletDataRequest.getBeginTime() != null && braceletDataRequest.getEndTime() != null){
                    WHERE("add_time > #{braceletDataRequest.startTime} and add_time < #{braceletDataRequest.endTime}");
                }
            }}.toString();
        }
    }
}
