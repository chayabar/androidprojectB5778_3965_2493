<?php
 try {
	require 'DB_Manage.php';
	if (isset($_REQUEST["_ID"]))  
		$ID = $_REQUEST["_ID"];  
	else  
		$ID = 'NULL'; 
	$lastName = $_REQUEST["lastName"]; 
	$firstName = $_REQUEST["firstName"];  
	$phoneNumber = $_REQUEST["phoneNumber"];  
	$eMail = $_REQUEST["eMail"];  
	$creditCard = $_REQUEST["creditCard"];  
	$sql = "INSERT INTO `Customer_table`( `_ID`, `lastName`, `firstName`,`phoneNumber`,`eMail`,`creditCard`)
VALUES ('$ID', '$lastName', '$firstName','$phoneNumber','$eMail','$creditCard')";  
	if ($conn->query($sql) === TRUE) {
		$last_ID = $conn->insert_ID; 
	    echo $last_ID;
	 }
	 else { 
		   echo "Error: " . $sql . "\n" . $conn->error; 
     }
}
catch(Exception $e) { 
	 echo "Exception Error See Log...."; 
	 error_log($e->getMessage() , 0); 
} 
$conn->close(); 
?>