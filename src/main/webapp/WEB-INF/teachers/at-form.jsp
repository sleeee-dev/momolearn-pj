<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


        <!DOCTYPE html>
        <html lang="ko">

        <head>
            <meta charset="utf-8">
            <title>강사 신청서 작성</title>
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
                    <div class="display-3" style="font-size: 20px;">강사 신청 > 목록 > 신청서 작성</div>
                </div>
            </div>
        </div>
    </div>
	<!-- Header End -->

            <!-- Form Start -->
            <form id="sm" name="ta" method="post" onsubmit="return allCheck()"
                action="${pageContext.request.contextPath}/applyteacher/write">
                <div align="center">
                    <h2>강사 등록 신청서</h2>

                    <div style="color: red; size: 12px;">
                        * 강사 신청 후 강사 등록 승인은 한번만 가능합니다.
                    </div>

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


                                <tr class="tr">
                                    <td class="td" align="center" colspan="2">추가 입력 정보</td>
                                </tr>
                                <tr>
                                    <td>연락처</td>
                                    <td><input type="text" id="phonenum" name="phoneNum" size="30"
                                            placeholder="연락처를 입력해주세요."></td>
                                </tr>
                                <tr>
                                    <td>포트폴리오 url</td>
                                    <td><input type="text" id="url" name="pfLink" size="30"
                                            placeholder="포트폴리오url을 입력해주세요."></td>
                                </tr>
                                <tr>
                                    <td>희망분야</td>
                                    <td>
                                        <input type="text" id="hope" name="hopeField" size="30"
                                            placeholder="희망분야를 입력해주세요.">
                                    </td>
                                </tr>
                                <tr>
                                    <td>자기소개</td>
                                    <td><textarea id="intro" name="intro" cols="55" rows="5" maxlength="700"
                                            placeholder="자기소개를 입력해주세요."></textarea></td>
                                </tr>
                                <tr>
                                    <td align="center" colspan="2">
                                        <div class="custom-control custom-checkbox">
                                            <input type="checkbox" class="custom-control-input" id="aggrement" required>
                                            <label class="custom-control-label" for="aggrement">개인정보 수집 및 이용에
                                                동의합니다.</label><br>
                                        </div>

                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2" align="center">
                                        <input class="btn btn-primary" type="submit" value="신청하기">
                                        <input class="btn btn-primary" type="reset" value="다시입력" onclick="clear()">
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </form>

            <script>
                function allCheck() {
                    const phonenum = document.getElementById("phonenum").value;
                    const url = document.getElementById("url").value;
                    const hope = document.getElementById("hope").value;
                    const intro = document.getElementById("intro").value;

                    if (phonenum === "") {
                        alert("연락처를 입력해주세요.");
                        return false;
                    }
                    if (url === "") {
                        alert("포트폴리오 url을 입력해주세요.");
                        return false;
                    }
                    if (hope === "") {
                        alert("희망분야를 입력해주세요.");
                        return false;
                    }
                    if (intro === "") {
                        alert("자기소개를 입력해주세요.");
                        return false;
                    }
                    return true;
                }

                function clear() {
                    document.getElementById("sm").reset();
                }
            </script>
            <!-- Form End -->

            <!-- Back to Top -->
            <jsp:include page="/separate/script.jsp"></jsp:include>
            <jsp:include page="/separate/footer.jsp"></jsp:include>

        </body>

        </html>