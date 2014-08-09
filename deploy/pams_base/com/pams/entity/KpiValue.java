package com.pams.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.blue.ssh.core.entity.IdEntity;

@Entity
@Table(name = "T_APP_KPIVALUE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Proxy(lazy = false)
public class KpiValue extends IdEntity
{
	private String flowdefid; //流程标识
	
	private String actdefid; // 流程节点标识
	
	private String cname; //指标名称
	
	private String pname; //指标英文名称
	
	private String datatype; //指标数据类型
	
	private String dataunit; //数据单位
	
	private String scopetype; // 指标应用范围类型（KPI应用于流程或单个节点）
	
	private double dvalue; // 指标要求值
	
	private String runflowkey; //流程实例
	
	private String runactkey; //活动实例
	
	private String loginname; //用户名
	
	private String username; //人员
	
	private double rvalue; // 实际值
	
	private Timestamp createtime; // 指标值记录时间
	


}
