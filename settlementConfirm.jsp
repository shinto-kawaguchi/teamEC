<%@ page language = "java" contentType = "text/html; charset = UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags" %>

<!DOCTYPE html>

<html>

<head>

	<meta charset = "UTF-8"/>

	<link rel = "stylesheet" href = "css/style_main.css">
	<link rel = "stylesheet" href = "css/style_confirm.css">

	<title>決済確認画面</title>

</head>

<body>

	<jsp:include page = "header.jsp"/>

	<script src = "./js/button.js"></script>

	<h1>決済確認画面</h1>

	<s:if test = "destinationInfoList == null || destinationInfoList.size() == 0">

		<div class = "message">宛先情報がありません。</div>

		<form method = "post">
			<div id = "buttonBox">
				<input type = "button" value = "新規宛先登録" onclick = "formSubmit(this, 'CreateDestinationAction')"/>
			</div>
		</form>

	</s:if>

	<s:else>

		<div class = "message">宛先情報を選択してください。</div>

		<form method = "post">

			<table>

				<tr id = "destination">
					<th id = "size1"><label>#</label></th>
					<th id = "size2"><label>姓</label></th>
					<th id = "size2"><label>名</label></th>
					<th id = "size3"><label>ふりがな</label></th>
					<th id = "size3"><label>住所</label></th>
					<th id = "size4"><label>電話番号</label></th>
					<th id = "size3"><label>メールアドレス</label></th>
				</tr>

				<s:iterator value = "destinationInfoList" status = "st">
					<tr>
						<td id = "radiocenter">
							<!--画面遷移時に常に１番上の宛先情報を選択した状態にする。-->
							<s:if test = "#st.index == 0">
								<input type = "radio" name = "id" checked = "checked" value = "<s:property value = 'id'/>"/>
							</s:if>
							<s:else>
								<input type = "radio" name = "id" value = "<s:property value = 'id'/>"/>
							</s:else>
						</td>
						<td><s:property value = "familyName"/></td>
						<td><s:property value = "firstName"/></td>
						<td><s:property value = "familyNameKana"/><span>　</span><s:property value = "firstNameKana"/></td>
						<td><s:property value = "userAddress"/></td>
						<td><s:property value = "telNumber"/></td>
						<td><s:property value = "email"/></td>
					</tr>
				</s:iterator>

			</table>

			<div id = "buttonArea">
				<div id = "topButton"><input type = "button" value = "決済" onclick = "formSubmit(this, 'SettlementCompleteAction')" /></div>
				<div id = "middleButton"><input type = "button" value = "削除" onclick = "formSubmit(this, 'DeleteDestinationAction')" /></div>
				<div id = "bottomButton"><input type = "button" value = "新規宛先登録" onclick = "formSubmit(this, 'CreateDestinationAction')" /></div>
			</div>

		</form>

	</s:else>

</body>

</html>
