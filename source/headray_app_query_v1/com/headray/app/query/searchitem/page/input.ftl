<script>
$("#bt_save").click(function() {page_save()});
$("#bt_close").click(function() {page_close()});

form.searchid.value = "${arg.searchid}";

function page_save()
{
	form.action = "invoke!insert.action";
	form._searchname.value = "searchitem.insert";
	if (Validator.Validate(form, 2))
	{
		form.submit();
	}	
}

function page_close()
{
	//window.location = "invoke!page.action";
	window.location = "invoke!page.action?_searchname=searchitem.browse&searchid=${arg.searchid}";
}
</script>