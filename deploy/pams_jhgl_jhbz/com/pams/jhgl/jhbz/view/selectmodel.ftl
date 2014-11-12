<script type="text/javascript">
$("#bt_save").click(function() {page_save()});
$("#bt_close").click(function() {page_close()});

// 保存
function page_save()
{
	var oids=[];
	var onames=[];
	$('.dataGrid tbody .checkbox').each(function(j,k)
	{		
		if($(this).val()==1)
		{
			oids.push($(this).attr('data-id')); //计划模板标识
			onames.push(dataJSON.tbody[j][1].name); // 计划模板名称
		}		
	})
	
	if(oids.length<=0)
	{
		alert("请选择计划模板！");
		return;
	}
	
	if(oids.length>1)
	{
		alert("仅能选择一个模板！");
		return;
	}
	
	var planmodelid = oids[0];	
	var planmodelname = onames[0];
		
	var opener=$('body',window.opener.document);
	opener.find('#planmodelname').val(planmodelname);
	opener.find('#planmodelid').val(planmodelid);
	window.close();
}

function page_close()
{
	window.close();
}

</script>