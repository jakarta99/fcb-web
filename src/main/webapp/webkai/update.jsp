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
		
		<form action="update" method="POST">
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
				<td><input type="text" name=stockOrder value=${stockOrder}></td>
				<td><input type="text" name=stockCode value=${stockCode}></td>
				<td><input type="text" name=stockName value=${stockName}></td>
				<td><input type="text" name=stockTrans value=${stockTrans}></td>
				<td><input type="text" name=etfCode value=${etfCode}></td>
				<td><input type="text" name=etfName value=${etfName}></td>
				<td><input type="text" name=etfTrans value=${etfTrans}></td>
				<td><input type="text" name=currCode value=${currCode}></td>
			</tr>
		</table>
		<input type="submit" value="修改" name="send" class="btn btn-default btn-lg"/>
		</form>
		
	</body>
</html>