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

       //初始化年份
       function initYearLabel() {
            var myDate = new Date();
            var year = myDate.getFullYear();
            $(".one").text(year + "年");
            $(".two").text(year - 1 + "年");
            $(".there").text(year - 2 + "年");
        }
        initYearLabel();

        $("#btn-query-InspectYear").on('click',function(){
            var myDate = new Date();
            var year = myDate.getFullYear();
            var year1 = year - 1;
            var year2 = year - 2;
            input = {
                InspectYear: year
            }

            hdk.ajax({
                api: appconfig.YearInspectQuery.queryPatInspectYearDetails,
                data: tool.buildJson(input)
            },function (Data) {
                var sum = 0;
                for (var i = 0; i < Data.data.length; i++) {
                    sum += parseInt(Data.data[i].Num);
                }
                $("#noyear").text(sum);
            
            });

            hdk.ajax({
                api: appconfig.YearInspectQuery.queryPatInspectYear
            },function (Data) {
                console.log(Data);
                var sum = 0;
                for (var i = 0; i < Data.data.length; i++) {
                    if (Data.data[i].InspectYear == "0") {
                        $("#eight").text(Data.data[i].Num);
                    } else if (Data.data[i].InspectYear == year) {
                        $("#data0").text(Data.data[i].Num);
                    } else if (Data.data[i].InspectYear == year1) {
                        $("#data1").text(Data.data[i].Num);
                    } else if (Data.data[i].InspectYear == year2) {
                        $("#data2").text(Data.data[i].Num);
                    } else {
                        sum += parseInt(Data.data[i].Num);
                    }
                }
                $("#seven").text(sum);
            });

            hdk.ajax({
                api: appconfig.YearInspectQuery.queryPatInspectYearDelete
            },function (Data) {
                console.log(Data);
                var sum = 0;
                for (var i = 0; i < Data.data.length; i++) {
                    if (Data.data[i].InspectYear == "0") {
                        $("#data8").text(Data.data[i].Num);
                    } else if (Data.data[i].InspectYear == year) {
                        $("#data4").text(Data.data[i].Num);
                    } else if (Data.data[i].InspectYear == year1) {
                        $("#data5").text(Data.data[i].Num);
                    } else if (Data.data[i].InspectYear == year2) {
                        $("#data6").text(Data.data[i].Num);
                    } else {
                        sum += parseInt(Data.data[i].Num);
                    }
                }
                $("#data7").text(sum);
            
            });

            hdk.ajax({
                api: appconfig.YearInspectQuery.queryPatInspectYearDeath
            },function (Data) {
                console.log(Data);
                var sum = 0;
                for (var i = 0; i < Data.data.length; i++) {
                    if (Data.data[i].InspectYear == "0") {
                        $("#data13").text(Data.data[i].Num);
                    } else if (Data.data[i].InspectYear == year) {
                        $("#data9").text(Data.data[i].Num);
                    } else if (Data.data[i].InspectYear == year1) {
                        $("#data10").text(Data.data[i].Num);
                    } else if (Data.data[i].InspectYear == year2) {
                        $("#data11").text(Data.data[i].Num);
                    } else {
                        sum += parseInt(Data.data[i].Num);
                    }
                }
                $("#data12").text(sum);
            
            });
        });

        $("#btn-query-InspectYearDetails2").on('click',function(){
            var myDate = new Date();
            var year = myDate.getFullYear();
            hdk.ajax({
                api: appconfig.YearInspectQuery.queryPatInspectYearDetails,
                data: tool.buildJson({
                    InspectYear: year,
                })
            }, function (result) {
                table.reload('InspectYearDetails', {
                    data: result.data
                });
            });
        });

        table.render({
            elem: '#InspectYearDetails',
            page: true,
            //width: 1700,
            // toolbar: true,
            // totalRow: true,
            cols: [
                [{
                    field: 'SyncError',
                    align: 'center',
                    title: '同步问题描述（SyncError）'
                }, {
                    field: 'Num',
                    align: 'center',
                    title: '本年未完成年审人数',
                    totalRow: true
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




    });



    exports('work/viewyearinfo', {})
});
