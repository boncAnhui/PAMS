<?php

if (!empty($_FILES)) {
	$tempFile = $_FILES['Filedata']['tmp_name'];
	$targetPath = $_SERVER['DOCUMENT_ROOT'] . $_REQUEST['folder'] . '/1111/';
	$targetFile =  str_replace('//','/',$targetPath) . $_FILES['Filedata']['name'];
			
		//move_uploaded_file($tempFile,$targetFile);
		
		//因为 demo 主机是 windows 所以需要转为 gb2312
		move_uploaded_file($tempFile,iconv("UTF-8","gb2312", $targetFile));
				
		echo "['".str_replace($_SERVER['DOCUMENT_ROOT'],'',$targetFile)."','".rand()."']";
}

//java 设置 保证中文文件名不 乱码
//upload.setHeaderEncoding("utf-8");  

//或者修改 fla 文件 和 uploadify.js 把 escape 改为 encodeURIComponent
//response 就是本页 echo 的东西
?>