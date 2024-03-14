package com.example.demo.service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.demo.dao.*;
import com.example.demo.entity.*;

@Service
public class DeptService {
	@Autowired
	private DeptDao deptDao;
	@Autowired
	private EmpDao empDao;
	
	public Map<String, Object> read(Long deptno) {
		Dept dept = deptDao.findByDeptno(deptno);
		List<Emp> emps = empDao.findByDeptno(deptno);
		
		Map<String,Object> map = new HashMap<>();
		map.put("dept", dept);
		map.put("emps", emps);
		return map;
	}
}
