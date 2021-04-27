layui.use(['layer','table','treeTable'], function () {
        var $ = layui.jquery;
        var table = layui.table;
        var treeTable = layui.treeTable;
        var layer = layui.layer;
        
        /*------------- 加载用户数据 --------------------------------*/
        var tableIns = treeTable.render({
            elem: '#CaiList',
            url : '/Employee/menuallservlet?action=menuseleall',
            toolbar: '#toolbarDemo',
            page : true,
            height: 'full-145',
            limit : 10,
            limits : [10,15,20,25],
            tree: {
                iconIndex: 2,           // 折叠图标显示在第几列
                isPidData: true,        // 是否是id、pid形式数据
                idName: 'id',  // id字段名称
                pidName: 'fatherid'     // pid字段名称
            },
            cols : [[
            	{fixed:"left",type: "checkbox", width:50},
                {field: 'id', title: '编号',  align:'center'},
                {field: 'menuname', title: '权限名称', minWidth:100, align:"center"},
                {field: 'fatherid', title: '父级id',  align:'center'},
                {field: 'type', title: '类型', align:'center'},
                {field: 'button', title: '触发按钮',  align:'center'}         
            ]]
        });
		//监听工具栏
		treeTable.on('toolbar(CaiList)', function(obj){
		    switch(obj.event){
		      case 'btn-expand':	//全部展开
		    	  insTb.expandAll('#demoTreeTb');
		      break;
		      
		      case 'btn-fold':	//全部折叠
		    	  insTb.foldAll('#demoTreeTb');
		      break;
		      
		      case 'addMenu':	//新增权限
		    	  layer.open({
		        		title : "添加权限",
		        		type : 2,
		        		content : "/Employee/menuAdd.jsp",
		        		area:['600px','600px'],
		    	  })
		      break;
		      
		      case 'upMenu':	//修改权限
					updataMenu();
		      break;
			        
		      case 'delMenu':	//删除权限
		    	  layer.confirm('确定删除此权限吗?', {icon: 3, title:'提示'}, function(index){
						delMenu();
						layer.close(index);
		            });
		      break;
		    };
		 });
		
	   //---------删除权限-------
		function delMenu(){
	    	var menuid = '';
	    	JSON.stringify(insTb.checkStatus().map(function (d) {
	    		menuid = d.authorityId;
	        }));
	    	if(menuid == null || menuid == ""){
	    		layer.msg("请选中一个节点进行删除");
	    	}else{
	    		$.ajax({
	    			url:"MenuServlet?action=delMenu",
	    			data:{"menuid":menuid},
	    			type:"post",
	    			dataType:"json",
	    			success:function(data){
	    				//var info = eval("("+data+")");
	    				if(data.status == 1){
	    					layer.msg("删除成功");
	    					insTb.reload();
	    				}else{
	    					layer.msg("删除失败");
	    				}
	    			}
	    		})
	    	}
	    }

       /*---------修改权限------*/
        function updataMenu(){
        	var authorityId =1;
        	/*JSON.stringify(insTb.checkStatus().map(function (d) {
				 authorityId = d.authorityId;
            }));*/
        	alert(1111)
        	if(authorityId == null || authorityId==""){
        		layer.msg('请选择一个进行修改');
        		alert(2222)
        	}else{
        		alert(333)
        		layer.open({
              		 type:2,
              		 title:"修改权限",
              		 area:['420px','420px'],
              		 content:"/menu/menuInfo.jsp",
              		 success:function(layero, index){
              			 $.post("MenuServlet?action=allMenuById",{"menuid":authorityId},function(data){
              				var info = eval('(' + data + ')')
              				var body = layui.layer.getChildFrame('body', index);
              				body.find("#mid").val(info.data.id);
              				body.find("#mname").val(info.data.mname);  //权限名
              				body.find("#mfunction").val(info.data.mfunction);	//请求路径
              				var select = 'dd[lay-value='+info.data.type+']';
              				body.find("#type2").siblings("div.layui-form-select").find('dl').find(select).click();	//菜单样式
              				body.find("#mbtn").val(info.data.mbtn);		//按钮代码
              				body.find("#icon").val(info.data.icon);		//icon图标
              				var menuid3 = info.data.mfatherid;
              				//上级的下拉框
                 			 $.post("MenuServlet?action=allMenuById",{"menuid":menuid3},function(res){
             					var cs = eval('(' + res + ')');
     		                    body.find("#fatherType2").val(cs.data.mname);
                 			 })
                 			 
              			 })
                	}
          	    })
        	}
        };
});