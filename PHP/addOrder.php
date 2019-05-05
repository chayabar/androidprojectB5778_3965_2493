<?php
 try {
	require 'DB_Manage.php';
	if (isset($_REQUEST["_orderID"]))  
		$orderID = $_REQUEST["_orderID"];  
	else  
		$orderID = 'NULL'; 
	$customerID = $_REQUEST["customerID"]; 
	$orderStatus = $_REQUEST["orderStatus"];  
	$carNumber = $_REQUEST["carNumber"];  
	$startRent = $_REQUEST["startRent"];  
	$endRent = $_REQUEST["endRent"]; 
	$startMileAge = $_REQUEST["startMileAge"];  
	$endMileAge = $_REQUEST["endMileAge"];  
	$fuelFilling = $_REQUEST["fuelFilling"];  
	$fuelLitter = $_REQUEST["fuelLitter"];  
	$charge = $_REQUEST["charge"];  
	
	$sql = "INSERT INTO `Order_table`( `_orderID`, `customerID`, `orderStatus`,`carNumber`,`startRent`,`endRent`,`startMileAge`,`endMileAge`,`fuelFilling`,`fuelLitter`,`charge`)
VALUES ('$orderID', '$customerID', '$orderStatus','$carNumber','$startRent','$endRent','$startMileAge','$endMileAge','$fuelFilling','$fuelLitter','$charge')";  
	if ($conn->query($sql) === TRUE) {
		$last_orderID = $conn->insert_orderID; 
	    echo $last_orderID;
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