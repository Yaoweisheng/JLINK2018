package com.jlink.service;

import com.jlink.entity.Enterprise;

public interface EnterpriseService {

    boolean saveEnterprise(Enterprise enterprise);

    Enterprise getEnterpriseById(Integer enterpriseId);

    boolean updateEnterprise(Enterprise enterprise);
}
