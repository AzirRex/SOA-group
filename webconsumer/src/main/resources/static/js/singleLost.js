var lost_ID;

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

function queryLost(){
  
     var lost_id=valueByName(location.search,"lost_ID");
     lost_ID=lost_id;
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/lostandfound/"+lost_id,
        async:true,
        dataType: 'json', 
        success: function (jsonData) {
            console.log(jsonData);
            
            var container = document.getElementById('name');
            container.innerHTML=jsonData.name;
            container = document.getElementById('user_id');
            container.innerHTML=jsonData.userId;
            container = document.getElementById('location');
            container.innerHTML=jsonData.location;
            container=document.getElementById('time');
            container.innerHTML=jsonData.time;
            container=document.getElementById('information');
            container.innerHTML=jsonData.information;
            $.ajax({
                type: "GET",
                url: "http://localhost:8080/account/"+jsonData.userId,
                async:true,
              
                success: function (jsonData) {
                    console.log(jsonData);
                    
                    var container=document.getElementById('LostUser');
                    container.innerHTML+=jsonData.name;
                    var container=document.getElementById('introduction');
                    container.innerHTML=jsonData.introduction;
                    
                    
                },
                error:function()
                {
                    alert("三次请求失败");
                }
            });
            
        },
        error:function()
        {
            alert("请求失败");
        }
    });

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/getReviews",
        async:true,
        data:{ "lost_id":lost_id },
        success: function (jsonData) {
            console.log(jsonData);
            
            for(var i=0;i<jsonData.length;i++)
                {
                    var container = document.getElementById('reviews');
                    container.innerHTML+='<div class="box-comment">'+
                    '<div class="unit flex-column flex-sm-row unit-spacing-md">'+
                      '<div class="unit-left"><a class="box-comment-figure" href="#"><img src="imges/头像.jpg" alt="" width="119" height="119"/></a></div>'+
                      '<div class="unit-body"><div class="group-sm group-justify"><div>'+
                            '<div class="group-xs group-middle">'+
                              '<h5 class="box-comment-author"><a href="#">'+jsonData[i].userName+'</a></h5>'
                              +'<div class="box-rating"><span class="icon mdi mdi-star"></span><span class="icon mdi mdi-star"></span><span class="icon mdi mdi-star"></span><span class="icon mdi mdi-star"></span><span class="icon mdi mdi-star-half"></span></div>'
                            +'</div></div><div class="box-comment-time">'+
                            '<time datetime="2019-11-30">'+jsonData[i].time+'</time></div></div>'+
                        '<p class="box-comment-text">'+jsonData[i].information+'</p></div></div></div><br><hr>';
                        document.getElementById('Reviews-contact-message').value="";
                }
          
            
        },
        error:function()
        {
            alert("二次请求失败");
        }
    });
    
}

function reviewsPublish()
{
    if(lost_ID!=null)
    var myDate=new Date();


    var time="111111";
    
    var message=document.getElementById('Reviews-contact-message').value; 
    var userName="Unknown Users";
    //alert(time+message+userName);
    if(message==null)
    {
        alert("评论不能为空");
    return false;
    }
    
    //http://localhost:8080/addReviews?userID=1&time=2020-11-29%2017:20:15&information=%E6%9D%A5%E5%88%B0%E4%BD%9C%E7%94%A8&userName=Unknown%20Users&lost_ID=1
    $.ajax({
        type: "GET",
        url: "http://47.115.178.7:8889/addReviews",
        data:{ "userID":1,"time":time,"information":message,"userName":userName,
        "lost_ID":lost_ID },
        async:true, 
        success: function (jsonData) {
            console.log(jsonData);
            var container = document.getElementById('reviews');
            container.innerHTML+='<div class="box-comment">'+
            '<div class="unit flex-column flex-sm-row unit-spacing-md">'+
              '<div class="unit-left"><a class="box-comment-figure" href="#"><img src="imges/头像.jpg" alt="" width="119" height="119"/></a></div>'+
              '<div class="unit-body"><div class="group-sm group-justify"><div>'+
                    '<div class="group-xs group-middle">'+
                      '<h5 class="box-comment-author"><a href="#">'+userName+'</a></h5>'
                      +'<div class="box-rating"><span class="icon mdi mdi-star"></span><span class="icon mdi mdi-star"></span><span class="icon mdi mdi-star"></span><span class="icon mdi mdi-star"></span><span class="icon mdi mdi-star-half"></span></div>'
                    +'</div></div><div class="box-comment-time">'+
                    '<time datetime="2019-11-30">'+time+'</time></div></div>'+
                '<p class="box-comment-text">'+message+'</p></div></div></div><br><hr>';
                document.getElementById('Reviews-contact-message').value="";
        },
        error:function()
        {
            alert("请求失败");
        }
    });
}
// document.getElementById("applySubmit").onclick = function() {
//     // 设置在此处单击#applySubmit时要发生的事件
//     alert("OK");

//    };

function apply()
{
   
    var name = document.getElementById('contact-first-name-2').value;
    var QQ = document.getElementById('contact-QQ').value;
    var e_mail = document.getElementById('contact-email-2').value;
    var phone = document.getElementById('contact-phone-2').value;
    var message = document.getElementById('apply-message-2').value;
    // var QQ = $.trim($('#contact-QQ').val());
    // var e_mail = $.trim($('#contact-email-2').val());
    // var phone = $.trim($('#contact-phone-2').val());
    // var message=$.trim($('#pply-message-2').val());
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/addApply",
        data:{"name":name,"e_mail":e_mail,"QQ":QQ,
        "phone":phone,"message":message,"user_id":1 },
        async:true, 
        success: function (jsonData) {
            console.log(jsonData);
            alert("申请发送成功，请随时注意查收回复");

        },
        error:function()
        {
            alert("请求失败");
        }
    });
}