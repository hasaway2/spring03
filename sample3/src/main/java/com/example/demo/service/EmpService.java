package com.example.demo.service;

import java.time.*;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.demo.dao.*;
import com.example.demo.entity.*;

@Service
public class EmpService {
	@Autowired
	private EmpDao empDao;
	@Autowired
	private DeptDao deptDao;

	public List<Emp> list() {
		return empDao.findAll();
	}

	public Emp read(Long empno) {
		return empDao.findByEmpno(empno);
	}

	public Boolean add(Emp emp) {
		Long newEmpno = empDao.max()+1;
		emp.setEmpno(newEmpno);
		
		if(emp.getHiredate()==null) {
			emp.setHiredate(LocalDate.now());
		}
		
		System.out.println(emp);
		return empDao.save(emp)==1L;
	}

	public Boolean delete(Long empno) {
		return empDao.deleteByEmpno(empno)==1L;
	}

	public Map<String, Object> findAddInfo() {
		List<String> jobs = empDao.findJobs();
		List<Long> deptnos = deptDao.findDeptno(); 
		List<Long> mgrs = empDao.findMgrs();
		
		Map<String, Object> addInfo = new HashMap<>();
		addInfo.put("jobs", jobs);
		addInfo.put("deptnos", deptnos);
		addInfo.put("mgrs", mgrs);
		return addInfo;
	}	
}
