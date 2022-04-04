<?php 
  session_start(); 
  if($_SESSION['user']){
    header('Location: profile.php');
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
<!--
  Страница входа в систему
-->
<body>
  <div class="container mlogin">
    <div id="login">
      <h1>Вход</h1>
      <form action="core/signin.php" method="POST" name="loginform" id="loginform">
        <p style="margin-bottom: 0px;"><label>E-mail<br>
            <input type="email" required="" name="email" class="input" id="email" require></label></p>
        <p class="emailinfo"> E-mail должен содержать только латинские буквы и цифры</p>
        <p><label>Пароль<br>
            <input type="password" required="" name="password" class="input" id="password" require></label></p>
        <p class="submit"><input type="submit"  name="login" class="button"  value="Вход"></p>
        <p class="regtext">Еще не зарегистрированы?<br><a href="register.php">Регистрация</a>!</p>
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