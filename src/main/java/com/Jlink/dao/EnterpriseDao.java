package com.Jlink.dao;

import com.Jlink.entity.Enterprise;

import java.util.List;

public interface EnterpriseDao {
    int saveEnterprise(Enterprise enterprise);

    Enterprise getEnterpriseById(Integer enterpriseId);

    List<Enterprise> getEnterpriseByUserId(Long userId);

    int updateEnterprise(Enterprise enterprise);
}
