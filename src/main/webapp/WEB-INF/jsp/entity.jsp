<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@include file="header.jsp"%>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	<h1 class="page-header">实体代码生成</h1>
	<!-- page-header属性会在文字下方显示一条横线 -->

	<ul class="nav nav-tabs" id="myTab">
		<li><a href="#json">json字符串生成实体</a></li>
		<li><a href="#str">字符串生成实体</a></li>
	</ul>
	<div class="error-container">
		<div id="errorDiv" class="alert alert-danger alert-dismissible"
			role="alert" style="display: none;">错误：解码错误</div>
	</div>
	<div class="tab-content">
		<div class="tab-pane active" id="json">
			<table class="compare-table">
				<tr>
					<td class="compare-first">
						<table class="inner-table">
							<tr>
								<td>内容：</td>
								<td><textarea id="txtSourceJsonEntity" class="form-control"
										rows="15"> {"name":"a1","password":"11","students":[{"name":"a2","password":"12","students":null,"age":12,"going":false,"maps":null},{"name":"a3","password":"13","students":null,"age":13,"going":true,"maps":null}],"age":10,"going":true,"maps":{"k1":{"name":"a4","password":"14","students":null,"age":14,"going":true,"maps":null},"k2":{"name":"a5","password":"53","students":null,"age":15,"going":true,"maps":null}}}</textarea></td>
							</tr>
							<tr>
								<td>包名：</td>
								<td><input id="txtPackage" type="text" class="form-control"
									value="com.parse.jsonbean" placeholder="com.parse.jsonbean" /></td>
							</tr>
							<tr>
								<td>类名：</td>
								<td><input id="txtClass" type="text" class="form-control"
									value="ParseBean" placeholder="ParseBean" /></td>
							</tr>
						</table>
					</td>
					<td class="compare-second">
						<p>
							<select class="form-control" style="width: 60%; margin: 0 auto;">
								<option>Java</option>
								<option>C#</option>
							</select>
						</p>
						<p>
							<button id="btnJsonEntity" class="btn btn-primary">生成
								&gt;</button>
						</p>
					</td>
					<td class="compare-third"><textarea id="txtDestJsonEntity"
							class="form-control" rows="15"></textarea></td>
				</tr>
			</table>
		</div>
		<div class="tab-pane active" id="str">
			<table class="compare-table">
				<tr>
					<td class="compare-first"><textarea id="txtSourceStr"
							class="form-control" rows="15">ID(i),Name(s),Age(i),Birthday(d),Married(b),Address</textarea>
					</td>
					<td class="compare-second">
						<p>
							<select class="form-control" style="width: 60%; margin: 0 auto;">
								<option>Java</option>
								<option>C#</option>
							</select>
						</p>
						<p>
							<button id="btnStr" class="btn btn-primary">生成 &gt;</button>
						</p>
					</td>
					<td class="compare-third"><textarea id="txtDestStr"
							class="form-control" rows="15"></textarea></td>
				</tr>
				<tr>
					<td>
						<div class="alert alert-warning" role="alert"
							style="text-align: left; margin-top: 10px;">
							注：括号中字符表示类型，i->int,s->string,d->DateTime,b->bool,如果没有括号，默认为string类型
						</div>
					</td>
					<td></td>
					<td></td>
				</tr>
			</table>
		</div>
	</div>

</div>

<%@ include file="tail.jsp"%>