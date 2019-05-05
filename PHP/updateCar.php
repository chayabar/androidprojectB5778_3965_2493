<?php
 try {
	require 'DB_Manage.php';
	if (isset($_REQUEST["_carNumber"]))  
		$carNumber = $_REQUEST["_carNumber"];  
	else  
		$carNumber = 'NULL';   
	$mileAge = $_REQUEST["mileAge"];  
$sql = "UPDATE `orders` SET `mileAge` = '$mileAge' WHERE `carNumber` = '$carNumber'";
	
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