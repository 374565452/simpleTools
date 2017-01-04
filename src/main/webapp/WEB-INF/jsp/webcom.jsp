<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getServerName() + ":" + request.getServerPort() + path + "/";
	String basePath2 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//System.out.println(path);
	//System.out.println(basePath);
	//System.out.println(basePath2);
%>

<%@include file="header.jsp"%>
<script type="text/javascript" src="../js/sockjs-1.1.1.min.js"></script>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	<h1 class="page-header">WEB、TCP/IP测试</h1>
	<!-- page-header属性会在文字下方显示一条横线 -->

	<ul class="nav nav-tabs" id="myTab">
		<li><a href="#netty">netty测试</a></li>
		<li><a href="#websocket">websocket测试</a>
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
						<p>浏览器通过Http方式来向服务器发送控制数据，Http服务器接收到数据后，找到相应的控制器controller,
							通过netty服务器向具体的客户端发送数据。此时控制器controller要等待客户端有数据返回处理wait状态，
							客户端有数据返回时，通过事件订阅的方式通知controller有数据返回，再由controller把结果返回给浏览器。</p>
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
			<table class="compare-table">
				<tr>
					<td class="compare-first"><textarea id="webSocketTest"
							class="form-control" rows="15">websocket测试数据</textarea>
						<p>浏览器通过websocket的方式与服务端建立一个长连接，被动的接上由服务端传送来的数据</p></td>
					<td class="compare-second">
						<p>
							<button id="btnWebSocket" class="btn btn-primary">发送数据
								&gt;</button>
						</p>
					</td>
					<td class="compare-third"><textarea id="webSocketResult"
							class="form-control" rows="15"></textarea></td>
				</tr>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
	var websocket;
	function connect() {
		var url='<%=basePath%>';
		var sockUrl='<%=basePath2%>';
		if ('WebSocket' in window) {
			//alert("WebSocket");
			websocket = new WebSocket("ws://" + url + "webSocketServer");
		} else if ('MozWebSocket' in window) {
			//alert("MozWebSocket")
			websocket = new MozWebSocket("ws://" + url + "webSocketServer");
		} else {
			//alert("SockJs")
			websocket = new SockJS(sockUrl + "sockjs/webSocketServer");
		}
		websocket.onopen = function(evnt) {
			//alert("connected to the server-----")
		};
		websocket.onmessage = function(evnt) {
			$("#webSocketResult").val(
					"(<font color='red'>" + evnt.data + "</font>)")
		};
		websocket.onerror = function(evnt) {
		};
		websocket.onclose = function(evnt) {
		};
	}
	function websocketSend(message){
		if(websocket.readyState === WebSocket.OPEN){
			websocket.send(message);
		}
	}
	$(function() {
		connect();
	});
</script>
<%@ include file="tail.jsp"%>