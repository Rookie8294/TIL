<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="http://localhost/html_prj/common/CSS/main_v20230906.css" />
<style type="text/css">
	#wrap{ width:502px; height:303px; margin:0px auto;}
#idDup{width:502px; height:303px; background: #FFFFFF}
#idDiv{ position: absolute; top:100px; left:80px; width:300px; }
</style>
<script type="text/javascript">
	window.onload= function(){
		document.getElementById("btn").addEventListener("click", sendId);

		alert("부모창의 ID : " + opener.window.document.frm.id.value );
	}//onload

function sendId(){
	var id = document.subFrm.id.value;
	opener.window.document.frm.id.value = id;
	self.close();
}
</script>

</head>
<body>
	<div id="wrap">
		<div id="idDup">
		<div id="idDiv">
			<form name="subFrm">
			<div>
				<label>아이디</label>
				<input type="text" name="id" class="inputBox" style="width:160px;"
				maxlength="16" autofocus="autofocus" value=" ${ param.id }"/>
				<%
				String id = request.getParameter("id");
				%>
				<input type="text" name="id" class="inputBox" style="width:160px;"
				maxlength="16" autofocus="autofocus" value="<%=id %>"/>
				
				<input type="text" name="id" class="inputBox" style="width:160px;"
				maxlength="16" autofocus="autofocus" value=" ${ param.id }"/>
				<input type="button" value="중복확인" class="btn" id="btn"/>
			</div>
		</form>
		</div>
		</div>
	</div>
</body>
</html>
