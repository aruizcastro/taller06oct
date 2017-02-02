<?php

/*
 * Following code will get single product details
 * A product is identified by product id (pid)
 */

// array for JSON response
$response = array();


// include db connect class
require_once '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// check for post data
if (isset($_GET["cedula"])) {
    $cedula = $_GET['cedula'];

    // get a product from products table
    $result = mysql_query("SELECT *FROM empleados WHERE cedula = $cedula");

    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {

            $result = mysql_fetch_array($result);

            $empleado = array();
            $empleado["cedula"] = $result["cedula"];
            $empleado["nombre"] = $result["nombre"];
            $empleado["apellido"] = $result["apellido"];
            $empleado["sueldo"] = $result["sueldo"];
           // $product["created_at"] = $result["created_at"];
           // $product["updated_at"] = $result["updated_at"];
            // success
            $response["success"] = 1;

            // user node
            $response["empleado"] = array();

            array_push($response["empleado"], $empleado);

            // echoing JSON response
            echo json_encode($response);
        } else {
            // no product found
            $response["success"] = 0;
            $response["message"] = "No empleado found";

            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "No empleado found";

        // echo no users JSON
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>