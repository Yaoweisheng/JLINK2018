package com.jlink.dao;

import com.jlink.entity.Department;

import java.util.List;

public interface DepartmentDao {
    int saveDepartment(Department department);

    Department getDepartmentByNo(String deptId);

    Department getDepartmentById(Integer deptId);

    int updateDepartment(Department department);

    List<Department> getDepartmentList();

    String getLastRootNo();

    String getLastBrotherNo(String parentNo);

    List<String> getPlinenubs();

    List<Department> getRoots();

    List<Department> getChildren(String deptNo);
}