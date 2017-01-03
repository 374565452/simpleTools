<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@include file="header.jsp"%>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	<h1 class="page-header">WEB、TCP/IP测试</h1>
	<!-- page-header属性会在文字下方显示一条横线 -->

	<ul class="nav nav-tabs" id="myTab">
		<li><a href="#netty">netty测试</a></li>
	</ul>
	<div class="error-container">
		<div id="errorDiv" class="alert alert-danger alert-dismissible"
			role="alert" style="display: none;">错误：解码错误</div>
	</div>
	<div class="tab-content">
		<div class="tab-pane" id="netty">
			<table class="compare-table">
				<tr>
					<td class="compare-first">
						<p>
							浏览器通过Http方式来向服务器发送控制数据，Http服务器接收到数据后，找到相应的控制器controller,
							通过netty服务器向具体的客户端发送数据。此时控制器controller要等待客户端有数据返回处理wait状态，
							客户端有数据返回时，通过事件订阅的方式通知controller有数据返回，再由controller把结果返回给浏览器。
						</p>
					</td>
					<td class="compare-second">
						<p>
							<button id="btnNetty" class="btn btn-primary">发送数据 &gt;</button>
						</p>
					</td>
					<td class="compare-third"><textarea id="nettyResult"
							class="form-control" rows="15"></textarea></td>
				</tr>
			</table>
		</div>

		<div class="tab-pane" id="websocket">
			努力开发中......
		</div>
	</div>
</div>

<%@ include file="tail.jsp"%>