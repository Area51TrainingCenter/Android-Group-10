<?php
header("Content-type: application/json; charset=utf-8");
require_once('conexion.php');
 
$something = $_GET['s'];
$sqlcode = mysql_query("select * from usuario order by id");

$jsonObj= array();
while($result=mysql_fetch_object($sqlcode))
{
  $jsonObj[] = $result;
}

echo json_encode($jsonObj);
?>