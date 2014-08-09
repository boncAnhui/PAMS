/**
 * @author	巩睿
 * @date		2004-06-09
 * @version v0.1
 **/

/** 全局变量 **/
//路径类


/*modified by davidguoshuang 2013-01-22   -start*/

/*
window.showModalDialog=function(a){
	window.open(a);
}

*/

/*modified by davidguoshuang 2013-01-22   -end*/

var rootpathform = "/pams";
var rootpath = "\\pams\\wfb\\blueflowui\\";
var skinpath = "default\\";
var imagepath= rootpath + "images\\"  ; 
var soundpath = rootpath+ "sounds\\";

//图片资源类

var startNormalImage="start.png" ;
var endNormalImage="end.png" ;
var personalNormalImage="personal.png" ;
var personalsubNormalImage="personalsub.png";
var serialNormalImage="serial.png" ;
var parallelNormalImage="parallel.png" ;
 
var imageWidth = "50"
var imageHeight = "50"

//其他类

var nodecount=0, linecount = 0;
var on = false;
var originX,originY,positionX,positionY;

var activenode;
var activeline;

var toolbarname = new Array("select","start","end","personal","serial","parallel","personalsub","line");

var canDraw = 0;
var drawMode = "";
var moveMode = "";

var linebreak = null;
var rightClickObj = null,leftClickObj = null;
var flowName = "",clasForSno="",snoForSno="";
//最小象素点

var minPoint = 25;

var beginNum=0;
var endNum=0;
var tmp_formid="",tmp_formname="",tmp_classid="",tmp_rtasklist="",tmp_field=""; 
 

/**事件封装 **/
//绘图板载入

window.onload=function()
{
	
}

//防止未下载完毕时发生异步错误


/*
window.onerror=function()
{ 
	return true;
}
*/

if(window.addEventListener){
	window.addEventListener('onerror',function(){return true;},false)
}else if(window.attachEvent){
	window.attachEvent('onerror',function(){return true;})	
}

//点击开始时

document.onselectstart=function()
{
	document.selection.empty();
	//event.returnValue=false;             //取消选择功能

}

//工具栏点击开始

toolbar.onmousedown=function()
{
	originX=event.x;		//记录鼠标位置
	originY=event.y;

	moveMode="toolbar";	                                //托拽模式(工具列)

	positionX=parseInt(toolbar.style.left);		//记录原位置坐标(转成数字)
	positionY=parseInt(toolbar.style.top);

	event.cancelBubble=true;
}

for(i=0;i<toolbarname.length;i++)
{
	var toolbartype = document.getElementById("toolbar-" + toolbarname[i]);
	toolbartype.onmousedown=function(){event.cancelBubble=true;}
	toolbartype.onclick=function()
	{
		for(k=0;k<toolbarname.length;k++)
		{
			 document.getElementById("toolbar-" + toolbarname[k]).style.border="solid 0";
		}

		// flowsound.URL= soundpath + this.id+".wav";
 		// flowsound.controls.play();

		this.style.border="1 inset";
		drawMode=this.id;	
		moveMode = "";
		canDraw=1;		
		event.cancelBubble=true;
	}
}

//鼠标移动时间

workarea.onmousemove=function()
{
  window.status='x:'+(event.x +document.body.scrollLeft) +';y:'+(event.y + document.body.scrollTop);

	if(on)
	{
		if(moveMode=="toolbar")
		{
			//toolbar.style.left=positionX+(event.x-originX);	//新位置＝原位置＋位移
			//toolbar.style.top=positionY+(event.y-originY);

		}
		else if(moveMode == "node")
		{			
			activenode.style.left=positionX+(event.x-originX);
			activenode.style.top=positionY+(event.y-originY);
			activenode.x = activenode.style.left;
			activenode.y = activenode.style.top;
		}
		else if(moveMode == "line")
		{
			moveMode = "linemove";
		}
	}
}

//右键菜单

document.oncontextmenu=function()
{
	cmenu.style.left=event.x + document.body.scrollLeft;
	cmenu.style.top=event.y + document.body.scrollTop;

	return false;
}

//隐藏右键菜单

function hideRightKey()
{
	cmenu.style.left = "-1000";
	cmenu.style.top = "-1000";

	cmenuNode.style.left = "-1000";
	cmenuNode.style.top = "-1000";

	cmenuLine.style.left = "-1000";
	cmenuLine.style.top = "-1000";
}
document.onclick=hideRightKey;
//放开鼠标键

workarea.onmouseup=function()
{


	if(moveMode == "node")
	{
		activenode.style.left=calcPoint(parseInt(activenode.style.left));
		activenode.style.top=calcPoint(parseInt(activenode.style.top));
		activenode.x = calcPoint(parseInt(activenode.style.left));
		activenode.y = calcPoint(parseInt(activenode.style.top));
	
		moveLine(activenode);
	}

	if(moveMode == "linemove")
	{
		//alert("lineup");
		//alert("mPoints="+activeline.mPoints + "\nlinebreak=" + linebreak + "\nx,y=" + event.x +"," + event.y);

		if(typeof(linebreak)=="string")
		{
			var temp = linebreak.substring(1,linebreak.length);
			var movedLinePoints = rebuildPoints(activeline.mPoints,"c",parseInt(temp),event.x+document.body.scrollLeft,event.y+document.body.scrollTop);	
		}
		else
		{
			var movedLinePoints = rebuildPoints(activeline.mPoints,"",linebreak+2,event.x+document.body.scrollLeft,event.y+document.body.scrollTop);	
		}

		activeline.points.value = movedLinePoints;
		activeline.mPoints = movedLinePoints;	
		
		activeline.strokecolor ="rgb( 0, 0, 0)";
	}


	on=false;					//关闭onMouseMove事件

//	moveMode="";		//关闭托拽模式

}

//记录鼠标点击菜单

workarea.onmousedown=function()
{
 moveMode=null;
 tmpname="";nodename="";htype="普通";
   
	originX=event.x;		//记录鼠标位置

	originY=event.y;
	if(drawMode=="toolbar-select")
	{
		on = false;
		moveMode= "";
		return;
	}
	
	if(drawMode=="toolbar-start" || drawMode=="toolbar-end" || drawMode=="toolbar-personal" || drawMode=="toolbar-serial"  || drawMode=="toolbar-parallel" || drawMode=="toolbar-personalsub")
	{
		var ctype = "";
		switch(drawMode)
		{
			case "toolbar-start":
				ctype = "BEGIN";nodename="begin";tmpname="开始";  htype="普通";
				break;
			case "toolbar-end":
				ctype="END";nodename="end";tmpname="结束";  htype="普通";
				break;
			case "toolbar-personal":
				ctype="NORMAL";nodecount += 1;	 nodename="node"+nodecount;	 tmpname="普通"+nodecount ;htype="普通";
				break;
			case "toolbar-serial":
				ctype="NORMAL";nodecount += 1;	  nodename="node"+nodecount;	 tmpname="多人串行"+nodecount ; htype="多人串行";
				break;
			case "toolbar-parallel":
				ctype="NORMAL";nodecount += 1;	 nodename="node"+nodecount;  tmpname="多人并行"+nodecount ;  htype="多人并行";
				break;
			case "toolbar-personalsub":
				ctype="SUBFLOW";nodecount += 1;	 nodename="node"+nodecount;  tmpname="子流程"+nodecount ;  htype="普通";
				break;
		}
		nodeimage = drawMode.substr(8,drawMode.length-8)+".png";
		//alert(nodeimage);

		var x = event.x+document.body.scrollLeft;
		var y = event.y+document.body.scrollTop;
		x = calcPoint(x-imageWidth/2);
		y = calcPoint(y-imageHeight/2);
		 
 if(ctype=="BEGIN") { if(beginNum==1)return;  else beginNum=1;}
 if(ctype=="END") {if(endNum==1   )return;else endNum=1;}
  
		startnode = new Node(nodename,tmpname, ctype, "", "", "", "MANUAL", "ALL", "MAIN", "flowid", tmp_formid, tmp_formname, htype, "OR", "OR", "N", "EVERYTIME","单人","0", "actionowner", "acttask",nodeimage,x,y,tmp_classid,"rList","stId","stName","actstratege","全写","actfield");
		startnode.draw();
		startnode.setAtt();

		//nodecount += 1;		
	}
}

function wkareadblclick()
{
	var flowid=document.forms[0].flowid.value;
	//alert(flowid);
	//alert(window.event)
	//window.event.cancelBubble=true;
	
	event.cancelBubble=true;
	
	clist=classMgrClassId ; 
	rolist=roleList.split(","); 
	if((userID=="-1")||(isClassManager=="1"))
	{
	 if((isClassManager=="1")&&(tmp_classid!=""))
	 
	 {
	   if (clist.indexOf(tmp_classid)>0) 
	   var rv = window.showModalDialog(rootpathform+"/module/app/system/workflow/bflow/bflow_insertflow.action?classMgrClassId="+classMgrClassId,workarea,"status:off;help:0; dialogWidth:"+pub_width_mid+"px;dialogHeight:"+pub_height_mid+"px;edge:sunken; ");
	   else
	   var rv = window.showModalDialog(rootpathform+"/module/app/system/workflow/bflow/bflow_insertflowforview.action?flowid="+flowid,workarea,"status:off;help:0; dialogWidth:"+pub_width_mid+"px;dialogHeight:"+pub_height_mid+"px;edge:sunken; ");
	   //var rv = window.showModalDialog(rootpath+"flow\\InsertFlowForView.jsp?classMgrClassId="+classMgrClassId,workarea,"status:off;help:0; dialogWidth:600px;dialogHeight:600px;edge:sunken; ");
	 }
	 else
	 var rv = window.showModalDialog(rootpathform+"/module/app/system/workflow/bflow/bflow_insertflow.action?classMgrClassId="+classMgrClassId,workarea,"status:off;help:0; dialogWidth:"+pub_width_mid+"px;dialogHeight:"+pub_height_mid+"px;edge:sunken; ");
	}
	else
	 {
	 if (document.forms[0].flowid.value!="") 
	 var rv = window.showModalDialog(rootpathform+"/module/app/system/workflow/bflow/bflow_insertflowforview.action?flowid="+flowid,workarea,"status:off;help:0; dialogWidth:"+pub_width_mid+"px;dialogHeight:"+(parseInt(pub_height_mid)+30)+"px;edge:sunken; ");
	 //var rv = window.showModalDialog(rootpath+"flow\\InsertFlowForView.jsp",workarea,"status:off;help:0;dialogWidth:600px;dialogHeight:600px;edge:sunken; ");
     else 
     var rv = window.showModalDialog(rootpathform+"/module/app/system/workflow/bflow/bflow_insertflow.action",workarea,"status:off;help:0; dialogWidth:"+pub_width_mid+"px;dialogHeight:"+(parseInt(pub_height_mid)+30)+"px;edge:sunken; ");
     }
	if (typeof(rv)=="undefined")
	{
		return;
	};
	var rFlow = new Flow(rv.mid,rv.cname,rv.sno,rv.ver,rv.state,rv.formid,rv.createformid,rv.startchoice,rv.readerchoice,rv.classid,rv.flowowner,rv.flowreader,rv.formname,rv.createformname,rv.classname,rv.field);
	rFlow.setAtt();
	document.all.flowname.innerText="流程名称："+rv.cname;
	document.all.fsno.innerText="流程编号："+rv.sno;
	document.all.flowsno.value= rv.sno;
	document.all.fclass.value= rv.classid;
	document.all.flowclass.innerText= "流程类别："+rv.classname;
	document.all.flowstate.innerText= "当前状态："+rv.state;
	document.all.flowver.innerText="版本："+rv.ver;
	
	//document.all.flowstate.innerText="当前状态："+rv.state;
  	document.all.fcname.value=rv.cname;
	tmp_formid= rv.formid;tmp_formname= rv.formname;tmp_classid=rv.classid;tmp_field=rv.field;
	
}



workarea.ondblclick=function(){wkareadblclick();}



function nodeMouseDown()
{
	 
	originX=event.x;		//记录鼠标位置

	originY=event.y;

	moveMode = "node";
	activenode = getParent(window.event.srcElement,"image");
    leftClickObj=activenode;
	//alert("nodemousedown");

	if(drawMode=="toolbar-select")
	{
		//activenode.className = "nodeSelected";		

	}


	positionX=parseInt(activenode.style.left);		//记录原位置坐标(转成数字)

	positionY=parseInt(activenode.style.top);


	if(drawMode == "toolbar-line")
	{
		
		moveMode = "linedraw";
	}

	on = true;           //打开onMouseMove事件

	
	event.cancelBubble=true;
}

function nodeMouseUp()
{   
	if(moveMode == "linedraw")
	{	
		//alert((event.x-originX) +"," + (event.y-originY));
         
		var endnode = getParent(window.event.srcElement,"image");
		if(activenode!=endnode) 
		{
        lineTmpname=endnode.cname;

		var linepoints =  (parseInt(activenode.x)+ parseInt(imageWidth/2))  +","
							       + (parseInt(activenode.y) +parseInt(imageHeight/2)) + "," 
								   + (parseInt(endnode.x) + parseInt((imageWidth)/2)) + "," 
								   + (parseInt(endnode.y) + parseInt((imageHeight)/2)) ;
 		 
		activeline = new Line( "line" + linecount , "转"+lineTmpname, "NORMAL", "1=1",activenode.id,endnode.id, "flowid", "正向",  "routetask",linepoints);
		activeline.draw();	
		activeline.setAtt();

		linecount += 1;		
		//moveMode = "";

		//activeline.setPoints()	
        }
	}
	on=false;			//关闭onMouse事件

}

function NodeOnContextMenu()
{
	cmenuNode.style.left=event.x + document.body.scrollLeft;
	cmenuNode.style.top=event.y + document.body.scrollTop;
	rightClickObj = window.event.srcElement;

	on = false;
	moveMode= "";
	return false;
}

function moveLine(node)
{
	//alert("move line");
	var nodeid = node.id;
	var olines = document.getElementsByTagName("polyline");
	for(var i=0;i<olines.length;i++)
	{

		var startid = olines(i).startactid;
		var endid = olines(i).endactid;
		
		//alert("startid=" + startid + " endid=" +endid + " nodeid=" + nodeid);

		var n= (parseInt(node.x)+ parseInt(imageWidth/2))  +","
							       + (parseInt(node.y) +parseInt(imageHeight/2));

        var  movedLinePoints = "";
	
		if(startid == node.id)
		{
			//alert("ddd");
			movedLinePoints = replaceHeadPoints(olines(i).mPoints,n)
			

			olines(i).points.value = movedLinePoints;
			olines(i).mPoints = movedLinePoints;
		}
		else if(endid == node.id)
		{
			movedLinePoints = replaceTailPoints(olines(i).mPoints,n)
			olines(i).points.value = movedLinePoints;
			olines(i).mPoints = movedLinePoints;
		}
	}
}


function nodeDblClick(l)
{
var strategicId=new Array(),strategicName=new Array(),endactid=new Array(),endactname=new Array();
	event.cancelBubble=true;
 	var el=null;
	 
	if(l=="0")	 el = getParent(rightClickObj,"image");
	else    el = getParent(leftClickObj,"image");
	
	//  alert(el.classid);
	// alert(tmp_classid);
	 if((tmp_classid=="")||(tmp_classid=="null")) 
	 {
	    alert("没有选择流程类别，请先在流程属性定义对话框中进行选择！");
	    return;
	 }
	el.classid=tmp_classid;
	// if((el.formid=="")||(el.formid=="null")) 
	el.formid=tmp_formid;
	   
	el.field=tmp_field; 
	// if((el.formname=="")||(el.formname=="null"))  return;
	el.formname=tmp_formname;
	//alert(el.ctype);
	//--------决策 stritage-------
	 t1=document.getElementsByTagName("polyline");
	 curActId=el.id;
	 st=0;
	 for(i=0;i<t1.length;i++)
	 {
	   if(curActId==t1[i].startactid)
	   {
	     strategicId[st]=t1[i].id;
	     strategicName[st]=t1[i].cname;
	     endactid[st]=t1[i].endactid;
	     st=st+1;
	     
	    // alert(t1[i].id+"="+t1[i].cname);
	   }
	  }
	 // alert(strategicId);
	  
	  // alert(endactid);
	  st=0;
	  tt=document.getElementsByTagName("image");
	  for(j=0;j<tt.length;j++)
	  {
	    for(i=0;i<endactid.length;i++)
	    {
	      if(tt[j].id==endactid[i])  {
	        endactname[st]="转"+tt[j].cname;
	        st=st+1;
	      }
	    }
	  }
	 // alert(endactname);
	  el.stId=strategicId;
	  el.stName=endactname;
	//--------决策-------

	
     if((el.ctype=="NORMAL")||(el.ctype=="SUBFLOW")||(el.ctype=="SERIAL")||(el.ctype=="PARALLEL")) { 
	var rv = window.showModalDialog(rootpathform+"/module/app/system/workflow/bact/bact_insertnode.action?actid="+el.id,el,"status:off;help:0;dialogWidth:"+pub_width_large+"px;dialogHeight:"+pub_height_large+"px;edge:sunken;");
	//window.open(rootpathform+"/module/app/system/workflow/bact/bact_insertnode.action?actid="+el.id,'insertnode','960','540');

	if (typeof(rv)=="undefined")
	{
		return;
	};

	
	var rNode = new Node(rv.id,rv.cname,rv.ctype,rv.subflowid,rv.subflowsno,rv.subflowname,rv.subflowcreate,rv.subflowclose,rv.subflowlink,rv.flowid,rv.formid,rv.formname,rv.handletype,rv.join,rv.split1,rv.isfirst,rv.outstyle,rv.selectstyle,rv.selectother,rv.actionowner,rv.acttask,rv.imagename,rv.x,rv.y,rv.classid,rv.rlist,rv.stId,rv.stName,rv.actstratege,rv.rwtype,rv.actfield,rv.field); 
	rNode.setAtt();}
}

function lineMouseDown()
{
	 //alert("linemousedown");

	originX=event.x;		//记录鼠标位置

	originY=event.y;

	moveMode = "line";

	activeline = window.event.srcElement;
	leftClickObj=activeline;
	//alert(window.event.srcElement.mPoints);
	//alert(activeline.id);

	positionX=parseInt(event.x);		//记录原位置坐标(转成数字)

	positionY=parseInt(event.y);	
	//alert(activeline.innerHTML);

	activeline.strokecolor ="rgb( 255, 0, 0)";

	linebreak = compPoint(activeline.mPoints,event.x + document.body.scrollLeft,event.y +document.body.scrollTop);
	//alert("linebreak=" + linebreak);


	on = true;           //打开onMouseMove事件
	//alert(moveMode +"::::::::::::::");

	event.cancelBubble=true;
}


function lineDblClick(l)
{ 
	 event.cancelBubble=true;
	var el = null;
	if(l=="0") el=getParent(rightClickObj,"polyline");
	else el=getParent(leftClickObj,"polyline");
	
	 //  alert(el.startactid);
	ds=document.getElementById(el.startactid);
	if (ds.cname=="开始")  return false;
	tmp_rtasklist=ds.rlist;
 	de=document.getElementById(el.endactid);
 	lineTmpName=de.cname;
 	
    
     // alert(tmp_rtasklist);

    el.rtlist=tmp_rtasklist;
    el.cname="转"+lineTmpName;
    //暂时注释路由的双击弹窗
    /*var rv = window.showModalDialog(rootpathform+"/module/app/system/workflow/route/route_insertline.action?routeid="+el.id,el,"status:off;help:0; dialogWidth:"+pub_width_mid+"px;dialogHeight:"+pub_height_mid+"px;edge:sunken; ");

	if (typeof(rv)=="undefined")
	{
		return;
	};	
	
	var rLine = new Line(rv.id,rv.cname,rv.routetype,rv.conditions,rv.startactid,rv.endactid,rv.flowid,rv.direct,rv.routetask,rv.mPoints,rv.rtlist); 
	rLine.setAtt();
*/	
}

function LineOnContextMenu()
{
	cmenuLine.style.left=event.x + document.body.scrollLeft;
	cmenuLine.style.top=event.y + document.body.scrollTop;
	rightClickObj = window.event.srcElement;

	on = false;
	moveMode= "";
	return false;

}



/**  流程定义类   **/

function Flow(id, cname, sno,ver,state, formid, createformid, startchoice, readerchoice, classid, flowowner,flowreader,formname,createformname,classname,field)
{
	this.mid= id;
	this.cname = cname;
	this.sno = sno;
	this.ver = ver;
	this.state = state;
	this.formid = formid;
	this.formname = formname;
	this.createformid = createformid;
	this.createformname = createformname;
	this.startchoice = startchoice;
	this.readerchoice = readerchoice;
	this.classid = classid;
	this.classname = classname;
	this.field = field;
	

	this.flowowner = flowowner;
	this.flowreader = flowreader;
	
	this.setAtt = setAtt;
	this.draw = draw;


	function setAtt()
	{
		var rFlow =  document.getElementById("workarea");
		rFlow.mid = this.mid;
		rFlow.cname = this.cname;
		rFlow.sno = this.sno;
		rFlow.ver = this.ver;
		rFlow.state = this.state;
		rFlow.formid = this.formid;
		rFlow.formname = this.formname;
		rFlow.createformid = this.createformid;
		rFlow.createformname = this.createformname;
		rFlow.startchoice = this.startchoice;
		rFlow.readerchoice = this.readerchoice;
		rFlow.classid = this.classid;
		rFlow.classname = this.classname;
		rFlow.field = this.field;

		rFlow.flowowner = this.flowowner;
		rFlow.flowreader = this.flowreader;
	}

	function draw()
	{
		
	}

	function remove()
	{
		
	}

}

function Node(id, cname, ctype, subflowid, subflowsno, subflowname, subflowcreate, subflowclose, subflowlink, flowid, formid, formname, handletype, join, split1, isfirst, outstyle,selectstyle,selectother, actionowner, acttask, imagename, x,y,classid,rlist,stId,stName,actstratege,rwtype,actfield,field)
{

	this.id = id;
	this.cname = cname;
	this.ctype = ctype;
	this.subflowid = subflowid;
	this.subflowsno = subflowsno;
	this.subflowname = subflowname; 
	this.subflowcreate = subflowcreate;
	this.subflowclose = subflowclose;
	this.subflowlink = subflowlink;
	
	this.flowid = flowid;
	this.formid = formid;
	this.formname = formname;
	this.handletype = handletype;
	this.join = join;
	this.split1 = split1;
	this.isfirst = isfirst;
	this.outstyle = outstyle;
	this.selectstyle = selectstyle;
	this.selectother = selectother;
	this.classid = classid;
	this.rlist = rlist;
	this.stId = stId;
	this.stName = stName;
	this.rwtype = rwtype;
	this.field = field;
	

	this.actionowner = actionowner;
	this.acttask = acttask;
	this.actstratege = actstratege;
	this.actfield = actfield;

	this.x = x;
	this.y = y;

	this.setAtt = setAtt;
	this.draw = draw;	
	this.remove = remove;

	this.position="absolute";
	this.cursor = "move";
	
	//alert(this.x);
	if(x=="null")
	{	
		this.x = "400";
	}
	if(y=="null")
	{
		this.y = "200";
	}

	if(imagename==null)
	{ 
		if((ctype=="BEGIN")&&(handletype=="普通"))
		{
			imagename = startNormalImage;
		}
        else if((ctype=="END")&&(handletype=="普通"))
		{
			imagename = endNormalImage;
		}
		else  if((ctype=="NORMAL")&&((handletype=="普通")||(handletype=="指定专人")))
		{
			imagename = personalNormalImage;
		}
		else if((ctype=="NORMAL")&&((handletype=="多人串行")||(handletype=="多部门串行")))
		{
			imagename = serialNormalImage;
		}
		else  if((ctype=="NORMAL")&&(handletype=="多人并行"))
		{
			imagename = parallelNormalImage; 
		}
		else if((ctype=="SUBFLOW"))
		{
			imagename = personalsubNormalImage;
		}
	}
	
	
	this.src = imagepath + imagename;
	this.imagename = imagename;

	function draw()
	{
		var rNode=document.createElement("v:image");
		rNode.id = this.id;
		rNode.src=this.src;
		rNode.style.width=imageWidth;
		rNode.style.height=imageHeight;		
		rNode.style.left=this.x;
		rNode.style.top=this.y;
		rNode.style.position = this.position;
		workarea.appendChild(rNode);	
		
		//alert("x:" + this.x + " y:" + this.y + "\nleft:" + rNode.style.left + " top:" + rNode.style.top);

		rNode.attachEvent("onmousedown",nodeMouseDown);			
		rNode.attachEvent("onmouseup",nodeMouseUp);
		rNode.attachEvent("ondblclick",nodeDblClick);
		rNode.attachEvent("oncontextmenu",NodeOnContextMenu);

		if(this.ctype=="BEGIN" || this.ctype=="END")
		{
			return;
		}
		
		var nobj = document.createElement("<span>");
		nobj.innerText = this.cname;		

		nobj.style.left=0;
		nobj.style.top=imageHeight-3;
		nobj.style.width= imageWidth;

		nobj.className = "sign";
		rNode.appendChild(nobj);
	}

	function setAtt()
	{
		var rNode =  document.getElementById(this.id);
		rNode.x = this.x;
		rNode.y = this.y;
		
		
		rNode.cname = this.cname;
		rNode.ctype = this.ctype;
		rNode.subflowid = this.subflowid;
		rNode.subflowsno = this.subflowsno;
		rNode.subflowname = this.subflowname;
		rNode.subflowcreate = this.subflowcreate;
		rNode.subflowclose = this.subflowclose;
		rNode.subflowlink = this.subflowlink;
		
		rNode.formid = this.formid;
		rNode.formname = this.formname;
		rNode.handletype = this.handletype;
		rNode.join = this.join;
		rNode.split1 = this.split1;
		rNode.isfirst = this.isfirst;
		rNode.outstyle = this.outstyle;
		rNode.selectstyle = this.selectstyle;
		rNode.selectother = this.selectother;
		rNode.classid = this.classid;
		rNode.rlist = this.rlist;
		rNode.stId = this.stId;
		rNode.stName = this.stName;
		rNode.rwtype = this.rwtype;
		rNode.field = this.field;
		rNode.style.zIndex = "99";

		rNode.actionowner = this.actionowner;
		rNode.acttask = this.acttask;
		rNode.actstratege = this.actstratege;
		rNode.actfield = this.actfield;
		rNode.imagename = this.imagename;	
		
		if(rNode.childNodes.length>=1)
		{
			rNode.childNodes(0).innerText = this.cname;
		}

	}

	function remove()
	{
		var rNode = document.getElementById(this.id);
		 
		rNode.removeNode(true);
	}

}

function Line(id, cname, routetype, conditions, startactid, endactid, flowid, direct, routetask, points,rtlist)
{
	this.id = id;
	this.cname = cname;
	this.routetype = routetype;
	this.conditions = conditions;
	this.startactid = startactid;
	this.endactid = endactid;
	this.flowid = flowid;
	this.direct = direct;
	this.rtlist = rtlist;

	this.routetask =  routetask;
	if(points == "null")
	{
		points = "400,200,500,300";	
	}

	this.mPoints = points;

	this.position="absolute";

	this.setAtt = setAtt;
	this.draw = draw;	
	this.remove = remove;
 
	
	function setAtt()
	{
		var rLine =  document.getElementById(this.id);
	
		rLine.cname = this.cname;
		rLine.routetype = this.routetype;
		rLine.conditions = this.conditions;
		rLine.startactid = this.startactid;
		rLine.endactid = this.endactid;
		rLine.flowid = this.flowid;
		rLine.direct = this.direct;
		rLine.rtlist = this.rtlist;

		rLine.routetask = this.routetask;

		rLine.mPoints = this.mPoints;
		//rLine.points.value = this.mPoints;

	}

	function draw()
	{
		var rLine=document.createElement("v:polyline");
		rLine.id = this.id;
		rLine.strokecolor = "rgb( 0, 0, 0)";
		rLine.strokeweight = "1.25pt";
		 
		rLine.style.position = this.position;
		rLine.style.left = 0;
		rLine.style.top = 0;
		rLine.style.zIndex = 999;
		rLine.filled="false";

		rLine.points = this.mPoints;
		rLine.mPoints = this.mPoints;
	
	//add arrow 2004-10-21
		rLineStroke=document.createElement("v:stroke");
		rLineStroke.EndArrow="block ";
		//rLineStroke.endarrowwidth="wide";
		rLineStroke.endarrowlength="long";
		rLineStroke.opacity="50%";
		rLine.appendChild(rLineStroke);
	//add arrow 2004-10-21	
		
 		workarea.appendChild(rLine);
		 

		rLine.attachEvent("onmousedown",lineMouseDown);			
		rLine.attachEvent("ondblclick",lineDblClick);
		rLine.attachEvent("oncontextmenu",LineOnContextMenu);


	}	

	function remove()
	{
		var rLine = document.getElementById(this.id);
		rLine.removeNode(true);
	}
}

function getParent(el, pTagName) 
{ 
    // getParent object	

	while (el && 
			(!el.tagName || el.tagName.toLowerCase() != pTagName.toLowerCase()) || el.className == "sign")
		{
			el = el.parentNode;
		}
		 
	return el;
}

function calcPoint(point)
{

	return Math.round(point/minPoint)*minPoint
}

function rebuildPoints(p,t,c,x,y)
{
	var arrayP = p.split(",");

	if(t == "c")
	{
		if(c>0 && c<arrayP.length)
		{
			arrayStart = arrayP.slice(0,parseInt(c))
			arrayEnd = arrayP.slice(c+2,arrayP.length);			
			var arrayMiddle = new Array(calcPoint(x),calcPoint(y));	

			return arrayStart.concat(arrayMiddle).concat(arrayEnd).join(",");
		}
		return p;
	}
	arrayStart = arrayP.slice(0,parseInt(c))
	arrayEnd = arrayP.slice(c,arrayP.length);
	var arrayMiddle = new Array(calcPoint(x),calcPoint(y));	
	return arrayStart.concat(arrayMiddle).concat(arrayEnd).join(",");
}

function replaceHeadPoints(p,n)
{
	var arrayP = p.split(",");
	arrayEnd = arrayP.slice(2,arrayP.length);
	return n+"," + arrayEnd.join(",")

}

function replaceTailPoints(p,n)
{
	var arrayP = p.split(",");
	arrayStart = arrayP.slice(0,arrayP.length-2);
	
	return arrayStart.join(",") + "," + n;
}


function compPoint(oldPoints, ex,ey)
{
	var arrayP=oldPoints.split(",");
	var i = 0;
	
	while(i<arrayP.length)
	{
		x1=parseInt(arrayP[i]);
		y1=parseInt(arrayP[i+1]);
		x2=parseInt(arrayP[i+2]);
		y2=parseInt(arrayP[i+3]);			
		
		//alert("x1=" + x1 + " y1=" + y1 + " \nx2=" + x2 + " y2=" + y2  + "\neventx=" +ex + " eventy=" +ey);

		if(Math.abs(ex-x1)<30 && Math.abs(ey-y1) <30)
		{
			return "c" + i;
		}

		if(Math.abs(ex-x2)<30 && Math.abs(ey-y2) <30)
		{
			return "c" + (i + 2);
		}

		if(x1 == x2)
		{
			xn=ex;
			yn=ey;
			k1 = x1 - xn;
			k2 = x2 - xn;
		}
		else
		{
			xn=ex;
			yn=(y1*(x2-xn)+y2*(xn-x1))/(x2-x1); //最先进的初中生高新技术！！！  计算x轴相交点y坐标

			k1=(yn-y1)/(xn-x1);  //注意 xn x1不能等!!!!!!!!!!!

			k2=(y2-yn)/(x2-xn);
		}

		//alert("x1=" + x1 + " y1=" + y1 + " \nx2=" + x2 + " y2=" + y2 + "\nxn=" + xn + " yn=" +yn + "\nk1=" + k1 +" k2=" +k2);

		if(parseInt(Math.abs((k1-k2)*1000)) < 15)
		{
			//alert("已经满足了斜率相等" + "\n" + "eventy =" + ey + " yn=" + yn);

			if(Math.abs(ey-yn) < 20)   //y轴坐标近似于事件发生点
			{
				if( (x2>xn && xn>x1 || x2<xn && xn<x1) && (y2>yn && yn>y1 ||  y2<yn && yn<y1) ) 
				{
					//alert("x1=" + x1 + " y1=" + y1 + " \n x2=" + x2 + " y2=" + y2 + "\n  xn=" + xn + " yn=" +yn);

				}
				return i;
				break;
			}
		}		
	
		i+=2;
	}
}

function contextOpen()
{
	 
	 
	wkareadblclick();	
}
function contextOpenNode()
{
	 
	nodeDblClick("0");	
}
 
function contextOpenNodeMenu()
{
  //alert(flowid);
  if(moveMode=="node")  nodeDblClick("1");
  else if(moveMode=="line")  lineDblClick("1");
  else wkareadblclick();
   		
}

function contextDeleteNode()
{
try{
	var rNode = getParent(rightClickObj,"image");
	if(rNode.ctype=="BEGIN") beginNum=0;;
	if(rNode.ctype=="END") endNum=0;;
	
//delete lines associated with the node;
	actId=rNode.id;
	oLines = document.getElementsByTagName("polyline");
	for(var i=0; i<oLines.length; i++)
	{
		   sid = oLines(i).startactid; 
		   if(sid==actId) oLines(i).removeNode(true);;
		   eid = oLines(i).endactid; 
		   if(eid==actId) oLines(i).removeNode(true);;
	}	
	oLines = document.getElementsByTagName("polyline");
	for(var i=0; i<oLines.length; i++)
	{
		   sid = oLines(i).startactid; 
		   if(sid==actId) oLines(i).removeNode(true);;
		   eid = oLines(i).endactid; 
		   if(eid==actId) oLines(i).removeNode(true);;
	}	
	oLines = document.getElementsByTagName("polyline");
	for(var i=0; i<oLines.length; i++)
	{
		   sid = oLines(i).startactid; 
		   if(sid==actId) oLines(i).removeNode(true);;
		   eid = oLines(i).endactid; 
		   if(eid==actId) oLines(i).removeNode(true);;
	}	
	
 	
	
	 
 rNode.removeNode(true);
 }catch(Exception)
 {
   rNode.removeNode(true); 
 }	
	
}
function contextDeleteNodeMenu()
{    

 if(moveMode=="node")
   {
	try{
	var rNode = getParent(leftClickObj,"image");
	 
	if(rNode.ctype=="BEGIN") beginNum=0;;
	if(rNode.ctype=="END") endNum=0;;
//delete lines associated with the node;
	actId=rNode.id;
	oLines = document.getElementsByTagName("polyline");
	for(var i=0; i<oLines.length; i++)
	{
		   sid = oLines(i).startactid; 
		   if(sid==actId) oLines(i).removeNode(true);;
		   eid = oLines(i).endactid; 
		   if(eid==actId) oLines(i).removeNode(true);;
	}	
	
	oLines = document.getElementsByTagName("polyline");
	for(var i=0; i<oLines.length; i++)
	{
		   sid = oLines(i).startactid; 
		   if(sid==actId) oLines(i).removeNode(true);;
		   eid = oLines(i).endactid; 
		   if(eid==actId) oLines(i).removeNode(true);;
	}	
	
	oLines = document.getElementsByTagName("polyline");
	for(var i=0; i<oLines.length; i++)
	{
		   sid = oLines(i).startactid; 
		   if(sid==actId) oLines(i).removeNode(true);;
		   eid = oLines(i).endactid; 
		   if(eid==actId) oLines(i).removeNode(true);;
	}	
	
	 
 rNode.removeNode(true);
 }catch(Exception)
 {
   rNode.removeNode(true); 
 }	
   }
 else if (moveMode=="line")  
   {
	var rLine = rightClickObj;
	rLine.removeNode(true);
   
   }	
}

function contextOpenLine()
{
	lineDblClick("0");	
}

function contextDeleteLine()
{
	var rLine = rightClickObj;
	rLine.removeNode(true);
}

//保存流程
function saveflow(nou)
{
	var objDom = new ActiveXObject("MSXML.DOMDocument");
	var objRoot = objDom.createElement("flowdefine");
	objDom.appendChild(objRoot) 
	var xmlHTTP = new ActiveXObject("Microsoft.XMLHTTP");

	//处理流程XML

	var oFlow = document.getElementById("workarea");	

	var flowNode = objDom.createElement("flow");
	objDom.documentElement.appendChild(flowNode);
     
	createAtrributes(objDom, flowNode, oFlow, "mid,cname,sno,ver,state,formid,createformid,startchoice,readerchoice,classid,field" );	
   
    flowName=trim(oFlow.cname);
    clasForSno=trim(oFlow.classid);
    snoForSno=trim(oFlow.sno);
    flowFormId= trim(oFlow.formid);
     
	//alert(oFlow.formid); 
	createChilds(objDom, flowNode, "flowowner","cname,ownerctx,ctype,ownerchoice",oFlow.flowowner);
	createChilds(objDom, flowNode, "flowreader","cname,readerctx,ctype",oFlow.flowreader);
	//处理节点XML

	var oNodes = document.getElementsByTagName("image");
	 
	for(var i=0; i<oNodes.length; i++)
	{
		var id = oNodes(i).id;
		var cname = oNodes(i).cname;
		
		if(id!=""&&id!=null&&typeof(cname)!="undefined")
		{
			var actNode = objDom.createElement("action"); 
			flowNode.appendChild(actNode); 
			
			//alert(oNodes(i).ctype);
			//if(((oNodes(i).ctype)=="BEGIN")||((oNodes(i).ctype)=="END"))
			//createAtrributes(objDom, actNode,oNodes(i), "id,cname,ctype,flowid,handletype,join,split1,isfirst,outstyle,selectstyle,selectother,imagename,x,y" );	
			//else
			
			if((oNodes(i).formid=="") ||(oNodes(i).formid==null))
			{
			 oNodes(i).formid=flowFormId;  
			}
			createAtrributes(objDom, actNode,oNodes(i), "id,cname,ctype,subflowid,subflowsno,subflowname,subflowcreate,subflowclose,subflowlink,flowid,formid,handletype,join,split1,isfirst,outstyle,selectstyle,selectother,imagename,x,y,rwtype" );	
			createChilds(objDom, actNode, "actowner","cname,ownerctx,ctype,ownerchoice,outstyle",oNodes(i).actionowner);
			createChilds(objDom, actNode, "acttask","id,sno,cname,descript,ctype,require,apptaskid",oNodes(i).acttask);
			createChilds(objDom, actNode, "actstratege","stid,stname,stctx,stcode,strouteid,stroutename",oNodes(i).actstratege); 
			createChilds(objDom, actNode, "actfield","fieldid,fieldcname,fieldename,fieldtype,fieldaccess",oNodes(i).actfield); 
		}	
	}
	//处理连接XML

	var oLines = document.getElementsByTagName("polyline");
	//alert(oLines.length);
	for(var i=0; i<oLines.length; i++)
	{
		var id = oLines(i).id;	
		if(id!=""&&id!=null) 
		{
			var routeNode = objDom.createElement("route"); 
			flowNode.appendChild(routeNode); 
			createAtrributes(objDom, routeNode,oLines(i), "id,cname,routetype,conditions,startactid,endactid,flowid,direct,mPoints" );	

			 
            createChilds(objDom, routeNode, "routetask","acttaskid,require,taskname",oLines(i).routetask);
		}
	}


	document.all.xmltest.innerText = objDom.xml;
	//alert(document.all.xmltest.innerText.length+"\n\r"+document.all.xmltest.innerText);
 document.all.flowname.innerText="流程名称："+flowName;
 
   
  if((flowName!="")&&(flowName!="null"))
  {
  if( (validateNull('fcname','流程名称') &&validateLong('fcname','流程名称','60')&&validateIllegal('fcname','流程名称') )) 
  {
   if(nou=='0') 
      {
      if(document.all.flowid.value=="")
      { 
         ssno=sno.split(":");cclas=clas.split(":");
          
         for(k=0;k<cclas.length-2;k++)
         {
           if(clasForSno==cclas[k])
             if(snoForSno==ssno[k]) 
             {
               alert("相同类别的流程出现相同的编号，请重新输入！");
               return;
             }
         }
         
      }
       
	  document.forms[0].action=rootpathform+"/module/app/system/workflow/bflow/bflow_insertbflow.action";
	  }
   if (nou=='1')
	  {
	  
	  document.forms[0].action=rootpathform+"/module/app/system/workflow/bflow/bflow_updatebflow.action";
	  }
   if (nou=='2')
	  {
	   
	   var fname = window.prompt("请输入新流程名称：",flowName+"-01"); 
	   
	  if  (fname==null)  { return}
	  if((fname=="")||(fname==null)) {alert("流程名称不能为空，保存失败！");return}
	  document.forms[0].fcname.value=fname;
	  if( (validateNull('fcname','流程名称') &&validateLong('fcname','流程名称','60')&&validateIllegal('fcname','流程名称') )) 
	      document.forms[0].action=rootpathform+"/module/app/system/workflow/bflow/bflow_saveasbflow.action?flowname="+fname;
	  else {document.forms[0].fcname.value=flowName;return; }   
	  }
   if (nou=='3')
	  {
	  
	  document.forms[0].action=rootpathform+"/module/app/system/workflow/bflow/bflow_usebflow.action";
	  }
   if (nou=='4')
	  {
	  
	  document.forms[0].action=rootpathform+"/module/app/system/workflow/bflow/bflow_invokebflow.action";
	  }
	  
	  
	        document.forms[0].submit();	
	   
    }     

   }
   else{alert("流程名称不能为空！");}	 
}

function createAtrributes(objDom, cnode, obj, atts)
{
  
	var att = atts.split(",");
	 
	for(i=0;i<att.length; i++)
	{    
		var objattID = objDom.createAttribute(att[i]);		
		eval("objattID.text= obj."+att[i]+";");
		cnode.setAttributeNode(objattID);
		 
	}

}
function createChilds(objDom, cnode, nodes, attnames,atts)
{
	if(typeof(atts)!="object" || atts==null){ return ;};
	
	 
	attname = attnames.split(",");
	for(i=0; i<atts.length; i++)
	{
		var objNode =  objDom.createElement(nodes); 
		cnode.appendChild(objNode); 
		for(k=0; k<atts[i].length; k++)
		{
			var objattID = objDom.createAttribute(attname[k]);
			eval("objattID.text='"+atts[i][k]+"';");		
			objNode.setAttributeNode(objattID);
		}
	}
}

//2004-7-16 wxg
function trim(strText){ 
 try
 { 
  while (strText.substring(0,1) == ' ') 
    strText = strText.substring(1, strText.length);
  while (strText.substring(strText.length-1,strText.length) == ' ')
    strText = strText.substring(0, strText.length-1);
  return strText;
  }catch(Exception){return "";}
}

