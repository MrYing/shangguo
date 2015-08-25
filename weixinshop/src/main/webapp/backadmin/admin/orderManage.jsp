<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<OBJECT ID="jatoolsPrinter"
	CLASSID="CLSID:B43D3361-D075-4BE2-87FE-057188254255"
	codebase="jatoolsPrinter.cab#version=5,7,0,0"></OBJECT>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/easyui/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	function searchOrder() {
		$("#dg").datagrid('load', {
			"order_id" : $("#order_id").val(),
			"user_id" : $("#user_id").val()
		});
	}

	function formatStatus(val, row) {
		if (val == 1) {
			return "待审核";
		} else if (val == 2) {
			return "审核通过";
		} else if (val == 3) {
			return "卖家已发货";
		} else if (val == 4) {
			return "交易已完成";
		}
		return "";
	}

	function openOrderDetailDialog() {
		/* var url="order_findListByOrderId.do" */
		var selectedRows = $("#dg").datagrid('getSelections');
		if (selectedRows.length != 1) {
			$.messager.alert("系统提示", "请选择一条要查看的数据！");
			return;
		}
		var row = selectedRows[0];
		$("#dg2").datagrid('load', {
			/* url : url, */
			"order_id" : row.order_id
		});
		$("#orderId").val(row.order_id);
		$("#user").val(row.user_id);
		$("#cost").val(row.monny + "(元)");
		var v = row.order_status;
		if (v == 1) {
			$("#status").val("待审核");
		} else if (v == 2) {
			$("#status").val("审核通过");
		} else if (v == 3) {
			$("#status").val("卖家已发货");
		} else if (v == 4) {
			$("#status").val("交易已完成");
		}
		$("#dlg").dialog("open").dialog("setTitle", "订单详情");
	}

	function closeOrderDetailDialog() {
		$("#dlg").dialog("close");
	}

	function modifyOrderStatus(status) {
		var selectedRows = $("#dg").datagrid('getSelections');
		if (selectedRows.length == 0) {
			$.messager.alert("系统提示", "请选择要处理的数据！");
			return;
		}
		var orderNosStr = [];
		for ( var i = 0; i < selectedRows.length; i++) {
			orderNosStr.push(selectedRows[i].order_id);
		}
		var orderNos = orderNosStr.join(",");
		$.messager.confirm("系统提示", "您确认要处理这<font color=red>"
				+ selectedRows.length + "</font>条数据吗？", function(r) {
			if (r) {
				$.post("order_modifyOrderStatus.do", {
					orderNos : orderNos,
					status : status
				}, function(results) {
					if (results[0].success) {
						$.messager.alert("系统提示", "数据已成功处理！");
						$("#dg").datagrid("reload");
					} else {
						$.messager.alert("系统提示", "数据处理失败！");
					}
				}, "json");
			}
		});
	}

	function test() {
		var selectedRows = $("#dg").datagrid('getSelections');
		if (selectedRows.length == 0) {
			$.messager.alert("系统提示", "请选择要打印的数据！");
			return;
		}
		var strIds = [];
		for ( var i = 0; i < selectedRows.length; i++) {
			strIds.push(selectedRows[i].order_id);
		}
		var ids = strIds.join(",");
		$.messager.confirm("系统提示", "您确认要打印这<font color=red>"
				+ selectedRows.length + "</font>条数据吗？", function(r) {
			if (r) {
				$.post("order_print.do", {
					ids : ids
				}, function(result) {
					$("#print_table").html("");
					for ( var obj in result) {

						var html = "<tr><td>" + "名称" + "</td><td>" + "单价"
								+ "</td><td>" + "数量" + "</td><td>" + "小计"
								+ "</td></tr>";
						var rowObject = result[obj].list;
						for ( var row in rowObject) {

							var tr = "<tr><td>" + rowObject[row].good_name
									+ "</td><td>"
									+ rowObject[row].present_price
									+ "</td><td>" + rowObject[row].buy_count
									+ "</td><td>" + rowObject[row].money
									+ "</td></tr>"								
									;
							html += tr;

						}
						html +="<tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/>";
						html +="<tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/>";
						$("#print_table").append(html);
					}

					doPrint();
					/* 					$("#print_table").html("");
					 var html = "<tr><td>" + "名称" + "</td><td>" + "单价"
					 + "</td><td>" + "数量" + "</td><td>" + "小计"
					 + "</td></tr>";
					 for ( var row in result.rows) {

					 var tr = "<tr><td>" + result.rows[row].good_name
					 + "</td><td>" + result.rows[row].present_price
					 + "</td><td>" + result.rows[row].buy_count
					 + "</td><td>" + result.rows[row].money
					 + "</td></tr>";
					 html += tr;

					 }
					 $("#print_table").append(html);
					 doPrint(); */
				}, "json");
			}
		});
	}

	function doPrint() {
		myDoc = {
			documents : document, // 要打印的div 对象在本文档中，控件将从本文档中的 id 为 'page1' 的div对象，
			// 作为首页打印id 为'page2'的作为第二页打印            
			copyrights : '杰创软件拥有版权  www.jatools.com' // 版权声明,必须   
		};
		jatoolsPrinter.print(myDoc, false); // 直接打印，不弹出打印机设置对话框 
	}
</script>
</head>




<body style="margin: 1px;">

	<div id='page1'
		style='background: #ffffff; margin: 10; width: 270; height: 450; float: left'>
		<table id="print_table">
		</table>
	</div>
	<div id='page2'
		style='background: #ffffff; margin: 10; width: 270; height: 450; float: left'>发票2
		金额:100</div>

	<input type="button" value="打印" onClick='test()'>

	<table id="dg" title="订单管理" class="easyui-datagrid" fitColumns="true"
		pagination="true" rownumbers="true" url="order_search.do" fit="true"
		toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="order_id" width="50" align="center">订单编号</th>
				<th field="user_id" width="50" align="center">用户编号</th>
				<th field="order_status" width="50" align="center"
					formatter="formatStatus">订单状态</th>
				<th field="order_time" width="50" align="center">下单时间</th>
				<th field="pay_type" width="50" align="center">支付方式</th>
				<th field="carry_type" width="50" align="center">送货方式</th>
				<th field="address" width="50" align="center">送货地址</th>
				<th field="phone" width="50" align="center">联系电话</th>
				<th field="receiver" width="50" align="center">收货人</th>
				<th field="delivery_time" width="50" align="center">delivery_time</th>
				<th field="monny" width="50" align="center">订单总额</th>
				<th field="activity_type" width="50" align="center">优惠方式</th>
				<th field="remark" width="50" align="center">备注</th>

			</tr>
		</thead>
	</table>

	<div id="tb">
		<div>
			<a href="javascript:openOrderDetailDialog()"
				class="easyui-linkbutton" iconCls="icon-detail" plain="true">查看订单详情</a>
			<a href="javascript:modifyOrderStatus(2)" class="easyui-linkbutton"
				iconCls="icon-shenhe" plain="true">审核通过</a> <a
				href="javascript:modifyOrderStatus(3)" class="easyui-linkbutton"
				iconCls="icon-fahuo" plain="true">卖家已发货</a> <a
				href="javascript:dataPrint()" class="easyui-linkbutton"
				iconCls="icon-fahuo" plain="true">打印小票</a>
		</div>
		<div>
			&nbsp;订单号：&nbsp;<input type="text" id="order_id" size="20" />
			&nbsp;用户编号：&nbsp;<input type="text" id="user_id" size="20" /> <a
				href="javascript:searchOrder()" class="easyui-linkbutton"
				iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 750px; height: 400px; padding: 10px 30px" closed="true"
		buttons="#dlg-buttons">
		<table cellspacing="8px">
			<tr>
				<td>订单号：</td>
				<td><input type="text" id="orderId" readonly="readonly" /></td>
				<td>&nbsp;</td>
				<td>订单人：</td>
				<td><input type="text" id="user" readonly="readonly" /></td>
			</tr>
			<tr>
				<td>总金额：</td>
				<td><input type="text" id="cost" readonly="readonly" /></td>
				<td>&nbsp;</td>
				<td>订单状态：</td>
				<td><input type="text" id="status" readonly="readonly" /></td>
			</tr>
		</table>
		<br />
		<table id="dg2" title="订单商品列表" class="easyui-datagrid"
			fitColumns="true" rownumbers="true" url="order_findListByOrderId.do"
			fit="true">
			<thead>
				<tr>
					<th field="order_id" width="100" align="center">订单编号</th>
					<th field="good_id" width="100" align="center">商品编号</th>
					<th field="good_name" width="100" align="center">商品名称</th>
					<th field="original_price" width="100" align="center">商品原价</th>
					<th field="present_price" width="100" align="center">商品现价</th>
					<th field="order_price" width="100" align="center">商品预定价</th>
					<th field="buy_count" width="100" align="center">购买数量</th>
					<th field="money" width="100" align="center">小计金额</th>
				</tr>
			</thead>
		</table>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:closeOrderDetailDialog()"
			class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>