<?php
    session_start();
    require_once 'connect.php';
    function returnBadLogin(){
        $_SESSION["message"]="Неправильный логин или пароль";
        header('Location: ../index.php');
    }

    // trim - убирает пробелы спереди и с конца
    // htmlspecialchars - обезвреживает(добавл. спец. символы) кавычки и теги (< >) (при этом они остаются видны)
    $email=htmlspecialchars(trim($_POST["email"])); 
    $password=htmlspecialchars(trim($_POST["password"]));

    $queryLogIn=$db->prepare("SELECT `full_name`,`email`,`password`,`avatar` FROM `users` WHERE email=:email");
    $params=['email'=>$email];
    $queryLogIn->execute($params);
    $result=$queryLogIn->fetchAll(); // получаем коллекцию
    if(!empty($result[0][0])){
        //$user=mysqli_fetch_assoc($check_user);
        $isGoodLogin=password_verify($password,$result[0]['password']);
        if(!$isGoodLogin)
            returnBadLogin();
        $_SESSION['user']=[
            "full_name"=>$result[0]['full_name'],
            "email"=>$result[0]['email'],
            "avatar"=>$result[0]['avatar']
        ];
        header('Location: ../profile.php');    
    }
    else
        returnBadLogin();
?>