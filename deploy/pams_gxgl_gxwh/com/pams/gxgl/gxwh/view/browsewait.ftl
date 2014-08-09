<script type="text/javascript">
$("#bt_input").click(function() {page_input()});
$("#bt_del").click(function() {page_del()});

// 新增
function page_input()
{
	url = "${base}/module/pams/gxgl/gxwh/apply_input.action?_searchname=pams.gxgl.gxwh.input";
	openwinT(url,'input',pub_width_large,pub_height_large,null,'新增');
}

function page_del()
{
	var oids=[];
	$('.dataGrid tbody .checkbox').each(function(j,k)
	{		
		if($(this).val()==1)
		{
			oids.push($(this).attr('data-id'))	
		}		
	})
	
	if(oids.length<=0)
	{
		alert("请先选择要删除的数据！");
		return;
	}
	
	if(confirm("确实要删除选中的记录?"))
	{
		window.location = "${base}/module/pams/meter/apply/apply_delete.action?runactkeys=" + oids;
	}
}
</script>