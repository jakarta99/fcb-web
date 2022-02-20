<%@page import="java.util.ArrayList"%>
<%@page import="tw.com.fcb.sample.kai.web.FileSecurities"%>
<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!--%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" % -->
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="BIG5">
		<title>Hello! Servlet!</title>
	</head>
	<body>
		<table border=1 cellpadding=5>
			<tr>
				<th> StockOrder </th>
				<th> StockCode </th>
				<th> StockName </th>
				<th> StockTransaction </th>
				<th> EtfCode </th>
				<th> EtfName </th>
				<th> EtfTransaction </th>
				<th> Currency </th>
				<th> 修改 </th>
				<th> 刪除 </th>
			</tr>
			<!--
			<tr>
				<td>${stockOrder}</td>
				<td>${stockCode}</td>
				<td>${stockName}</td>
				<td>${stockTrans}</td>
				<td>${etfCode}</td>
				<td>${etfName}</td>
				<td>${etfTrans}</td>
				<td>${currCode}</td>
			</tr>
			-->
			
			<!-- Using Java Code To Display Data -->
			<%
			ArrayList<FileSecurities> lists = (ArrayList<FileSecurities>)request.getAttribute("listData");
			for(FileSecurities list : lists){
			%>
				<tr>
					<td><%=list.getSecuritiesOrder()%></td>
					<td><%=list.getStockCode()%></td>
					<td><%=list.getStockName()%></td>
					<td><%=list.getStockTransaction()%></td>
					<td><%=list.getEtfCode()%></td>
					<td><%=list.getEtfName()%></td>
					<td><%=list.getEtfTransaction() %></td>
					<td><%=list.getCurrencyEnum() %></td>
					<td><a href=""> update </td>
					<td><a href=""> delete </td>
				</tr>
			<% } %>
			
			<!-- Test forEach Fail...QQ -->
			<!--
			<tr>
				<c:forEach items="${listData}" var="data" varStatus="loop">
				<td><c:out value="${data.stockOrder}"></c:out></td>
				<td><c:out value="${data.stockCode}"></c:out></td>
				<td><c:out value="${data.stockName}"></c:out></td>
				<td><c:out value="${data.stockTrans}"></c:out></td>
				<td><c:out value="${data.etfCode}"></c:out></td>
				<td><c:out value="${data.etfName}"></c:out></td>
				<td><c:out value="${data.etfTrans}"></c:out></td>
				<td><c:out value="${data.currCode}"></c:out></td>
				</c:forEach>
			</tr>
			-->
			
		</table>
		<h3>總共有 <%=lists.size()%> 筆資料</h3>
	</body>
</html>