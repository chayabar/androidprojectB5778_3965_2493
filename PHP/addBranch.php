<?php
 try {
	require 'DB_Manage.php';
	if (isset($_REQUEST["_branchNumber"]))  
		$branchNumber = $_REQUEST["_branchNumber"];  
	else  
		$branchNumber = 'NULL'; 
	$parkingSpaces = $_REQUEST["parkingSpaces"]; 
	$address = $_REQUEST["address"];  
	$sql = "INSERT INTO `Branch_table`( `_branchNumber`, `parkingSpaces`, `address`)
VALUES ('$branchNumber', '$parkingSpaces', '$address')";  
	if ($conn->query($sql) === TRUE) {
		$last_branchNumber = $conn->insert_branchNumberd; 
	    echo $last_branchNumber;
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