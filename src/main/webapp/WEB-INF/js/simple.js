$(function () {
    $('#myTab a:first').tab('show');
    $('#myTab a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    })
});
//对html标签进行编码操作
function htmlencode(s) {
    var div = document.createElement('div');
    div.appendChild(document.createTextNode(s));
    return div.innerHTML;
}
//对编码后的html进行解码操作
function htmldecode(s) {
    var div = document.createElement('div');
    div.innerHTML = s;
    return div.innerText || div.textContent;
}

//全角空格为12288，半角空格为32 
//其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248 
//半角转换为全角函数 
function ToDBC(txtstring) {
 var tmp = "";
 for (var i = 0; i < txtstring.length; i++) {
     if (txtstring.charCodeAt(i) == 32) {
         tmp = tmp + String.fromCharCode(12288);
     }
     if (txtstring.charCodeAt(i) < 127) {
         tmp = tmp + String.fromCharCode(txtstring.charCodeAt(i) + 65248);
     }
 }
 return tmp;
}

//全角转换为半角函数 
function ToCDB(str) {
 var tmp = "";
 for (var i = 0; i < str.length; i++) {
     if (str.charCodeAt(i) > 65248 && str.charCodeAt(i) < 65375) {
         tmp += String.fromCharCode(str.charCodeAt(i) - 65248);
     }
     else {
         tmp += String.fromCharCode(str.charCodeAt(i));
     }
 }
 return tmp
}
//UTF8字符转换
function UTF8() {
    this.encode = function (str) {
        var temp = "", rs = "";
        for (var i = 0, len = str.length; i < len; i++) {
            temp = str.charCodeAt(i).toString(16);
            rs += "\\u" + new Array(5 - temp.length).join("0") + temp;
        }
        return rs;
    }
    this.decode = function (str) {
        return str.replace(/(\\u)(\w{4}|\w{2})/gi, function ($0, $1, $2) {
            return String.fromCharCode(parseInt($2, 16));
        });
    }
}
//unicode字符转换
function Unicode() {
    this.encode = function (str) {
        var res = [];
        for (var i = 0; i < str.length; i++)
            res[i] = ("00" + str.charCodeAt(i).toString(16)).slice(-4);
        return "\\u" + res.join("\\u");
    }
    this.decode = function (str) {
        str = str.replace(/\\/g, "%");
        return unescape(str);
    }
}
//Ascii字符转换
function Ascii() {
    this.encode = function (content) {
        var result = '';
        for (var i = 0; i < content.length; i++)
            result += '&#' + content.charCodeAt(i) + ';';
        return result;
    }
    this.decode = function (content) {
        var code = content.match(/&#(\d+);/g);
        result = '';
        for (var i = 0; i < code.length; i++)
            result += String.fromCharCode(code[i].replace(/[&#;]/g, ''));
        return result;
    }
}

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