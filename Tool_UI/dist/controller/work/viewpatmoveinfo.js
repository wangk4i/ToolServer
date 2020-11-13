layui.define(function(exports){
    
    layui.use(['laydate','table','element','hdk','form','tool','appconfig'], function(){
        var $ = layui.$
        ,laydate = layui.laydate
        ,router = layui.router()
        ,table = layui.table
        ,setter = layui.setter
        ,form = layui.form
        ,element = layui.element
        ,laytpl = layui.laytpl
        ,hdk = layui.hdk
        ,tool=layui.tool
        ,admin = layui.admin
        ,params = {}
        ,headers = {}
        ,userInfo = {}
        ,thisTab = 0
        ,appconfig = layui.appconfig
        ;

        //日期范围
        laydate.render({
            elem: '#starttime',
            theme: '#393D49'
        });

        //日期范围
        laydate.render({
            elem: '#endtime',
            theme: '#393D49'
        });


        form.on('submit(*)', function (data) {

            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });


        //患者基本信息跨省迁出查询

        var moveout = table.render({
            elem: '#SyncMoveOutPatInfo',
            page: true,
            toolbar: true,
            //width: 1700,
            title: '患者基本信息跨省迁出国网对比信息',
            cols: [
                [{
                    field: 'FIELDPK',
                    align: 'center',
                    title: '国网主键编号'
                }, {
                    field: 'IDCode',
                    align: 'center',
                    title: '患者身份证号'
                }, {
                    field: 'PatInfoCd',
                    align: 'center',
                    title: '本省患者编号'
                }, {
                    field: 'PatNam',
                    align: 'center',
                    title: '患者姓名'
                }, {
                    field: 'OutOrgNam',
                    align: 'center',
                    title: '患者迁出机构'
                }, {
                    field: 'OutZoneNam',
                    align: 'center',
                    title: '患者迁出地区'
                }, {
                    field: 'OutDate',
                    align: 'center',
                    title: '迁出日期'
                }, {
                    field: 'OutType',
                    align: 'center',
                    title: '省网流转记录迁出状态'
                }, {
                    field: 'MoveStatusCd',
                    align: 'center',
                    title: '国网迁出接收结果',
                    templet: function (d) {
                        if (d.MoveStatusCd == "FlowState002") {
                            return '<span style="color:#008000;">迁出成功</span>'
                        }
                        if (d.MoveStatusCd == "FlowState001") {
                            return '<span style="color: #ff790d;">迁出中</span>'
                        }
                        if (d.MoveStatusCd == "FlowState003") {
                            return '<span style="color:#cc0000;">迁出被拒</span>'
                        }
                        if (d.MoveStatusCd == undefined) {
                            return '<span style="color:#cc0000;"></span>'
                        }

                    }
                }, {
                    field: 'RefuseCause',
                    align: 'center',
                    title: '拒绝原因'
                }, {
                    field: 'SyncState',
                    align: 'center',
                    title: '同步状态'
                }]
            ],
            data: [],
            done: function () {

                //隐藏原有的excel导出按钮
                $('[lay-id=SyncMoveOutPatInfo]').find('.layui-table-tool-self').find(
                    '[lay-event=LAYTABLE_EXPORT]').hide();

                //添加新按钮
            }
        });

        var ExcelData = '';

        $("#btn-querypat-moveout").on('click',function(){
            var starttime = $("#starttime").val();
            var endtime = $("#endtime").val();


            if (starttime.length == 0) {
                layer.msg("请输入开始日期");
                return false;
            } else if (endtime.length == 0) {
                layer.msg("请输入截至日期");
                return false;
            } else if (starttime > endtime) {
                layer.msg("开始日期不能大于结束日期");
                return false;
            }
            /***
             * 结束日期不超出开始日期31天
             * @type {number}
             */
            var startDate = Date.parse(starttime);
            var endDate = Date.parse(endtime);
            var days = (endDate - startDate) / (1 * 24 * 60 * 60 * 1000);

            if ((days + 1) > 31) {
                layer.msg("结束日期不超出开始日期31天");
                return false;
            } else {
                hdk.ajax({
                    api: appconfig.MoveInAndOut.GetMoveOutResult,
                    data: tool.buildJson({
                        StartDate: starttime,
                        EndDate: endtime
                    })
                }, function (result) {
                    console.log(result.data);
                    ExcelData = result.data;
                    //返回数据加载到数据表格中
                    table.reload('SyncMoveOutPatInfo', {
                        data: result.data
                    });
                });
            }
        });

        $("#btn-exportpat-moveout").on('click',function(){
            table.exportFile(moveout.config.id, ExcelData, 'xls');
        });


        //患者上挂撤回

        table.render({
            elem: '#moveInAndOutpatInfo_table',
            page: false,
            cellMinWidth: 120,
            cols: [
                [{
                    field: 'Cd',
                    align: 'center',
                    title: '患者主键'
                }, {
                    field: 'PatNam',
                    align: 'center',
                    title: '患者姓名'
                }, {
                    field: 'ZoneNam',
                    align: 'center',
                    title: '管理地区'
                }, {
                    field: 'OrgNam',
                    align: 'center',
                    title: '管理单位'
                }, {
                    field: 'FIELDPK',
                    align: 'center',
                    title: '流转患者主键'
                }]
            ],
            data: []
        });


        form.on('submit(moveInAndOutpatInfoByIDCode)', function (o) {
            //获取身份证输入框中的值
            var IDCode = $('input[name=PatIDCode]').val();
            if (IDCode == undefined) {
                return
            }
            hdk.ajax({
                api: appconfig.MoveInAndOut.QueryMoveOutInformation,
                data: JSON.stringify({
                    CardID: o.field.PatIDCode
                })
            }, function (result) {
                //返回数据加载到数据表格中
                table.reload('moveInAndOutpatInfo_table', {
                    data: result.data
                });
            });
        })


        //上挂回收，回收菜单功能
        $("#btn-callbackpat-moveout").on('click',function(){
            var moveInAndOutId = $('input[name=moveInAndOutId]').val();
            if (moveInAndOutId == undefined) {
                layer.msg("流转编号不能为空");
                return
            }
            if (moveInAndOutId.length == 0) {
                layer.msg("流转编号不能为空");
                return
            }

            hdk.ajax({
                api: appconfig.MoveInAndOut.CallBackMove,
                data: JSON.stringify({
                    moveInAndOutId: moveInAndOutId
                })
            }, function (result) {
                if (result.data.result == "1") {
                    layer.msg("迁出收回成功" + JSON.stringify(result.data.keyMessage));
                } else {
                    layer.msg("迁出收回失败" + result.data.keyMessage);
                }
            });

        });


        //获取正迁入到本省的患者信息
        function jsonFormat(json, options) {

            var reg = null,
                formatted = '',
                pad = 0,
                PADDING = '    '; // one can also use '\t' or a different number of spaces

            // optional settings
            options = options || {};
            // remove newline where '{' or '[' follows ':'
            options.newlineAfterColonIfBeforeBraceOrBracket = (options
                .newlineAfterColonIfBeforeBraceOrBracket === true) ? true : false;
            // use a space after a colon
            options.spaceAfterColon = (options.spaceAfterColon === false) ? false : true;

            // begin formatting...
            if (typeof json !== 'string') {
                // make sure we start with the JSON as a string
                json = JSON.stringify(json);
            } else {
                // is already a string, so parse and re-stringify in order to remove extra whitespace
                json = JSON.parse(json);
                json = JSON.stringify(json);
            }

            // add newline before and after curly braces
            reg = /([\{\}])/g;
            json = json.replace(reg, '\r\n$1\r\n');

            // add newline before and after square brackets
            reg = /([\[\]])/g;
            json = json.replace(reg, '\r\n$1\r\n');

            // add newline after comma
            reg = /(\,)/g;
            json = json.replace(reg, '$1\r\n');

            // remove multiple newlines
            reg = /(\r\n\r\n)/g;
            json = json.replace(reg, '\r\n');

            // remove newlines before commas
            reg = /\r\n\,/g;
            json = json.replace(reg, ',');

            // optional formatting...
            if (!options.newlineAfterColonIfBeforeBraceOrBracket) {
                reg = /\:\r\n\{/g;
                json = json.replace(reg, ':{');
                reg = /\:\r\n\[/g;
                json = json.replace(reg, ':[');
            }
            if (options.spaceAfterColon) {
                reg = /\:/g;
                json = json.replace(reg, ': ');
            }

            $.each(json.split('\r\n'), function (index, node) {
                var i = 0,
                    indent = 0,
                    padding = '';

                if (node.match(/\{$/) || node.match(/\[$/)) {
                    indent = 1;
                } else if (node.match(/\}/) || node.match(/\]/)) {
                    if (pad !== 0) {
                        pad -= 1;
                    }
                } else {
                    indent = 0;
                }

                for (i = 0; i < pad; i++) {
                    padding += PADDING;
                }

                formatted += padding + node + '\r\n';
                pad += indent;
            });

            return formatted;
        }

        form.on('submit(moveIn_query)', function (obj) {
            console.log(obj.field);
            var req = {
                "PatInfoCd": obj.field.moveIN_patinfocd
            };
            hdk.ajax({
                api: appconfig.MoveInAndOut.QueyrPatInfoByMoveCd,
                data: JSON.stringify(req)
            },function (result) {
                //查询数据成功后的执行回调，
                if (result.code == '1') {

                    // layer.open({
                    //     type: 1,
                    //     skin: 'layui-layer-demo', //样式类名
                    //     anim: 2,
                    //     area: ['1200px', '600px'],
                    //     content: '<pre>'+jsonFormat(JSON.stringify(result))+'</pre>'
                    // });

                    layer.open({
                        title: '患者详情',
                        id: 'gw_moveInPat_details',
                        type: 1,
                        area: ['90%', '700px'],
                        // content: $('#gwpatinfotpl_layer').html(),
                        success: function (layero, index) {
                            var getTpl = gwmoveinpattpl_layer.innerHTML
                                ,view = document.getElementById('gw_moveInPat_details');
                            laytpl(getTpl).render(result, function(html){
                                view.innerHTML = html;
                            });
                        }
                    });
                    //layer.alert(JSON.stringify(jsonObj1.data[0]));
                } else {
                    layer.alert(result.message);
                }
            });
            return false;
        });


    });
    exports('work/viewpatmoveinfo', {})
});