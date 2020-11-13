layui.define(function(exports){
    layui.use(['laydate','table','element','hdk','form','tool','appconfig'], function() {
        let MsgTxtDetail;
        let MsgXmlDetail;
        let MsgJsonDetail;
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
        element.render();

        //患者消息跟踪
        $('.health-query .multi-checkbox div').on('click',function () {  //,hdk.config.activeTab
            $(this).siblings().removeClass("hyd-selected");
            $(this).toggleClass("hyd-selected");
        });


        //查询历史消息
        form.on('submit(history_msg_query)', function (input) {
            var checkMsgType = $('div[hyd-name="history_msg_type"]>div.hyd-selected>span',hdk.config.activeTab).attr("hyd-value");

            hdk.ajax({
                api: appconfig.HistoryMsg.HistoricalMsgQuery,
                data: JSON.stringify({
                    MsgType: checkMsgType,
                    MsgID: input.field.history_msg_id
                })
            }, function (result) {
                if (result.code==1){
                    var getTpl = historymsgtpl.innerHTML
                        ,view = document.getElementById('history_msg_view');
                    laytpl(getTpl).render(result, function(html){
                        view.innerHTML = html;
                    });
                } else {
                    layer.msg('请求失败: ' + result.message);
                }
            });
            return false;
        });

        //查看消息详情

        window.onMsgDetails = function (obj, fileId, islocal) {

            getMsgFileDetails(fileId, islocal);

            layer.open({
                type: 1,
                // shade: false,
                id:'history_msg_file_details',
                area: ['72%', '718px'],
                // resize: false,
                title: '消息详情',
                content: $("#historymsgdetailtpl").html(),
                success: function (layero ,index) {

                    document.getElementById("MsgJsonFileDetail").innerText = MsgJsonDetail;
                    document.getElementById("MsgXmlFileDetail").innerText = MsgXmlDetail;
                    document.getElementById("MsgTxtFileDetail").innerText = MsgTxtDetail;


                    /*var thiss=$("#history_msg_file_details",layero);
                    $('.health-query .multi-checkbox div',thiss).on('click',function () {
                        $(this).siblings().removeClass("hyd-selected");
                        $(this).toggleClass("hyd-selected");

                        var checkMsgFileType = $('div[hyd-name="history_msg_file_type"]>div.hyd-selected>span',thiss).attr("hyd-value");
                        switch (checkMsgFileType) {
                            case "1":
                                document.getElementById("filedetail_box").innerText = MsgJsonDetail;
                                break;
                            case "2":
                                document.getElementById("filedetail_box").innerText = MsgXmlDetail;
                                break;
                            case "3":
                                document.getElementById("filedetail_box").innerText = MsgTxtDetail;
                                break;
                            default:
                                break;
                        }
                    });*/
                }

            })

        }

        //获取静态资源文件
        MsgJsonDetail = '';
        MsgXmlDetail = '';
        MsgTxtDetail = '';
        function getMsgFileDetails(fileId, islocal) {
            $.ajax({
                url: appconfig.HistoryMsg.HistoricalMsgFileGet,
                data: JSON.stringify({
                    MsgFileID: fileId,
                    islocal: islocal
                }),
                dataType: 'json', //服务器返回json格式数据
                type: 'post', //HTTP请求类型
                headers: {
                    'Content-Type': 'application/json'
                },
                async:false,
                success: function (Data) {

                    if (Data.code=='1'){
                        MsgJsonDetail = Data.data.msgJson;
                        MsgXmlDetail = Data.data.msgXml
                        MsgTxtDetail = Data.data.msgTxt;
                    } else {
                        layer.msg('请求失败: ' + Data.message);
                    }

                },
                error: function (xhr, type, errorThrown) {
                    console.log("错误");
                    console.log(errorThrown);
                }
            })
        }

        //格式化显示xml
        var parse_xml = function(content) {
            var xml_doc = null;
            try {
                xml_doc = (new DOMParser()).parseFromString(content.replace(/[\n\r]/g, ""), 'text/xml');
            } catch (e) {
                return false;
            }
            var flag=0
            function build_xml(index, list, element) {
                var t = []
                for (var i = 0; i < flag; i++) {
                    t.push('&nbsp;&nbsp;&nbsp;&nbsp;');
                }
                t = t.join("");
                list.push(t + '&lt;<span class="code-key">'+ element.nodeName +'</span>&gt;<br/>');
                for (var i = 0; i < element.childNodes.length; i++) {
                    var nodeName = element.childNodes[i].nodeName;
                    if (element.childNodes[i].childNodes.length===0){
                        var value_txt =""
                        var item = t + '&nbsp;&nbsp;&nbsp;&nbsp;&lt;<span class="code-key">' + nodeName +
                            '</span>&gt;' + value_txt + '&lt;/<span class="code-key">' + nodeName + '</span>&gt;<br/>';
                        list.push(item);
                    } else if ( (element.childNodes[i].childNodes.length === 1 && element.childNodes[i].childNodes[0].nodeValue!=null)) {
                        var value = element.childNodes[i].childNodes[0].nodeValue;
                        var value_color = !isNaN(Number(value)) ? 'code-number' : 'code-string';
                        var value_txt = '<span class="'+ value_color +'">' + value + '</span>';
                        var item = t + '&nbsp;&nbsp;&nbsp;&nbsp;&lt;<span class="code-key">' + nodeName +
                            '</span>&gt;' + value_txt + '&lt;/<span class="code-key">' + nodeName + '</span>&gt;<br/>';
                        list.push(item);

                    } else {
                        flag++
                        build_xml(++index, list, element.childNodes[i]);
                        flag--
                    }
                }
                list.push(t + '&lt;/<span class="code-key">'+ element.nodeName +'</span>&gt;<BR/>');
            }

            var list = [];
            build_xml(0, list, xml_doc.documentElement);

            return list.join("");
        };





    });

    exports('work/historicalmsg', {})
});