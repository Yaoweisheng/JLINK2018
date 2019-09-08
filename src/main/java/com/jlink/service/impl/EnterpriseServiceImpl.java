package com.jlink.service.impl;

import com.jlink.dao.EnterpriseDao;
import com.jlink.entity.Enterprise;
import com.jlink.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {
    @Autowired
    private EnterpriseDao enterpriseDao;

    @Override
    public boolean saveEnterprise(Enterprise enterprise) {
        return enterpriseDao.saveEnterprise(enterprise) > 0;
    }

    @Override
    public Enterprise getEnterpriseById(Integer enterpriseId) {
        return enterpriseDao.getEnterpriseById(enterpriseId);
    }

    @Override
    public boolean updateEnterprise(Enterprise enterprise) {
        return false;
    }
}
