<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@include file="header.jsp"%>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	<h1 class="page-header">编码转换功能</h1>
	<!-- page-header属性会在文字下方显示一条横线 -->

<ul class="nav nav-tabs" id="myTab">
    <li><a href="#urlencode">UrlEncode</a></li>
    <li><a href="#htmlencode">HtmlEncode</a></li>
    <li><a href="#dbc">全角半角</a></li>
    <li><a href="#upper">大小写转换</a></li>
    <li><a href="#timestamp">Unix时间戳</a></li>
</ul>

	<div class="error-container">
		<div id="errorDiv" class="alert alert-danger alert-dismissible"
			role="alert" style="display: none;">错误：解码错误</div>
	</div>
	<div class="tab-content">
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
		<div class="tab-pane active" id="dbc">
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
		<div class="tab-pane active" id="upper">
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
		<div class="tab-pane active" id="timestamp">
			<table class="compare-table">
				<tr>
					<td class="compare-first"><textarea id="txtSourceUnix"
							class="form-control " rows="15">2016-12-30 13:24:10</textarea></td>
					<td class="compare-second">
						<p>
							<button id="btnTransUnix" class="btn btn-primary">日期转时间戳
								&gt;</button>
						</p>
						<p>
							<button id="btnTransDate" class="btn btn-primary">&lt;
								时间戳转日期</button>
						</p>
					</td>
					<td class="compare-third"><textarea id="txtDestUnix"
							class="form-control" rows="15"></textarea></td>
				</tr>
			</table>
		</div>


	</div>
</div>

<%@ include file="tail.jsp"%>