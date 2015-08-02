<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品管理</title>
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


<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/ueditor/ueditor.all.min.js">
	
</script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/ueditor/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript">
	var url;
	/* var ue = UE.getEditor('product_text'); */
	$(function() {

		$('#gategory_id').combobox({
			url : 'goodsGategory_search.do?drop=true',
			valueField : 'gategory_id',
			textField : 'name'
		});

	});

	/**
	 * 将数值四舍五入(保留2位小数)后格式化成金额形式
	 *
	 * @param num 数值(Number或者String)
	 * @return 金额格式的字符串,如'1,234,567.45'
	 * @type String
	 */
	function formatMonney(value) {

		return value;
	}

	function searchProduct() {
		$("#dg").datagrid('load', {
			"goods_name" : $("#s_productName").val()
		});

	}

	function formatProPic(val, row) {
		return "<img width=100 height=100 src='${pageContext.request.contextPath}/"+row.image_url+"'/>";
	}

	function formatSmallTypeId(val, row) {
		return row.smallType.id;
	}

	function formatSmallTypeName(val, row) {
		return row.smallType.name;
	}

	function formatBigTypeId(val, row) {
		return row.bigType.id;
	}

	function formatBigTypeName(val, row) {
		return row.bigType.name;
	}



	function openProductAddDialog() {
		$("#dlg").dialog("open").dialog("setTitle", "添加商品信息");
		$('#gategory_id').combobox('setValue', "1");
		url = "goods_save.do";
	}

	function openProductModifyDialog() {
		var selectedRows = $("#dg").datagrid('getSelections');
		if (selectedRows.length != 1) {
			$.messager.alert("系统提示", "请选择一条要编辑的数据！");
			return;
		}
		var row = selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle", "编辑商品信息");
		$("#goods_name").val(row.goods_name);
		$("#price").val(row.price);
		$("#original_price").val(row.original_price);
		$("#order_price").val(row.order_price);
		$("#inventory").val(row.inventory);
		$("#status").val(row.status);
		$("#introduce").val(row.introduce);
		$("#times").val(row.times);
		$("#goods_order").val(row.goods_order);
		$("#product_text").val(row.product_text);
		$("#goods_url").val(row.goods_url);
		$("#present_price").val(row.present_price);
		$("#goods_id").val(row.goods_id);
		$('#gategory_id').combobox('setValue', row.gategory_id);

		url = "goods_update.do";
	}

	function saveProduct() {
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
					$.messager.alert("系统提示", result[0].error);
					return;
				}
			}
		});

	}

	function resetValue() {
		$("#goods_name").val("");
		$("#price").val("");
		$("#original_price").val("");
		$("#order_price").val("");
		$("#inventory").val("");
		$("#status").val("");
		$("#introduce").val("");
		$("#times").val("");
		$("#goods_order").val("");
		$("#product_text").val("");
		$("#goods_url").val("");
		$("#present_price").val("");
		$("#goods_id").val("");
		$('#gategory_id').combobox('setValue', "");

	}

	function closeProductDialog() {
		$("#dlg").dialog("close");
		resetValue();
	}

	function deleteProduct() {
		var selectedRows = $("#dg").datagrid('getSelections');
		if (selectedRows.length == 0) {
			$.messager.alert("系统提示", "请选择要删除的数据！");
			return;
		}
		var strIds = [];
		for ( var i = 0; i < selectedRows.length; i++) {
			strIds.push(selectedRows[i].goods_id);
		}
		var ids = strIds.join(",");
		$.messager.confirm("系统提示", "您确认要删除这<font color=red>"
				+ selectedRows.length + "</font>条数据吗？", function(r) {
			if (r) {
				$.post("goods_delete.do", {
					ids : ids
				}, function(result) {
					if (result[0].success) {
						$.messager.alert("系统提示", "数据已成功删除！");
						$("#dg").datagrid("reload");
					} else {
						$.messager.alert("系统提示", "数据删除失败！" + result[0].error);
					}
				}, "json");
			}
		});
	}


</script>
</head>
<body style="margin: 1px;">
	<table id="dg" title="商品管理" class="easyui-datagrid" fitColumns="true"
		pagination="true" rownumbers="true" url="goods_search.do" fit="true"
		toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="goods_id" width="40" align="center">编号</th>
				<th field="goods_name" width="100" align="center">商品名称</th>
				<th field="price" width="40" align="center">价格</th>
				<th field="gategory_id" width="40" align="center">类别编号</th>
				<th field="original_price" width="40" align="center">原价</th>
				<th field="present_price" width="40" align="center">现价</th>
				<th field="order_price" width="40" align="center">预定价格</th>
				<th field="inventory" width="40" align="center">库存(kg)</th>
				<th field="status" width="40" align="center">状态</th>
				<th field="introduce" width="80" align="center">商品简介</th>
				<th field="product_text" width="150" align="center">商品详情</th>
				<th field="times" width="40" align="center">浏览次数</th>
				<th field="goods_order" width="30" align="center">排序号</th>
				<th field="goods_url" width="100" align="center"
					formatter="formatProPic">商品图片</th>
			</tr>
		</thead>
	</table>

	<div id="tb">
		<div>
			<a href="javascript:openProductAddDialog()" class="easyui-linkbutton"
				iconCls="icon-add" plain="true">添加</a> <a
				href="javascript:openProductModifyDialog()"
				class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> <a
				href="javascript:deleteProduct()" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>
			&nbsp;商品名称：&nbsp;<input type="text" id="s_productName" size="20"
				onkeydown="if(event.keyCode==13) searchProduct()" /> <a
				href="javascript:searchProduct()" class="easyui-linkbutton"
				iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 600px; height: 400px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post" enctype="multipart/form-data">
			<table cellspacing="5px">

				<tr>
					<td>商品名称：</td>
					<td colspan="2"><input type="text" id="goods_name"
						name="goods_name" class="easyui-validatebox" required="true"
						style="width: 300px" /></td>
					<td>商品类别：</td>
					<td><input id="gategory_id" name="gategory_id" required="true" /></td>
				</tr>
				<tr>
					<td>原价：</td>
					<td colspan="2"><input type="text" id="original_price"
						name="original_price" class="easyui-numberbox"
						data-options="min:0,precision:2" required="true" /></td>
					<td>现价：</td>
					<td colspan="2"><input type="text" id="present_price"
						name="present_price" class="easyui-numberbox"
						data-options="min:0,precision:2" required="true" /></td>
				</tr>
				<tr>
					<td>预定价格：</td>
					<td colspan="2"><input type="text" id="order_price"
						name="order_price" class="easyui-numberbox"
						data-options="min:0,precision:2" required="true" /></td>
					<td>库存：</td>
					<td colspan="2"><input type="text" id="inventory"
						name="inventory" class="easyui-numberbox"
						data-options="min:0,precision:2" required="true" /></td>
				</tr>
				<tr>
					<td>状态：</td>
					<td colspan="2"><input type="text" id="status" name="status"
						class="easyui-validatebox" required="true" /></td>
					<td>排序：</td>
					<td colspan="2"><input type="text" id="goods_order"
						name="goods_order" class="easyui-validatebox" required="true" /></td>
				</tr>
				<tr>
					<td>商品简介：</td>
					<td colspan="2"><input type="text" id="introduce"
						name="introduce" class="easyui-validatebox" required="true" /></td>
					<!-- 					<td>所属类别：</td>
					<td colspan="2"><input type="text" id="gategory_id"
						name="gategory_id" class="easyui-validatebox" required="true" />
					</td> -->
				</tr>
				<tr>
					<td>商品图片：</td>
					<td colspan="2"><input type="file" id="image_url"
						name="image_url" /></td>
				</tr>
				<tr>
					<td valign="top">商品详情：</td>
					<td colspan="4">
						<!-- <script id="product_text" name="product_text" type="text/plain" style="width:600px;height:400px;"></script>
					 --> <textarea id="product_text" name="product_text"></textarea> <!-- class="ckeditor" -->
						<input type="hidden" id="goods_id" name="goods_id" />
					</td>
				</tr>
			</table>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:saveProduct()" class="easyui-linkbutton"
			iconCls="icon-ok">保存</a> <a href="javascript:closeProductDialog()"
			class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>