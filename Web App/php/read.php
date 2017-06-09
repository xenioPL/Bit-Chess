<?php
$servername = "localhost";
$username = "username";
$password = "password";

// Create connection
$conn = mysqli_connect($servername, $username, $password);

// Check connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

$sql = "SELECT history FROM positions ORDER BY id DESC";
$result = $conn->query($sql);
$row = mysqli_fetch_assoc($result);
echo json_decode($row[history]);


?>