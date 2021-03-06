package com.sunshine.hardware.dao;

import com.sunshine.hardware.model.BraceletData;
import com.sunshine.hardware.model.Probe;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

public interface ProbeDao {

    @Insert("insert into probe (probe_mac, status, location, is_normal, regular_throughput, on_line, version) values (#{probe.probeMac}, #{probe.status}, #{probe.location}, #{probe.isNormal}, #{probe.regularThroughput}, #{probe.onLine}, #{probe.version})")
    int insertProbeData(@Param("probe") Probe probe);

    @Delete("delete from probe where id = #{id}")
    int deleteProbeData(@Param("id") int id);

    @Select("select id, probe_mac as probeMac, status, location, is_normal as isNormal, regular_throughput as regularThroughput, on_line as onLine, version from probe where id = #{id}")
    Probe getProbeById(@Param("id") int id);

    @SelectProvider(type = ProbeProvider.class, method = "selectProbe")
    List<Probe> selectProbe(@Param("probe") Probe probe);

    @SelectProvider(type = ProbeProvider.class, method = "countProbe")
    Long countProbe(@Param("probe") Probe probe);

    @UpdateProvider(type = ProbeProvider.class, method = "updateProbe")
    int updateProbe(@Param("probe") Probe probe);

    class ProbeProvider {
        public String updateProbe(@Param("probe") Probe probe){
            return new SQL(){{
                UPDATE("probe");
                if(probe.getStatus() != null){
                    SET("status = #{probe.status}");
                }
                if(StringUtils.isNotBlank(probe.getLocation())){
                    SET("location = #{probe.location}");
                }
                if(probe.getIsNormal() != null){
                    SET("is_normal = #{probe.isNormal}");
                }
                if(probe.getRegularThroughput() != null){
                    SET("regular_throughput= #{probe.regularThroughput}");
                }
                if(probe.getOnLine() != null){
                    SET("on_line= #{probe.onLine}");
                }
                if(StringUtils.isNotBlank(probe.getVersion())){
                    SET("version= #{probe.version}");
                }
                if(StringUtils.isNotBlank(probe.getProbeMac())){
                    WHERE("probe_mac = #{probe.probeMac}");
                }
                if(probe.getId() != 0){
                    WHERE("id = #{probe.id}");
                }

            }}.toString();
        }

        public String selectProbe(@Param("probe") Probe probe){
            StringBuilder pageStr = new StringBuilder();
            if(probe.getPageSize() != 0 && StringUtils.isNotBlank(probe.getOrderName())){
                pageStr.append(" ORDER BY ").
                        append(probe.getOrderName()).
                        append(" ").append(probe.getOrderType()).
                        append(" limit ").
                        append(probe.getStart()).
                        append(",").
                        append(probe.getPageSize());
            }
            return new SQL(){{
                SELECT("id, probe_mac as probeMac, status, location, is_normal as isNormal, regular_throughput as regularThroughput, on_line as onLine, version");
                FROM("probe");
                if(StringUtils.isNotBlank(probe.getProbeMac())){
                    WHERE("probe_mac = #{probe.probeMac}");
                }
                if(StringUtils.isNotBlank(probe.getLocation())){
                    WHERE("location like concat('%', #{probe.location}, '%')");
                }
            }}.toString() + pageStr.toString();
        }

        public String countProbe(@Param("probe") Probe probe){
            return new SQL(){{
                SELECT("count(*)");
                FROM("probe");
                if(StringUtils.isNotBlank(probe.getProbeMac())){
                    WHERE("probe_mac = #{probe.probeMac}");
                }
                if(StringUtils.isNotBlank(probe.getLocation())){
                    WHERE("location like concat('%', #{probe.location}, '%')");
                }
            }}.toString();
        }
    }
}
