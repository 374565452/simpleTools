<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@include file="header.jsp"%>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	<h1 class="page-header">加密解密</h1>
	<!-- page-header属性会在文字下方显示一条横线 -->

	<ul class="nav nav-tabs" id="myTab">
		<li><a href="#md5">MD5</a></li>
		<li><a href="#base64">Base64</a></li>
	</ul>
	<div class="error-container">
		<div id="errorDiv" class="alert alert-danger alert-dismissible"
			role="alert" style="display: none;">错误：解码错误</div>
	</div>
	<div class="tab-content">
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

	</div>
</div>

<%@ include file="tail.jsp"%>