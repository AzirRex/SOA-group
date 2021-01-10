
//http://localhost:8083/
    // 提交表单
    function valueByName(search,name) {
        var start=search.indexOf(name+"=");
        if(start==-1)
        return null;
        else
        {
            var end=search.indexOf("&",start);
            if(end==-1)
            end=search.length;
        }

        var str=search.substring(start,end);
        var arr=str.split("=");
        return arr[1];
    }

    function queryServer(){
      
        // var product_id=valueByName(location.search,"product_id");
        // alert(product_id)
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/lostandfound",
            async:true,
            dataType: 'json', 
            success: function (jsonData) {
                console.log(jsonData);
                var container = document.getElementById('show');
                var span = document.createElement('div');
                for(var i=0;i<jsonData.length;i++)
                {
                container.innerHTML+='<div class="col-sm-6 col-md-4 col-lg-3">'
                +'<article class="product wow fadeInRight" data-wow-delay=".3s">'
                    +'<div class="product-body">'
                       +'<div class="product-figure"><img src="img/shop-1.png" alt=""'
                        +'        width="165" height="120" /> </div>'
                        +'<h5 class="product-title"><a href="single-lost.html">'+ jsonData[i].name +'</a></h5>'

                        +'<div class="product-price-wrap">'
                          +  '<div class="product-price">位置：'+jsonData[i].location+'</div>'
                        +'</div></div>'
                    +'<div class="product-button-wrap">'
                        +'<div class="product-button"><a'
                          +'      class="button button-gray-14 button-zakaria fl-bigmug-line-search74"href="single-lost.html?lost_ID='+jsonData[i].lost_ID+'">查看</a></div>'
                        +'<div class="product-button"><a class="button button-primary-2 button-zakaria fl-bigmug-line-shopping202"'
                               +' href="single-lost.html?lost_ID='+jsonData[i].lost_ID+'">查看</a></div></div></article></div>' 

                }
                //data recor
            },
            error:function()
            {
                //alert("请求失败");
            }
        });
        // $.ajax({
        //     type: "get",
        //     url: "http://localhost:8080/account/1",    //向后端请求数据的url
        //     success: function (data) {
        //      alert(data);
        //     },
        //     error: function()
        //     {
        //         alert("失败");
        //     }
        // });
    }

    function delete_info(id)
    {
        if(!id)
        {
            alert('Error！');
            return false;
        }
        // var form_data = new Array();

        $.ajax(
                {
                    url: "action/user_action.php",
                    data:{"id":id, "act":"del"},
                    type: "post",
                    beforeSend:function()
                    {
                        $("#tip").html("<span style='color:blue'>正在处理...</span>");
                        return true;
                    },
                    success:function(data)
                    {
                        if(data > 0)
                        {
                            alert('操作成功');
                            $("#tip").html("<span style='color:blueviolet'>恭喜，删除成功！</span>");

                            // document.location.href='world_system_notice.php'
                             location.reload();
                        }
                        else
                        {
                            $("#tip").html("<span style='color:red'>失败，请重试</span>");
                            alert('操作失败');
                        }
                    },
                    error:function()
                    {
                        alert('请求出错');
                    },
                    complete:function()
                    {
                        // $('#tips').hide();
                    }
                });

        return false;
    }

    // 编辑表单
    function get_edit_info(user_id)
    {
        if(!user_id)
        {
            alert('Error！');
            return false;
        }
        // var form_data = new Array();

        $.ajax(
                {
                    url: "action/user_action.php",
                    data:{"user_id":user_id, "act":"get"},
                    type: "post",
                    beforeSend:function()
                    {
                        // $("#tip").html("<span style='color:blue'>正在处理...</span>");
                        return true;
                    },
                    success:function(data)
                    {
                        if(data)
                        {

                            // 解析json数据
                            var data = data;

                            var data_obj = eval("("+data+")");

                            // 赋值
                            $("#user_id").val(data_obj.user_id);
                           
                            $("#name").val(data_obj.name);
                            $("#address").val(data_obj.address);
                            $("#remark").val(data_obj.remark);
                            $("#act").val("edit");

                            // 将input元素设置为readonly
                            $('#user_id').attr("readonly","readonly")
                           // location.reload();
                        }
                        else
                        {
                            $("#tip").html("<span style='color:red'>失败，请重试</span>");
                          //  alert('操作失败');
                        }
                    },
                    error:function()
                    {
                        alert('请求出错');
                    },
                    complete:function()
                    {
                        // $('#tips').hide();
                    }
                });

        return false;
    }

    // 提交表单
    function check_form()
    {
        var user_id = $.trim($('#user_id').val());
        var user_name     = $.trim($('#user_name').val());
        var address     = $.trim($('#address').val());
        var getTime=$.trim($('#getTime').val());
        var remark=$.trim($('#remark').val());
        //alert(user_id);
        

        if(!user_id)
        {
            alert('用户ID不能为空！');
            return false;
        }
        if(!user_name)
        {
            alert('物体名称不能为空！');
            return false;
        }
        if(!address)
        {
            alert('拾取地点不能为空！');
            return false;
        }
          
           console.log(getTime);
           console.log(remark);

        
        $.ajax(
                {
                    //http://localhost:8080/addlost?user_id=1&user_name=123&address=456&getTime=123&remark=123&type=0
                    url: "http://localhost:8080/addlost",
                    data:{"user_id":user_id,"name":user_name,"address":address,"getTime":getTime,
                    "information":remark,"type":0},
                    type: "GET",
                    beforeSend:function()
                    {
                        $("#tip").html("<span style='color:blue'>正在处理...</span>");
                        return true;
                    },
                    success:function(data)
                    {
                            $("#tip").html("<span style='color:blueviolet'>恭喜成功！</span>");
                            // document.location.href='system_notice.php'
                            alert( "添加成功！");
                            location.reload();
                      
                    },
                    error:function()
                    {
                        alert('请求出错');
                    },
                    complete:function()
                    {
                        $('#acting_tips').hide();
                    }
                });

        return false;
    }

    $(function () { $('#addUserModal').on('hide.bs.modal', function () {
        // 关闭时清空edit状态为add
        $("#act").val("add");
        location.reload();
    })
    });

    function MyFound()
    {
        window.location.href="DataTables.html?user_id=1"
    }
