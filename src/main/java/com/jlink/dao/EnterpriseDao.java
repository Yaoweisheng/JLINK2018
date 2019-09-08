package com.jlink.dao;

import com.jlink.entity.Enterprise;

import java.util.List;

public interface EnterpriseDao {
    int saveEnterprise(Enterprise enterprise);

    Enterprise getEnterpriseById(Integer enterpriseId);

    List<Enterprise> getEnterpriseByUserId(Integer userId);

    int updateEnterprise(Enterprise enterprise);
}
