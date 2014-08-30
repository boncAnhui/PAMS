
function conseq(s)
{
//alert(s); 
  s1=s.split(",");len=s1.length;s2="";
  for(i=len-1;i>=0;i--)
  {
   
  s2+=s1[i]+",";
  }
  s2=s2.substring(0,s2.length-1);
  return s2;
}




function getRadioValue(radioel)
{
	for(var i=0; i<radioel.length; i++)
	{
		if(radioel[i].checked)
		{
			return radioel[i].value;
		}
	}

	return "";
}
function setRadioValue(radioel,s)
{ 
	for(var i=0; i<radioel.length; i++)
	{
		if(radioel[i].value == s)
		{
			radioel[i].checked = 1;
			return;
		}
	}
}

function rvalue(el)
{
	if(typeof(el) == "undefined")
	{
		return "";
	}
	return el;
}




function switchValue(a1)
{
	len = a1.length;
	vlen = a1[0].length;
	rs = new Array();
	for(i=0;i<vlen;i++)
	{
		eval("el" + i + " = new Array()");
	}
	
	for(i=0;i<len;i++)
	{
		for(k=0;k<vlen;k++)
		{
			eval("el" + k + " [el"+k+".length]   = a1[i][k];");
		}
	}

	for(i=0;i<vlen;i++)
	{
		eval( "rs[rs.length] = el" +i +";");
	}
	return rs;
}

function getMultiValue(fieldnames)
{
	temp = new Array();
	var fn = fieldnames.split(",");
	for(i=0;i<fn.length;i++)
	{
	   eval("var fvs= document.forms[0]."+fn[i]+".value;");
	   if(fvs.substr(fvs.length-1,fvs.length)==",")
	   {
		   fvs.substr(0,fvs.length-1);
	   }
		var fv = fvs.split(",");		
		temp[temp.length] =fv;
	}	

	return switchValue(temp);	
}

function setMultiValue(fieldnames,fieldvalues)
{	
	try
	{
		if(typeof(fieldvalues)!="object" || fieldvalues==null ){ return ;};
		//fieldvalues=conseq(fieldvalues);
		//alert(fieldvalues);
		
		rs = switchValue(fieldvalues);
		//alert("test\n" +rs);
		var fn = fieldnames.split(",");
	
		for(i=0; i<fn.length; i++)
		{
			eval("document.forms[0]." + fn[i] + ".value=rs[" + i + "].join(',');") ;	
		}
	}catch(Exception)
	{
		return;
	}
	
	
}