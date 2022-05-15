<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Title</title>
	
    <script src="assets/js/croppie.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.js"></script>
	
</head>
<body>
	
    <div class="container">
		
<br>
<p></p>

	                    <div>Complaint</div>
	                        <form id="complaint">
	                            <div>
	                                <label>User ID</label>
	                                <div>
	                                    <input type="text" id="user_id" class="form-control" name="user_id">
	                                </div>
	                            </div>
	                            
	                            <div>
	                                <label>Type</label>
	                                <div>
	                                    <input type="text" id="Type" class="form-control" name="type">
	                                </div>
	                            </div>
	                            
	                            <div>
	                                <label>Complaint</label>
	                                <div>
	                                    <input type="text" id="complaint" class="form-control" name="complaint">
	                                </div>
	                            </div>
	                            
	                            <div>
	                                <label>Date</label>
	                                <div>
	                                    <input type="date" id="date" class="form-control" name="date">
	                                </div>
	                       
	                            <div>
	                                <button type="submit" class="btn btn-success">
	                                    Add
	                                </button>
	                                <a href="edit_n_delete.jsp" class="btn btn-success">
	                                    List
	                                </a>
	                            </div>
	                    	</form>
	                    </div>

</body>
</html>
<script>

$(document).ready(function () {

    $("#complaint").validate({
        rules: {
        	user_id: "required",
        	type: {
                number: true,
                required: true
            },
        	complaint: "required",
        	date: "required",
        	
        },
        messages: {
        	user_id: "N Required!",
        	type: {
                number: "format",
                required: "required"
            },
            complaint: "n Required!",
            date: "p Required!",
           
        },
        submitHandler: function () {
        	var fromData = JSON.stringify({
                "user_id" : $('#user_id').val(),
                "type" : $('#type').val(),
                "complaint" : $('#complaint').val(),
                "date" : $('#date').val(),
             
            });
        	
        	console.log(fromData);

            $.ajax({
                type: "POST",
                url: 'api/complaint',
                dataType : 'json',
				contentType : 'application/json',
				data: fromData,
                success: function(data){
                	console.log(data);
                	if(data['success']=="success"){
                		alert("Added Successfull!");
                        $("#complaint")[0].reset();
					}else{
						alert("Unsuccessfull!");
					}
                },
                failure: function(errMsg) {
                	alert("Connection Error!");
                }
            });
        }
    });

    $("#complaint").submit(function(e) {
        e.preventDefault();
    });

});

</script>