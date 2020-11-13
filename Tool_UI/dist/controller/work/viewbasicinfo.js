layui.define(function(exports){
    
    layui.use(['laydate','table','element','hdk','form','tool','appconfig'], function(){
        var $ = layui.$
        ,laydate = layui.laydate
        ,router = layui.router()
        ,table = layui.table
        ,setter = layui.setter
        ,form = layui.form
        ,element = layui.element
        ,hdk = layui.hdk
        ,tool=layui.tool
        ,admin = layui.admin
        ,params = {}
        ,headers = {}
        ,userInfo = {}
        ,thisTab = 0
        ,appconfig = layui.appconfig
        ;
    

        var provinceData=[];
        layui.form.render();
        form.on('submit(*)', function (data) {

            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。 
        });

        //获取省数组
        hdk.ajax({
            api: appconfig.BasicInfoQuery.QueryProvince
        }, function (result) {
            if (result.code == "1") {
                provinceData=result.data;
                initprovinceData();
            } 
        });
        //填充省级下拉
        function initprovinceData(){
            $.each(provinceData, function (index, item) {
                $('#province').append(new Option(item.Nam, item.Cd));
                $('#province1').append(new Option(item.Nam, item.Cd));
                $('#province2').append(new Option(item.Nam, item.Cd));
            });
            layui.form.render();
        }

        //机构有无基层直报员    
        /**
         * 获取省级下拉框的数据加载市级数据
         */
        form.on('select(provincefiletr)', function (data) {

            $("#city").empty();
            $("#county").empty();
            $("#organ").empty();
            hdk.ajax({
                api: appconfig.BasicInfoQuery.QueryZoneByLv,
                data: tool.buildJson({
                    ParCd: data.value
                })
            }, function (result) {
                $.each(result.data, function (index, item) {
                    $('#city').append(new Option(item.Nam, item.Cd));
                })
                layui.form.render();
            });
        });


        /**
         * 获取市下拉框的数据加载区县级数据
         */ //countyfiletr
        form.on('select(cityfiletr)', function (data) {
            $("#county").empty();

            hdk.ajax({
                api: appconfig.BasicInfoQuery.QueryZoneByLv,
                data: tool.buildJson({
                    ParCd: data.value,
                })
            }, function (result) {
                $.each(result.data, function (index, item) {
                    $('#county').append(new Option(item.Nam, item.Cd));
                })
                layui.form.render();
            });
        });

        /**
         * 获取区县下拉框的数据加载机构数据
         */
        form.on('select(countyfiletr)', function (data) {
            $("#organ").empty();
            hdk.ajax({
                api: appconfig.BasicInfoQuery.QueryManOrgByZone,
                data: tool.buildJson({
                    ZoneCd: data.value,
                })
            }, function (result) {
                console.log(result);
                $.each(result.data, function (index, item) {
                    $('#organ').append(new Option(item.Nam, item.OrgCode));
                })
                layui.form.render();
            });
        });

        $("#btn-query-organ-zb1").on('click',function() {
            /***
             * 首先判断机构cd是否为空
             */
            var organCd = $("#organ").val();  //.val();
            if (organCd == null) {
                layer.msg("未选择查询机构");
                return;
            }
            $.ajax({
                url: appconfig.BasicInfoQuery.GetUserInformationByOrgcode,
                data: JSON.stringify({
                    OrganCd: organCd,
                }),
                dataType: 'json', //服务器返回json格式数据
                type: 'post', //HTTP请求类型
                headers: {
                    'Content-Type': 'application/json'
                },
                success: function (Data) {
                    var datas;
                    if (Data.code == '1'){
                        datas = [Data];
                    }else {
                        layer.msg('请求失败: ' + Data.message);
                        datas= [];
                    }
                    //返回数据加载到数据表格中
                    table.reload('isreporter_table', {
                        data: datas
                    });
                },
                error: function (xhr, type, errorThrown) {
                    console.log("错误");
                    console.log(errorThrown);
                }
            })

        });

        table.render({
            elem: '#isreporter_table',
            page: false,
            //width: 100,
            cols: [
                [{
                    field: 'data',
                    align: 'center',
                    title: '查询结果'
                }]
            ],
            data: []
        });




        var localdata = new Array();
        var gwdata = new Array();

        //地区数据查询    

        /**
         * 获取省级下拉框的数据加载市级数据
         */
        form.on('select(shengfiletr)', function (data) {
            /**
             * 每次点击清空一次select框
             */
            $("#city1").find("option:not(:first)").remove();
            $("#county1").find("option:not(:first)").remove();
            hdk.ajax({
                api: appconfig.BasicInfoQuery.QueryZoneByLv,
                data: tool.buildJson({
                    ParCd: data.value,
                })
            }, function (result) {
                $.each(result.data, function (index, item) {

                    $('#city1').append(new Option(item.Nam, item.Cd));
                })
                layui.form.render();
            });
        });


        /**
         * 获取市下拉框的数据加载区县级数据
         */ //countyfiletr
        form.on('select(shifiletr)', function (data) {

            /**
             * 每次点击清空一次select框
             */
            $("#county1").find("option:not(:first)").remove();

            hdk.ajax({
                api: appconfig.BasicInfoQuery.QueryZoneByLv,
                data: tool.buildJson({
                    ParCd: data.value,
                })
            }, function (result) {
                $.each(result.data, function (index, item) {
                    $('#county1').append(new Option(item.Nam, item.Cd));
                })
                layui.form.render();
            });
        });

        $("#btn-area-query").on('click',function() {
            var province1 = $("#province1 option:selected").text();
            var city1 = $("#city1 option:selected").text();
            var county1 = $("#county1 option:selected").text();
            var Nam = '';
            var Cd = '';
            if (province1 == '请选择') {
                layer.msg("请选择地区");
            } else {
                if (province1 != '请选择') {
                    Nam = province1;
                    Cd = $("#province1").val();
                }
                if (city1 != '请选择') {
                    Nam = city1;
                    Cd = $("#city1").val();
                }
                if (county1 != '请选择') {
                    Nam = county1;
                    Cd = $("#county1").val();
                }
                getlocalZoneInfo(Cd, Nam);

            }
        });

        $("#btn-area-export").on('click',function() {
            //console.log(exportZonedata);
            table.exportFile(['本地编码', '本地地区名称', '国网编码', '国网地区名称', '状态编码', '状态'], exportZonedata, 'xls');
        });
        /**
         * 查询表格初始化
         * */
        var exportZonedata = ''
        table.render({
            elem: '#zone_table',
            page: true,
            //width: 1700,
            toolbar: true,
            cols: [
                [{
                    field: 'LocalCode',
                    align: 'center',
                    title: '本地编码'
                }, {
                    field: 'LocalName',
                    align: 'center',
                    title: '本地地区名称'
                }, {
                    field: 'RemoteCode',
                    align: 'center',
                    title: '国网编码'
                }, {
                    field: 'RemoteName',
                    align: 'center',
                    title: '国网地区名称'
                }, {
                    field: 'StrState',
                    align: 'center',
                    title: '状态'
                }]
            ],
            data: [],
            done: function (res, curr, count) {
                //var table=new Array();
                //table=res.value;
                // for (var i = 0; i < table.length; i++) {
                //  if(table[i].ItemState=="2"){
                //   $("tr").eq(i+1).css("background-color","red")
                // }
                // }

            },
            parseData: function (res) { //将原始数据解析成 table 组件所规定的数据
                return {
                    "code": res.status, //解析接口状态
                    "msg": res.message, //解析提示文本
                    "count": res.total, //解析数据长度
                    "data": res.rows.item //解析数据列表
                };
            }
        });

        //获取本地数据
        function getlocalZoneInfo(Cd, Nam) {
            hdk.ajax({
                api: appconfig.GW.CompareZoneDataByZoneCode,
                data: JSON.stringify({
                    Code: Cd,
                    Name: Nam
                })
            }, function (result) {
                console.log(result);
                exportZonedata = result.data;
                //返回数据加载到数据表格中
                table.reload('zone_table', {
                    data: result.data
                });
            });
        }

        



        //机构数据查询  


        /**
         * 获取省级下拉框的数据加载市级数据
         */
        form.on('select(sheng)', function (data) {
            /**
             * 每次点击清空一次select框
             */
            $("#city2").find("option:not(:first)").remove();
            $("#county2").find("option:not(:first)").remove();
            hdk.ajax({
                api: appconfig.BasicInfoQuery.QueryZoneByLv,
                data: tool.buildJson({
                    ParCd: data.value,
                })
            }, function (result) {
                $.each(result.data, function (index, item) {

                    $('#city2').append(new Option(item.Nam, item.Cd));
                })
                layui.form.render();
            });
        });


        /**
         * 获取市下拉框的数据加载区县级数据
         */ //countyfiletr
        form.on('select(shi)', function (data) {

            /**
             * 每次点击清空一次select框
             */
            $("#county2").find("option:not(:first)").remove();

            hdk.ajax({
                api: appconfig.BasicInfoQuery.QueryZoneByLv,
                data: tool.buildJson({
                    ParCd: data.value,
                })
            }, function (result) {
                $.each(result.data, function (index, item) {
                    $('#county2').append(new Option(item.Nam, item.Cd));
                })
                layui.form.render();
            });
        });


        $("#btn-organ-query").on('click',function() {
            var province2 = $("#province2 option:selected").text();
            var city2 = $("#city2 option:selected").text();
            var county2 = $("#county2 option:selected").text();
            var Cd = '';
            if (province2 == '请选择') {
                layer.msg("请选择地区");
            } else {
                if (province2 != '请选择') {
                    Cd = $("#province2").val();
                }
                if (city2 != '请选择') {
                    Cd = $("#city2").val();
                }
                if (county2 != '请选择') {
                    Cd = $("#county2").val();
                }
                getOrganInfo(Cd);

            }
        });
 
        table.render({
            elem: '#organ_table',
            page: true,
            //width: 1700,
            toolbar: true,
            cols: [
                [{
                    field: 'LocalCode',
                    align: 'center',
                    title: '本地编码'
                }, {
                    field: 'LocalName',
                    align: 'center',
                    title: '本地地区名称'
                }, {
                    field: 'RemoteCode',
                    align: 'center',
                    title: '国网编码'
                }, {
                    field: 'RemoteName',
                    align: 'center',
                    title: '国网地区名称'
                }, {
                    field: 'StrState',
                    align: 'center',
                    title: '状态'
                }, {
                    fixed: 'right',
                    title: '操作',
                    toolbar: '#SyncOrgbar',
                    width: 150
                }]
            ],
            data: [],
            parseData: function (res) { //将原始数据解析成 table 组件所规定的数据
                return {
                    "code": res.status, //解析接口状态
                    "msg": res.message, //解析提示文本
                    "count": res.total, //解析数据长度
                    "data": res.rows.item //解析数据列表
                };
            }
        });
        table.on('tool(organ_table)', function (obj) {
            Inputobj = {
                OrgCode: obj.data.LocalCode
            }
            layer.confirm('是否同步该机构？', {
                btn: ['是', '否'] //按钮
            }, function () {
                var index = layer.load(2, {
                    shade: [0.3, '#fff']
                }); //0代表加载的风格，支持0-2
                hdk.ajax({
                    api: appconfig.GW.SyncOrg,
                    data: JSON.stringify(Inputobj)
                }, function (result) {
                    if (result.code == '1') {
                        layer.close(index);
                        layer.alert(result.data);
                        $(".isreporter2").trigger('click');
                    } else {
                        layer.close(index);
                        layer.alert(result.message);
                    }
                });
            }, function () {
                layer.closeAll();
            });
        })
        exportOrgdata = '';
        //获取本地数据
        function getOrganInfo(Cd) {
            hdk.ajax({
                api: appconfig.GW.CompareOrganDataByZoneCode,
                data: JSON.stringify({
                    Code: Cd
                })
            }, function (result) {
                console.log(result);
                exportOrgdata = result.data;
                //返回数据加载到数据表格中
                table.reload('organ_table', {
                    data: result.data
                });
            });
        }

        $("#btn-organ-export").on('click',function() {
            //console.log(exportOrgdata);
            table.exportFile(['本地编码', '本地地区名称', '国网编码', '国网地区名称', '状态编码', '状态'], exportOrgdata, 'xls');
        });

    });
    exports('work/viewbasicinfo', {})
});