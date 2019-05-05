<?php
 try {
	require 'DB_Manage.php';
	if (isset($_REQUEST["_orderID"]))  
		$orderID = $_REQUEST["_orderID"];  
	else  
	
		$orderID = 'NULL'; 
	$orderStatus= $_REQUEST["orderStatus"];
    $endRent= $_REQUEST["endRent"];
	$endMileAge= $_REQUEST["endMileAge"];
	$fuelFilling= $_REQUEST["fuelFilling"];
	$fuelLitter= $_REQUEST["fuelLitter"];
	$charge= $_REQUEST["charge"];
	


	
	$sql = "UPDATE `orders` SET `orderStatus` = '$orderStatus',`endRent`='$endRent',`endMileAge`='$endMileAge',`fuelFilling`='$fuelFilling',`fuelLitter`='$fuelLitter',`charge`= '$charge'  WHERE `orderID` = '$orderID'";
	
	if($conn->query($sql) === TRUE) {
		$last_id = $conn->insert_id;
		echo $last_id;
	}
	
	else {
		echo "Error: ". $sql . "\n" . $conn->error;
	}
}
catch(Exception $e){
	echo "Exception Error See Log....";
	error_log($e->getMessage() , 0);
}
$conn->close(); 
?>