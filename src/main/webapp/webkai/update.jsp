<%@page import="java.util.ArrayList"%>
<%@page import="tw.com.fcb.sample.kai.web.FileSecurities"%>
<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!--%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" % -->
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="BIG5">
		<title>Servlet - Update</title>
		<%@ include file="style.jsp" %>
	</head>
	<body>
		<a href="index">首頁</a>
		<table class="table table-bordered table-striped table-hover table-condensed">
			<tr>
				<th> StockOrder </th>
				<th> StockCode </th>
				<th> StockName </th>
				<th> StockTransaction </th>
				<th> EtfCode </th>
				<th> EtfName </th>
				<th> EtfTransaction </th>
				<th> Currency </th>
			</tr>
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
		</table>
		<button type="button" class="btn btn-default btn-lg">
			<span class="glyphicon glyphicon-star" aria-hidden="true">修改</span>
		</button>
	</body>
</html>