<?php
    $hostname="www.johannfjs.com";
    $username="johannfj_user"; //write your username
    $password="welcome123"; //write your password
    $db_name="johannfj_prueba"; //write your db name
    $con=mysql_connect($hostname,$username,$password);
    mysql_select_db($db_name,$con) or die ("Cannot connect the Database");
    mysql_query("SET NAMES 'utf8'",$con); 
?>