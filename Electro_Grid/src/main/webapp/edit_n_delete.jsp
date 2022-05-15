<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>title</title>
	
    
    <script src="assets/js/croppie.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.js"></script>
	
</head>
<body>
	
    <div class="container">
		
<br>
<p></p>
	                    <div>Complaint List</div>
	                    <div>
	                        <div id="usersDiv">
	                    	
	            			</div>
	                    </div>
	                    
	                   
<div id="hideDiv" style="display: none">
    <form id="complaint">
    <input type="hidden" id="edit_id" name="edit_id">
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
	                       	                         
              <button type="submit" class="btn btn-success">
                  Edit
              </button>
          </div>
  	</form>
</div> 
	                    
</div>
	
</body>
</html>

<script>

    function deletes(id) {
    	if (confirm("Delete ?") == true) {
        	$.ajax({
                type: "DELETE",
                url: "app/user",
                data: JSON.stringify({ 'id' : id}),
                dataType: "json",
    			contentType : 'application/json',
                success: function(data){
                	if(data['success']=="success"){
                		alert("Delete Successfull!");
    					reload();
    				}else if(data['success']=="0"){
    					alert("Delete Unsuccessful!");
    				}
                },
                failure: function(errMsg) {
                    alert('Error');
                }
            });
    	}
    }

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
            		"id" : $('#edit_id').val(),
            		"user_id" : $('#user_id').val(),
                    "type" : $('#type').val(),
                    "complaint" : $('#complaint').val(),
                    "date" : $('#date').val(),
                });
            	
            	console.log(fromData);

                $.ajax({
                    type: "PUT",
                    url: 'api/complaint',
                    dataType : 'json',
    				contentType : 'application/json',
    				data: fromData,
                    success: function(data){
                    	if(data['success']=="success"){
                    		alert("Edit Successfull!");
                        	document.getElementById("hideDiv").style.display = "none";
                            $("#complaint")[0].reset();
    						reload();
    					}else{
    						alert("Unsuccessful!");
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

    function reload(){
    	$.ajax({
            type: "GET",
            url: "app/user",
            success: function(data){
            	$("#usersDiv").html(data);
            },
            failure: function(errMsg) {
                alert('Error');
            }
        });
    }

    reload();
    
    function edit(id) {
    	document.getElementById("hideDiv").style.display = "block";
    	$.ajax({
            type: "POST",
            url: "api/complaint/get",
            data: JSON.stringify({ 'id' : id}),
            dataType: "json",
			contentType : 'application/json',
            success: function(data){
            	console.log(data),
                $('#edit_id').val(data['id']),
                $('#user_id').val(data['user_id']),
                $('#type').val(data['type']),
                $('#complaint').val(data['complaint']),
                $('#date').val(data['date']),
               
            failure: function(errMsg) {
                alert('Error');
            }
        });

        
    }
    
    
</script>