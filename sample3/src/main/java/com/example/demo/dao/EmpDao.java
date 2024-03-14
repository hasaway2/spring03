package com.example.demo.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.example.demo.dto.*;
import com.example.demo.entity.*;

@Mapper
public interface EmpDao {
	@Select("select count(*) from emp where ename=#{username} and empno=#{password} and rownum=1")
	public Boolean findByEnameAndEmpno(LoginDto dto);

	@Select("select empno, ename, job, hiredate, deptno from emp")
	public List<Emp> findAll();
	
	@Select("select * from emp where empno=#{empno} and rownum=1")
	public Emp findByEmpno(Long empno);

	@Select("select max(empno) from emp")
	public Long max();
	
	@Insert("insert into emp values(#{empno}, #{ename}, #{job}, #{mgr}, #{hiredate}, #{sal}, #{comm, jdbcType=INTEGER}, #{deptno})")
	public Long save(Emp emp);

	@Delete("delete from emp where empno=#{empno} and rownum=1")
	public Long deleteByEmpno(Long empno);

	@Select("select * from emp where deptno=#{deptno}")
	public List<Emp> findByDeptno(Long deptno);

	@Select("select distinct job from emp")
	public List<String> findJobs();

	@Select("select distinct mgr from emp where mgr is not null")
	public List<Long> findMgrs();
}
