package com.Jlink.service;

import com.Jlink.entity.Enterprise;

public interface EnterpriseService {

    boolean saveEnterprise(Enterprise enterprise);

    Enterprise getEnterpriseById(Integer enterpriseId);

    boolean updateEnterprise(Enterprise enterprise);
}
