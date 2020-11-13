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

        form.on('submit(*)', function (data) {

            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });

        form.verify({
            orgCode: function (value) {
                var orgchangevalue = $('#LAY_orgchange').val();
                if (value == orgchangevalue) {
                    return '迁移机构不能相同!';
                }
            },
            orgCodeview: function (value) {
                var orgchangevalue = $('#LAY_orgchange_view').val();
                if (value == orgchangevalue) {
                    return '迁移机构不能相同!';
                }
            },
            zoneCode: function (value) {
                var zonechangevalue = $('#LAY_zonechange').val();
                if (value !== zonechangevalue) {
                    return '不能跨市迁移';
                }
            }
        });


        form.on('submit(handleOrganiseChange_edit_btn)', function (obj) {
            console.log(obj.field);
            hdk.ajax({
                api: appconfig.GW.handleOrganiseChange,
                data: JSON.stringify(obj.field)
            },function (jsonObj1) {
                //查询数据成功后的执行回调，
                if (jsonObj1.code == '1') {
                    layer.open({
                        type: 1,
                        skin: 'layui-layer-demo', //样式类名
                        anim: 2,
                        area: ['380px', '380px'],
                        content: "<p>国网返回状态:" + jsonObj1.data[0].GWresult +
                            "<p><br><p>国网返回信息:" + jsonObj1.data[0]
                            .GWkeyMessage + "<p><br><p>省网返回信息:" + jsonObj1
                            .data[0].Flag + "<p>"
                    });
                    //layer.alert(JSON.stringify(jsonObj1.data[0]));
                } else {
                    layer.alert(jsonObj1.message);
                }
                //执行迁移，成功后的数据回调
            });
            return false;
        });


        /**
         * 设置首页页码
         * */
        var page = 1;
        /**
         * 设置一页显示的条数
         * */
        var limit = 10;

        var pram = {
            "pageNum": (page - 1) * 10,
            "pageSize": page * limit
        }
        form.on('submit(handleOrganiseChange_view_btn)', function (o) {
            hdk.ajax({
                api: appconfig.GW.queryhandleOrganiseChange,
                data: JSON.stringify($.extend(o.field, pram))
            },function (jsonObj1) {
                //查询数据成功后的执行回调，
                if (jsonObj1.code != '1') {
                    layer.alert(jsonObj1.message);
                    return;
                }

                table.render({
                    elem: '#handleOrganiseChange_view',
                    data: jsonObj1.data,
                    cols: [
                        [{
                            field: 'ZonecodeMain',
                            title: '迁往地区编码',
                            width: '10%',
                            align: 'center'
                        }, {
                            field: 'ZoneMainNam',
                            title: '迁往地区名称',
                            width: '12%',
                            align: 'center'
                        }, {
                            field: 'OrgcodeMain',
                            title: '迁往机构编码',
                            width: '10%',
                            align: 'center'
                        }, {
                            field: 'OrgMainNam',
                            title: '迁往机构名称',
                            width: '15%',
                            align: 'center'
                        }, {
                            field: 'ZonecodePassive',
                            title: '源地区编码',
                            width: '10%',
                            align: 'center'
                        }, {
                            field: 'ZonePassiveNam',
                            title: '源地区名称',
                            width: '12%',
                            align: 'center'
                        }, {
                            field: 'OrgcodePassive',
                            title: '源机构编码',
                            width: '10%',
                            align: 'center'
                        }, {
                            field: 'OrgPassiveNam',
                            title: '源机构名称',
                            width: '15%',
                            align: 'center'
                        }, {
                            field: 'Action',
                            title: '迁移类型',
                            align: 'center'
                        }]
                    ]
                });
                laypage.render({
                    elem: 'handleOrganiseChange_viewpage',
                    count: jsonObj1.count,
                    limit: 10,
                    layout: ['prev', 'page', 'next', 'count', 'skip'],
                    jump: function (obj, first) {
                        if (!first) {
                            page = obj.curr;
                            hdk.ajax({
                                api: appconfig.GW.queryhandleOrganiseChange,
                                data: $.extend(o.field,pram)
                            }, function (res) {
                                table.reload(
                                    'handleOrganiseChange_view', {
                                        data: res.data
                                    });
                            });
                        }
                    }
                });
                    
                //执行迁移，成功后的数据回调
                
            });
        });
        

    });



    exports('work/organchange', {})
});