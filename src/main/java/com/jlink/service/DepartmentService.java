package com.jlink.service;

import com.jlink.entity.Department;

import java.util.List;

public interface DepartmentService {

    boolean saveDepartment(Department department);

    Department getDepartmentByNo(String deptNo);

    Department getDepartmentById(Integer deptId);

    boolean updateDepartment(Department department);

    List<Department> getDepartmentList();

    String getNewNo(String ParentNo);

    List<String> getPlinenubs();
}
