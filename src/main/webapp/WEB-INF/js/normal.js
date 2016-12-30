$(function(){
	$("#btnGetGuid").click(function(){
		var url="/function/guid";
		callHttp(url,null,function(data){
			//alert(data.data);
			$("#txtSourceGuid").val(data.data);
		});
	});
	
	$("#btnMd5").click(function(){
		var src=$("#txtSourceMd5").val();
		if(src==""){
			error("请填定要加密的内容信息。");
		}else{
			var half=false;
			if($("#rad16").is(':checked')){
				half=true;
			}
			callHttp("/function/md5",{"src":src,"half":half},function(data){
				//alert(data.data);
				$("#txtDestMd5").val(data.data);
			});
		}
	});
});