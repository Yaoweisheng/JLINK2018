package com.jlink.service.impl;

import com.jlink.dao.DepartmentDao;
import com.jlink.entity.Department;
import com.jlink.service.DepartmentService;
import com.jlink.dto.Node;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentDao departmentDao;
    @Override
    public boolean saveDepartment(Department department) {
        return departmentDao.saveDepartment(department) > 0;
    }

    @Override
    public Department getDepartmentByNo(String deptNo) {
        return departmentDao.getDepartmentByNo(deptNo);
    }

    @Override
    public Department getDepartmentById(Integer deptId) {
        return departmentDao.getDepartmentById(deptId);
    }

    @Override
    public boolean updateDepartment(Department department) {
        return departmentDao.updateDepartment(department) > 0;
    }

    @Override
    public List<Department> getDepartmentList() {
        return departmentDao.getDepartmentList();
    }

    @Override
    public String getNewNo(String ParentNo) {
        if(StringUtils.isBlank(ParentNo)){
            String lastRootNo = departmentDao.getLastRootNo();
            if(lastRootNo == null){
                return "01";
            }
            else{
                return String.format("%02d", Integer.valueOf(lastRootNo.substring(0,2))+1);
            }
        }
        else{
            String LastBrotherNo = departmentDao.getLastBrotherNo(ParentNo);
            if(!StringUtils.isBlank(LastBrotherNo)){
                if(LastBrotherNo.equals(ParentNo)){
                    return ParentNo + "01";
                }
                int pl = ParentNo.length();
                return ParentNo + String.format("%02d", Integer.valueOf(LastBrotherNo.substring(pl, pl+2)) + 1);
            }
            return "-1";
        }
    }

    @Override
    public List<String> getPlinenubs() {
        return departmentDao.getPlinenubs();
    }

    @Override
    public List<Node> getDepartmentTree() {
        List<Node> departmentNodes = new LinkedList<>();
        List<Department> departments = departmentDao.getRoots();
        for (Department d :
                departments) {
            Node departmentNode = new Node();
            departmentNode.setId(d.getDeptNo());
            departmentNode.setText(d.getDeptName());
            departmentNode.setChildren(getChildren(d.getDeptNo()));
            departmentNodes.add(departmentNode);
        }
        return departmentNodes;
    }

    private List<Node> getChildren(String no){
        List<Node> departmentNodes = new LinkedList<>();
        List<Department> departments = departmentDao.getChildren(no);
        for (Department d :
                departments) {
            Node departmentNode = new Node();
            departmentNode.setId(d.getDeptNo());
            departmentNode.setText(d.getDeptName());
            departmentNode.setChildren(getChildren(d.getDeptNo()));
            departmentNodes.add(departmentNode);
        }
        return departmentNodes;
    }
}
