<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@include file="header.jsp"%>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	<h1 class="page-header">单位换算</h1>
	<!-- page-header属性会在文字下方显示一条横线 -->

	<ul class="nav nav-tabs" id="myTab">
		<li><a href="#jinzhi">进制换算</a></li>
		<li><a href="#time">时间换算</a></li>

	</ul>
	<div class="error-container">
		<div id="errorDiv" class="alert alert-danger alert-dismissible"
			role="alert" style="display: none;">错误：解码错误</div>
	</div>
	<div class="tab-content">
		<div class="tab-pane active" id="jinzhi">
			<table class="compare-table">
				<tr>
					<td class="compare-first">
						<table class="inner-table">
							<tr>
								<td class="input-group"><span class="input-group-btn">
										<button class="btn btn-default" type="button">十进制</button>
								</span> <input id="txt10" type="text" class="form-control"> <span
									class="input-group-btn">
										<button data-val="10" class="btn btn-primary btnCal"
											type="button">计算</button>
								</span></td>
							</tr>
							<tr>
								<td class="input-group"><span class="input-group-btn">
										<button class="btn btn-default" type="button">二进制</button>
								</span> <input id="txt2" type="text" class="form-control"> <span
									class="input-group-btn">
										<button data-val="2" class="btn btn-primary btnCal"
											type="button">计算</button>
								</span></td>
							</tr>
							<tr>
								<td class="input-group"><span class="input-group-btn">
										<button class="btn btn-default" type="button">八进制</button>
								</span> <input id="txt8" type="text" class="form-control"> <span
									class="input-group-btn">
										<button data-val="8" class="btn btn-primary btnCal"
											type="button">计算</button>
								</span></td>
							</tr>
							<tr>
								<td class="input-group"><span class="input-group-btn">
										<button class="btn btn-default" type="button">十六进制</button>
								</span> <input id="txt16" type="text" class="form-control"> <span
									class="input-group-btn">
										<button data-val="16" class="btn btn-primary btnCal"
											type="button">计算</button>
								</span></td>
							</tr>
						</table>
					</td>
					<td class="compare-second"></td>
					<td class="compare-third"></td>
				</tr>
			</table>
		</div>
		<div class="tab-pane active" id="time">
			<table class="compare-table">
				<tr>
					<td class="compare-first">
						<table class="inner-table">
							<tr>
								<td class="input-group"><span class="input-group-btn">
										<button class="btn btn-default" type="button">天</button>
								</span> <input id="txtDay" type="text" class="form-control"> <span
									class="input-group-btn">
										<button data-val="day" class="btn btn-primary btnCalTime"
											type="button">计算</button>
								</span></td>
							</tr>
							<tr>
								<td class="input-group"><span class="input-group-btn">
										<button class="btn btn-default" type="button">时</button>
								</span> <input id="txtHour" type="text" class="form-control"> <span
									class="input-group-btn">
										<button data-val="hour" class="btn btn-primary btnCalTime"
											type="button">计算</button>
								</span></td>
							</tr>
							<tr>
								<td class="input-group"><span class="input-group-btn">
										<button class="btn btn-default" type="button">分</button>
								</span> <input id="txtMinute" type="text" class="form-control">
									<span class="input-group-btn">
										<button data-val="minute" class="btn btn-primary btnCalTime"
											type="button">计算</button>
								</span></td>
							</tr>
							<tr>
								<td class="input-group"><span class="input-group-btn">
										<button class="btn btn-default" type="button">秒</button>
								</span> <input id="txtSecond" type="text" class="form-control">
									<span class="input-group-btn">
										<button data-val="second" class="btn btn-primary btnCalTime"
											type="button">计算</button>
								</span></td>
							</tr>
							<tr>
								<td class="input-group"><span class="input-group-btn">
										<button class="btn btn-default" type="button">毫秒</button>
								</span> <input id="txtMillisecond" type="text" class="form-control">
									<span class="input-group-btn">
										<button data-val="millisecond"
											class="btn btn-primary btnCalTime" type="button">计算</button>
								</span></td>
							</tr>
						</table>
					</td>
					<td class="compare-second"></td>
					<td class="compare-third"></td>
				</tr>
			</table>
		</div>


	</div>
</div>

<%@ include file="tail.jsp"%>