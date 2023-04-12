<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


        <!DOCTYPE html>
        <html lang="ko">

        <head>
            <meta charset="utf-8">
            <title>강사 신청 목록</title>
            <meta content="width=device-width, initial-scale=1.0" name="viewport">
            <meta content="" name="keywords">
            <meta content="" name="description">

            <jsp:include page="/separate/head.jsp"></jsp:include>
            <link href="${pageContext.request.contextPath}/css/at-form.css" rel="stylesheet">
        </head>

        <body>
            <jsp:include page="/separate/header2.jsp"></jsp:include>
	<!-- Header Start -->
    <div class="container-fluid py-4">
        <div class="container">
            <div class="row justify-content">
                <div class="col-lg-10">
                    <div class="display-3" style="font-size: 20px;">강사 신청 > 목록</div>
                </div>
            </div>
        </div>
    </div>
	<!-- Header End -->

            <!-- 강사 신청 전체 리스트 Start -->
            <div class="container">
                <form method="get">
                    <div class="form-group">
                        <label>승인 상태 선택</label>
                        <select name="approve">
                            <option value="">전체</option>
                            <option value="true">승인완료</option>
                            <option value="false">대기중</option>
                        </select>
                        <button type="submit" class="btn btn-primary">검색</button>
                    </div>
                </form>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>신청번호</th>
                            <th>회원id</th>
                            <th>신청일</th>
                            <th>승인여부</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${not empty list}">
                                <c:forEach var="list" items="${list}">
                                    <c:if test="${empty param.approve or list.approve == param.approve}">
                                        <tr>
                                            <td>${list.id}</td>
                                            <td><a href="${pageContext.request.contextPath}/applyteacher/read/${list.id}">${list.membersMemId}</a></td>
                                            <!-- <td><a href="${pageContext.request.contextPath}/applyteacher/read/${list.id}">${list.membersMemId}</a></td> -->
                                            <td>${list.applyRege}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${list.approve}">
                                                        <span class="text-success">승인완료</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="text-danger">대기중</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </c:when>
                            <c:when test="${empty list}">
                                <tr>
                                    <td colspan="5" class="text-center">등록된 신청서가 없습니다.</td>
                                </tr>
                            </c:when>
                        </c:choose>
                    </tbody>
                </table>
            </div>
            <!--강사 신청 리스트 End -->

            <!-- Back to Top -->
            <jsp:include page="/separate/script.jsp"></jsp:include>
            <jsp:include page="/separate/footer.jsp"></jsp:include>



        </body>

        </html>