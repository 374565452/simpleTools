<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@include file="header.jsp"%>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	<h1 class="page-header">常用工具</h1>
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
							class="form-control " rows="15"> {"name":"a1","password":"11","students":[{"name":"a2","password":"12","students":null,"age":12,"going":false,"maps":null},{"name":"a3","password":"13","students":null,"age":13,"going":true,"maps":null}],"age":10,"going":true,"maps":{"k1":{"name":"a4","password":"14","students":null,"age":14,"going":true,"maps":null},"k2":{"name":"a5","password":"53","students":null,"age":15,"going":true,"maps":null}}}</textarea>
					</td>
					<td class="compare-second">
						<p>
							<button id="btnJson" class="btn btn-primary">格式化 &gt;</button>
						</p>
						<!--  
						<p>
							<button id="btnCopyJson" class="btn btn-default">复制结果</button>
						</p>
						-->
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
							class="form-control " rows="15">MD5加密</textarea></td>
					<td class="compare-second">
						<p>
							<input id="rad16" type="radio" name="radHalf" /><label
								class="lblNormal" for="rad16">16位</label>
						</p>
						<p>
							<input id="rad32" type="radio" name="radHalf" checked="checked" /><label
								class="lblNormal" for="rad32">32位</label>
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
							<button id="btnGetDbc" class="btn btn-primary">半转全 &gt;</button>
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
			<textarea spellcheck="false" id="txtSourceLen" class="form-control "
				rows="15">hello world!世界</textarea>
			<p class="mt15">
			<div class="alert alert-warning alert-dismissible compress-tip"
				role="alert">压缩比：100%</div>
			<div class="alert alert-warning alert-dismissible length-tip"
				role="alert" style="display: none;"></div>
			<span id="btnGetLen" class="btn btn-primary">计算</span>
			</p>

		</div>

	</div>
</div>

<%@ include file="tail.jsp"%>