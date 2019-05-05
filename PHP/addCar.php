<?php
 try {
	require 'DB_Manage.php';
	if (isset($_REQUEST["_carNumber"]))  
		$carNumber = $_REQUEST["_carNumber"];  
	else  
		$carNumber = 'NULL'; 
	$houseBranch = $_REQUEST["houseBranch"]; 
	$modelCode = $_REQUEST["modelCode"];  
	$mileAge = $_REQUEST["mileAge"];  
	$sql = "INSERT INTO `Car_table`( `_carNumber`, `houseBranch`, `modelCode`,`mileAge`)
VALUES ('$carNumber', '$houseBranch', '$modelCode','$mileAge')";  
	if ($conn->query($sql) === TRUE) {
		$last_carNumber = $conn->insert_carNumber; 
	    echo $last_carNumber;
	}
	else 
	{ 
	   echo "Error: " . $sql . "\n" . $conn->error; 
    }
}
catch(Exception $e) { 
	 echo "Exception Error See Log...."; 
	 error_log($e->getMessage() , 0); 
} 
$conn->close(); 
?>