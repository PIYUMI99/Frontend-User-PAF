<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

 <!DOCTYPE html>
<html>
<head>   
<link rel="stylesheet" href="css/styles.css">
<title>Register Page</title>

   <div class="container">
  <h1> Registration Form</h1>
  <form name="registration" class="registartion-form" onsubmit="return formValidation()">
    <table>
      <tr>
        <td><label for="name">Name:</label></td>
        <td><input type="text" name="name" id="name" placeholder="Enter your name"></td>
      </tr>
      <tr>
        <td><label for="nic">NIC:</label></td>
        <td><input type="text" name="nic" id="nic" placeholder="Enter your nic"></td>
      </tr>
      <tr>
        <td><label for="address">Address:</label></td>
        <td><input type="text" name="address" id="address" placeholder="Enter your address"></td>
      </tr>
      <tr>
        <td><label for="telNo">Phone Number:</label></td>
        <td><input type="interger" name="telNo" id="telNo"></td>
      </tr>
		<td><label for="accNo">Account Number:</label></td>
        <td><input type="text" name="accNo" id="accNo" placeholder="Enter your accNo"></td>

      <tr>
        <td colspan="6"><input type="submit" class="submit" value="Register" /></td>
      </tr>
    </table>
  </form>
</div>
</body>
</html>