$(function() {
	$("#btnGetGuid").click(function() {
		var url = "/function/guid";
		callHttp(url, null, function(data) {
			// alert(data.data);
			$("#txtSourceGuid").val(data.data);
		});
	});

	$("#btnMd5").click(function() {
		var src = $("#txtSourceMd5").val();
		if (src == "") {
			error("请填定要加密的内容信息。");
		} else {
			var half = false;
			if ($("#rad16").is(':checked')) {
				half = true;
			}
			callHttp("/function/md5", {
				"src" : src,
				"half" : half
			}, function(data) {
				// alert(data.data);
				$("#txtDestMd5").val(data.data);
			});
		}
	});

	$("#btnNetty").click(function() {
		callHttp("/tcp/netty", null, function(data) {
			$("#nettyResult").val(data.data);
			// alert(data.data);
		});
	});

	$("#btnWebSocket").click(function() {
		var src = $("#webSocketTest").val().trim();
		if (src == "") {
			error("发送内容不能为空，请填写必要的发送信息！");
		} else {
			websocketSend(src);
		}
	});
	
	$("#btnErcode").click(function() {
		genErcode();
	});
	$("#btnErcodeWithMode").click(function() {
		genErcode();
	});
	
});

function genErcode(){
	var text = $("#selText").val();
	var level = $("#selLevel").val();
	var scale = $("#txtScale").val();
	var margin = $("#selMargin").val();
	var trans = $("#chkTrans").is(":checked");
	if (text == "") {
		error("请填写内容");
	} else {
		alert(text);
		var url="/function/ercodeModel?text="+text+"&level="+level+"&size="+scale+"&margin="+margin+"&trans="+trans;
		url = encodeURI(encodeURI(url));
        $("#imgPreview").attr("src", url);
	}
}