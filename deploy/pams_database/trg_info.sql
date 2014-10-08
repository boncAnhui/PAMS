create table T_APP_PUBINFO
(
  id                     VARCHAR2(32) not null,
  cclassid               VARCHAR2(255),
  cclassname             VARCHAR2(255),
  creater                VARCHAR2(255),
  creatername            VARCHAR2(255),
  createtime             DATE,
  deptid                 VARCHAR2(255),
  deptname               VARCHAR2(255),
  filenums               NUMBER(10) not null,
  filetype               VARCHAR2(255),
  infosharescope         VARCHAR2(255),
  infosharescopectype    VARCHAR2(255),
  infosharescopeid       VARCHAR2(255),
  amount                 NUMBER(12) not null,
  obtaintime             DATE,
  obtaintimed            VARCHAR2(255),
  obtaintimet            VARCHAR2(255),
  publishtime            DATE,
  shareauthor            VARCHAR2(255),
  sourceid               VARCHAR2(255),
  sourcename             VARCHAR2(255),
  temp                   VARCHAR2(255),
  temp1                  VARCHAR2(255),
  reptype                VARCHAR2(255),
  cno                    VARCHAR2(255),
  title                  VARCHAR2(255),
  infosharescopeinternal VARCHAR2(255)
)






create or replace trigger trg_info after insert or update or delete
on t_App_Infoshare for each row

begin
  if inserting then
    insert into t_app_pubinfo(
           id,cclassid,cclassname,creater,creatername,createtime,deptid,deptname,title,
           filenums,filetype,infosharescope,infosharescopectype,infosharescopeid,amount,
           obtaintime,obtaintimed,obtaintimet,publishtime,shareauthor,sourceid,sourcename,cno,infosharescopeinternal,reptype)
         values(
           :new.ID,:new.cclassid,:new.cclassname,:new.creater,:new.creatername,:new.createtime,:new.deptid,:new.deptname,:new.title,
           :new.filenums,:new.filetype,:new.infosharescope,:new.infosharescopectype,:new.infosharescopeid,0,
           :new.obtaintime,:new.obtaintimed,:new.obtaintimet,:new.publishtime,:new.shareauthor,:new.sourceid,:new.sourcename,
           :new.cno,:new.infosharescopeinternal,'infoshare'
         );
  elsif updating then
    update t_app_pubinfo t set t.cclassid=:new.cclassid,t.cclassname=:new.cclassname,t.creater=:new.creater,t.creatername=:new.creatername,t.createtime=:new.createtime,
           t.deptid=:new.deptid,t.deptname=:new.deptname,t.filenums=:new.filenums,t.title=:new.title,
           t.filetype=:new.filetype,t.infosharescope=:new.infosharescope,t.infosharescopectype=:new.infosharescopectype,
           t.infosharescopeid=:new.infosharescopeid,t.amount=0,
           t.obtaintime=:new.obtaintime,t.obtaintimed=:new.obtaintime,t.obtaintimet=:new.obtaintimet,t.publishtime=:new.publishtime,t.shareauthor=:new.shareauthor,
           t.sourceid=:new.sourceid,t.sourcename=:new.sourcename,t.cno=:new.cno,t.infosharescopeinternal=:new.infosharescopeinternal
           where id=:OLD.id;
  elsif deleting then
    delete from t_app_pubinfo where id=:OLD.id;
  end if;

end;
