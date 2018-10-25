<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	<footer class="sticky-footer">
      <div class="container">
        <div class="text-center">
          <small>Copyright Â© Phúc Website 2017</small>
        </div>
      </div>
    </footer>
      <script type="text/javascript">
	function setRead()
	{
		$.ajax({
			url: '<%=request.getContextPath()%>/admin/message',
			type: 'POST',
			cache: false,
			data: {
					},
			success: function(data){
					
			},
			error: function (){
			
			}
		});
	}
	</script>
    
    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
      <i class="fa fa-angle-up"></i>
    </a>
    <!-- Logout Modal-->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true"><i class="fa fa-times"></i></span>
            </button>
          </div>
          <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
          <div class="modal-footer">
            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
            <a class="btn btn-primary" href="<%=request.getContextPath() %>/admin/logout">Logout</a>
          </div>
        </div>
      </div>
    </div>