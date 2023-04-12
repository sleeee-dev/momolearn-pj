<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수강바구니</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">
<style>
.custom-table {
  background-color: #dcdcdc;
  border-radius: 10px;
}
</style>

 <!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
 <!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>

<jsp:include page="/separate/head.jsp"></jsp:include>

</head>
<body>
	<jsp:include page="/separate/header.jsp"></jsp:include>

	<div id="searchList">
	    <!-- Header Start -->
	    <div class="container-fluid py-4">
	        <div class="container">
	            <div class="row justify-content">
	                <div class="col-lg-10">
	                    <div class="display-3" style="font-size: 20px;">수강 바구니</div>
	                </div>
	            </div>
	        </div>
	    </div>
		<!-- Header End -->
		<!-- 장바구니 Start -->
		<div class="container">
			<table class="table custom-table">
				<thead>
					<tr>
						<th scope="col"><input type="checkbox" id="checkAll"></th>
						<th scope="col"><h5>강의명</h5></th>
						<th scope="col"><h5>가격</h5></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="cart" items="${cart}">
						<tr>
							<td scope="row"><input type="checkbox" id="check"></td>
							<td scope="row" id="title" class="text-secondary font-weight-bold">${cart.lecture.title}</td>
							<td scope="row" id="price" class="text-secondary font-weight-bold">${cart.lecture.price}원</td>
						</tr>
					</c:forEach>
					<c:if test="${empty cart}">
						<tr><td colspan="3" class="text-center"><h4 class="text-secondary" style="font-style: italic;">장바구니가 비어있습니다.</h4></td></tr>
					</c:if>
					<tr><td></td></tr>
					<tr>
						<td colspan="2" style="text-align: left;"><h6>구매 강의: <span id="checkedTitles" class="text-primary"></span></h6></td>
						<td colspan="1" style="text-align: right;"><h6>총 합계: <span id="totalPrice" class="text-primary">0</span>원</h6></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td><button id="delete" class="btn btn-danger" onclick="deleteCart()">삭제하기</button></td>
						<td></td>
						<td style="text-align: right;"><button id="payment" class="btn btn-primary mr-3" onclick="requestPay()">결제하기</button></td>
					</tr>
				</tfoot>
			</table>
		</div>
		<!-- 장바구니 End -->
		<!-- 구매자 정보 hidden -->
		<input type="hidden" id="id" value="${members.memId}">
		<input type="hidden" id="name" value="${members.name}">
	</div>

	<script>
	
	  let checkAll = document.querySelector("#checkAll");
	  let checkList = document.querySelectorAll("#check");
	  let totalPrice = document.querySelector("#totalPrice");
	  
	  function updateTotalPrice() {
	    let sum = 0;
	    let checkedTitles = [];
	    
	    document.querySelectorAll("input#check:checked").forEach((item) => {
	    	
	      sum += parseInt(item.parentNode.parentNode.querySelector("#price").innerText.replace(",", "").replace("원", ""));
	      checkedTitles.push(item.parentNode.parentNode.querySelector("#title").innerText);
	      
	    });
	    
	    totalPrice.innerText = sum.toLocaleString();
	    document.querySelector("#checkedTitles").innerText = checkedTitles.length > 0 ? checkedTitles[0] + " 외 " + (checkedTitles.length - 1) + "개" : "";
	
	  }
	
	  checkAll.addEventListener("click", () => {
		  
	    checkList.forEach((item) => {
	    	
	      item.checked = checkAll.checked;
	      
	    });
	    
	    updateTotalPrice();
	    
	  });
	
	  checkList.forEach((item) => {
		  
	    item.addEventListener("click", () => {
	    	
	      checkAll.checked = [...checkList].every((item) => item.checked);
	      updateTotalPrice();
	      
	    });
	    
	  });
	</script>


	<jsp:include page="/separate/script.jsp"></jsp:include>
	<jsp:include page="/separate/footer.jsp"></jsp:include>

    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/payment.js"></script>
</body>
</body>
</html>