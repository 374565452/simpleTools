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
	$("#btnBarcode").click(function(){
		//先校验下数据
		var text=$("#txtCode").val();
		var width=$("#txtWidth").val();
		var height=$("#txtHeight").val();
		var trans = $("#chkTrans").is(":checked");
		var type= $("#selType").val();
		//alert(type);
		//alert(text);
		if (text == "") {
			error("请填写内容");
		}else{
			var url="/verify/barcode";
			callHttp(url,{"content":text,"width":width,"height":height,"trans":trans,"type":type},function(data){
				var url="/function/barCode?content="+text+"&width="+width+"&height="+height+"&type="+type+"&trans="+trans;
				url = encodeURI(encodeURI(url));
				$("#imgPreview").attr("src", url);
			});
		}
	});
	
	$("#btnBase64Encode").click(function(){
		var txtSourceBase64=$("#txtSourceBase64").val();
		if(txtSourceBase64 == ""){
			error("请填写内容")
		}else{
			callHttp("/function/base64encode",{"txt":txtSourceBase64},function(data){
				$("#txtDestBase64").val(data.data);
			});
		}
	});
	
	
	$("#btnBase64Decode").click(function(){
		var txtSourceBase64=$("#txtDestBase64").val();
		if(txtSourceBase64 == ""){
			error("请填写内容")
		}else{
			callHttp("/function/base64decode",{"txt":txtSourceBase64},function(data){
				$("#txtSourceBase64").val(data.data);
			});
		}
	});
	
	  // 半角转全角
    $("#btnGetDbc").click(function () {
        var text = $("#txtSourceDbc").val();
        if (text == "") {
            error("请填写要转换的字符");
        }
        else {
            $("#txtDestDbc").val(ToDBC(text));
        }
    });

    // 全角转半角
    $("#btnGetDbcBack").click(function () {
        var text = $("#txtDestDbc").val();
        if (text == "") {
            error("请填写要转换的字符");
        }
        else {
            $("#txtSourceDbc").val(ToCDB(text));
        }
    });
    
    $("#btnUrlEncode").click(function(){
    	var text=$("#txtSourceUrl").val();
    	if (text == "") {
            error("请填写需要转换的URL地址");
        }else {
        	
        	$("#txtDestUrl").val(encodeURIComponent(text).toLowerCase());
        }
    });
    
    $("#btnUrlDecode").click(function(){
    	var txt=$("#txtDestUrl").val();
    	if (txt == "") {
            error("请填写需要解码的URL地址");
        }else{
        	$("#txtSourceUrl").val(decodeURIComponent(txt));
        }
    });
	
    $("#btnHtmlEncode").click(function(){
    	var txt=$("#txtSourceHtml").val();
    	if (txt == "") {
            error("请填写需要编码的html字符串");
        }else{
        	$("#txtDestHtml").val(htmlencode(txt));
        }
    });
    $("#btnHtmlDecode").click(function(){
    	var txt=$("#txtDestHtml").val();
    	if (txt == "") {
            error("请填写需要解码的html字符串");
        }else{
        	$("#txtSourceHtml").val(htmldecode(txt));
        }
    });
    
    // ASCII编码
    $("#btnAsciiEncode").click(function () {
        var text = $("#txtSourceAscii").val();
        //alert(text);
        if (text == "") {
            error("请填写要编码的内容");
        }
        else {
            var a = new Ascii();
            $("#txtDestAscii").val(a.encode(text));
        }
    });

    // ASCII解码
    $("#btnAsciiDecode").click(function () {
        var text = $("#txtDestAscii").val();
        if (text == "") {
            error("请填写要解码的内容");
        }
        else {
            var a = new Ascii();
            $("#txtSourceAscii").val(a.decode(text));
        }
    });

    // UTF8编码
    $("#btnUrlEncodeUtf8").click(function () {
        var text = $("#txtSourceUrlUtf8").val();
        if (text == "") {
            error("请填写要编码的内容");
        }
        else {
            var u = new UTF8();
            $("#txtDestUrlUtf8").val(u.encode(text));
        }
    });

    // UTF8解码
    $("#btnUrlDecodeUtf8").click(function () {
        var text = $("#txtDestUrlUtf8").val();
        if (text == "") {
            error("请填写要编码的内容");
        }
        else {
            var u = new UTF8();
            $("#txtSourceUrlUtf8").val(u.decode(text));
        }
    });

    // Unicode编码
    $("#btnUnicodeEncode").click(function () {
        var text = $("#txtSourceUnicode").val();
        if (text == "") {
            error("请填写要编码的内容");
        }
        else {
            var u = new Unicode()
            $("#txtDestUnicode").val(u.encode(text));
        }
    });

    // Unicode解码
    $("#btnUnicodeDecode").click(function () {
        var text = $("#txtDestUnicode").val();
        if (text == "") {
            error("请填写要解码的内容");
        }
        else {
            var u = new Unicode()
            $("#txtSourceUnicode").val(u.decode(text));
        }
    });
    
 // 大小写转换
    $("#btnGetUpper").click(function () {
        var text = $("#txtSourceUpper").val();
        if (text == "") {
            error("请填写要转换大写的内容");
        }
        else {
            $("#txtDestUpper").val(text.toUpperCase());
        }
    });

    $("#btnGetLower").click(function () {
        var text = $("#txtSourceUpper").val();
        if (text == "") {
            error("请填写要转换小写的内容");
        }
        else {
            $("#txtDestUpper").val(text.toLowerCase());
        }
    });

    // 字符长度
    $("#btnGetLen").click(function () {
        var text = $("#txtSourceLen").val();
        if (text == "") {
            error("请填写要计算长度的字符");
        }else {
        	//此正则是为了计算字符串含有的所有中文字符
            var re = /[\u4E00-\u9FA5]/g;
            var zh=text.match(re);
            var len=0;
            if(zh){
            	len=zh.length;
            }
             //= text.match(re).length;
            $(".length-tip").show().text("字符长度：" + text.length + "，汉字数量："+len);
        }
    });
    
    $("#btnJson").click(function(){
    	var text = $("#txtSourceJson").val();
        if (text == "") {
            error("请填写要计算长度的字符");
        }else {
        	callHttp("/function/jsonformat",{"json":text},function(data){
				$("#txtDestJson").val(data.data);
			});
        }
    });
    
    $("#btnJsonEntity").click(function(){
    	var text = $("#txtSourceJsonEntity").val();
    	var packageName=$("#txtPackage").val();
    	var className=$("#txtClass").val();
        if (text == "") {
            error("请填写要计算长度的字符");
        }else {
        	callHttp("/function/jsonparse",{"json":text,"packageName":packageName,"className":className},function(data){
				$("#txtDestJsonEntity").val(data.data);
			});
        }
    });
    
    // 二进制转换
    $(".btnCal").click(function () {
        var val = $(this).attr("data-val");
        switch (val) {
            case "10":
                var text = $("#txt10").val();
                if (text == "") {
                    error("请填写十进制值");
                }
                else {
                    $("#txt2").val(parseInt(text, 10).toString(2));
                    $("#txt8").val(parseInt(text, 10).toString(8));
                    $("#txt16").val(parseInt(text, 10).toString(16));
                }
                break;
            case "2":
                var text = $("#txt2").val();
                if (text == "") {
                    error("请填写二进制值");
                }
                else {
                    $("#txt10").val(parseInt(text, 2).toString(10));
                    $("#txt8").val(parseInt(text, 2).toString(8));
                    $("#txt16").val(parseInt(text, 2).toString(16));
                }
                break;
            case "8":
                var text = $("#txt8").val();
                if (text == "") {
                    error("请填写八进制值");
                }
                else {
                    $("#txt10").val(parseInt(text, 8).toString(10));
                    $("#txt2").val(parseInt(text, 8).toString(2));
                    $("#txt16").val(parseInt(text, 8).toString(16));
                }
                break;
            case "16":
                var text = $("#txt16").val();
                if (text == "") {
                    error("请填写十六进制值");
                }
                else {
                    $("#txt10").val(parseInt(text, 16).toString(10));
                    $("#txt2").val(parseInt(text, 16).toString(2));
                    $("#txt8").val(parseInt(text, 16).toString(8));
                }
                break;
        }
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
		//alert(text);
		var url="/function/ercodeModel?text="+text+"&level="+level+"&size="+scale+"&margin="+margin+"&trans="+trans;
		url = encodeURI(encodeURI(url));
        $("#imgPreview").attr("src", url);
	}
}