<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


        <!DOCTYPE html>
        <html lang="ko">

        <head>
            <meta charset="utf-8">
            <title>강사 신청서 상세 조회</title>
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
                            <div class="display-3" style="font-size: 20px;">강사 신청 > 신청서 상세</div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Header End -->

            <!-- Form Start -->
            <div align="center">
                <h2>강사 등록 신청서</h2>
                <div>
                    <div class="col-sm-5">

                        <table class="table table-bordered">

                            <tr class="tr">
                                <td class="td" align="center" colspan="2">회원 기본 정보</td>
                            </tr>
                            <tr>
                                <td>아이디</td>
                                <td><input type="text" id="id" name="id" disabled value="${member.memId}"></td>
                            </tr>
                            <tr>
                                <td>이름</td>
                                <td><input type="text" id="name" name="name" disabled value="${member.name}"></td>
                            </tr>
                            <tr>
                                <td>메일주소</td>
                                <td><input type="text" id="email" name="email" size="30" disabled
                                        value="${member.email}"></td>
                            </tr>
                            <tr>
                                <td>승인상태</td>
                                <td>
                                    <input type="text" id="approve" name="approve" size="30" disabled
                                        value="${apply.approve == 'true' ? '승인완료' : '대기중'}">
                                </td>
                            </tr>

                            <tr class="tr">
                                <td class="td" align="center" colspan="2">추가 입력 정보</td>
                            </tr>
                            <tr>
                                <td>연락처</td>
                                <td><input type="text" id="phonenum" name="phonenum" size="30" disabled
                                        value="${apply.phoneNum}"></td>
                            </tr>
                            <tr>
                                <td>포트폴리오 url</td>
                                <td><input type="text" id="url" name="url" size="30" disabled value="${apply.pfLink}">
                                </td>
                            </tr>
                            <tr>
                                <td>희망분야</td>
                                <td>
                                    <input type="text" id="hope" name="hope" size="30" disabled
                                        value="${apply.hopeField}">
                                </td>
                            </tr>
                            <tr>
                                <td>자기소개</td>
                                <td><textarea id="intro" name="intro" cols="55" rows="5" maxlength="700"
                                        disabled>${apply.intro}</textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center">
                                    <div class="col-sm-5 button-container"
                                        style="display: flex; justify-content: center; gap: 20px;">
                                        <c:choose>
                                            <c:when test="${members.grade == 'admin' && apply.approve == 'false'}">
                                                <form id="sm" name="ta" method="post"
                                                    action="${pageContext.request.contextPath}/applyteacher/approve/${apply.id}">
                                                    <input class="btn btn-primary" type="submit" value="승인"
                                                        id="approveButton"
                                                        onclick="return confirm('강사신청서를 승인하시겠습니까?');">
                                                </form>
                                                <button type="button" class="btn btn-primary mr-2"
                                                    onclick="location.href='${pageContext.request.contextPath}/applyteacher/applylist'">목록</button>
                                            </c:when>
                                            <c:when test="${members.grade == 'admin' && apply.approve == 'true'}">
                                                <form id="sm" name="ta" method="post"
                                                    action="${pageContext.request.contextPath}/applyteacher/approve/${apply.id}">
                                                    <input class="btn btn-secondary" type="submit" value="승인" disabled>
                                                </form>
                                                <button type="button" class="btn btn-primary mr-2"
                                                    onclick="location.href='${pageContext.request.contextPath}/applyteacher/applylist'">목록</button>
                                            </c:when>
                                            <c:when test="${members.grade == 'student'}">
                                                <form id="sm" name="ta" method="get"
                                                    action="${pageContext.request.contextPath}/applyteacher/updateform">
                                                    <input class="btn btn-primary" type="submit" value="수정">
                                                </form>
                                                <form id="sm" name="ta" method="post"
                                                    action="${pageContext.request.contextPath}/applyteacher/delete/${apply.id}">
                                                    <input class="btn btn-primary" type="submit" value="삭제"
                                                        onclick="return confirm('강사신청서를 삭제하시겠습니까?');">
                                                </form>
                                                <button type="button" class="btn btn-primary mr-2"
                                                    onclick="location.href='${pageContext.request.contextPath}/applyteacher/myapplylist'">목록</button>
                                            </c:when>
                                        </c:choose>
                                    </div>

                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            <!-- Form End -->

            <script>
                const approveButton = document.getElementById("approveButton");
                if (approveButton) {
                    approveButton.disabled = false;
                }
            </script>

            <!-- Back to Top -->
            <jsp:include page="/separate/script.jsp"></jsp:include>
            <jsp:include page="/separate/footer.jsp"></jsp:include>

        </body>

        </html>