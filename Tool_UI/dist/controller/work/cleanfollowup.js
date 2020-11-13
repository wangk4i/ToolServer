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

        laydate.render({
            elem: '#test3'
            , type: 'month'
        });
        form.on('submit(*)', function (data) {

            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });

        form.render();

        //跨省迁出查询
        var gwout_patInfo_table = table.render({
            elem: '#out_patInfo_table',
            page: true,
            cols: [
                [{
                    field: 'Cd',
                    align: 'center',
                    title: '患者主键',
                    width: '18.4%' //295
                }, {
                    field: 'PatNam',
                    align: 'center',
                    title: '患者姓名',
                    width: '7.5%'
                }, {
                    field: 'IDCode',
                    align: 'center',
                    title: '身份证号',
                    width: '12.5%'
                }, {
                    field: 'GenderCd',
                    align: 'center',
                    title: '性别',
                    width: '4.3%'
                }, {
                    field: 'OutZoneNam',
                    align: 'center',
                    title: '迁出地区',
                    width: '6.9%'
                }, {
                    field: 'OutOrgNam',
                    align: 'center',
                    title: '迁出机构',
                    width: '17.9%'
                }, {
                    field: 'InOrgNam',
                    align: 'center',
                    title: '迁入机构',
                    width: '17.9%'
                }, {
                    field: 'OutDate',
                    align: 'center',
                    title: '迁出日期',
                    minWidth: 231
                }]
            ],
            data: []
        });

        form.on('submit(out_pat_query)', function (input) {
            hdk.ajax({
                api: appconfig.SyncPatFollowup.QueryOutPatForList,
                data: JSON.stringify({
                    Year: input.field.Year
                    }
                )
            }, function (result) {
                if (result.code === 1) {
                    gwout_patInfo_table.reload({data: result.data, page: {curr: 1}});
                } else {
                    layer.msg(result.message);
                }
            });
            return false;
        });

        table.on('rowDouble(out_patInfo_table)', function(obj){
                console.log(obj.data.Cd) //得到当前行数据
                element.tabChange('TabBrief', '2'); //切换到 lay-id="yyy" 的这一项
                $('#Cd').val('');
                $('#Cd').val(obj.data.Cd);
                $('[lay-filter=followup_query]').trigger('click');
        });

        var InputobjFollowup='';
        form.on('submit(followup_query)', function (input) {
            $(".patinfo-list").empty();
            InputobjFollowup = {
                Cd: input.field.Cd
            }
            $.ajax({
                url: appconfig.SyncPatFollowup.QueryPatInfoByCdForCard,
                data: JSON.stringify(InputobjFollowup),
                dataType: 'json', //服务器返回json格式数据
                type: 'post', //HTTP请求类型
                headers: {
                    'Content-Type': 'application/json'
                },
                success: function (Data) {
                    
                    if(Data.code=='1'){
                        if(Data.data.length > 0){
                            var patinfo_html=''
                            Data.data.forEach( item =>{
                                patinfo_html +='<div class="layui-col-md12"><div class="layui-card"><div class="layui-card-body">'
                                        +'<div class="layui-form">'
                                        +'<div class="layui-inline"><label class="layui-form-label">患者姓名：</label><div class="layui-input-inline" style="padding-top: 6px;min-width: 143px;">'+((item.PatNam == undefined) ? "" : item.PatNam)+'</div></div>'
                                        +'<div class="layui-inline"><label class="layui-form-label">患者性别：</label><div class="layui-input-inline" style="padding-top: 6px;min-width: 143px;">'+((item.GenderCd == undefined) ? "" : item.GenderCd)+'</div></div>'
                                        +'<div class="layui-inline"><label class="layui-form-label">管理地区：</label><div class="layui-input-inline" style="padding-top: 6px;min-width: 143px;">'+((item.ZoneNam == undefined) ? "" : item.ZoneNam)+'</div></div>'
                                        +'<div class="layui-inline"><label class="layui-form-label">管理机构：</label><div class="layui-input-inline" style="padding-top: 6px;min-width: 143px;">'+((item.OrgNam == undefined) ? "" : item.OrgNam)+'</div></div>'

                                        +'<div class="layui-inline"><label class="layui-form-label">身份证号：</label><div class="layui-input-inline" style="padding-top: 6px;min-width: 143px;">'+((item.IDCode == undefined) ? "" : item.IDCode)+'</div></div>'
                                        +'<div class="layui-inline"><label class="layui-form-label">患者编码：</label><div class="layui-input-inline" style="padding-top: 6px;min-width: 143px;">'+((item.PatCode == undefined) ? "" : item.PatCode)+'</div></div>'
                                        +'<div class="layui-inline"><label class="layui-form-label">出生日期：</label><div class="layui-input-inline" style="padding-top: 6px;min-width: 143px;">'+((item.BirthDate == undefined) ? "" : item.BirthDate)+'</div></div>'
                                        +'<div class="layui-inline"><label class="layui-form-label">在库状态：</label><div class="layui-input-inline" style="padding-top: 6px;min-width: 143px;">'+((item.BaseStatusCd == undefined) ? "" : item.BaseStatusCd)+'</div></div>'

                                        +'<div class="layui-inline"><label class="layui-form-label">年审时间：</label><div class="layui-input-inline" style="padding-top: 6px;min-width: 143px;">'+((item.InspectYear == undefined) ? "" : item.InspectYear)+'</div></div>'
                                        +'<div class="layui-inline"><label class="layui-form-label">删除状态：</label><div class="layui-input-inline" style="padding-top: 6px;min-width: 143px;">'+((item.DelStatus == undefined) ? "" : item.DelStatus)+'</div></div>'
                                        +'<div class="layui-inline"><label class="layui-form-label">同步时间：</label><div class="layui-input-inline" style="padding-top: 6px;min-width: 143px;">'+((item.SyncTime == undefined) ? "" : item.SyncTime)+'</div></div>'
                                        +'<div class="layui-inline"><label class="layui-form-label">失败信息：</label><div class="layui-input-inline" style="padding-top: 6px;min-width: 143px;">'+((item.SyncError == undefined) ? "" : item.SyncError)+'</div></div>'
                                        +'</div>'
                                        +'</div></div></div>'
                            })
                            $(".patinfo-list").html(patinfo_html);

                            loadFollowup();
                        } 
                    } else {
                        layer.msg("查询失败 "+ Data.message );
                    }
                },
                error: function (xhr, type, errorThrown) {
                    console.log("错误");
                    console.log(errorThrown);
                }
            })
            
            return false;
        });

        function loadFollowup(){
            $(".followup-list").empty();
            $.ajax({
                url: appconfig.SyncPatFollowup.QueryPatFollowupByCdForCard,
                data: JSON.stringify(InputobjFollowup),
                dataType: 'json', //服务器返回json格式数据
                type: 'post', //HTTP请求类型
                headers: {
                    'Content-Type': 'application/json'
                },
                success: function (Data) {
                    
                    if(Data.code=='1'){
                        if(Data.data.length > 0){

                            var html='';
                            Data.data.forEach( item =>{
                                if(((item.SyncStatus == undefined) ? "" : item.SyncStatus)=='失败'){
                                    html +='<div class="layui-col-md3 '+((item.Cd == undefined) ? "" : item.Cd)+'"><div class="layui-card" style="border: 2px solid red"><div class="layui-card-body">'
                                }
                                else
                                {
                                    html +='<div class="layui-col-md3 '+((item.Cd == undefined) ? "" : item.Cd)+'"><div class="layui-card"><div class="layui-card-body">'
                                }
                                 

                                html +='<div class="layui-form-item all" hyd-data="'+ JSON.stringify(item).replace(/"/g, '&quot;') +'">';
                                html +='<label class="layui-form-label">随访主键:</label><div class="layui-input-block Cd" style="width:145px;float:left">'+((item.Cd == undefined) ? "" : item.Cd)+'</div>'
                                //  if(((item.FIELDPK == undefined) ? "" : item.FIELDPK)!=""){
                                //     html+='</div>'
                                //  }
                                //  else
                                //  {
                                //     html+='<button type="button" style="height:20px" class="layui-btn layui-btn-radius layui-btn-xs "  onclick="sync(' + JSON.stringify(item).replace(/"/g, '&quot;') + ')" >同步</button><button type="button" style="height:20px" class="layui-btn layui-btn-radius layui-btn-danger layui-btn-xs " onclick="del(' + JSON.stringify(item).replace(/"/g, '&quot;') + ')">删除</button></div>'
                                //  }  
                                 html+='<button type="button" style="height:20px" class="layui-btn layui-btn-radius layui-btn-xs followup-sync" >同步</button><button type="button" style="height:20px" class="layui-btn layui-btn-radius layui-btn-danger layui-btn-xs followup-sync-delete">删除</button></div>'
                                 html +='<div class="layui-form-item all"><label class="layui-form-label">随访日期:</label><div class="layui-input-block FollowupDate" >'+((item.FollowupDate == undefined) ? "" : item.FollowupDate)+'</div></div>'   
                                 +'<div class="layui-form-item all"><label class="layui-form-label">下次日期:</label><div class="layui-input-block NextDate" >'+((item.NextDate == undefined) ? "" : item.NextDate)+'</div></div>'
                                 +'<div class="layui-form-item all"><label class="layui-form-label">病情情况:</label><div class="layui-input-block CaseClassCd" >'+((item.CaseClassCd == undefined) ? "" : item.CaseClassCd)+'</div></div>'   
                                 +'<div class="layui-form-item all"><label class="layui-form-label">国网编号:</label><div class="layui-input-block FIELDPK" >'+((item.FIELDPK == undefined) ? "" : item.FIELDPK)+'</div></div>'   
                                 +'<div class="layui-form-item all"><label class="layui-form-label">删除状态:</label><div class="layui-input-block DelStatus" ">'+((item.DelStatus == undefined) ? "" : item.DelStatus)+'</div></div>'   
                                 +'<div class="layui-form-item all"><label class="layui-form-label">同步状态:</label><div class="layui-input-block SyncStatus" ">'+((item.SyncStatus == undefined) ? "" : item.SyncStatus)+'</div></div>'   
                                 +'<div class="layui-form-item all"><label class="layui-form-label">同步时间:</label><div class="layui-input-block SyncTime" ">'+((item.SyncTime == undefined) ? "" : item.SyncTime)+'</div></div>'   
                                 +'<div class="layui-form-item all"><label class="layui-form-label">失败信息:</label><div style="overflow:hidden;width:185px;height:22px" title="'+((item.SyncError == undefined) ? "" : item.SyncError)+'"><div class="layui-input-block SyncError" ">'+((item.SyncError == undefined) ? "" : item.SyncError)+'</div></div></div></div></div></div>'
                                //  if(((item.FIELDPK == undefined) ? "" : item.FIELDPK)!=""){
                                //     html+="</div></div></div></div>";
                                //  }
                                //  else
                                //  {
                                //     //html += '<div class="layui-form-item"><div style="text-align: center;"><button type="button" class="layui-btn layui-btn-radius layui-btn-xs "  onclick="sync(' + JSON.stringify(item).replace(/"/g, '&quot;') + ')" >同步</button>'
                                //     //     +'<button type="button" class="layui-btn layui-btn-radius layui-btn-danger layui-btn-xs " onclick="del(' + JSON.stringify(item).replace(/"/g, '&quot;') + ')">删除</button></div></div></div></div></div></div>'
                                //  }
                                //  if(((item.FIELDPK == undefined) ? "" : item.FIELDPK)==""&&((item.DelStatus == undefined) ? "" : item.DelStatus)=="数据正常"){
                                //     html += '<div class="layui-form-item"><div style="text-align: center;"><button type="button" class="layui-btn layui-btn-radius layui-btn-sm "  onclick="sync(' + JSON.stringify(item).replace(/"/g, '&quot;') + ')" >同步</button>'
                                //          +'<button type="button" class="layui-btn layui-btn-radius layui-btn-danger layui-btn-sm " onclick="del(' + JSON.stringify(item).replace(/"/g, '&quot;') + ')">删除</button></div></div></div></div></div></div>'
                                //  }
                                //  if(((item.FIELDPK == undefined) ? "" : item.FIELDPK)==""&&((item.DelStatus == undefined) ? "" : item.DelStatus)=="数据已被逻辑删除")   
                                //  html +='<div class="layui-form-item"><div style="text-align: center;"><button type="button" class="layui-btn layui-btn-radius layui-btn-danger layui-btn-sm " onclick="del(' + JSON.stringify(item).replace(/"/g, '&quot;') + ')">删除</button></div></div></div></div></div></div>'   
                            })
                            $(".followup-list").html(html);
                        } else {
                            layer.msg("未查询到随访数据");
                        }                                
                    } else {
                        layer.msg("查询失败 "+ Data.message );
                    }
                },
                error: function (xhr, type, errorThrown) {
                    console.log("错误");
                    console.log(errorThrown);
                }
            })
        } 

        //监听同步按钮
        $("#LAY-component-grid-list>.followup-list").on("click",".layui-card-body button.followup-sync",function(){
            var item=$(this).parent().attr("hyd-data");
            var index = layer.load(2, {shade: [0.3,'#fff']}); //0代表加载的风格，支持0-2
            var input = JSON.parse(item);
            $.ajax({
                url: appconfig.SyncPatFollowup.SyncPatFollowupByKey,
                data: JSON.stringify(
                    $.extend({
                        Cd: input.Cd,
                        ZoneCd: input.ZoneCd,
                        OrganCd: input.OrganCd,
                        OperatorCd: tool.getInputbase().UserID
                    }
                    ,tool.getInputbase()
                    )),
                dataType: 'json', //服务器返回json格式数据
                type: 'post', //HTTP请求类型
                headers: {
                    'Content-Type': 'application/json'
                },
                success: function (Data) {
                    if(Data.code=='1'){
                        layer.close(index);
                        layer.alert(Data.data);
                        loadFollowup();
                    }
                    else {
                        layer.close(index);
                        layer.alert(Data.message);
                        loadFollowup();
                    }
                },
                error: function (xhr, type, errorThrown) {
                    layer.close(index);
                    layer.alert("同步失败");
                }
            })    
        });

        //监听删除按钮
        $("#LAY-component-grid-list>.followup-list").on("click",".layui-card-body button.followup-sync-delete",function(){
            var item=$(this).parent().attr("hyd-data");
            var input = JSON.parse(item);
            layer.confirm('是否删除该记录？', {
                btn: ['是','否'] //按钮
                }, function(){
                    var index = layer.load(2, {shade: [0.3,'#fff']}); //0代表加载的风格，支持0-2
                    $.ajax({
                        url: appconfig.SyncPatFollowup.DelPatFollowupByKey,
                        data: JSON.stringify(
                            $.extend({
                                Cd: input.Cd,
                                ZoneCd: input.ZoneCd,
                                OrganCd: input.OrganCd,
                                OperatorCd: tool.getInputbase().UserID
                            }
                            ,tool.getInputbase()
                            )),
                        dataType: 'json', //服务器返回json格式数据
                        type: 'post', //HTTP请求类型
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        success: function (Data) {
                            if(Data.code=='1'){
                                layer.close(index);
                                layer.alert(Data.data);
                                loadFollowup();
                            }
                            else {
                                layer.close(index);
                                layer.alert(Data.message);
                                loadFollowup();
                            }
                        },
                        error: function (xhr, type, errorThrown) {
                            layer.close(index);
                            layer.alert("删除失败");
                        }
                    }) 
                }, function(){
                    layer.closeAll();
            });
        });


    });



    exports('work/cleanfollowup', {})
});