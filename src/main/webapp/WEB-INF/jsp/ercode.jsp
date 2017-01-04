<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@include file="header.jsp"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	<h1 class="page-header">二维码</h1>
	<!-- page-header属性会在文字下方显示一条横线 -->

	<ul class="nav nav-tabs" id="myTab">
		<li><a href="#ercode">生成二维码</a></li>
		<li><a href="#ercodeshi">二维码识别</a></li>
	</ul>
	<div class="error-container">
		<div id="errorDiv" class="alert alert-danger alert-dismissible"
			role="alert" style="display: none;">错误：解码错误</div>
	</div>
	<div class="tab-content">
		<div class="tab-pane active" id="ercode">
			<table class="compare-table">
				<tr>
					<td class="compare-first">
						<table class="inner-table">
							<tr>
								<td>内容：</td>
								<td><textarea id="selText" rows="3" class="form-control">Simple Tools</textarea></td>
							</tr>
							<tr>
								<td>纠错：</td>
								<td><select id="selLevel" class="form-control">
										<option value="L">L 7%</option>
										<option value="M">M 15%</option>
										<option value="Q">Q 25%</option>
										<option value="H">H 30%</option>
								</select></td>
							</tr>
							<tr>
								<td>尺寸：</td>
								<td><input id="txtScale" type="text" class="form-control"
									value="150" placeholder="最大不能超过800像素" /></td>
							</tr>
							<tr>
								<td>边距：</td>
								<td><select id="selMargin" class="form-control">
										<option selected="selected">0</option>
										<option>1</option>
										<option>2</option>
								</select></td>
							</tr>
							<tr>
								<td></td>
								<td><input id="chkTrans" type="checkbox" /> <label for="chkTrans" class="lblNormal">背景透明</label>
								</td>
							</tr>
						</table>
					</td>
					<td class="compare-second">
						<p>
							<button id="btnErcode" class="btn btn-primary">生成（直接参数）</button>
						</p>
						<p>
							<button id="btnErcodeWithMode" class="btn btn-primary">生成(model)</button>
						</p>
					</td>
					<td class="compare-third"><img id="imgPreview"
						class="img-thumbnail" src="../images/erCode.png"
						style="max-width: 150px; max-height: 150px;" />
						<p class="mt15">[ 请右击图片另存为... ]</p></td>

				</tr>
			</table>
		</div>
		<div class="tab-pane" id="ercodeshi">
			努力开发中
		</div>

	</div>
</div>

<%@ include file="tail.jsp"%>