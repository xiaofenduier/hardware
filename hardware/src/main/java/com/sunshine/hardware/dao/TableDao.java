package com.sunshine.hardware.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface TableDao {

    @Update("CREATE TABLE IF NOT EXISTS ${tableName} like bracelet_201909")
    int createBraceletTable(@Param("tableName") String tableName);
}
