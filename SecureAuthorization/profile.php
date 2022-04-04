<?php 
    session_start(); 
    if(!$_SESSION['user']){
        header('Location: ../index.php');
    }
?>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Вход в систему</title>
        <link href="assets/css/main.css" rel="stylesheet">
        <link
            href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
            rel='stylesheet' type='text/css'>
    </head>
    <body>
        <div class="container" id="container" style=" margin: 170px auto 0">
            <div id="login">
            <h1>Личный кабинет</h1>
                <img src="<?=$_SESSION['user']['avatar']?>"width="200" alt="Фото профиля" class="avatar">
                <h2 style="font-size: 18px;">Добро пожаловать, <span><?=$_SESSION['user']['full_name']?></span></h2>
                <h2 style="font-size: 18px;">Почта: <span><?=$_SESSION['user']['email']?></span></h2>
                <h2 style="font-size: 18px;"><a href="users.php">Список пользователей</a></span></h2>
                <p><a href="core/logout.php">Выйти</a> из системы</p>
            </div>
        </div>
    </body>
</html>