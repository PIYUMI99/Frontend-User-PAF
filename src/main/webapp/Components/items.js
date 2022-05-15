 $(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
{
$("#alertSuccess").hide();
}

$("#alertError").hide();

})
//save
$(document).on("click", "#btnSave", function(event)
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateUserForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidUserIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "UsersAPI", 
 type : type, 
 data : $("#formUser").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onUserSaveComplete(response.responseText, status); 
 } 
 }); 
});

function onUserSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divUsersGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 }
$("#hidUserIDSave").val(""); 
$("#formUser")[0].reset(); 
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
		{ 
		$("#hidUserIDSave").val($(this).data('serviceid')); 
		 $("#AccountNo").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#Address").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#Inquiry").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#Status").val($(this).closest("tr").find('td:eq(3)').text()); 
		 $("#TelNo").val($(this).closest("tr").find('td:eq(4)').text()); 
		});




$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "UsersAPI", 
		 type : "DELETE", 
		 data : "ServiceId=" + $(this).data("serviceid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onUserDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});
		
function onUserDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divUsersGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}


// CLIENT-MODEL================================================================
function validateUserForm()
{
	// CODE
	if ($("#AccountNo").val().trim() == "")
	{
	return "Insert AccountNo.";
	}
	// NAME
	if ($("#Address").val().trim() == "")
	{
	return "Insert Address.";
	}
	if ($("#Inquiry").val().trim() == "")
	{
	return "Insert Inquiry.";
	}
	if ($("#Status").val().trim() == "")
	{
	return "Insert Status.";
	}
	if ($("#TelNo").val().trim() == "")
	{
	return "Insert Tel No.";
    }


	return true;
}