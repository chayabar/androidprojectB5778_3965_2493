<?php
 try {
	require 'DB_Manage.php';
	if (isset($_REQUEST["_modelCode"]))  
		$modelCode = $_REQUEST["_modelCode"];  
	else  
		$modelCode = 'NULL'; 
	$companyName = $_REQUEST["companyName"]; 
	$modelName = $_REQUEST["modelName"];  
	$engineCapacity = $_REQUEST["engineCapacity"];
	$gearBox = $_REQUEST["gearBox"];
	$seats = $_REQUEST["seats"];
	$color = $_REQUEST["color"];
	$yearManufacture = $_REQUEST["yearManufacture"];

	$sql = "INSERT INTO `CarModel_table`( `_modelCode`, `companyName`, `modelName`,`engineCapacity`,`gearBox`,`seats`,`color`,`yearManufacture`)
VALUES ('$modelCode', '$companyName', '$modelName','$engineCapacity','$gearBox','$seats','$color','$yearManufacture')";  
	if ($conn->query($sql) === TRUE) {
		$last_modelCode = $conn->insert_modelCode; 
	    echo $last_modelCode;
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