function computingTime(usertime,currentTime){
    var usertime=new Date(usertime).getTime();
    var currentTime=currentTime.getTime();
    var date3=currentTime-usertime;
    var days=Math.floor(date3/(24*3600*1000))
	var leave1=date3%(24*3600*1000)//计算天数后剩余的毫秒数
    var hours=Math.floor(leave1/(3600*1000))
    //计算相差分钟数
    var leave2=leave1%(3600*1000)        //计算小时数后剩余的毫秒数
    var minutes=Math.floor(leave2/(60*1000))
        //计算相差秒数
    var leave3=leave2%(60*1000)      //计算分钟数后剩余的毫秒数
    var seconds=Math.round(leave3/1000)
    //alert(" 相差 "+days+"天 "+hours+"小时 "+minutes+" 分钟"+seconds+" 秒");
    seconds=hours*60*60+minutes*60+seconds;
    //alert(seconds);

    if(seconds>appconfig.overtime){
        //登录过期锁
        layui.data('health', {
            key: 'islogin'
            , value: false
        });

    } else {
       localStorage.setItem("usertime", new Date());
    }
    if (!layui.data('health').islogin) {
        layer.msg('该账户登录时间已过期，请重新登录！', {
            icon: 5,
            time: 2000,
            shade: [0.8, '#393D49']
        }, function () {
            //do something
            localStorage.setItem("usertime", new Date());
            window.parent.parent.parent.parent.location.href = "/tool/";
        });
    }
}


function MonitoringPage(){
  //监听页面是否发生操作
    document.body.onmousedown = function () {
      var currentTime = new Date();
      var usertime = localStorage.getItem("usertime");
      computingTime(usertime,currentTime);
    }
};

if (layui.data('health').islogin) {	localStorage.setItem("usertime", new Date());}
MonitoringPage();



