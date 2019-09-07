<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css?family=Pacifico&display=swap" rel="stylesheet">
    <link href="../css/header.css" rel="stylesheet">
    <link href="../css/home.css" rel="stylesheet">
    <script src="https://use.fontawesome.com/releases/v5.10.2/js/all.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    
    <title>INBYUL</title>
</head>
<script>

</script>
<body>
    <header>
    <div class="f_bar">
        <div class="ff_bar">
        <div class="s_bar">
            <div class="t_bar">
                <div class="logo">
                    <a href="#" style="margin:0px;">Inbyul</a>
                </div>
            </div>
            <div class="t_bar">
                <input placeholder="검색"/>
            </div>
            <div class="t_bar">
                <a href="#">
                    <i class="far fa-compass fa-2x"></i>
                </a>
                <a href="#">
                    <i class="far fa-heart fa-2x"></i>
                </a>
                <a href="#">
                    <i class="far fa-user fa-2x"></i>
                </a>
                
            </div>

        </div>
    </div>
    </div>
    </header>

    <section class="m_container">
        <main> 
            <section class="m_1" >
            </section>
                
            <aside class="m_1">
                <div class="m_2">   
                    <div class="info_f">
                        <div class="info_s">
                            <img src="../image/profile.jpg" style="width: 50px; height:50px;" >
                        </div>
                        <div class="info_s">
                            <div class="info_t">
                                <div>
                                    <span><c:out value="${customUser.uid}"/></span>
                                </div>
                                <div>
                                    <span style="font-size:13px; color:#b0b0b0"><c:out value="${customUser.uname}"/></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <a href="write.html" style="text-decoration: none;">
                    <button type="button" class="btn btn-block" style="background-color:#3897f0;color:white;" >
                        글쓰기
                    </button>
                    </a>
                </div>
            </aside>
            


        </main>
    
    </section>

    <div class="fixed-write">
            <a href="write.html" style="text-decoration: none; cursor:pointer;"><i class="fas fa-plus-circle fa-4x"></i></a>
    </div>

</body>
</html>