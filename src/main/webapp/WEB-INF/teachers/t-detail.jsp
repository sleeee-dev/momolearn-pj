<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="utf-8">
            <title>강사 프로필</title>
            <meta content="width=device-width, initial-scale=1.0" name="viewport">
            <meta content="" name="keywords">
            <meta content="" name="description">

            <jsp:include page="/separate/head.jsp"></jsp:include>
        </head>

        <body>
            <jsp:include page="/separate/header2.jsp"></jsp:include>

            <!-- Header Start -->
            <div class="container-fluid py-4">
                <div class="container">
                    <div class="row justify-content">
                        <div class="col-lg-10">
                            <div class="display-3" style="font-size: 20px;">강사 프로필</div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Header End -->

            <!-- Team Start -->
            <div class="container-fluid">
                ${result}
                <div class="row">
                    <!-- 사이드바 -->
                    <nav class="col-md-3 d-none d-md-block bg-light sidebar">
                        <div class="sidebar-sticky">
                            <h5
                                class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                                <span id="teacher-name">${result.name}</span>
                            </h5>
                            <ul class="nav flex-column mb-2">
                                <li class="nav-item">
                                    <img src="/../../momolearn/img/profile/test01.jpg" class="rounded-circle mx-auto d-block my-3" style="width: 80%; height: auto;">
                                </li>
                            </ul>

                            <ul class="nav flex-column mb-2">
                                <li class="nav-item">
                                  <button class="btn btn-link" type="button" id="menu1">프로필</button>
                                </li>
                                <li class="nav-item">
                                  <button class="btn btn-link" type="button" id="menu2">개설강의</button>
                                </li>
                              </ul>
                            
                        </div>
                    </nav>

                    <!-- 메인 컨텐츠 -->
                    <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
                        <div id="content"></div>
                    </main>

                </div>
            </div>

            <!-- Team End -->


            <!-- Back to Top -->
            <jsp:include page="/separate/script.jsp"></jsp:include>
            <jsp:include page="/separate/footer.jsp"></jsp:include>
            <script src="${pageContext.request.contextPath}/js/t-detail.js"></script>
        </body>

        </html>