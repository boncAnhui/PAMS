oldObj=null;

function showTool()
{
	srcElem=event.srcElement;
	if(srcElem.tagName!="IMG")return;
	if(oldObj!=null)
	{
		oldObj.filters.gray.enabled=true;
	}
	srcElem.filters.gray.enabled=false;
	oldObj=srcElem;
}

function showSubMenu(obj)
{
	for(i=0;i<subMenu.children.length;i++)
	{
		if(subMenu.children[i].tagName=="IMG")continue;
		subMenu.children[i].style.display="none";
	}

	obj.style.display="";
	menuIndicate.style.left=event.srcElement.offsetLeft+event.srcElement.offsetWidth/2
}