/**
 * @author	巩睿
 * @date		2004-06-09
 * @version v0.1
 **/

/** 全局变量 **/
//路径类

var rootpathform = "/pams";
var rootpath = "\\pams\\wfb\\blueflowui\\";
var rootpathForReview = "\\pams\\wfb\\blueflowui\\";
var skinpath = "default\\";
var imagepath= rootpath + "images\\"  ; 
var soundpath = rootpath+ "sounds\\";

//图片资源类

var startNormalImage="start.png",startPlayImage="startForPlay.gif";
var endNormalImage="end.png",endPlayImage="endForPlay.gif";
var personalNormalImage="personal.png",personalPlayImage="personalForPlay.gif";
var serialNormalImage="serial.png",serialPlayImage="serialForPlay.gif";
var parallelNormalImage="parallel.png",parallelPlayImage="parallelForPlay.gif";
var currentActionImage="currentPersonal.gif",currentPlayImage="currentForPlay.gif";
var personalsubNormalImage="personalsub.png";
 
var imageWidth = "50"
var imageHeight = "50"

//其他类

var nodecount=0, linecount = 0;
var on = false;
var originX,originY,positionX,positionY;

var activenodel;
var activeline;

var toolbarname = new Array("select","start","end","personal","serial","parallel","line");

var canDraw = 0;
var drawMode = "";
var moveMode = "";

var linebreak = null;
var rightClickObj = null;
var flowName = "";
//最小象素点

var minPoint = 25;

var beginNum=0;
var endNum=0;
var tmp_formid="",tmp_formname="",tmp_classid="",tmp_rtasklist="";
 
/**事件封装 **/
//绘图板载入

function window.onload()
{
	
}

//防止未下载完毕时发生异步错误

function window.onerror()
{ 
	return true
}

//点击开始时

function document.onselectstart()
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
return;
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
{   return false;
	cmenu.style.left=event.x + document.body.scrollLeft;
	cmenu.style.top=event.y + document.body.scrollTop;

	return false;
}

//隐藏右键菜单

document.onclick=function()
{
	cmenu.style.left = "-1000";
	cmenu.style.top = "-1000";

	cmenuNode.style.left = "-1000";
	cmenuNode.style.top = "-1000";

	cmenuLine.style.left = "-1000";
	cmenuLine.style.top = "-1000";
}

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
	}

	//activeline.strokecolor ="rgb( 0, 0, 0)";


	on=false;					//关闭onMouseMove事件

//	moveMode="";		//关闭托拽模式

}

//记录鼠标点击菜单

workarea.onmousedown=function()
{
 tmpname="";htype="普通";
   
	originX=event.x;		//记录鼠标位置

	originY=event.y;
	if(drawMode=="toolbar-select")
	{
		on = false;
		moveMode= "";
		return;
	}
	
	if(drawMode=="toolbar-start" || drawMode=="toolbar-end" || drawMode=="toolbar-personal" || drawMode=="toolbar-serial"  || drawMode=="toolbar-parallel" )
	{
		var ctype = "";
		switch(drawMode)
		{
			case "toolbar-start":
				ctype = "BEGIN";tmpname="开始";  htype="普通";
				break;
			case "toolbar-end":
				ctype="END";tmpname="结束";  htype="普通";
				break;
			case "toolbar-personal":
				ctype="NORMAL"; htype="普通";
				break;
			case "toolbar-serial":
				ctype="NORMAL";   htype="多人串行";
				break;
			case "toolbar-parallel":
				ctype="NORMAL";    htype="多人并行";
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
  
		startnode = new Node("node" + nodecount,tmpname, ctype, "flowid", tmp_formid, tmp_formname, htype, "OR", "OR", "N", "EVERYTIME","多人","N", "actionowner", "acttask",nodeimage,x,y,tmp_classid,"stId","stName","actstratege");
		startnode.draw();
		startnode.setAtt();

		nodecount += 1;		
	}
}

workarea.ondblclick=function()
{
}

function nodeMouseDown()
{
	
	originX=event.x;		//记录鼠标位置

	originY=event.y;

	moveMode = "node";
	activenode = getParent(window.event.srcElement,"image");

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


		var linepoints =  (parseInt(activenode.x)+ parseInt(imageWidth/2))  +","
							       + (parseInt(activenode.y) +parseInt(imageHeight/2)) + "," 
								   + (parseInt(endnode.x) + parseInt(imageWidth/2)) + "," 
								   + (parseInt(endnode.y) + parseInt(imageHeight/2)) ;

		activeline = new Line( "line" + linecount , "R"+linecount, "NORMAL", "",activenode.id,endnode.id, "flowid", "正向",  "routetask",linepoints);
		activeline.draw();	
		activeline.setAtt();

		linecount += 1;		
		//moveMode = "";

		//activeline.setPoints()	

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


//流程跟踪
		function flowtrack_click(rootPath,dataid,tableid,actdefid)
		{
			if (actdefid=="null" || actdefid=="undefined" || actdefid=="")
			 	page=rootPath+"/workflow/FlowTraceForm?dataid="+dataid+"&tableid="+tableid;
			 else
				page=rootPath+"/workflow/FlowTraceForm?dataid="+dataid+"&tableid="+tableid
					+"&currentActdefid="+actdefid;
			openModal(page,'',550,350);	
		} 
		
	function openModal(url,dialogFeatures,oWidth,oHeight)
	{
	//+"&rnd="+Math.floor(Math.random()*100000)
	oModal=window.showModalDialog(url,dialogFeatures,"status:off;help:0;center:1;dialogWidth:"+oWidth+"px;dialogHeight:"+oHeight+"px;")
	return oModal;
	}
		

function nodeDblClick()
{
	event.cancelBubble=true;
	var el = getParent(window.event.srcElement,"image");
	
	var cccactid=el.id;

	el.classid=tmp_classid;
	el.formid=tmp_formid;
	el.formname=tmp_formname;
	
	if((el.ctype=="NORMAL")||(el.ctype=="SERIAL")||(el.ctype=="PARALLEL"))
	{
		rpath=el.rootpath;
		tableid=el.tableid;
		dataid=el.dataid;
		actdefid=el.actdefid;
		runflowkey=runflowkey;
     
	}
}

function lineMouseDown()
{
	 
	 //alert("linemousedown");

	originX=event.x;		//记录鼠标位置

	originY=event.y;

	moveMode = "line";

	activeline = window.event.srcElement;
	//alert(window.event.srcElement.mPoints);
	//alert(activeline.id);

	positionX=parseInt(event.x);		//记录原位置坐标(转成数字)

	positionY=parseInt(event.y);	
	//alert(activeline.innerHTML);

	//activeline.strokecolor ="rgb(255,0,0)";
	 

	linebreak = compPoint(activeline.mPoints,event.x + document.body.scrollLeft,event.y +document.body.scrollTop);
	//alert("linebreak=" + linebreak);


	on = true;           //打开onMouseMove事件
	//alert(moveMode +"::::::::::::::");

	event.cancelBubble=true;
}
 

function lineDblClick()
{ 
	return true;
	event.cancelBubble=true;
	var el = getParent(window.event.srcElement,"polyline");
	
	 //  alert(el.startactid);
	ds=document.getElementById(el.startactid);
	
	tmp_rtasklist=ds.rlist;
 
     // alert(tmp_rtasklist);

    el.rtlist=tmp_rtasklist;
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

function Flow(id, cname, formid, createformid, startchoice, readerchoice, classid, flowowner,flowreader,formname,createformname,classname)
{
	this.mid= id;
	this.cname = cname;
	this.formid = formid;
	this.formname = formname;
	this.createformid = createformid;
	this.createformname = createformname;
	this.startchoice = startchoice;
	this.readerchoice = readerchoice;
	this.classid = classid;
	this.classname = classname;
	

	this.flowowner = flowowner;
	this.flowreader = flowreader;
	
	this.setAtt = setAtt;
	this.draw = draw;


	function setAtt()
	{
		var rFlow =  document.getElementById("workarea");
		rFlow.mid = this.mid;
		rFlow.cname = this.cname;
		rFlow.formid = this.formid;
		rFlow.formname = this.formname;
		rFlow.createformid = this.createformid;
		rFlow.createformname = this.createformname;
		rFlow.startchoice = this.startchoice;
		rFlow.readerchoice = this.readerchoice;
		rFlow.classid = this.classid;
		rFlow.classname = this.classname;

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

function Node(id, cname, ctype, flowid, formid, formname, handletype, join, split1, isfirst, outstyle,selectstyle,selectother, actionowner, acttask, imagename, x,y,classid,stId,stName,actstratege,rlist,rootpath,tableid,dataid,actdefid)
{
	this.id = id;
	this.cname = cname;
	this.ctype = ctype;
	
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
	this.rootpath = rootpath;
	this.tableid = tableid;
	this.dataid = dataid;
	this.actdefid = actdefid;
	this.stId = stId;
	this.stName = stName;
	

	this.actionowner = actionowner;
	this.acttask = acttask;
	this.actstratege = actstratege;

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
			
		}else if((ctype=="END")&&(handletype=="普通"))
		{
			imagename = endNormalImage;
		}else if((ctype=="NORMAL")&&((handletype=="普通")||(handletype=="指定专人")))
		{   
			if(currentActId.indexOf(this.id)>=0) imagename=currentActionImage; 
			else 
			imagename = personalNormalImage;
		}else if((ctype=="NORMAL")&&((handletype=="多人串行")||(handletype=="多部门串行")))
		{
			if(currentActId.indexOf(this.id)>=0) imagename=currentActionImage; 
			else 
			imagename = serialNormalImage;
		}else if((ctype=="NORMAL")&&(handletype=="多人并行"))
		{
			if(currentActId.indexOf(this.id)>=0) imagename=currentActionImage; 
			else 
			imagename = parallelNormalImage;
		}else if((ctype=="SUBFLOW"))
		{
			if(currentActId.indexOf(this.id)>=0) imagename=currentActionImage; 
			else
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
		//rNode.attachEvent("oncontextmenu",NodeOnContextMenu);

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
		rNode.rootpath = this.rootpath;
		rNode.tableid = this.tableid;
		rNode.dataid = this.dataid;
		rNode.actdefid = this.actdefid;
		rNode.stId = this.stId;
		rNode.stName = this.stName;
		rNode.style.zIndex = "99";

		rNode.actionowner = this.actionowner;
		rNode.acttask = this.acttask;
		rNode.actstratege = this.actstratege;
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
	this.drawForView = drawForView;	
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
		rLine.strokeweight = "1pt";
		
		rLine.style.position = this.position;
		rLine.style.left = 0;
		rLine.style.top = 0;
		rLine.style.zIndex = "999";
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
		//rLine.attachEvent("oncontextmenu",LineOnContextMenu);


	}	

	function drawForView()
	{
		var rLine=document.createElement("v:polyline");
		rLine.id = this.id;
		rLine.strokecolor = "green";
		rLine.strokeweight = "1.8pt";
		
		rLine.style.position = this.position;
		rLine.style.left = 0;
		rLine.style.top = 0;
		rLine.style.zIndex = "999";
		rLine.filled="false";

		rLine.points = this.mPoints;
		rLine.mPoints = this.mPoints;
	//add arrow 2004-10-21
		rLineStroke=document.createElement("v:stroke");
		rLineStroke.color = "green";
		rLineStroke.EndArrow="block ";
		//rLineStroke.endarrowwidth="wide";
		rLineStroke.endarrowlength="long";
		rLineStroke.opacity="100%"; 
		rLine.appendChild(rLineStroke);
	//add arrow 2004-10-21	

		workarea.appendChild(rLine);	

		rLine.attachEvent("onmousedown",lineMouseDown);			
		rLine.attachEvent("ondblclick",lineDblClick);
		//rLine.attachEvent("oncontextmenu",LineOnContextMenu);


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

function contextOpenNode()
{
		
}

function contextDeleteNode()
{
	var rNode = getParent(rightClickObj,"image");
	if(rNode.ctype=="BEGIN") beginNum=0;;
	if(rNode.ctype=="END") endNum=0;;
	rNode.removeNode(true);
}

function contextOpenLine()
{
		
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
    
	createAtrributes(objDom, flowNode, oFlow, "mid,cname,formid,createformid,startchoice,readerchoice,classid" );	
    flowName=trim(oFlow.cname);
	//alert(oFlow.flowowner); 
	createChilds(objDom, flowNode, "flowowner","cname,ownerctx,ctype,ownerchoice",oFlow.flowowner);
	createChilds(objDom, flowNode, "flowreader","cname,readerctx,ctype",oFlow.flowreader);
	//处理节点XML

	var oNodes = document.getElementsByTagName("image");
	 
	for(var i=0; i<oNodes.length; i++)
	{
		var id = oNodes(i).id;
		if(id!="")
		{
			var actNode = objDom.createElement("action"); 
			flowNode.appendChild(actNode); 
			
			//alert(oNodes(i).ctype);
			//if(((oNodes(i).ctype)=="BEGIN")||((oNodes(i).ctype)=="END"))
			//createAtrributes(objDom, actNode,oNodes(i), "id,cname,ctype,flowid,handletype,join,split1,isfirst,outstyle,selectstyle,selectother,imagename,x,y" );	
			//else
			createAtrributes(objDom, actNode,oNodes(i), "id,cname,ctype,flowid,formid,handletype,join,split1,isfirst,outstyle,selectstyle,selectother,imagename,x,y" );	
			createChilds(objDom, actNode, "actowner","cname,ownerctx,ctype,ownerchoice,outstyle",oNodes(i).actionowner);
			createChilds(objDom, actNode, "acttask","id,sno,cname,descript,ctype,require,apptaskid",oNodes(i).acttask);
		}	
	}
	//处理连接XML

	var oLines = document.getElementsByTagName("polyline");
	//alert(oLines.length);
	for(var i=0; i<oLines.length; i++)
	{
		var id = oLines(i).id;	
		if(id!="") 
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
   if(nou=='0') 
      {
	  document.forms[0].action=rootpathform+"/WFBFlowForm?ACTION=InsertBFlow";
	  }
   if (nou=='1')
	  {
	  
	  document.forms[0].action=rootpathform+"/WFBFlowForm?ACTION=UpdateBFlow";
	  }
   if (nou=='2')
	  {
	   
	   var fname = window.prompt("请输入新流程名称：",flowName+"-01"); 
	   
	  if  (fname==null)  { return}
	  if((fname=="")||(fname==null)) {alert("流程名称不能为空，保存失败！");return}
	  document.forms[0].action=rootpathform+"/WFBFlowForm?ACTION=SaveAsBFlow&flowname="+fname;
	  }
	     document.forms[0].submit();	
	   
       //alert("成功保存流程！"); 
       ///FrameWorkWeb/WFBDefineForm?ACTION=View&flowid="	   

   }
   else{alert("流程名称不能为空！");}	 
}

function createAtrributes(objDom, cnode, obj, atts)
{
	var att = atts.split(",")
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
//
function NodeP(id,cname,  ctype,imagename, x,y,rootpath,tableid,dataid,actdefid)
{

	this.id = id;
	this.ctype = ctype;
	this.x = x;
	this.y = y;
	this.rootpath = rootpath;
	this.tableid = tableid;
	this.dataid = dataid;
	this.actdefid = actdefid;
	
	this.cname = cname;
/*	this.flowid = flowid;
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
	

	this.actionowner = actionowner;
	this.acttask = acttask;
*/

	this.setAtt = setAtt;
	this.draw = draw;	
	//this.remove = remove;

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
		if(ctype=="BEGIN")
		{
			imagename = startNormalImage;
			
		}else if(ctype=="END")
		{
			imagename = endNormalImage;
		}else if(ctype=="NORMAL")
		{   
			if(currentActId.indexOf(this.id)>=0) imagename=currentActionImage; 
			else 
			imagename = personalNormalImage;
		}else if(ctype=="SERIAL")
		{
			if(currentActId.indexOf(this.id)>=0) imagename=currentActionImage; 
			else 
			imagename = serialNormalImage;
		}else if(ctype=="PARALLEL")
		{
			if(currentActId.indexOf(this.id)>=0) imagename=currentActionImage; 
			else 
			imagename = parallelNormalImage;
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
		//rNode.attachEvent("oncontextmenu",NodeOnContextMenu);

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
		rNode.ctype = this.ctype;
		
		
		rNode.cname = this.cname;
		/*rNode.formid = this.formid;
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
		*/
		rNode.rootpath = this.rootpath;
		rNode.tableid = this.tableid;
		rNode.dataid = this.dataid;
		rNode.actdefid = this.actdefid;
		rNode.style.zIndex = "99";

		//rNode.actionowner = this.actionowner;
		//rNode.acttask = this.acttask;
		rNode.imagename = this.imagename;	
		
		//if(rNode.childNodes.length>=1)
		//{
		//	rNode.childNodes(0).innerText = this.cname;
		//}

	}

	function remove()
	{
		var rNode = document.getElementById(this.id);
		 
		rNode.removeNode(true);
	}

}
//play
var pi=0,conplay=0,ml=1,timeid=null,currentStep=0,currentImage=startNormalImage,  imagename=null;;
var idp="",ctypep="",cnamep="",xp="",yp="",rootpathp="",tableidp="",dataidp="",actdefidp="",idpp="";
 

 
function play() 
{ 

	 oNodes = document.getElementsByTagName("image");
 
 	if(pi==0) 
	{ 
	 for(var j=0; j<oNodes.length; j++)
	 {
	   if(oNodes(j).ctype=="BEGIN") break;
	    
	 }
	 idp = oNodes(j).id; 
	 ctypep=oNodes(j).ctype;
	 cnamep = oNodes(j).cname;
	 xp=oNodes(j).x;
	 yp = oNodes(j).y;
	 rootpathp=oNodes(j).rootpath;
	 tableidp = oNodes(j).tableid;
	 dataidp=oNodes(j).dataid;
	 actdefidp = oNodes(j).actdefid ;
	 }
  	
	 imagename=currentImage; 
 	   
	 rNode = document.getElementById(idp);//delete play image
	 rNode.removeNode(true); 
     v_Node = new NodeP( idp ,cnamep ,ctypep ,imagename, xp , yp ,rootpathp,tableidp,dataidp,actdefidp);
     //retrieve old image anf store old value into idp...
     v_Node.draw();
     v_Node.setAtt();   
	 
	for(var i=0; i<oNodes.length; i++)
	{
	 idp = oNodes(i).id;
	 ctypep=oNodes(i).ctype;
	 cnamep = oNodes(i).cname;
	 xp=oNodes(i).x;
	 yp = oNodes(i).y;
	 rootpathp=oNodes(i).rootpath;
	 tableidp = oNodes(i).tableid;
	 dataidp=oNodes(i).dataid;
	 actdefidp = oNodes(i).actdefid ;
	    
		if(idp==allactList[pi]) 
		{
		currentImage=oNodes(i).imagename;
		if(currentImage==currentActionImage)
		 {
		 imagename = currentPlayImage;
		 }
		 else
		 {
		if(ctypep=="BEGIN")         imagename = startPlayImage;
		else if(ctypep=="END")      imagename = endPlayImage;
		else if(ctypep=="NORMAL")   imagename = personalPlayImage;
		else if(ctypep=="SERIAL")   imagename = serialPlayImage;
		else if(ctypep=="PARALLEL") imagename = parallelPlayImage;
		}
        oNodes(i).removeNode(true);//delete old image
        v_Node = new NodeP( idp ,cnamep ,ctypep ,imagename, xp , yp ,rootpathp,tableidp,dataidp,actdefidp);
        //change into play image using the old value
        v_Node.draw();
        v_Node.setAtt();  
        
 		currentStep=i;
 		
		break;
		}
		
     }
  
  timeid=setTimeout("play()",speed);
  
  pi++;
  
  if(pi>stepLength) 
  {
   
    
    pi=0;
    currentStep=0;
    currentImage=startNormalImage, imagename=null;;
    idp="",ctypep="",cnamep="",xp="",yp="",rootpathp="",tableidp="",dataidp="",actdefidp="";
     
   if(!(document.all.cplay.checked))
    {
   document.all.play.style.display='';
   document.all.suspend.style.display='none';
   document.all.stop.style.display='none';
   document.all.resume.style.display='none';
   clearTimeout(timeid);
    }
   }
}
 
 
//mplay-----------------------------------
var cidp="", cImagename="" ;
var ccnamep="",cctypep="",cimagename="",cxp="" ;
var cyp="",crootpathp="",ctableidp="",cdataidp="",cactdefidp="" ;
  		
var  idp1="", cnamep1="", ctypep1="", imagename1="", xp1="" , yp1="", rootpathp1="", tableidp1="", dataidp1="", actdefidp1="" ;
 
var kk=0,nodeLen=0; 
function mplay() 
{ 
	 oNodes = document.getElementsByTagName("image");
	 nodeLen=oNodes.length;
idp1=""; cnamep1=""; ctypep1=""; imagename1=""; xp1="" ; yp1=""; rootpathp1=""; tableidp1=""; dataidp1=""; actdefidp1="" ;	 
	 for(var j=0; j<oNodes.length; j++)//get all nodes value and store them into idp1...
	 {
	 idp1 += oNodes(j).id+";"; 
	 ctypep1+=oNodes(j).ctype+";";
	 cnamep1 += oNodes(j).cname+";";
	 xp1+=oNodes(j).x+";";
	 yp1 += oNodes(j).y+";";
	 rootpathp1+=oNodes(j).rootpath+";";
	 tableidp1 += oNodes(j).tableid+";";
	 dataidp1+=oNodes(j).dataid+";";
	 actdefidp1 += oNodes(j).actdefid +";";
	 }
	 idp1=idp1.split(";");ctypep1=ctypep1.split(";");cnamep1=cnamep1.split(";");xp1=xp1.split(";");yp1=yp1.split(";");
	 rootpathp1=rootpathp1.split(";");tableidp1=tableidp1.split(";");dataidp1=dataidp1.split(";");actdefidp1=actdefidp1.split(";");
 
 	if(pi==0) 
	{ 
	 for(var j=0; j<nodeLen; j++)
	 {
	   if(ctypep1[j]=="BEGIN") break;
	    
	 }
 	         idp = idp1[j] ; 
	         ctypep=ctypep1[j] ;
	         cnamep = cnamep1[j] ;
	         xp=xp1[j] ;
	         yp = yp1[j] ;
	         imagename= imagename1[j] ;
	         rootpathp=rootpathp1[j] ;
	         tableidp = rootpathp1[j] ;
	         dataidp=dataidp1[j] ;
	         actdefidp = actdefidp1[j] ;
	 }
  	
	  
 	   
 	 if((allactList[pi].split(":")).length<=1)//only one node following
 	 { 
 	       imagename=currentImage ;
	       rNode = document.getElementById(idp);
	       rNode.removeNode(true);//delete play image
	       //retrieve old image anf store old value into idp...
           v_Node = new NodeP( idp ,cnamep ,ctypep ,imagename, xp , yp ,rootpathp,tableidp,dataidp,actdefidp);
           v_Node.draw();
           v_Node.setAtt();  
 	 }
 	 else      //more than one  nodes following
 	 {
 	 
 //---------retrievee the last node.
  	       imagename=currentImage ;
	       rNode = document.getElementById(idp);
	       rNode.removeNode(true);
           v_Node = new NodeP( idp ,cnamep ,ctypep ,imagename, xp , yp ,rootpathp,tableidp,dataidp,actdefidp);
           v_Node.draw();
           v_Node.setAtt();  
 
 //---------
 
 	     
 	   mll=allactList[pi].split(":");
 	   ml=mll.length;
 	   for(j=0;j<nodeLen; j++)// 11
 	   {
 	   
 	         idp = idp1[j] ; 
	         ctypep=ctypep1[j] ;
	         cnamep = cnamep1[j] ;
	         xp=xp1[j] ;
	         yp = yp1[j] ;
	         imagename= imagename1[j] ;
	         rootpathp=rootpathp1[j] ;
	         tableidp = rootpathp1[j] ;
	         dataidp=dataidp1[j] ;
	         actdefidp = actdefidp1[j] ;
 	   
 	 
 	 
 	       for(k=0;k<ml;k++)
 	       {   
	         if(idp==mll[k])
	         {
	         //   alert(idp+"="+ml);
	         cidp+=idp+";";      
	         ccnamep+=cnamep+";";cctypep+=ctypep+";"; cxp+=xp +";" ;cImagename+=imagename+";";
	         cyp+=yp +";";crootpathp+=rootpathp+";";ctableidp+=tableidp+";";cdataidp+=dataidp+";";
	         cactdefidp+=actdefidp+";";
	          
	         rNode = document.getElementById(idp);
	         rNode.removeNode(true);
             v_Node = new NodeP( idp  ,cnamep  ,ctypep  ,imagename , xp  , yp  ,rootpathp ,tableidp ,dataidp ,actdefidp );
             v_Node.draw();
             v_Node.setAtt(); 
             
              
             } 
 	     
 	       }
 	   }//end 11
 	    
  	 }//end else
	 
	 
	for(var i=0; i<nodeLen; i++)// general 1 only one node 
	{
 	         idp = idp1[i] ; 
	         ctypep=ctypep1[i] ;
	         cnamep = cnamep1[i] ;
	         xp=xp1[i] ;
	         yp = yp1[i] ;
	         imagename= imagename1[i] ;
	         rootpathp=rootpathp1[i] ;
	         tableidp = rootpathp1[i] ;
	         dataidp=dataidp1[i] ;
	         actdefidp = actdefidp1[i] ;
	    
		if((allactList[pi].split(":")).length<=1)
		{
		    if(idp==allactList[pi]) 
		      {
		          currentImage =oNodes(i).imagename;
		          if(currentImage ==currentActionImage) imagename = currentPlayImage;
		          else
		          {
		            if(ctypep=="BEGIN")         imagename = startPlayImage;
		            else if(ctypep=="END")      imagename = endPlayImage;
		            else if(ctypep=="NORMAL")   imagename = personalPlayImage;
		            else if(ctypep=="SERIAL")   imagename = serialPlayImage;
		            else if(ctypep=="PARALLEL") imagename = parallelPlayImage;
		          }
                oNodes(i).removeNode(true);
                v_Node = new NodeP( idp ,cnamep ,ctypep ,imagename, xp , yp ,rootpathp,tableidp,dataidp,actdefidp);
                v_Node.draw();
                v_Node.setAtt();  
        
 		        currentStep=i;
		     break;
		     }
		}
	 }//end general 1
		//---------------more than one nodes following.
 		if((allactList[pi].split(":")).length>1)//1
 		{ 
 	       mll=allactList[pi].split(":");
 	       ml=mll.length;
 	       cidp=cidp.split(";");ccnamep=ccnamep.split(";");cctypep=cctypep.split(";"); cImagename=cImagename.split(";");cxp=cxp.split(";");
 	       cyp=cyp.split(";");crootpathp=crootpathp.split(";");ctableidp=ctableidp.split(";");cdataidp=cdataidp.split(";");
 	       cactdefidp=cactdefidp.split(";");
        	for(var i=0; i<nodeLen; i++)// general 2
	        {
 	         idp = idp1[i] ; 
	         ctypep=ctypep1[i] ;
	         cnamep = cnamep1[i] ;
	         xp=xp1[i] ;
	         yp = yp1[i] ;
	         imagename= imagename1[i] ;
	         rootpathp=rootpathp1[i] ;
	         tableidp = rootpathp1[i] ;
	         dataidp=dataidp1[i] ;
	         actdefidp = actdefidp1[i] ;
	        
 	        for(k=0;k<ml;k++)
 	        {   
	         if(idp ==mll[k])
	         {     
	              currentImage=cImagename[k];
		           if(currentImage==currentActionImage) imagename = currentPlayImage;
		          else
		          {
		            if(cctypep[k]=="BEGIN")         imagename = startPlayImage;
		            else if(cctypep[k]=="END")      imagename = endPlayImage;
		            else if(cctypep[k]=="NORMAL")   imagename = personalPlayImage;
		            else if(cctypep[k]=="SERIAL")   imagename = serialPlayImage;
		            else if(cctypep[k]=="PARALLEL") imagename = parallelPlayImage;
		          }
		           
	            oNodes(i).removeNode(true);
                v_Node = new NodeP( cidp[k] ,ccnamep[k] ,cctypep[k] ,imagename, cxp[k] , cyp[k] ,crootpathp[k],ctableidp[k],cdataidp[k],cactdefidp[k]);
                v_Node.draw();
                v_Node.setAtt();  
                 
              //break;
             } 
 	        }
 	        }//end general 2
 	        
 	        
  		  currentStep=i;
 		}//end 1
 		//-----------------------------
 		 
		
     
  
  timeid=setTimeout("mplay()",speed);
  
  pi++;
   
  
  if(pi>stepLength) 
  {
   
    
    pi=0;
    currentStep=0;
    kk=0;
    currentImage=startNormalImage, imagename=null;;
    idp="";ctypep="";cnamep="";xp="";yp="";rootpathp="";tableidp="";dataidp="";actdefidp="";
     cidp=""; cImagename="" ;
     ccnamep="";cctypep="";cimagename="";cxp="" ;
     cyp="";crootpathp="";ctableidp="";cdataidp="";cactdefidp="" ;
     
   if(!(document.all.cplay.checked))
    {
   document.all.play.style.display='';
   document.all.suspend.style.display='none';
   document.all.stop.style.display='none';
   document.all.resume.style.display='none';
   clearTimeout(timeid);
    }
   }
}

function stop()
{
	  imagename=currentImage; 
  	  rNode = document.getElementById(idp);
	  rNode.removeNode(true);
      v_Node = new NodeP( idp ,cnamep ,ctypep ,imagename, xp , yp ,rootpathp,tableidp,dataidp,actdefidp);
      v_Node.draw();
      v_Node.setAtt();  
      pi=0;
      currentStep=0;
      currentImage=startNormalImage, imagename=null;;
    idp="";ctypep="";cnamep="";xp="";yp="";rootpathp="";tableidp="";dataidp="";actdefidp="";
     cidp=""; cImagename="" ;
     ccnamep="";cctypep="";cimagename="";cxp="" ;
     cyp="";crootpathp="";ctableidp="";cdataidp="";cactdefidp="" ;
   
   
   clearTimeout(timeid);
}
function suspend()
{
    clearTimeout(timeid);
}

function test()
{
	oNodes = document.getElementsByTagName("image");
	for(var j=0; j<oNodes.length; j++)
	{
		idp = oNodes(j).id; alert(idp);
	} 
}