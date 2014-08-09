package com.pams.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.blue.ssh.core.entity.IdEntity;

@Entity
@Table(name = "T_APP_METERAPPLY")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Proxy(lazy = false)
// 户表申请单
public class MeterApply extends IdEntity
{

	private String wh; // 文号
	
	private String wjlb; // 文件类别
	
	private String mj; // 密级
	
	private String jjcd; // 紧急程度
	
	private String bm; // 编码
	
	private String bt; // 标题
	
	private String zzxqmc; //住宅小区名称
	
	private String jc; //简称
	
	private String xmmc; //项目名称
	
	private String dz; //地址

	private String jhxx; //计划信息

	private String zz; //资质

	private String zzsm; //资质说明
	
	private String jsdwfr; //建设单位法人
	
	private String jsdwfrdh; // 建设单位法人电话
	
	private String jsdwfrsfzh; // 建设单位法人身份证号
	
	private String jsdwwtr; // 建设单位委托人
	
	private String jsdwwtrdh; // 建设单位委托人电话
	
	private String jsdwwtrsfzh; // 建设单位委托人身份证号
	
	private String ywxlb; // 业务项类别
	
	private Date wgrq; // 完工日期
	
	private Date rzrq ; // 入住日期
	
	private Float zjzmj; // 总建筑面积
	
	private Float zzmj; // 住宅面积
	
	private Float symj; // 商业面积
	
	private Float gjmj; // 公建面积
	
	private Float gjss; // 公建设施
	
	private String gjsssm; // 公建设施说明
	
	private String ydrl; // 用电容量
	
	private Integer zhs; // 总户数

	public String getWh()
	{
		return wh;
	}

	public void setWh(String wh)
	{
		this.wh = wh;
	}

	public String getWjlb()
	{
		return wjlb;
	}

	public void setWjlb(String wjlb)
	{
		this.wjlb = wjlb;
	}

	public String getMj()
	{
		return mj;
	}

	public void setMj(String mj)
	{
		this.mj = mj;
	}

	public String getJjcd()
	{
		return jjcd;
	}

	public void setJjcd(String jjcd)
	{
		this.jjcd = jjcd;
	}

	public String getBm()
	{
		return bm;
	}

	public void setBm(String bm)
	{
		this.bm = bm;
	}

	public String getBt()
	{
		return bt;
	}

	public void setBt(String bt)
	{
		this.bt = bt;
	}

	public String getZzxqmc()
	{
		return zzxqmc;
	}

	public void setZzxqmc(String zzxqmc)
	{
		this.zzxqmc = zzxqmc;
	}

	public String getJc()
	{
		return jc;
	}

	public void setJc(String jc)
	{
		this.jc = jc;
	}

	public String getXmmc()
	{
		return xmmc;
	}

	public void setXmmc(String xmmc)
	{
		this.xmmc = xmmc;
	}

	public String getDz()
	{
		return dz;
	}

	public void setDz(String dz)
	{
		this.dz = dz;
	}

	public String getJhxx()
	{
		return jhxx;
	}

	public void setJhxx(String jhxx)
	{
		this.jhxx = jhxx;
	}

	public String getZz()
	{
		return zz;
	}

	public void setZz(String zz)
	{
		this.zz = zz;
	}

	public String getZzsm()
	{
		return zzsm;
	}

	public void setZzsm(String zzsm)
	{
		this.zzsm = zzsm;
	}

	public String getJsdwfr()
	{
		return jsdwfr;
	}

	public void setJsdwfr(String jsdwfr)
	{
		this.jsdwfr = jsdwfr;
	}

	public String getJsdwfrdh()
	{
		return jsdwfrdh;
	}

	public void setJsdwfrdh(String jsdwfrdh)
	{
		this.jsdwfrdh = jsdwfrdh;
	}

	public String getJsdwfrsfzh()
	{
		return jsdwfrsfzh;
	}

	public void setJsdwfrsfzh(String jsdwfrsfzh)
	{
		this.jsdwfrsfzh = jsdwfrsfzh;
	}

	public String getJsdwwtr()
	{
		return jsdwwtr;
	}

	public void setJsdwwtr(String jsdwwtr)
	{
		this.jsdwwtr = jsdwwtr;
	}

	public String getJsdwwtrdh()
	{
		return jsdwwtrdh;
	}

	public void setJsdwwtrdh(String jsdwwtrdh)
	{
		this.jsdwwtrdh = jsdwwtrdh;
	}

	public String getJsdwwtrsfzh()
	{
		return jsdwwtrsfzh;
	}

	public void setJsdwwtrsfzh(String jsdwwtrsfzh)
	{
		this.jsdwwtrsfzh = jsdwwtrsfzh;
	}

	public String getYwxlb()
	{
		return ywxlb;
	}

	public void setYwxlb(String ywxlb)
	{
		this.ywxlb = ywxlb;
	}

	public Date getWgrq()
	{
		return wgrq;
	}

	public void setWgrq(Date wgrq)
	{
		this.wgrq = wgrq;
	}

	public Date getRzrq()
	{
		return rzrq;
	}

	public void setRzrq(Date rzrq)
	{
		this.rzrq = rzrq;
	}

	public Float getZjzmj()
	{
		return zjzmj;
	}

	public void setZjzmj(Float zjzmj)
	{
		this.zjzmj = zjzmj;
	}

	public Float getZzmj()
	{
		return zzmj;
	}

	public void setZzmj(Float zzmj)
	{
		this.zzmj = zzmj;
	}

	public Float getSymj()
	{
		return symj;
	}

	public void setSymj(Float symj)
	{
		this.symj = symj;
	}

	public Float getGjmj()
	{
		return gjmj;
	}

	public void setGjmj(Float gjmj)
	{
		this.gjmj = gjmj;
	}

	public Float getGjss()
	{
		return gjss;
	}

	public void setGjss(Float gjss)
	{
		this.gjss = gjss;
	}

	public String getGjsssm()
	{
		return gjsssm;
	}

	public void setGjsssm(String gjsssm)
	{
		this.gjsssm = gjsssm;
	}

	public String getYdrl()
	{
		return ydrl;
	}

	public void setYdrl(String ydrl)
	{
		this.ydrl = ydrl;
	}

	public Integer getZhs()
	{
		return zhs;
	}

	public void setZhs(Integer zhs)
	{
		this.zhs = zhs;
	}
	
	
	
}
