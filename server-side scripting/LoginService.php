<?php
$conn = mysqli_connect('13.76.209.146','root','baabteadmin123!','login');
if(isset($_REQUEST['email']) && isset($_REQUEST['password']))
{
	$email= $_REQUEST['email'];
	$password=$_REQUEST['password'];
	

	$query = "select Username,Password from tbl_login where Username='$email'and Password='$password'";

	$result = mysqli_query($conn,$query);
	if(mysqli_num_rows($result)==1)
	{
	$rw[]=mysqli_fetch_assoc($result);

	$rw[0]['ResponseCode']="200";
	$rw[0]['Msg']="Success";
	echo json_encode($rw);

	}
	else
	{
	$query = "select Username,Password from tbl_login where Username='$email'";
	$result = mysqli_query($conn,$query);
	if(mysqli_num_rows($result)==1)
	{
	$rw[]=mysqli_fetch_assoc($result);
	$rw[0]['ResponseCode']="500";
	$rw[0]['Msg']="Password Incorrect!";
	echo json_encode($rw);
	}
	else
	{
	$er = array('ResponseCode'=>"500","Msg"=>"Email id does not exist");
	echo json_encode($er);
	}
	}

}
  else
  {
   $er= array("ResponseCode"=>"404","Msg"=>"Email or password is not defined");
   echo json_encode($er);

  }

  ?>