<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
		</div>
	</div>
	<script>
		(function() {
			//当前页导航高亮显示
			var isIndex = true;
			var href = location.href.toLowerCase();
			var $lis = $(".nav-pill").children("li");
			$.each($lis, function() {
				var nav = $(this).attr("data-flag");
				if (href.indexOf(nav) > 0) {
					$(this).addClass("active");
					isIndex = false;
					return false;
				}
			});

			if (isIndex)
				$(".nav-pill li").eq(0).addClass("active");
		}());
	</script>
	<script type="text/javascript"
		src="../js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>