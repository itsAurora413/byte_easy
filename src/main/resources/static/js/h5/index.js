function show (){

    $.ajax({
        url:"/like",
        type:"post",
        dataType:"json",
        data:{
            "title":title,
            "forum":forum,
        },
        success:function(data){
            console.log(data);
            if(data.code==200){
                var html="";
                html+="<li>";
                for (let student of data.data){
                    html += "<h1>" + forum.title + "</h1>";
                    html += "<p>" + forum.forum + "</p>";
                    html += "<div><img src=''>" + "12" + "<img src=''>" +  "4"  + "</div>";
                }
                html+="</li>";
                $("#show-student").html(html);
            }

        },
        error:function(a,b,c){
            console.log("异常了");
            console.log(a);
            console.log(b);
            console.log(c);
        }
    });

}
