<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@include file="header.jsp"%>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	<h1 class="page-header">条形码生成</h1>
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
		<div class="tab-pane active" id="barcode">
			<table class="compare-table">
				<tr>
					<td class="compare-first">
						<table class="inner-table">
							<tr>
								<td>类型：</td>
								<td><select id="selType" class="form-control">
										<option>AZTEC</option>
										<option>CODABAR</option>
										<option>CODE_39</option>
										<option>CODE_93</option>
										<option>CODE_128</option>
										<option>DATA_MATRIX</option>
										<option>EAN_8</option>
										<option selected="selected">EAN_13</option>
										<option>ITF</option>
										<option>MAXICODE</option>
										<option>PDF_417</option>
										<option>QR_CODE</option>
										<option>RSS_14</option>
										<option>RSS_EXPANDED</option>
										<option>UPC_A</option>
										<option>UPC_E</option>
										<option>All_1D</option>
										<option>UPC_EAN_EXTENSION</option>
										<option>MSI</option>
										<option>PLESSEY</option>
										<option>JAN13</option>
								</select></td>
							</tr>
							<tr>
								<td>内容：</td>
								<td><input id="txtCode" type="text" class="form-control"
									value="6923450656150" /></td>
							</tr>
							<tr>
								<td>宽度：</td>
								<td><input id="txtWidth" type="text" class="form-control"
									value="250" /></td>
							</tr>
							<tr>
								<td>高度：</td>
								<td><input id="txtHeight" type="text" class="form-control"
									value="100" /></td>
							</tr>
							<tr>
								<td></td>
								<td><input id="chkTrans" type="checkbox" /> <label
									for="chkTrans" class="lblNormal">背景透明</label></td>
							</tr>
						</table>
					</td>
					<td class="compare-second">
						<p>
							<button id="btnBarcode" class="btn btn-primary">生成 &gt;</button>
						</p>
					</td>
					<td class="compare-third"><img id="imgPreview"
						class="img-thumbnail" src="/Images/barcode.png"
						style="width: 250px; height: 100px;" />
						<p class="mt15">[ 请右击图片另存为... ]</p></td>
				</tr>
			</table>
		</div>



	</div>
</div>

<%@ include file="tail.jsp"%>