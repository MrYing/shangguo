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
	var url;

	function searchGoodsGategory() {
		$("#dg").datagrid('load', {
			"name" : $("#name").val()
		});
	}

	function openGoodsGategoryAddDialog() {
		$("#dlg").dialog("open").dialog("setTitle", "添加类别信息");
		url = "goodsGategory_save.do";
	}

	function saveGoodsGategory() {
		$("#fm").form("submit", {
			url : url,
			onSubmit : function() {
				return $(this).form("validate");
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result[0].success) {
					$.messager.alert("系统提示", "保存成功");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				} else {
					$.messager.alert("系统提示", "保存失败,原因：" + result[0].error);
					return;
				}
			}
		});
	}

	function openGoodsGategoryModifyDialog() {
		var selectedRows = $("#dg").datagrid('getSelections');
		if (selectedRows.length != 1) {
			$.messager.alert("系统提示", "请选择一条要编辑的数据！");
			return;
		}
		var row = selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle", "编辑类别信息");
		$("#gategory_id").val(row.gategory_id);
		$("#name").val(row.name);
		$("#introduce").val(row.introduce);
		url = "goodsGategory_update.do";
	}

	function resetValue() {
		$("#name").val("");
		$("#introduce").val("");
	}

	function closeGoodsGategoryDialog() {
		$("#dlg").dialog("close");
		resetValue();
	}

	function deleteGoodsGategory() {
		var selectedRows = $("#dg").datagrid('getSelections');
		if (selectedRows.length == 0) {
			$.messager.alert("系统提示", "请选择要删除的数据！");
			return;
		}
		var strIds = [];
		for ( var i = 0; i < selectedRows.length; i++) {
			strIds.push(selectedRows[i].gategory_id);
		}
		var ids = strIds.join(",");
		$.messager.confirm("系统提示", "您确认要删除这<font color=red>"
				+ selectedRows.length + "</font>条数据吗？", function(r) {
			if (r) {
				$.post("goodsGategory_delete.do", {
					ids : ids
				}, function(result) {
					if (result[0].exist) {
						$.messager.alert("系统提示", "编号为" + result[0].existId
								+ "的类别存在对应的商品，不能删除");

					} else if (result[0].success) {
						$.messager.alert("系统提示", "删除成功！");
						$("#dg").datagrid("reload");
					} else {
						$.messager.alert("系统提示", "数据删除失败！");
					}
				}, "json");
			}
		});

	}
</script>
</head>
<body style="margin: 1px;">
	<table id="dg" title="商品大类管理" class="easyui-datagrid" fitColumns="true"
		pagination="true" rownumbers="true" url="goodsGategory_search.do"
		fit="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="gategory_id" width="50" align="center">编号</th>
				<th field="name" width="100" align="center">类别名称</th>
				<th field="introduce" width="200" align="center">简介</th>
			</tr>
		</thead>
	</table>

	<div id="tb">
		<div>
			<a href="javascript:openGoodsGategoryAddDialog()"
				class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a> <a
				href="javascript:openGoodsGategoryModifyDialog()"
				class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> <a
				href="javascript:deleteGoodsGategory()" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>
			&nbsp;类别名称：&nbsp;<input type="text" id="s_name" size="20"
				onkeydown="if(event.keyCode==13) searchGoodsGategory()" /> <a
				href="javascript:searchGoodsGategory()" class="easyui-linkbutton"
				iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 600px; height: 300px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table cellspacing="8px">
				<tr>
					<td>类别名称：</td>
					<td colspan="3"><input type="text" id="name" name="name"
						class="easyui-validatebox" required="true" /></td>
				</tr>
				<tr>
					<td valign="top">简介：</td>
					<td colspan="3"><textarea rows="7" cols="50" id="introduce"
							name="introduce"></textarea><input type="hidden" id="gategory_id"
						name="gategory_id" /></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:saveGoodsGategory()" class="easyui-linkbutton"
			iconCls="icon-ok">保存</a> <a
			href="javascript:closeGoodsGategoryDialog()"
			class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>