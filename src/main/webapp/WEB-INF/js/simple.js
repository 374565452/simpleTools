$(function () {
    $('#myTab a:first').tab('show');
    $('#myTab a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    })
});

function callHttp(url,param,callback){
	$.ajax({
		type:'post',
		url:url,
		data:param,
		success:function(data){
			if(data && data.flag){
				if(callback != null){
					callback(data);
				}
			}else{
				error(data.error);
			}
		}
	});
}

function error(msg){
	if($("#errorDiv")){
		$("#errorDiv").show().text("错误："+msg);
		setTimeout(function(){
			$("#errorDiv").hide();
		},8000)
	}else{
		alert(msg);
	}
}