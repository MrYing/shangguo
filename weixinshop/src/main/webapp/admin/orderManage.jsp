<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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

	function formatUserId(val, row) {
		return row.user.id;
	}

	function formatUserName(val, row) {
		return row.user.userName;
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
		var selectedRows = $("#dg").datagrid('getSelections');
		if (selectedRows.length != 1) {
			$.messager.alert("系统提示", "请选择一条要查看的数据！");
			return;
		}
		var row = selectedRows[0];
		$("#dg2").datagrid('load', {
			"id" : row.id
		});
		$("#orderNo").val(row.orderNo);
		$("#user").val(row.user.userName + "(ID:" + row.user.id + ")");
		$("#cost").val(row.cost + "(元)");
		var v = row.status;
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
			orderNosStr.push(selectedRows[i].orderNo);
		}
		var orderNos = orderNosStr.join(",");
		$.messager.confirm("系统提示", "您确认要处理这<font color=red>"
				+ selectedRows.length + "</font>条数据吗？", function(r) {
			if (r) {
				$.post("order_modifyOrderStatus.action", {
					orderNos : orderNos,
					status : status
				}, function(result) {
					if (result.success) {
						$.messager.alert("系统提示", "数据已成功处理！");
						$("#dg").datagrid("reload");
					} else {
						$.messager.alert("系统提示", "数据处理失败！");
					}
				}, "json");
			}
		});
	}
</script>
</head>
<body style="margin: 1px;">
	<table id="dg" title="订单管理" class="easyui-datagrid" fitColumns="true"
		pagination="true" rownumbers="true" url="order_search.do" fit="true"
		toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="order_id" width="50" align="center">订单编号</th>
				<th field="user_id" width="50" align="center">用户编号</th>
				<th field="order_status" width="50" align="center">订单状态</th>
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
				iconCls="icon-fahuo" plain="true">卖家已发货</a>
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
				<td><input type="text" id="orderNo" readonly="readonly" /></td>
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
					<th field="cb" checkbox="true" align="center"></th>
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