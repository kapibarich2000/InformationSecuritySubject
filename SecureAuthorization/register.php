<?php 
    session_start();
    require_once 'core/connect.php';
    if($_SESSION['user']){
        header('Location: profile.php');
    }
?>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>Регистрация новых пользователей</title>
    <link href="assets/css/main.css" rel="stylesheet">
    <link
        href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
        rel='stylesheet' type='text/css'>
</head>

<body>
    <div class="container mregister">
        <div id="login">
            <h1>Регистрация</h1>
            <form action="core/signup.php" method="post" name="registerform" enctype="multipart/form-data" id="registerform" >
                <p><label>Полное имя</label><br>
                    <input type="text" required="" name="full_name" class="input" id="full_name"></p>
                <p style="margin-bottom: 0px;"><label>E-mail<br></label>
                    <input type="email" required="" name="email" class="input" id="email"></p>
                <p class="emailinfo"> E-mail должен содержать только латинские буквы и цифры.</p>
                <p style="margin-bottom: 0px;"><label>Пароль</label><br>
                    <input type="password" required="" name="password" class="input" id="password"></p>
                    <p class="emailinfo"> Минимальная длина пароля 6 символов.</p>
                <p><label>Подтверждение пароля</label><br>
                    <input type="password" required="" name="password_confirm" class="input" id="password"></p> <!--Подтверждение пароля  size="32" -->
                <p style="margin-bottom: 2px;"><label>Изображение пользователя</label><br>
                    <input type="file" required="" name="avatar"></p> 
                    <p class="emailinfo"> Формата jpg или png, фото не более 2Мбайт.</p>   
                <p class="submit"><input type="submit" name="register" class="button" id="register" value="Зарегистрироваться"></p>
                <p class="regtext"><br><br>Уже зарегистрированы?<br><a href="index.php">Войдите в систему</a>!</p>
                <?php
                    if($_SESSION["message"]){
                        echo '<p class="msg">' . $_SESSION["message"] .'</p>'; 
                    }
                    unset($_SESSION["message"]);
                ?>
            </form>
        </div>
    </div>
    <footer>
        © 2022 <a href="https://vk.com/kapibarich">Kapibarich </a>. Все права защищены.
    </footer>
</body>

</html>


<pre>
    <?php
    /*  $queryCheckEmail=$db->prepare("SELECT `id` FROM `users` WHERE email=:email");
        $params=['email'=>"test@test.c5m"];
        $queryCheckEmail->execute($params);
        $result=$queryCheckEmail->fetchAll();
        print_r($result);
        echo $result['id'];
        if(!$result[0])
            echo"Empty";
    */
        //if($queryCheckEmail->fetchAll()
    ?>
</pre>