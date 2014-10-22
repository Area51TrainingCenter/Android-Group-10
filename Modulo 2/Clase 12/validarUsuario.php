<?php
header("Content-type: application/json; charset=utf-8");
require_once "conexion.php";
$ojbdata = new stdClass();
$ojbdata->correo = '';
$ojbdata->contrasenia = '';

$ojbresponse = new stdClass();
$ojbresponse->response = "ERROR";

if( isset($_GET['correo']) && $_GET['correo'] != ''){
	$ojbdata->correo = strip_tags( trim( $_GET['correo'] ) );
	$ojbdata->correo = filter_var( $ojbdata->correo , FILTER_SANITIZE_EMAIL);

    if( !filter_var($ojbdata->correo, FILTER_VALIDATE_EMAIL) ){
    	$ojbdata->correo = '';
    }
}

if( isset($_GET['contrasenia']) && $_GET['contrasenia'] != '' ){
	$ojbdata->contrasenia = strip_tags(trim($_GET['contrasenia']));
}

if( $ojbdata->correo != '' && $ojbdata->contrasenia != '' ){
	$sql = "SELECT id, nombres, apellidoPaterno, apellidoMaterno, genero
			FROM usuario
			WHERE correo = '$ojbdata->correo'
			AND contrasenia = '$ojbdata->contrasenia'";

	$resultado = mysql_query( $sql );

	$jsonObj= array();
	while($result=mysql_fetch_object($resultado))
	{
	  $jsonObj[] = $result;
	}
}
echo json_encode($jsonObj);
?>