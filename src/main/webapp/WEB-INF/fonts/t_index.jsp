<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="Json格式化,编程工具,在线工具,开发工具,IT工具">
<meta name="keywords" content="Json,开发者,编程工具,各种工具类软件" />
<meta name="author" content="374565452@qq.com">

<link href="../css/bootstrap.min.css" rel="stylesheet" />
<link href="../css/bootstrap-theme.min.css" rel="stylesheet"/>
<link href="../css/dashboard.css" rel="stylesheet" />
<link href="../css/style.css" rel="stylesheet" />
<link href="../css/ie10-viewport-bug-workaround.css" rel="stylesheet" />

<script type="text/javascript" src="../js/ie-emulation-modes-warning.js"></script>
<script type="text/javascript" src="../js/jquery.min-1.12.4.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/simple.js"></script>
<link>

<title>面向开发者-常用小工具-DevSimpleTools</title>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Dev SimpleTools</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">赞助名单</a></li>
					<li><a href="#">意见反聩</a></li>
					<li><a href="#">开发日志</a></li>
					<li><a href="#">关于本站</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar nav-pill">
					<li><a href="/">常用工具</a></li>
					<li data-flag="entity"><a href="/tools/entity">实体生成</a></li>
					<li data-flag="encode"><a href="/tools/encode">编码解码</a></li>
					<li data-flag="encrypt"><a href="/tools/encrypt">加密解密</a></li>
					<li data-flag="transfer"><a href="/tools/transfer">字符转换</a></li>
					<li data-flag="compress"><a href="/tools/compress">文件压缩</a></li>
					<li data-flag="unit"><a href="/tools/unit">单位换算</a></li>
					<li data-flag="ercode"><a href="/tools/ercode">二维码生成</a></li>
					<li data-flag="barcode"><a href="/tools/barcode">条形码生成</a></li>
					<li data-flag="docs"><a href="/tools/docs">常用参考表</a></li>
				</ul>
			</div>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">Dashboard</h1>
				<!-- page-header属性会在文字下方显示一条横线 -->

				<ul class="nav nav-tabs" id="myTab">
					<li><a href="#json">json格式化</a></li>
					<li><a href="#urlencode">UrlEncode</a></li>
					<li><a href="#htmlencode">HtmlEncode</a></li>
					<li><a href="#md5">MD5</a></li>
					<li><a href="#base64">Base64</a></li>
					<li><a href="#guid">GUID</a></li>
					<li><a href="#dbc">全角半角</a></li>
					<li><a href="#upper">大小写转换</a></li>
					<li><a href="#strlen">字符长度</a></li>
				</ul>
				<div class="error-container">
					<div id="errorDiv" class="alert alert-danger alert-dismissible"
						role="alert" style="display: none;">错误：解码错误</div>
				</div>
				<div class="tab-content">
					<div class="tab-pane active" id="json">
						<table class="compare-table">
							<tr>
								<td class="compare-first"><textarea id="txtSourceJson"
										class="form-control " rows="15"> {&quot;code&quot;: 1, &quot;value&quot;: 9}</textarea>
								</td>
								<td class="compare-second">
									<p>
										<button id="btnJson" class="btn btn-primary">格式化 &gt;</button>
									</p>
									<p>
										<button id="btnCopyJson" class="btn btn-default">复制结果</button>
									</p>
								</td>
								<td class="compare-third"><textarea id="txtDestJson"
										class="form-control" rows="15"></textarea></td>
							</tr>
						</table>
					</div>
					<div class="tab-pane" id="urlencode">
						<table class="compare-table">
							<tr>
								<td class="compare-first"><textarea id="txtSourceUrl"
										class="form-control " rows="15">http://www.baidu.com?t=hello</textarea>
								</td>
								<td class="compare-second">
									<p>
										<button id="btnUrlEncode" class="btn btn-primary">编码
											&gt;</button>
									</p>
									<p>
										<button id="btnUrlDecode" class="btn btn-primary">&lt;
											解码</button>
									</p>
								</td>
								<td class="compare-third"><textarea id="txtDestUrl"
										class="form-control" rows="15"></textarea></td>
							</tr>
						</table>
					</div>
					<div class="tab-pane" id="htmlencode">
						<table class="compare-table">
							<tr>
								<td class="compare-first"><textarea id="txtSourceHtml"
										class="form-control " rows="15">&lt;p&gt;hello&lt;/p&gt;</textarea>
								</td>
								<td class="compare-second">
									<p>
										<button id="btnHtmlEncode" class="btn btn-primary">编码
											&gt;</button>
									</p>
									<p>
										<button id="btnHtmlDecode" class="btn btn-primary">&lt;
											解码</button>
									</p>
								</td>
								<td class="compare-third"><textarea id="txtDestHtml"
										class="form-control" rows="15"></textarea></td>
							</tr>
						</table>
					</div>
					<div class="tab-pane" id="md5">
						<table class="compare-table">
							<tr>
								<td class="compare-first"><textarea id="txtSourceMd5"
										class="form-control " rows="15">666666</textarea></td>
								<td class="compare-second">
									<p>
										<input id="rad16" type="radio" name="radHalf" /><label
											class="lblNormal" for="rad16">16位</label>
									</p>
									<p>
										<input id="rad32" type="radio" name="radHalf"
											checked="checked" /><label class="lblNormal" for="rad32">32位</label>
									</p>
									<p>
										<button id="btnMd5" class="btn btn-primary">MD5 &gt;</button>
									</p>
								</td>
								<td class="compare-third"><textarea id="txtDestMd5"
										class="form-control" rows="15"></textarea></td>
							</tr>
						</table>
					</div>
					<div class="tab-pane" id="base64">
						<table class="compare-table">
							<tr>
								<td class="compare-first"><textarea id="txtSourceBase64"
										class="form-control " rows="15">123456</textarea></td>
								<td class="compare-second">
									<p>
										<button id="btnBase64Encode" class="btn btn-primary">编码
											&gt;</button>
									</p>
									<p>
										<button id="btnBase64Decode" class="btn btn-primary">&lt;
											解码</button>
									</p>
								</td>
								<td class="compare-third"><textarea id="txtDestBase64"
										class="form-control" rows="15"></textarea></td>
							</tr>
						</table>
					</div>
					<div class="tab-pane" id="guid">
						<table class="compare-table">
							<tr>
								<td class="compare-first"><textarea id="txtSourceGuid"
										class="form-control " rows="15"></textarea></td>
								<td class="compare-second">
									<p>
										<button id="btnGetGuid" class="btn btn-primary">生成GUID</button>
									</p>
								</td>
								<td class="compare-third"><textarea class="form-control"
										rows="15" style="visibility: hidden;"></textarea></td>
							</tr>
						</table>
					</div>
					<div class="tab-pane" id="dbc">
						<table class="compare-table">
							<tr>
								<td class="compare-first"><textarea id="txtSourceDbc"
										class="form-control " rows="15">abc</textarea></td>
								<td class="compare-second">
									<p>
										<button id="btnGetDbc" class="btn btn-primary">半转全
											&gt;</button>
									</p>
									<p>
										<button id="btnGetDbcBack" class="btn btn-primary">&lt;
											全转半</button>
									</p>
								</td>
								<td class="compare-third"><textarea id="txtDestDbc"
										class="form-control" rows="15"></textarea></td>
							</tr>
						</table>
					</div>
					<div class="tab-pane" id="upper">
						<table class="compare-table">
							<tr>
								<td class="compare-first"><textarea id="txtSourceUpper"
										class="form-control " rows="15">hello WORLD</textarea></td>
								<td class="compare-second">
									<p>
										<button id="btnGetUpper" class="btn btn-primary">转大写
											&gt;</button>
									</p>
									<p>
										<button id="btnGetLower" class="btn btn-primary">转小写
											&gt;</button>
									</p>
								</td>
								<td class="compare-third"><textarea id="txtDestUpper"
										class="form-control" rows="15"></textarea></td>
							</tr>
						</table>
					</div>
					<div class="tab-pane" id="strlen">
						<table class="compare-table">
							<tr>
								<td class="compare-first"><textarea id="txtSourceLen"
										class="form-control " rows="15">wanxiaoer</textarea></td>
								<td class="compare-second">
									<p>
										<button id="btnGetLen" class="btn btn-primary">计算
											&gt;</button>
									</p>
								</td>
								<td class="compare-third"><textarea id="txtDestLen"
										class="form-control" rows="15"></textarea></td>
							</tr>
						</table>
					</div>

				</div>
			</div>
		</div>
	</div>
	<script>
		(function() {
			//当前页导航高亮显示
			var isIndex = true;
			var href = location.href.toLowerCase();
			var $lis = $(".nav-pill").children("li");
			$.each($lis, function() {
				var nav = $(this).attr("data-flag");
				if (href.indexOf(nav) > 0) {
					$(this).addClass("active");
					isIndex = false;
					return false;
				}
			});

			if (isIndex)
				$(".nav-pill li").eq(0).addClass("active");
		}());
	</script>
	<script type="text/javascript"
		src="../js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>