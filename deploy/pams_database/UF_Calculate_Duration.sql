create or replace function UF_Calculate_Duration(fv_todate in timestamp,fv_fromdate in timestamp)
return number is rn_Duration number;
v_fromdate varchar2(19);
v_todate varchar2(19);
lv_fromdate_10 varchar2(10);
lv_todate_10 varchar2(10);
lv_fromdate_19 varchar2(19);
lv_todate_19 varchar2(19);
lv_mid_day_10 varchar2(10);
lv_day_of_the_week varchar2(10);
i number;
ln_count number;
ln_difference_int number;
ln_difference_float number;
lb_is_holiday boolean;
lb_is_holiday_from boolean;
lb_is_holiday_to boolean;
begin
v_fromdate:=to_char(fv_fromdate,'yyyy-mm-dd hh24:mi:ss');
v_todate:=to_char(fv_todate,'yyyy-mm-dd hh24:mi:ss');
       if length(trim(v_fromdate))=19 and length(trim(v_todate))=19 then
          lv_fromdate_10:=SUBSTR(trim(v_fromdate),1,10);
          lv_todate_10:=SUBSTR(trim(v_todate),1,10);
          lv_fromdate_19:=trim(v_fromdate);
          lv_todate_19:=trim(v_todate);

          ln_difference_int:=to_date(lv_todate_10,'yyyy-mm-dd')-to_date(lv_fromdate_10,'yyyy-mm-dd');
          ln_difference_float:=round(to_date(lv_todate_19,'yyyy-mm-dd hh24:mi:ss')-to_date(lv_fromdate_19,'yyyy-mm-dd hh24:mi:ss'),4);

          /* 判断fromdate是否为节假日---begin*/
          ln_count:=0;
          lv_day_of_the_week:=trim(to_char(to_date(lv_fromdate_10,'yyyy-mm-dd'),'day','NLS_DATE_LANGUAGE = American'));
          if lv_day_of_the_week='saturday' or lv_day_of_the_week='sunday' then
             select count(holidays) into ln_count from t_sys_holidays where holidays=lv_fromdate_10 and is_workday=1;
             if ln_count=1 then
                lb_is_holiday_from:=FALSE;
             else
                lb_is_holiday_from:=TRUE;
             end if;
          else
             select count(holidays) into ln_count from t_sys_holidays where holidays=lv_fromdate_10 and is_workday=0;
             if ln_count=1 then
                lb_is_holiday_from:=TRUE;
             else
                lb_is_holiday_from:=FALSE;
             end if;
          end if;
         /* 判断fromdate是否为节假日---end*/
         
         /* 判断todate是否为节假日---begin*/
         ln_count:=0;
         lv_day_of_the_week:=trim(to_char(to_date(lv_todate_10,'yyyy-mm-dd'),'day','NLS_DATE_LANGUAGE = American'));
         if lv_day_of_the_week='saturday' or lv_day_of_the_week='sunday' then
             select count(holidays) into ln_count from t_sys_holidays where holidays=lv_todate_10 and is_workday=1;
             if ln_count=1 then
                lb_is_holiday_to:=FALSE;
             else
                lb_is_holiday_to:=TRUE;
             end if;
          else
             select count(holidays) into ln_count from t_sys_holidays where holidays=lv_todate_10 and is_workday=0;
             if ln_count=1 then
                lb_is_holiday_to:=TRUE;
             else
                lb_is_holiday_to:=FALSE;
             end if;
          end if;
          /* 判断todate是否为节假日---end*/
          
          
          if lv_fromdate_10=lv_todate_10 then
             if lb_is_holiday_from and lb_is_holiday_to then
               rn_Duration:=0;
             else
               rn_Duration:=round(to_date(lv_todate_19,'yyyy-mm-dd hh24:mi:ss')-to_date(lv_fromdate_19,'yyyy-mm-dd hh24:mi:ss'),4);
             end if;
          else
              if lb_is_holiday_from then
               rn_Duration:=0;
              else
                rn_Duration:=round(to_date(lv_fromdate_10||' 23:59:59','yyyy-mm-dd hh24:mi:ss')-to_date(lv_fromdate_19,'yyyy-mm-dd hh24:mi:ss'),4);
              end if;
              
               if lb_is_holiday_to then
                  rn_Duration:=rn_Duration+0;
               else
                  rn_Duration:=rn_Duration+round(to_date(lv_todate_19,'yyyy-mm-dd hh24:mi:ss')-to_date(lv_todate_10||' 00:00:00','yyyy-mm-dd hh24:mi:ss'),4);
               end if;
          end if;
          
         

          if ln_difference_float>2 then
             i:=0;
             loop
                i:=i+1;
                lv_mid_day_10:=to_char(to_date(lv_fromdate_10,'yyyy-mm-dd')+i,'yyyy-mm-dd');
                /* 判断mid_da是否为节假日---begin*/
                ln_count:=0;
                lv_day_of_the_week:=trim(to_char(to_date(lv_mid_day_10,'yyyy-mm-dd'),'day','NLS_DATE_LANGUAGE = American'));
                if lv_day_of_the_week='saturday' or lv_day_of_the_week='sunday' then
                   select count(holidays) into ln_count from t_sys_holidays where holidays=lv_mid_day_10 and is_workday=1;
                   if ln_count=1 then
                      lb_is_holiday:=FALSE;
                   else
                      lb_is_holiday:=TRUE;
                   end if;
                else
                   select count(holidays) into ln_count from t_sys_holidays where holidays=lv_mid_day_10 and is_workday=0;
                   if ln_count=1 then
                      lb_is_holiday:=TRUE;
                   else
                      lb_is_holiday:=FALSE;
                   end if;
                end if;
                /* 判断mid_da是否为节假日---end*/
                if lb_is_holiday then
                   rn_Duration:=rn_Duration+0;
                else
                   rn_Duration:=rn_Duration+1;
                end if;
             exit when i=ln_difference_int-1;
             end loop;
          end if;
       else
         rn_Duration:=-1;
       end if;

       return(rn_Duration);
end;
/
