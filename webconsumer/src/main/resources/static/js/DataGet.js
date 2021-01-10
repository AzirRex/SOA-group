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

function dataGet(){
    console.log(111);
     $()
    }
//    $.ajax({
//        type: "GET",
//        url: "http://localhost:8080/lostandfound/"+lost_id,
//        async:true,
//        dataType: 'json', 
//        success: function (jsonData) {
//            console.log(jsonData);
           
//            var container = document.getElementById('name');
//            container.innerHTML=jsonData.name;
//            container = document.getElementById('user_id');
//            container.innerHTML=jsonData.userId;
//            container = document.getElementById('location');
//            container.innerHTML=jsonData.location;
//            container=document.getElementById('time');
//            container.innerHTML=jsonData.time;
//            container=document.getElementById('information');
//            container.innerHTML=jsonData.information;
//        },
//        error:function()
//        {
//            alert("请求失败");
//        }
//    });

/*获取表格中某行的学生信息*/
