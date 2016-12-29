<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@include file="header.jsp" %>
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
            <textarea id="txtSourceJsonEntity" class="form-control" rows="15">{
    "ID": 1,
    "Name": "jack",
    "Address": {
        "Province": "浙江",
        "City": {
            "Name": "杭州",
            "Code": "310000"
        }
    },
    "Hobby": [
        {
            "Category": "运动",
            "Name": "篮球"
        },
        {
            "Category": "音乐",
            "Name": "小提琴"
        }
    ]
}</textarea>
        </td>
        <td class="compare-second">
            <p>
                <select class="form-control" style="width:60%;margin:0 auto;">
                    <option>C#</option>
                    <option>Java</option>
                </select>
            </p>
            <p><button id="btnJsonEntity" class="btn btn-primary">生成 &gt;</button></p>
        </td>
        <td class="compare-third">
            <textarea id="txtDestJsonEntity" class="form-control" rows="15"></textarea>
        </td>
    </tr>
</table>
    </div>
    <div class="tab-pane active" id="str">
        <table class="compare-table">
    <tr>
        <td class="compare-first">
            <textarea id="txtSourceStr" class="form-control" rows="15">ID(i),Name(s),Age(i),Birthday(d),Married(b),Address</textarea>
        </td>
        <td class="compare-second">
            <p>
                <select class="form-control" style="width:60%;margin:0 auto;">
                    <option>C#</option>
                    <option>Java</option>
                </select>
            </p>
            <p><button id="btnStr" class="btn btn-primary">生成 &gt;</button></p>
        </td>
        <td class="compare-third">
            <textarea id="txtDestStr" class="form-control" rows="15"></textarea>
        </td>
    </tr>
    <tr>
        <td>
            <div class="alert alert-warning" role="alert" style="text-align:left; margin-top:10px;">
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

<%@ include file="tail.jsp" %>