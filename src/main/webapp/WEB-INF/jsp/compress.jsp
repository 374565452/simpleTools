<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@include file="header.jsp"%>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	<h1 class="page-header">格式化</h1>
	<!-- page-header属性会在文字下方显示一条横线 -->

	<ul class="nav nav-tabs" id="myTab">
		<li><a href="#js">js压缩格式化</a></li>
		<li><a href="#css">css压缩格式化</a></li>

	</ul>
	<div class="error-container">
		<div id="errorDiv" class="alert alert-danger alert-dismissible"
			role="alert" style="display: none;">错误：解码错误</div>
	</div>
	<div class="tab-content">
		<div class="tab-pane active" id="js">
			<!-- 
			<script src="/Assets/jsformat/my.js"></script>
			<script src="/Assets/jsformat/Words.js"></script>
			<script src="/Assets/jsformat/jsformat.js"></script>
			<script src="/Assets/jsformat/Packer.js"></script> -->
			<script>
				// js压缩格式化
				function jsCode(obj) {
					this.init = function() {
						var code = obj.val();
						if (!this.code || this.code == "") {
							this.code = code; //先保存起来  以方便后续还原
						}
						return code;
					}
					this.showTip = function(text) {
						$(".compress-tip").show().text(text);
					}
					this.closeTip = function() {
						$(".compress-tip").hide();
					}
					this.compress = function() {
						var code = this.init();
						code = jsmin("", code, 2);
						code = code.replace(/^\s+/, '');
						this.showTip("压缩前："
								+ jsmin.oldSize
								+ ", 压缩后："
								+ jsmin.newSize
								+ ", 压缩率："
								+ (Math.round(jsmin.newSize / jsmin.oldSize
										* 1000) / 10) + '%');
						return code;
					}
					this.format = function() {
						var code = this.init();
						code = code.replace(/^\s+/, '');
						if (code && code.charAt(0) === '<') {
							code = style_html(code, 4, ' ', 80);
						} else {
							code = js_beautify(code, 4, ' ');
						}
						this.closeTip();
						return code;
					}
					this.recover = function() {
						this.closeTip();
						return (this.code) ? this.code : obj.value;
					}
				}
			</script>

			<div class="tab-pane active" id="js">
				<textarea id="jsContent" class="form-control" rows="15">function filterFilter() {
    return function(array, expression, comparator) {
        if (!isArrayLike(array)) {
            if (array == null) {
                return array;
            } else {
                throw minErr('filter')('notarray', 'Expected array but received: {0}', array);
            }
        }
        var expressionType = getTypeForFilter(expression);
        var predicateFn;
        var matchAgainstAnyProp;
        switch (expressionType) {
        case 'function':
            predicateFn = expression;
            break;
        case 'boolean':
        case 'null':
        case 'number':
        case 'string':
            matchAgainstAnyProp = true;
        case 'object':
            predicateFn = createPredicateFn(expression, comparator, matchAgainstAnyProp);
            break;
        default:
            return array;
        }
        return Array.prototype.filter.call(array, predicateFn);
    };
}</textarea>
				<script>
					var jsObj = new jsCode($("#jsContent"));
				</script>
				<p class="mt15">
					<div class="alert alert-warning alert-dismissible compress-tip" role="alert">压缩比：100%</div>
					<span class="btn btn-primary" onclick="$('#jsContent').val(jsObj.compress())">压缩</span>
					<span class="btn btn-primary" onclick="$('#jsContent').val(jsObj.format())">格式化</span> 
					<span class="btn btn-primary" onclick="$('#jsContent').val(jsObj.recover())">还原</span>
				</p>
			</div>

		</div>
		<div class="tab-pane active" id="css">
			<textarea id="cssCode" class="form-control" rows="15">
		    .pagination {
		    display: inline-block;
		    padding-left: 0;
		    margin: 20px 0;
		    border-radius: 4px;
		    }
		    .pagination li {
		    display: inline;
		    }
		</textarea>
			<script>
				var codeObj = new cssCode($('#cssCode'));
			</script>
			<p class="mt15">
				<div class="alert alert-warning alert-dismissible compress-tip" role="alert">压缩比：100%</div>
				<span class="btn btn-primary" onclick="$('#cssCode').val(codeObj.compress())">压缩</span> 
				<span class="btn btn-primary" onclick="$('#cssCode').val(codeObj.format())">格式化(多行)</span> 
				<span class="btn btn-primary" onclick="$('#cssCode').val(codeObj.formatOver())">格式化(单行)</span> 
				<span class="btn btn-primary" onclick="$('#cssCode').val(codeObj.recover())">还原</span>
			</p>
		</div>
	</div>


</div>

<%@ include file="tail.jsp"%>