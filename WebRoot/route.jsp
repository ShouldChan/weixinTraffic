<%@ page language="java" pageEncoding="gb2312"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
   	<base href="<%=basePath%>">
   	<title>���е���</title>
   	<meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=0;">
   	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<style type="text/css">
		body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=gxaD8jNy91I3KZx3TsNFhnMRK1bHnoUc"></script>
</head>
<%
	String p1 = request.getParameter("p1");
	String p2 = request.getParameter("p2");
%>
<body>
   	<div id="allmap"></div>
</body>
<script type="text/javascript">
	// ������㡢�յ�ľ�γ�������
	var p1 = new BMap.Point(<%=p1%>);
	var p2 = new BMap.Point(<%=p2%>);
	
	// ������ͼ���������������Ĭ�����ż���
	var map = new BMap.Map("allmap");
	map.centerAndZoom(new BMap.Point((p1.lng+p2.lng)/2, (p1.lat+p2.lat)/2), 17);
	// ���½�������Ű�ť
	map.addControl(new BMap.NavigationControl({anchor: BMAP_ANCHOR_BOTTOM_RIGHT, type: BMAP_NAVIGATION_CONTROL_ZOOM}));
	
	// ���е�������
	var walking = new BMap.WalkingRoute(map, {renderOptions:{map: map, autoViewport: true}});
	walking.search(p1, p2);
</script>
</html>