<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@include file="header.jsp"%>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	<h1 class="page-header">编码解码</h1>
	<!-- page-header属性会在文字下方显示一条横线 -->

	<ul class="nav nav-tabs" id="myTab">
		<li><a href="#ascii">ASCII</a></li>
		<li><a href="#utf8">UTF-8</a></li>
		<li><a href="#unicode">Unicode</a></li>
	</ul>
	<div class="error-container">
		<div id="errorDiv" class="alert alert-danger alert-dismissible"
			role="alert" style="display: none;">错误：解码错误</div>
	</div>
	<div class="tab-content">
		<div class="tab-pane active" id="ascii">
			<table class="compare-table">
				<tr>
					<td class="compare-first"><textarea id="txtSourceAscii"
							class="form-control " rows="15">hello</textarea></td>
					<td class="compare-second">
						<p>
							<button id="btnAsciiEncode" class="btn btn-primary">编码
								&gt;</button>
						</p>
						<p>
							<button id="btnAsciiDecode" class="btn btn-primary">&lt;
								解码</button>
						</p>
					</td>
					<td class="compare-third"><textarea id="txtDestAscii"
							class="form-control" rows="15"></textarea></td>
				</tr>
			</table>
		</div>
		<div class="tab-pane" id="utf8">
			<table class="compare-table">
				<tr>
					<td class="compare-first"><textarea id="txtSourceUrlUtf8"
							class="form-control " rows="15">http://www.baidu.com?t=hello</textarea>
					</td>
					<td class="compare-second">
						<p>
							<button id="btnUrlEncodeUtf8" class="btn btn-primary">编码
								&gt;</button>
						</p>
						<p>
							<button id="btnUrlDecodeUtf8" class="btn btn-primary">&lt;
								解码</button>
						</p>
					</td>
					<td class="compare-third"><textarea id="txtDestUrlUtf8"
							class="form-control" rows="15"></textarea></td>
				</tr>
			</table>
		</div>
		<div class="tab-pane" id="unicode">
			<table class="compare-table">
				<tr>
					<td class="compare-first"><textarea id="txtSourceUnicode"
							class="form-control " rows="15">&lt;p&gt;hello&lt;/p&gt;</textarea>
					</td>
					<td class="compare-second">
						<p>
							<button id="btnUnicodeEncode" class="btn btn-primary">编码
								&gt;</button>
						</p>
						<p>
							<button id="btnUnicodeDecode" class="btn btn-primary">&lt;
								解码</button>
						</p>
					</td>
					<td class="compare-third"><textarea id="txtDestUnicode"
							class="form-control" rows="15"></textarea></td>
				</tr>
			</table>
		</div>


	</div>
</div>

<%@ include file="tail.jsp"%>