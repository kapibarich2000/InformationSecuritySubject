<?php
    require_once 'connect.php';
    session_start();
    
    // trim - убирает пробелы спереди и с конца
    // htmlspecialchars - обезвреживает(добавл. спец. символы) кавычки и теги (< >) (при этом они остаются видны)
    $full_name=htmlspecialchars(trim($_POST["full_name"]));
    $email=htmlspecialchars(trim($_POST["email"])); 
    $password=htmlspecialchars(trim($_POST["password"]));
    $password_confirm=htmlspecialchars(trim($_POST["password_confirm"]));

    // strip_tags - убирает теги (< >) strip_tags($_POST["full_name"],FILTER_SANITIZE_EMAIL)

    // ------  ПОДГОТОВЛЕННЫЙ ЗАПРОС В PDO СДЕЛАЕТ САМ ЭКРАНИРОВАНИЕ  -----------
    //addslashes - экранирует строку (добавляет слеш ко всем кавычкам) 
    //$full_name=addslashes($full_name);
    // $email=addslashes($email); 
    // $password=addslashes($password);
    // $password_confirm=addslashes($password_confirm);

    // Проверка длины
    if(strlen($full_name)<2||strlen($full_name)>95){
        $_SESSION["message"]="Недопустимая длина имени";
        header('Location: ../register.php');
        exit();
    }
    if(strlen($password)<6||strlen($password)>60){
        $_SESSION["message"]="Недопустимая длина пароля";
        header('Location: ../register.php');
        exit();
    }
    
    if($password!=$password_confirm){ 
        $_SESSION["message"]="Пароли не совпадают";
        header('Location: ../register.php'); 
        exit();   
    }
    // Проверяем, не сущестует ли пользователя с таким email
    $queryCheckEmail=$db->prepare("SELECT `id` FROM `users` WHERE email=:email");
    $params=['email'=>$email];
    $queryCheckEmail->execute($params);
    $result=$queryCheckEmail->fetchAll();
    if(!empty($result[0][0])){
        $_SESSION["message"]="Пользователь с таким email уже зарегистрирован";
        header('Location: ../register.php');
        exit();
    }
    // Проверить тип картинки
    $allowedTypes = array(IMAGETYPE_PNG, IMAGETYPE_JPEG);
    $detectedType = exif_imagetype($_FILES['avatar']['tmp_name']);
    $errorImageType=!in_array($detectedType, $allowedTypes);
    // Проверить размер картинки
    $errorImageWeight=$_FILES['avatar']['size'] > 2097152;
    // Попробуем загрузить
    // $path='uploads/'.time().$_FILES['avatar']['name'];
    $path='uploads/'.time()."UserAvatar.jpg"; 
    $errorImageDonload=!move_uploaded_file($_FILES['avatar']['tmp_name'],'../'.$path);
    if($errorImageDonload||$errorImageType||$errorImageWeight){
        $_SESSION["message"]="Ошибка при загрузке изображения";
        header('Location: ../register.php');
        exit();    
    }
    $password=password_hash($password,PASSWORD_DEFAULT);// Хешируем пароль
    $insertNewUser=$db->prepare("INSERT INTO `users` (`full_name`, `email`, `password`, `avatar`) 
    VALUES (:full_name, :email, :password, :avatar)");
    $params = ['full_name'=>$full_name,'email'=>$email,'password'=>$password,'avatar'=>$path];
    $insertNewUser->execute($params);
    $_SESSION["message"]="Регистрация прошла успешно";
    header('Location: ../index.php');
?>

