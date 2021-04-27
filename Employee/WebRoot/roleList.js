layui.extend({
	dtree : '{/}admin/js/lay-module/layui_ext/dtree/dtree' // {/}的意思即代表采用自有路径，即不跟随 base 路径
}).use([ 'form', 'layer', 'laydate', 'table', 'upload', 'dtree' ], function() {
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : top.layer,
		$ = layui.jquery,
		laydate = layui.laydate,
		upload = layui.upload,
		table = layui.table;
	var dtree = layui.dtree,
		layer = layui.layer,
		$ = layui.jquery;

	//表格渲染
	var tableIns = table.render({
		elem : '#roleList',
		url : '/Employee/roleallservler?action=roleseleall',
		cellMinWidth : 95,
		page : false,
		toolbar : '#toolbarDemo',
		height : "full-125",
		limit : 20,
		limits : [ 10, 15, 20, 25 ],
		cols : [ [
			{
				type : "checkbox",
				fixed : "left",
				width : 50
			},
			{
				field : 'rolename',
				title : '角色名',
				width : 150,
				align : "center"
			},
			{
				field : 'roleid',
				title : '编号',
				align : 'center'
			},
			
			{
				field : 'loginname',
				title : '登录名',
				width : 150,
				align : "center"
			},
			{
				field : 'username',
				title : '员工名',
				width : 150,
				align : "center"
			},
			{
				field : 'age',
				title : '年龄',
				width : 150,
				align : "center"
			},
			{
				field : 'phone',
				title : '手机号',
				width : 150,
				align : "center"
			},

		] ],
	done : function(res, curr, count) {
		merge(res);
	}
	});
	function merge(res) {
		var data = res.data;
		var mergeIndex = 0; //定位需要添加合并属性的行数
		var mark = 1; //这里涉及到简单的运算，mark是计算每次需要合并的格子数
		var columsName = [ 'roleid', 'loginname','username','age','phone']; //需要合并的列名称s
		var columsIndex = [ 1, 2 ]; //需要合并的列索引值

		for (var k = 0; k < columsName.length; k++) { //这里循环所有要合并的列
			var trArr = $(".layui-table-body>.layui-table").find("tr"); //所有行
			for (var i = 1; i < res.data.length; i++) { //这里循环表格当前的数据
				var tdCurArr = trArr.eq(i).find("td").eq(columsIndex[k]); //获取当前行的当前列
				var tdPreArr = trArr.eq(mergeIndex).find("td").eq(columsIndex[k]); //获取相同列的第一列

				if (data[i][columsName[k]] === data[i - 1][columsName[k]]) { //后一行的值与前一行的值做比较，相同就需要合并
					mark += 1;
					tdPreArr.each(function() { //相同列的第一列增加rowspan属性
						$(this).attr("rowspan", mark);
					});
					tdCurArr.each(function() { //当前行隐藏
						$(this).css("display", "none");
					});
				} else {
					mergeIndex = i;
					mark = 1; //一旦前后两行的值不一样了，那么需要合并的格子数mark就需要重新计算
				}
			}
			mergeIndex = 0;
			mark = 1;
		}
	}
	//工具栏事件
	table.on('toolbar(roleList)', function(obj) {
		var checkStatus = table.checkStatus(obj.config.id);
		var data = checkStatus.data;
		var roleid = '';
		for (i = 0; i < data.length; i++) {
			roleid = data[i].id;
		}
		switch (obj.event) {
		case 'delRole': //删除角色
			if (data.length != 1) {
				layer.msg("请选择一行数据进行操作")
				return false;
			}
			layer.confirm('删除角色后用户对应的权限也会删除,确定删除吗?', {
				icon : 3,
				title : '提示'
			}, function(index) {
				delRole(roleid);
				layer.close(index);
			});
			break;

		case 'hairMenu':	//分配权限
			if(data.length == 0 || data.length > 1){
				layer.msg("请选择一行数据进行操作")
				return ;
			}else{
				hairMenu(roleid);
			}
      break;

		case 'addRole': //新增角色
			addRole();
			break;

		case 'upRoleMenu': //修改角色权限
			if (data.length == 0 || data.length > 1) {
				layer.msg("请选择一行数据进行操作")
				return;
			} else {
				upRoleMenu(roleid);
			}
			break;
		}
		;
	});

	//删除角色
	function delRole(roleid) {
		$.ajax({
			url : "/MenuTest/RoleServlet?action=delRole",
			type : "post",
			data : {
				"roleid" : roleid
			},
			success : function(data) {
				if (data == 1) {
					layer.msg("删除成功");
					tableIns.reload("#newsList");
				}
			}
		})
	}

	//修改角色
	function upRole(roleid) {
		layui.layer.open({
			title : "修改角色",
			type : 2,
			content : "admin/page/system/role/roleInfo.jsp",
			area : [ '350px', '230px' ],
			success : function(layero, index) {
				$.ajax({
					url : "/MenuTest/RoleServlet?action=allRoleByRoleid",
					type : "post",
					data : {
						"roleid" : roleid
					},
					success : function(data) {
						var info = eval("(" + data + ")")
						var body = layui.layer.getChildFrame('body', index);
						body.find("#roleid").val(info.id);
						body.find("#rname2").val(info.rname);
						body.find("#rname").val(info.rname);
					}
				})
			/*        			//获取新窗口对象
			                    var iframeWindow = layero.find('iframe')[0].contentWindow;
			                    //重新渲染
			                    iframeWindow.layui.form.render();*/
			}
		});
	}

	//新增角色
	function addRole() {
		layui.layer.open({
			title : "新增角色",
			type : 2,
			content : "admin/page/system/role/roleAdd.jsp",
			area : [ '350px', '200px' ],
		});
	}

	//修改角色权限
	 function hairMenu(roleid){
	    	layui.layer.open({
	    		title : "分配权限",
	    		type : 1,
	    		content : $('#dtree1'),
	    		area:['300px','500px'],
	    		success:function(){
	    		    //给dtree树加载数据
	    			dtree.render({
					  elem: "#dataTree3",
					  url: "/Employee/QuanXian?action=allMenu",
					  dataStyle: "layuiStyle",  //使用layui风格的数据格式
					  dataFormat: "list",  //配置data的风格为list
					  response:{message:"msg",statusCode:0},  //修改response中返回数据的定义
					  checkbar:true,
					  line: true,  // 显示树线
					  done: function(res, $ul, first){
						  $.ajax({
							  url:"/Employee/QuanXianId?action=allMenuid",
							  type:"post",
							  data:{"id":roleid},
							  success:function(res){
								  var cs = eval('(' + res + ')');
								  $.each(cs,function(index,row){
									dtree.chooseDataInit("dataTree3",[row.id]); // 初始化选中
								  })
							  }
						  })
	  		    	  }
	    			});
	    		},
	    		btn:['分配权限'],
	    		yes: function(index, layero){
	    			var params = dtree.getCheckbarNodesParam("dataTree3");
	    			var infos = JSON.stringify(params);
	    			var cs = eval('(' + infos + ')');
	    			var menuidList = new Array();	//所有选中值的权限id
	    			//alert(menuidList.length);
	    			$.each(cs,function(index,row){
						menuidList[index] = row.nodeId;
	    			})
	    			$.ajax({
	    				url:"/Employee/FenPei",
	    				data:{"array":menuidList,"userid":roleid},
	    				type:"post",
	    				traditional:true,
	    				success:function(data){
	    					var demo = eval('(' + data + ')');
	    					if(demo.status == 1){
	    						layer.msg(demo.message);
	    						layer.close(index)	//关闭
	    					}else{
	    						layer.msg("分配失败");
	    					}
	    				}
	    			})
	    		}
	    	})
	    }

})