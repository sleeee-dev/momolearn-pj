<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="utf-8">
	<meta content="width=device-width, initial-scale=1.0" name="viewport">
	<meta content="" name="keywords">
	<meta content="" name="description">
	
	<title>프로필 정보</title>
	
	<jsp:include page="/separate/head.jsp"></jsp:include>
</head>

<style>
.a {
	margin-right: 100px;
}

.table-hover tbody tr:hover {
	background-color: #EBFBFF;
}
</style>

<body>
	<jsp:include page="/separate/header2.jsp"></jsp:include>

	<!-- Header Start -->
    <div class="container-fluid py-4">
        <div class="container">
            <div class="row justify-content">
                <div class="col-lg-10">
                    <div class="display-3" style="font-size: 20px;">내 정보</div>
                </div>
            </div>
        </div>
    </div>
	<!-- Header End -->

	<!-- Info Start -->
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-8">
				<div class="card">
				
					<div class="card-header bg-primary">
						<h5 class="text-white font-weight-bold">${members.name}님의 정보</h5>
					</div>

					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-hover">
								<tbody>
									<tr>
										<th>아이디</th>
										<td>${members.memId}</td>
									</tr>
									
									<tr>
										<th>이름</th>
										<td>${members.name}</td>
									</tr>
									
									<tr>
										<th>이메일</th>
										<td>${members.email}</td>
									</tr>
									
									<tr>
										<th>등급</th>
										<td>${members.grade}</td>
									</tr>
									
									<tr>
										<th>프로필사진</th>
										<td>${members.profile}</td>
									</tr>
									
								</tbody>
							</table>
						</div>

						<div class="form-group row mb-0">
							<div class="col-md-6 offset-md-4">
								<button class="btn btn-primary"
									onclick="location.href='${pageContext.request.contextPath}/member/updatepage?memId=${members.memId}'">
									수정하기</button>
									
								<c:if test="${members.grade ne 'admin'}">
									<button class="btn btn-danger" id="delete_btn"
										onclick='confirmDelete("${pageContext.request.contextPath}/member/delete/${members.memId}")'>
										탈퇴하기</button>
								</c:if>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Info End -->
	
	<script type="text/javascript">
	
		function confirmDelete(url) {
			
			if (confirm('탈퇴하시겠습니까?')) {
				
				location.href = url;
			}
		}
	</script>

	<!-- Back to Top -->
	<jsp:include page="/separate/script.jsp"></jsp:include>
	<jsp:include page="/separate/footer.jsp"></jsp:include>
	
</body>
</html>
