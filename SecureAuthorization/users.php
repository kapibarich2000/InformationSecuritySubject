<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Таблица пользователей</title>
        <link href="assets/css/main.css" rel="stylesheet">
        <link
            href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
            rel='stylesheet' type='text/css'>
    
            <style>
            table {
                width: 300px; /* Ширина таблицы */
                border: 1px solid; /* Рамка вокруг таблицы */
                margin: auto; /* Выравниваем таблицу по центру окна  */
                margin-top: 100px;
                margin-bottom: 50px;
            }
            td {
                border: 1px solid; /* Рамка вокруг таблицы */
                text-align: center; /* Выравниваем текст по центру ячейки */
                padding: 15px;
                white-space: nowrap;
            }
        </style>
        </head>
<body>
<?php
require 'core/connect.php';

echo '<table border="1">';

$query=$db->prepare("SELECT `id`,`full_name`,`email`,`avatar` FROM `users`;");
$query->execute();
$users=$query->fetchAll();

// Шапка
echo "<tr> <td>Фото</td><td>id</td><td>Full name</td><td>Почта</td></tr>";
foreach( $users as $user){ // перебор строк
    echo '<tr>';
    // echo  $user['avatar'];
    // колонки <img src="">
    //echo '<td>'."ASDSADSADA". $user['id'] .'</td>';
    echo '<td> <img src="'. $user['avatar'] .'" width="100"></td>';
    echo '<td>'. $user['id'] .'</td>';
    echo '<td >'. $user['full_name'] .'</td>';
    echo '<td>'. $user['email'] .'</td>';
    echo '</tr>';
}

echo '</table>';

echo "<h2 style='text-align:center'><u><a href='profile.php'>Вернуться в профиль</a></u></h2>"
?>
</body>
</html>