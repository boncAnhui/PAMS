
 
// commonvalidate.js
//
//
//	函数说明：校验是否为有效数字和字母	
//	属于公用功能函数
//  作者：胡伟红
//  日期：2004-05-14
//  备注：
	function validateAlpha_numeric(fieldname,fieldtitle){		
		
		if (eval("document.form1."+fieldname+".value")==""){
		   	return true;
		}else{
			if (!IF_Alpha(eval("document.form1."+fieldname+".value")) && !If_Integer(eval("document.form1."+fieldname+".value"))){
			    alert(fieldtitle+" 必须有效的字符串和数字！");
	    	    return false;
		    }
		}
		return true;
	}
	function validateNumer(fieldname,fieldtitle){		
		
		if (eval("document.form1."+fieldname+".value")==""){
		   	return true;
		}else{
			if ( !If_Integer(eval("document.form1."+fieldname+".value"))){
			    alert(fieldtitle+" 必须有效数字！");
	    	    return false;
		    }
		}
		return true;
	}
	function IF_Alpha(s){
	  
	    for (var i=0; i<s.length; i++) {  
	        var Char = s.charAt(i);  
	        if ((Char < "a" || Char > "z") && (Char < "A" || Char > "Z") && (Char < "0" || Char > "9"))
	        	return false;  
   		 }  
	    return true;  
	}  	
	function validateIllegal(fieldname,fieldtitle){
		if (eval("document.form1."+fieldname+".value")==""){
		   	return true;
		}else{
			if (!IF_Illegal(eval("document.form1."+fieldname+".value"))){
			    alert(fieldtitle+" 不能含有非法字符（ \", \' , \“,\”,\‘,\’）！");
	    	    return false;
		    }
		}
		return true;
	
	}
	function IF_Illegal(s){
	  
	    for (var i=0; i<s.length; i++) {  
	        var Char = s.charAt(i);  
	        if (Char =="\"" || Char =="\'" || Char=="\“" ||Char == "\”" || Char=="\‘" ||Char == "\’")
	        	return false;  
   		 }  
	    return true;  
	}  
	//赵翌20040706  
	//s1源字符串s2校验字符串s3 提示信息
	function IS_Illegal(s1,s2,s3){
		if (s1.indexOf(s2)>=0)
		{
			alert(s3+"中含有非法字符串"+s2);
			return true;
		}
		return false;  
	}  
//
//	函数说明：校验是否为有效日期	
//	属于公用功能函数
//  作者：胡伟红
//  日期：2004-02-27
//  备注：
	function validateDate(fieldname,fieldtitle){
		//alert("校验日期");
		if (eval("document.form1."+fieldname+".value")==""){
		   	return true;
		}else{
			if (!If_Date(eval("document.form1."+fieldname+".value"))){
			    alert(fieldtitle+" 不是有效的日期！");
	    	    return false;
		    }
		}
		return true;
	}
//	函数说明:判断输入值的长度是否超出了范围
//	属于公用功能函数
//  作者：胡伟红
//  日期：2004-02-27
//  备注：
	function validateLong(fieldname,fieldtitle,range){ 
		if (!If_LengthOutOfRange(eval("document.form1."+fieldname+".value"),range)){
		    if(fieldtitle == "类目顺序号")
		    {
		        alert(fieldtitle+" 长度超出范围，最大的长度是"+(range / 2)+"个汉字("+range+"个字符)");
		    }
		    else
		    {
	        	alert(fieldtitle+" 长度超出范围，最大的长度是"+parseInt(range/2,10)+"个汉字("+range+"个字符)");
	        }
		    return false;
		}
		return true;
	}
	//赵翌20040706
	//s1源字符转s2提示信息s3长度范围
	function IS_OutOfLong(s1,s2,s3){
		if (!If_LengthOutOfRange(s1,s3)){
	        alert(s2+" 长度超出范围，最大的长度是"+parseInt(s3/2,10)+"个汉字("+s3+"个字符)");
		    return true;
		}
		return false;
	}
//	函数说明：判断输入值是否为空
//	属于公用功能函数
//  作者：胡伟红
//  日期：2004-02-27
//  备注：
	function validateNull(fieldname,fieldtitle){
		//alert("校验为空");
		//alert(eval("document.form1."+fieldname+".value"));
		if (!If_NotNullValue(eval("document.form1."+fieldname+".value"))){
	        alert(fieldtitle+" 不能为空！");
		    return false;
     	}
     	return true;
	}
//	函数说明：判断输入值是否为空
//	属于公用功能函数
//  作者：赵翌
//  日期：2004-06-15
//  备注：
	function convalidataNull(controlname,fieldtitle){
		//alert("校验为空");
		//alert(eval("document.form1."+fieldname+".value"));
		if (!If_NotNullValue(eval(controlname+".value"))){
	        alert(fieldtitle+" 不能为空！");
		    return false;
     	}
     	return true;
	}
//	函数说明：判断输入值是否为浮点数
//	属于公用功能函数
//  作者：胡伟红
//  日期：2004-02-27
//  备注：	
	function validateFloat(fieldname,fieldtitle,range){
		//alert("校验浮点");		
		rangeArraytemp = range.split("-");	  	 	  	 
		var rangestart=new String(rangeArraytemp[0]);
		var rangeend = new String(rangeArraytemp[1]);	      	 
		if (rangestart.substr(0,1)=="f"){
	  		value1="-"+rangestart.substr(1,rangestart.length-1);
		}else{
	     	value1=rangestart;
	    }     	  	 
	    if (rangeend.substr(0,1)=="f"){
	  		value2="-"+rangeend.substr(1,rangeend.length-1);
		}else{
	    	value2=rangeend;
	    }
	    rangestring="["+value1+","+value2+"]";	    
	    if (eval("document.form1."+fieldname+".value")==""){
		   	return true;
		}else{
		    if (isNaN(eval("document.form1."+fieldname+".value")*1)){			
		    	alert(fieldtitle+" 不是有效的小数！");
			    return false;
			}if (!If_Number(eval("document.form1."+fieldname+".value")))
			{
				alert(fieldtitle+" 不是有！");
			    return false;
			}else{
				if (range==""){							
					return true;
				}else{	
					if (!IF_OutRange(eval("document.form1."+fieldname+".value"),range)){//校验数字的范围
						alert(fieldtitle+" 超出范围！正确范围是"+rangestring);
						return false;
					}
				}				
		   	}  
		}
		return true;
	}
//	函数说明：判断输入值是否为整型
//	属于公用功能函数
//  作者：胡伟红
//  日期：2004-02-27
//  备注：
	function validateInt(fieldname,fieldtitle,range){
		//alert("校验整型");	
		rangeArraytemp = range.split("-");	  	 	  	 
		var rangestart=new String(rangeArraytemp[0]);
		var rangeend = new String(rangeArraytemp[1]);	      	 
		if (rangestart.substr(0,1)=="f"){
	  		value1="-"+rangestart.substr(1,rangestart.length-1);
		}else{
	     	value1=rangestart;
	    }     	  	 
	    if (rangeend.substr(0,1)=="f"){
	  		value2="-"+rangeend.substr(1,rangeend.length-1);
		}else{
	    	value2=rangeend;
	    }
	    rangestring="["+value1+","+value2+"]";
	   // alert("here");
	    if (eval("document.form1."+fieldname+".value")==""){
		   	return true;
		}else{
			if (!If_Integer(eval("document.form1."+fieldname+".value"))){//校验是否为合理的整数
				alert(fieldtitle+" 不是有效的整数！");
			    return false;
			}else{
				if (range==""){							
					return true;
				}else{				    						
					if (!IF_OutRange(eval("document.form1."+fieldname+".value"),range)){//校验数字的范围
						alert(fieldtitle+" 超出范围！正确范围是"+rangestring);
						return false;
					}
				}
			} 
		}
		return true;
	}
//	函数说明：判断输入值是否为合理的Email地址
//	属于公用功能函数
//  作者：胡伟红
//  日期：2004-02-27
//  备注：
	function validateEmail(fieldname,fieldtitle){
		//alert("校验Email");
		if( (eval("document.form1."+fieldname+".value")=="") || (eval("document.form1."+fieldname+".value")==" ")){
		   	return true;
		}else{
			if (!IF_isEmail(eval("document.form1."+fieldname+".value"))){
			    alert(fieldtitle+" 不是有效的Email地址！（形如 ***@***.***）");
	    	    return false;
		    }
		}
		return true;
	}
//	函数说明：校验输入值是否为合理的电话号码
//	属于公用功能函数
//  作者：胡伟红
//  日期：2004-02-27
//  备注：
	function validateTelNo(fieldname,fieldtitle){
		//alert("校验电话号码");
		if (eval("document.form1."+fieldname+".value")==""){
		   	return true;
		}else{
			if (!IF_istel(eval("document.form1."+fieldname+".value"))){
			    alert(fieldtitle+" 不是有效的电话号码！（只能由-()1234567890;组成）");
	    	    return false;
		    }
		}
		return true;
	}
//	函数说明：校验输入值是否为合理的网址
//	属于公用功能函数
//  作者：胡伟红
//  日期：2004-02-27
//  备注：
	function validateUrl(fieldname,fieldtitle){
		//alert("校验网址");
		if (eval("document.form1."+fieldname+".value")==""){
		   	return true;
		}else{
			if (!IF_isurl(eval("document.form1."+fieldname+".value"))){
			    alert(fieldtitle+" 不是有效的网址！例如：http://XXX.XXX.XXX 等");
	    	    return false;
		    }
		}
		return true;
	}
//	函数说明：校验输入值的小数位数是否超过范围
//	属于公用功能函数
//  作者：胡伟红
//  日期：2004-02-27
//  备注：
	function validateFloatOverNo(fieldname,fieldtitle,range){ 
		
		if (isNaN(eval("document.form1."+fieldname+".value")*1)){
		   	alert(fieldtitle+" 不是有效的小数！");
			return false;
		}else{
			var str=trim(eval("document.form1."+fieldname+".value"));
		    ArrayOfSplit=str.split(".");
		    var ii=parseInt(range);
		 	if(ArrayOfSplit[1].length>ii){
		    	alert(fieldtitle+" 的小数位数过多，最多可输入"+ii+"位!");
		    	return false;
		        //clearInputContent(fieldname);
		    }
	    }
	    return true;
	} 
	//校验标准：网址中存在http://.就认为是合理的网络地址
	function IF_isurl(address) {
	    //alert(address);
	    addressarray=address.split("//");
	    //alert(addressarray[0]);	    
		if ((address.indexOf ('http://') == -1)|| (address.indexOf ('.') == -1)){
			return false;
		}else{
		  if ((address.indexOf ('http://') != -1)|| (address.indexOf ('.') != -1))
		  	 if (addressarray[0]!="http:"){return false; }  
		}
		
		return true;
	}
	
	//校验电话号码
	function IF_istel(vTelno){ 
		var tel=vTelno; 
		var validchars = "-()1234567890; "; 
		for (i=0;i<tel.length;i++){ 
			telchar=tel.charAt(i); 
			if (validchars.indexOf(telchar, 0) == -1) 
				return false; 
		} 
		return true; 
	}
	//校验email	
	function IF_isEmail(vEmail){ 
    	var email=vEmail; 
 		// valid format "a@b.cd" 
 		invalidChars = " /;,:{}[]|*%$#!()`<>?"; 		
		for (i=0; i< invalidChars.length; i++){ 
			badChar = invalidChars.charAt(i); 
			if (email.indexOf(badChar,0) > -1){ 
			   return false; 
			} 
		} 
		atPos = email.indexOf("@",1) 
		// there must be one "@" symbol 
		if (atPos == -1){ 
			return false; 
		} 
		if (email.indexOf("@", atPos+1) != -1){ 
		// and only one "@" symbol 
			return false; 
		} 
		periodPos = email.indexOf(".",atPos); 
		if(periodPos == -1){ 
		 // and at least one "." after the "@" 
			return false; 
	    } 
		if ( atPos +2 > periodPos) {  // and at least one character between "@" and "." 
  			return false; 
 		} 
 		if ( periodPos +3 > email.length){ 
  			return false; 
 		} 
 		return true; 
	} 
	
//	函数说明
//	判断输入值是否是日期型
//  作者：
//  日期：
//  备注：
 	function If_Date(Input){ 	    
		var str=new String(Input);
		ArrayOfSplit=str.split("-");
		ArrayOfSplit1=str.split("/");
		if(ArrayOfSplit.length!=3&&ArrayOfSplit1.length!=3){
			return false;
		}	
		
		if(ArrayOfSplit.length==3){				
			for(var i=0;i<ArrayOfSplit.length;i++){
				
				if (isNaN(ArrayOfSplit[i]*1)){
				    return false;
				}
			}
			
			if(ArrayOfSplit[0]>3000||ArrayOfSplit[0]<1900){
				//如果年份超出范围（1900，3000）不合理
				return false;
			}			
			if (ArrayOfSplit[1]==2){
				//如果月份等于2，则天数不能大于30
				if (ArrayOfSplit[2]>30)
					return false;
			}
			if (ArrayOfSplit[1].length>2){
				//月份的长度不能超过两位
				return false;
			}
			if(ArrayOfSplit[1]>12||ArrayOfSplit[1]<1){
				//月份不等大于12
				return false;
			}
			
			if (ArrayOfSplit[2].length>2){
				//每个月的天数不能大于两位
				return false;
			}
			if(ArrayOfSplit[2]>31||ArrayOfSplit[2]<1){
				//每个月只有31 天
				return false;
			}
			return true;
		}

		if(ArrayOfSplit1.length==3)	{
			for(var i=0;i<ArrayOfSplit1.length;i++)	{
				if (isNaN(ArrayOfSplit[i]*1)){
				    return false;
				}
			}

     	    if(ArrayOfSplit1[0]>3000||ArrayOfSplit1[0]<1900){
				return false;
			}
			if (ArrayOfSplit1[1]==2){
				//如果月份等于2，则天数不能大于30
				if (ArrayOfSplit1[2]>30)
					return false;
			}
			if (ArrayOfSplit1[1].length>2){
				//月份的长度不能超过两位
				return false;
			}
			if(ArrayOfSplit1[1]>12||ArrayOfSplit1[1]<1){
				return false;
			}
			if (ArrayOfSplit1[2].length>2){
				//每个月的天数不能大于两位
				return false;
			}
			if(ArrayOfSplit1[2]>31||ArrayOfSplit1[2]<1){
				return false;
			}

			return true;
		}
	}
	

//	函数说明：判断输入值是否是浮点型
//  作者：zhaoyi
//  日期：20040715
//  备注：
	function If_Number(Input){ 
		//alert(parseFloat(Input));			    
		if(isNaN(parseFloat(Input))==false){
					    
			Input_int = parseFloat(Input*1);	
			//alert("*"+parseFloat(Input*1)+"*");
			//alert("*"+Input+"*");	
			//alert(Input == Input_int)							
			if(Input == Input_int){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

//	函数说明：判断字符串内容是否都为合法数字字符
//  作者：垃圾代码
//  日期：
//  备注：
    function If_Numeric(Input,I){

		var str=new String(Input);
		for(var i=0;i<str.length;i++){       
			if (I==0){
            	if((str.charAt(i)<'0'||str.charAt(i)>'9')&&(str.charAt(0)!='-')){
					return false;
                }else if ((i!=0)&&(str.charAt(i)<'0'||str.charAt(i)>'9')){
                    return false
                }
            }else{
            	if (str.charAt(i)<'0'||str.charAt(i)>'9'){
	                return false;
                }
            }
		}
		return true;
	}

//	函数说明：判断输入值是否是整数型
//  作者：zhaoyi
//  日期：20040715
//  备注：
	function If_Integer(Input){
		if(isNaN(parseInt(Input))==false){		    
			Input_int = parseInt(Input*1);	
			var Input_Parseed_string=Input_int + "";						
			if((Input == Input_int)&&(Input.length == Input_Parseed_string.length)){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

//	函数说明:判断字符串是否为空
//  作者：
//  日期：
//  备注：
	function If_NotNullValue(Value)	{  
	   
		var str = trim(Value);		
		if (str=="")
			return false;			
		if(str.length==0){ 
			return false;
		}		
		return true;
	}
	function IF_OverLength(value,fieldLength){
		if (value > fieldLength){
			return false;
		}else {
			return true;
		}
	
	}
	function trim(str){
		if(str.length == 0){
			str = "";
		}
		if (str.length == 1 && str.charAt(0) == " "){
			str = "";
		}
		var i;
		var first = 0;
		var last = 0;
		first = 0;
		for (i = 0; i < str.length; i++){
			first = i;
			tempChar = str.charAt(i);
			if (tempChar == " ")
			continue;
			else
			break;
		}

		last = str.length - 1;
		for (i = str.length - 1; i >= 0; i--){
			last = i;
			tempChar = str.charAt(i);
			if (tempChar == " "){
				continue;
			}else{
				break;
			}
		}

		if (last < first){
			return "";
		}else{
			return str.substr(first, last - first + 1);
		}
	}
	function If_Overnumbers(FieldValue,HowLong){     
		//alert(FieldValue+"$$$$$##"+HowLong);
		var str=new String(FieldValue);
		have=0;
		//alert(str);
		for(var i=0;i<str.length;i++){
		    if (str.charAt(i)=='.') {
		    	have++;
		    }
		}
		//alert(have);
		
		if (have==0){
			return true;
		}
		else{
	    	var  AfterDot=FieldValue.split(".");
	    
	  		 //alert(AfterDot[1].length+"***");
	   		//alert(HowLong+"***");
	  		// alert(AfterDot[1].length>HowLong);
	    	if (AfterDot[1].length>HowLong){
	        	return false;
	   		 }
	    	else {    		
	    		return true;
	    	}        
	    }
	                  
	        
	}   

	function IF_OutRange(value,range){
	    
		rangeArraytemp = range.split("-");	
		
		var range1=new String(rangeArraytemp[0]);
		var range2=new String(rangeArraytemp[1]);
		
		if (range1.substr(0,1)=="f"){
			value1="-"+range1.substr(1,range1.length-1);
		}else{
			value1=range1;
		}
		if (range2.substr(0,1)=="f"){
			value2="-"+range2.substr(1,range2.length-1);
		}else{
			value2=range2;
		}
		//alert(value+"**"+value1+"**"+rangeArraytemp[1]);
	   // alert((parseFloat(value) > parseFloat(value1))+"$$"+(parseFloat(value) < parseFloat(rangeArraytemp[1])));
			if ((parseFloat(value) >= parseFloat(value1)) && (parseFloat(value) <= parseFloat(value2)))
			{
				return true;
			}else
		    {
				return false;
		     }
	}
	function If_LengthOutOfRange(value,range){
        //zhaoyi20040325增加可以判断中文字串的实际长度
        //数据长度不要长于255，否则会影响性能  
		var str=new String(value);
		var k=0;
		var i=0;
		for(i=0;i<str.length;i++){
          if (str.charCodeAt(i)>255){
              k++;}
           k++; 
        }
        if (k>parseInt(range)){return false;}
        else {return true }
        
		/*if(str.Length>parseInt(range)){
			return false;
		}
		else {return true }*/
	}
	//清空表单的内容
	function clearInputContent(inputName){
		inputNameArray = inputName.split(",");
		len = inputName.split(",").length;	
		for(i=0;i<len;i++){
			eval("form1."+inputNameArray[i]+".value=''");
		}  
	}    
//date:2004-03-10   add by chai
////******************************************************************
// function  :转换日历中添加'0'的问题
// parameter :inDate
// return    :添加'0'的日期
//******************************************************************

    function tranDate(inDate){
    	
  		var newDate = null;
  		var reDate = "";
  		newDate = inDate.split("-");
  		for (var i = 0;i< newDate.length;i++){
  			if (i ==1){
  				if (newDate[i].length ==1)
  					newDate[i] = 0+newDate[i];
  			}else if (i ==2){
  				if (newDate[i].length ==1)
  					newDate[i] = 0+newDate[i];
  			}
  			reDate = reDate+newDate[i];
  		}	
  		 return reDate;  		 
  	}
 
 
 
//date:2004-4-10 add by chaixiaozong
/*=====获得当前日期    返回值：2004-4-10=====*/
function getCurrentDate()
{
	var date = new Date();
	var year = date.getYear();
	var month = date.getMonth();
	month = month+1;
	var date = date.getDate();
	var ymd = year+"-"+month+"-"+date;
	return ymd; 
}
 
//date:2004-4-10 add by chaixiaozong
//比较两个日期的大小 example: 2004-4-12 > 2004-4-10
//输入：compareDates(2004-4-12,2004-4-10)
//要调用tranDate(inDate)函数;
function compareDates(date1,date2)
{
        var d1 = tranDate(date1);
        var d2 = tranDate(date2);
        if(d1 >= d2)
        {
             //alert(date1+">或="+date2);
             return true;//date1>=date2;
        }
        else
        {
             // alert(date1+"<"+date2);
             return false;//date1<date2;
        }
}

//date:2004-7-21 add by blue
//限制输入框输入文本的最大长度： 支持中英文混排(稍有bug)，中文算作2个，英文算1个，
//sample:  <input type="text" value="" onpropertychange="checklength(this,12)" />;
//

function checklength(obj,len)
{
	var zcd = obj.value.length;
	var zwcd = obj.value.match(/\W/g) != null ? obj.value.match(/\W/g).length : 0;
	obj.maxLength=len - zwcd;

//	alert("总长度：" + zcd + "\n中文长度：" + zwcd + "限定长度:" + obj.maxLength);
	if(zwcd>0 && zwcd > obj.maxLength)
	{
		var duoyu =((zwcd+zcd) - len )/2;
		var shengyu = zcd - duoyu;
		//alert("剩余长度："+shengyu);
		obj.value = obj.value.substr(0,shengyu)
	}
}

