<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<section id="subscribe_section" class="subscribe_section">
    <div class="row">
        <form action="javascript:subcribe()" method="post" class="form-horizontal">
            <div class="form-group form-group-lg">
                <label class="col-sm-6 control-label" for="formGroupInputLarge">
                    <h1><span class="red-color">Đăng ký </span>để nhận thông tin mới</h1>
                </label>

                <div class="col-sm-3">
                    <input required type="email" id="id_subcribe" name="subscribe" class="form-control input-lg">
                </div>
                <div class="col-sm-1">
                    <input type="submit" value="Sign Up" class="btn btn-large pink">
                </div>
                <div class="col-sm-2"></div>
            </div>
        </form>
    </div>
    <div id="ajax_subcribe"> 
    </div>
</section>
<!-- Subscriber Section -->

<section id="footer_section" class="footer_section">

    <div class="footer_bottom_Section">
        <div class="container">
            <div class="row">
                <div class="footer">
                    <div class="col-sm-3">
                        <div class="social">
                            <a target="_blank" href="https://www.facebook.com/sharer/sharer.php?u=https://www.facebook.com/phuc.pham.5268" class="icons-sm fb-ic"><i class="fa fa-facebook"></i></a>
                            <!--Twitter-->
                            <a target="_blank" href="https://twitter.com/share?text=&url=https://www.facebook.com/phuc.pham.5268" class="icons-sm tw-ic"><i class="fa fa-twitter"></i></a>
                            <!--Google +-->
                            <a target="_blank" href="https://plus.google.com/share?url=https://www.facebook.com/phuc.pham.5268" class="icons-sm inst-ic"><i class="fa fa-instagram"> </i></a>
                            <!--Linkedin-->
                            <a target="_blank" href="https://www.pinterest.com/pin/create/button/?url=https://www.facebook.com/phuc.pham.5268" class="icons-sm tmb-ic"><i class="fa fa-tumblr"> </i></a>
                            <!--Pinterest-->
                            <a target="_blank" href="https://www.pinterest.com/pin/create/button/?url=https://www.facebook.com/phuc.pham.5268" class="icons-sm rss-ic"><i class="fa fa-rss"> </i></a>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <p>&copy; Copyright 2016-Tech News . Design by: <a href="https://uicookies.com">uiCookies</a> </p>
                    </div>
                    <div class="col-sm-3">
                        <p>Technology News Magazine</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</div>
<!-- #content-wrapper -->

</div>
<!-- .offcanvas-pusher -->

<a href="#" class="crunchify-top"><i class="fa fa-angle-up" aria-hidden="true"></i></a>



</div>
<!-- #main-wrapper -->

<!-- jquery Core-->
<script src="<%=request.getContextPath() %>/templates/public/js/jquery-2.1.4.min.js"></script>

<!-- Bootstrap -->
<script src="<%=request.getContextPath() %>/templates/public/js/bootstrap.min.js"></script>

<!-- Theme Menu -->
<script src="<%=request.getContextPath() %>/templates/public/js/mobile-menu.js"></script>

<!-- Owl carousel -->
<script src="<%=request.getContextPath() %>/templates/public/js/owl.carousel.min.js"></script>

<!-- Theme Script -->
<script src="<%=request.getContextPath() %>/templates/public/js/script.js"></script>
<script type="text/javascript">
		function addForm(id_cmt_tmp,id_news_tmp)
		{
			$.ajax({
				url: '<%=request.getContextPath()%>/detail',
				type: 'POST',
				cache: false,
				data: {
						id_cmt : id_cmt_tmp,	
						id_news : id_news_tmp,
						},
				success: function(data){
						$('#divCmt' + id_cmt_tmp).html(data);
						$('#msg' + id_cmt_tmp).html("Comment của bạn đã được ghi nhận");
				},
				error: function (){
				// Xử lý nếu có lỗi
						alert('lỗi rồi');
				}
			});
		}
</script>
<script type="text/javascript">
		function addCmt(id_news_tmp,type_tmp,id_cmt_tmp)
		{
			var id_name =  "#name" + id_cmt_tmp;
			var id_mail = "#email" + id_cmt_tmp;
			var id_content = "#content" + id_cmt_tmp;
			var name_tmp = $(id_name).val();
			var email_tmp = $(id_mail).val();
			var content_tmp = $(id_content).val();
			$.ajax({
				url: '<%=request.getContextPath()%>/comment/add',
				type: 'POST',
				cache: false,
				data: {
						id_cmt : id_cmt_tmp,
						type: type_tmp,
						id_news : id_news_tmp,
						name : name_tmp,
						email : email_tmp,
						content : content_tmp,
						},
				success: function(data){
						$('#ajax').html(data);
				},
				error: function (){
				// Xử lý nếu có lỗi
						alert('lỗi rồi');
				}
			});
		}
</script>
<script type="text/javascript">
	$(document).ready(function() {
	    var elements = $("input,textarea,select");
	    for (var i = 0; i < elements.length; i++) {
	        elements[i].oninvalid = function(e) {
	            e.target.setCustomValidity("");
	            if (!e.target.validity.valid) {
	                 e.target.setCustomValidity(e.target.getAttribute("requiredmsg"));
	            }
	        };
	        elements[i].oninput = function(e) {
	            e.target.setCustomValidity("");
	        };
	    }
	})
	</script>
	<script type="text/javascript">
	function search()
	{
		var key_tmp = $("#idkey_tmp").val();
		$.ajax({
			url: '<%=request.getContextPath()%>/cat',
			type: 'POST',
			cache: false,
			data: {
					key : key_tmp,	
					},
			success: function(data){
					$('#ajax_search').html(data);
			},
			error: function (){
			// Xử lý nếu có lỗi
					alert('lỗi rồi');
			}
		});
	}
	</script>
	
	<script type="text/javascript">
	function subcribe()
	{
		var email_tmp = $("#id_subcribe").val();
		$.ajax({
			url: '<%=request.getContextPath()%>/subcribe',
			type: 'POST',
			cache: false,
			data: {
					email : email_tmp,	
					},
			success: function(data){
					$('#ajax_subcribe').html(data);
			},
			error: function (){
			// Xử lý nếu có lỗi
				$('#ajax_subcribe').html("<h5 style='font-style: italic; color:red;text-align:center'>Có lỗi trong quá trình xử lý</h5>");
			}
		});
	}
	</script>
	
	
	
</body>
</html>