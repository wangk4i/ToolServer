layui.define(function(exports){

    layui.use(['laydate','table','element','hdk','form','tool','appconfig'], function() {
        var $ = layui.$
            , laydate = layui.laydate
            , router = layui.router()
            , table = layui.table
            , setter = layui.setter
            , form = layui.form
            , element = layui.element
            , laytpl = layui.laytpl
            , hdk = layui.hdk
            , tool = layui.tool
            , admin = layui.admin
            , params = {}
            , headers = {}
            , userInfo = {}
            , thisTab = 0
            , appconfig = layui.appconfig
        ;
        form.render();

        //消息概况查询
        form.on('submit(track_msgOverview_query)', function (input) {
            $.ajax({
                url: appconfig.MsgMonitor.GetMsgOverview,
                // data: JSON.stringify(InputobjPatInfo),
                dataType: 'json', //服务器返回json格式数据
                type: 'get', //HTTP请求类型
                headers: {
                    'Content-Type': 'application/json'
                },
                success: function (Data) {
                    if (Data.code=='1'){
                        var getTpl = MsgOverviewTpl.innerHTML
                            ,view = document.getElementById('track_msgOverview_view');
                        laytpl(getTpl).render(Data, function(html){
                            view.innerHTML = html;
                        });

                    } else {
                        layer.msg('请求失败: ' + Data.message);
                    }

                },
                error: function (xhr, type, errorThrown) {
                    console.log("错误");
                    console.log(errorThrown);
                }
            })

            return false;


        });

        //消息文件查询
        form.on('submit(track_msgLocation_query)', function (input) {
            $.ajax({
                url: appconfig.MsgMonitor.GetMsgLocation,
                data: JSON.stringify({
                    MsgId: input.field.track_msgId
                }),
                dataType: 'json', //服务器返回json格式数据
                type: 'post', //HTTP请求类型
                headers: {
                    'Content-Type': 'application/json'
                },
                success: function (Data) {
                    if (Data.code=='1'){
                        var getTpl = MsgLocationTpl.innerHTML
                            ,view = document.getElementById('track_msgLocation_view');
                        laytpl(getTpl).render(Data, function(html){
                            view.innerHTML = html;
                        });

                    } else {
                        layer.msg('请求失败: ' + Data.message);
                    }

                },
                error: function (xhr, type, errorThrown) {
                    console.log("错误");
                    console.log(errorThrown);
                }
            })

            return false;


        });







    });
    exports('work/msgtrackmonitor', {})
});