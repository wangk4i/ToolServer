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

        form.render();

        form.on('submit(*)', function (data) {

            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });

        form.on('switch(switchIDCode_Check)', function (data) {
            var str = String(this.checked);
            if (str == "true") {
                $(".switchIDCode").attr("lay-verify", "identity");
                layer.tips('请输入身份证号', data.othis);
            }
            if (str == "false") {
                $(".switchIDCode").removeAttr("lay-verify");
                layer.tips('请输入其它证件号', data.othis)
            }
        });

        //患者查重
        var repeat_patInfo_table = table.render({
            elem: '#repeat_patInfo_table',
            page: false,
            loading: true,
            cols: [
                [{
                    field: 'DischargeInformationId',
                    align: 'center',
                    title: '患者主键'
                }, {
                    field: 'ZoneNam',
                    align: 'center',
                    title: '管理地区'
                }, {
                    field: 'OrgNam',
                    align: 'center',
                    title: '管理单位'
                }, {
                    field: 'PatientName',
                    align: 'center',
                    title: '患者姓名'
                }, {
                    field: 'GenderCode',
                    align: 'center',
                    title: '性别'
                }, {
                    field: 'IDCode',
                    align: 'center',
                    title: '身份证号'
                }, {
                    field: 'BasicInformationNumber',
                    align: 'center',
                    title: '患者编号'
                }, {
                    field: 'LivingAddressDetails',
                    align: 'center',
                    title: '现住址'
                }, {
                    field: 'ContactInformation',
                    align: 'center',
                    title: '联系电话'
                }]
            ],
            data: []
        });
        form.on('submit(repeat_pat_query)', function (input) {
            hdk.ajax({
                api: appconfig.PatInfoServer.GetRepeatInfoByIdcode, 
                data: JSON.stringify(
                    {
                        idcode: input.field.IDNo,
                        idtype: input.field.IDType
                    }
                    )
            }, function (result) {
                repeat_patInfo_table.reload({data:result.data});
            });

            return false;
        });

        //患者删除
        form.on('submit(pat_delete)', function (input) {
            hdk.ajax({
                api: appconfig.PatInfoServer.DeleteBasicInformation,
                data: JSON.stringify($.extend({
                    basicInformationId: input.field.BasicInformationId,
                    deleteCause: input.field.DeleteCause,
                    OperatorCd: tool.getInputbase().UserID
                }
                ,tool.getInputbase()
                ))
            }, function (result) {
                layer.alert('删除成功');
            });
            return false;
        });

        //患者恢复
        form.on('submit(pat_recovery)', function (input) {
            hdk.ajax({
                api: appconfig.PatInfoServer.RecoveryBasicInformation,
                data: JSON.stringify($.extend({
                    basicInformationId: input.field.BasicId,
					recoveryCause: input.field.RecoveryCause,
                    OperatorCd: tool.getInputbase().UserID
                }
                ,tool.getInputbase()
                ))
            }, function (result) {
                layer.alert('恢复成功');
            });
            return false;
        });

        //清空输入框
        function clearcon(index){
            $(index).val('');
        }

        //患者同步
        var sync_patinfo_table=table.render({
            elem: '#sync_patInfo_tables',
            page: false,
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
                    field: 'SyncTime',
                    align: 'center',
                    title: '同步时间'
                }, {
                    field: 'PatCode',
                    align: 'center',
                    title: '患者编号'
                }, {
                    field: 'LAddress',
                    align: 'center',
                    title: '现住址'
                }, {
                    field: 'FIELDPK',
                    align: 'center',
                    title: '国网患者主键'
                }, {
                    field: 'SyncError',
                    align: 'center',
                    title: '错误信息'
                }]
            ],
            data: []
        });
        //新建变量保存表格中的数据
        var objrow;
        //监听查询按钮
        form.on('submit(syncpat_query)', function (o) {
            //获取身份证输入框中的值
            var CardId = $('input[name=CardID]').val();
            if (CardId == undefined) {
                return
            }
            hdk.ajax({
                api: appconfig.PatInfoServer.QueryBasicInformation,
                data: JSON.stringify({
                    CardID: CardId
                })
            }, function (result) {
                objrow = result.data;
                var datas = [
                    result.data
                ];
                //返回数据加载到数据表格中
                sync_patinfo_table.reload({
                    data: datas
                });
            });
        });


        /**
         * 同步按钮点击事件
         */
        $("#patinfo-Syncs").on("click",function(){
            //有数据表示查询成功
            if (!$.isEmptyObject(objrow)) {
                hdk.ajax({
                    api: appconfig.PatInfoServer.SyncBasicInformation,
                    data: JSON.stringify($.extend({
                        Cd: objrow.Cd,
                        ZoneCd: objrow.ZoneCd,
                        OrganCd: objrow.OrganCd,
                        OperatorCd: tool.getInputbase().UserID
                    }
                    ,tool.getInputbase()
                    ))
                }, function (result) {
                    layer.msg(result.message);
                });
            }
        });

        //患者信息
        var gw_patInfo_result=[];
        var base_patinfo_table=table.render({
            elem: '#basicpatInfo_table',
            page: false,
            cols: [
                [{//1650
                    field: 'ID',
                    align: 'center',
                    title: '国网主键',
                    width: '19.7%', //325
                }, {
                    field: 'ZoneCode',
                    align: 'center',
                    title: '管理地区',
                    width: '11.0%', //181
                }, {
                    field: 'OrgCode',
                    align: 'center',
                    title: '管理单位',
                    width: '11.0%', //180
                }, {
                    field: 'PatientName',
                    align: 'center',
                    title: '患者姓名',
                    width: '7.6%', //128
                }, {
                    field: 'IdCard',
                    align: 'center',
                    title: '证件号码',
                    width: '13.0%', //215
                }, {
                    field: 'GenderCode',
                    align: 'center',
                    title: '性别',
                    width: '6.6%', //108
                    templet: function (d) {
                        if (d.GenderCode == "1") {
                            return '<span>男</span>'
                        }
                        if (d.GenderCode == "2") {
                            return '<span>女</span>'
                        }
                        if (d.GenderCode == undefined) {
                            return '<span></span>'
                        }
                    }
                }, {
                    field: 'CurrentAddrDetail',
                    align: 'center',
                    title: '现住址',
                    width: '21.3%', //352
                }, {
                    field: '',
                    align: 'center',
                    title: '操作',
                    minWidth: '145',
                    toolbar: '#basicpatInfobar'
                }]
            ],
            data: []
        });
        $("#btn-query-basic-patInfo").on('click',function(){
            var basicId2 = $("input[name='BasicId2']").val();
            if (basicId2.length == 0) {
                layer.msg("请输入患者国网主键");
            } else {
                hdk.ajax({
                    api: appconfig.PatInfoServer.QueryPatInfoMana,
                    data: JSON.stringify({
                        basicInformationId: basicId2,
                    })
                }, function (result) {
                    // console.log(result.data);
                    gw_patInfo_result = result;
                    var datas = [
                        result.data
                    ];
                    //返回数据加载到数据表格中
                    base_patinfo_table.reload({
                        data: datas
                    });
                });
            }

        });

        /***
         * 监听查看按钮
         */
        //监听行工具事件
        table.on('tool(basicpatInfo_table)', function (obj) {
            var data = obj.data //
                ,
                layEvent = obj.event; //
            if (layEvent === 'detail') {
                layer.open({
                    title: '患者详情',
                    id: 'gw_pat_details',
                    type: 1,
                    area: ['90%', '700px'],
                    // content: $('#gwpatinfotpl_layer').html(),
                    success: function (layero, index) {
                        var getTpl = gwpatinfotpl_layer.innerHTML
                            ,view = document.getElementById('gw_pat_details');
                        laytpl(getTpl).render(gw_patInfo_result, function(html){
                            view.innerHTML = html;
                        });
                    }
                });
                // console.log(JSON.stringify(gw_patInfo_result));
            } else if (layEvent === 'gw_pat_delete'){
                layer.open({
                    type: 1,
                    title: '患者删除',
                    area: ['400px', '320px'],
                    btn: ['删除', '取消'],
                    id: 'layer_pat_delete',
                    content: $('#gw_pat_delete_layer').html(),
                    success: function (layer, index) {
                        form.render();
                    },
                    yes: function(index, layero){
                        var deleteCause = $('#DeleteCause1').val();
                        hdk.ajax({
                            api: appconfig.PatInfoServer.DeleteBasicInformation,
                            data: JSON.stringify($.extend({
                                    basicInformationId: data.ID,
                                    deleteCause: deleteCause
                                }
                                ,tool.getInputbase()
                            ))
                        }, function (result) {
                            if (result.code=='1'){
                                layer.msg('删除成功');
                            } else {
                                layer.msg(result.message);
                            }

                        });
                        layer.close(index);
                        return false;
                    }

                })

            }
        });

        //死亡患者同步
         /**
         * 查询表格初始化
         * */
        var dead_patinfo_table=table.render({
            elem: '#basicDeathpatInfo_table',
            page: false,
            cols: [
                [{//1656
                    field: 'Cd',
                    align: 'center',
                    title: '患者主键',
                    width: '11.0%'
                }, {
                    field: 'PatNam',
                    align: 'center',
                    title: '患者姓名',
                    width: '7.3%'
                }, {
                    field: 'ZoneNam',
                    align: 'center',
                    title: '管理地区',
                    width: '6.91%'
                }, {
                    field: 'OrgNam',
                    align: 'center',
                    title: '管理单位',
                    width: '9.8%'
                }, {
                    field: 'SyncTime',
                    align: 'center',
                    title: '同步时间',
                    width: '10.6%'
                }, {
                    field: 'BaseStatus',
                    align: 'center',
                    title: '患者状态',
                    width: '7.2%'
                }, {
                    field: 'DeathDate',
                    align: 'center',
                    title: '死亡日期',
                    width: '7.9%'
                }, {
                    field: 'DeathCause',
                    align: 'center',
                    title: '死亡原因',
                    width: '7.9%'
                }, {
                    field: 'DeadDetailValue',
                    align: 'center',
                    title: '死亡明细',
                    width: '8.0%'
                }, {
                    field: 'SyncStatus',
                    align: 'center',
                    title: '同步状态',
                    width: '8.2%'
                },{
                    field: 'SyncError',
                    align: 'center',
                    title: '错误信息',
                    // width: '8.2%'
                },{
                    field: '',
                    align: 'center',
                    title: '操作',
                    toolbar: '#Deathbar',
                    width: '5.4%'
                }]
            ],
            data: []
        });
        //监听table的操作栏
        form.on('submit(basicDeathpatInfoByIDCode)', function (o) {
            //获取身份证输入框中的值
            var IDCode = $('input[name=PatIDCode]').val();
            if (IDCode == undefined) {
                return
            }
            hdk.ajax({
                api: appconfig.PatInfoServer.QueryBasicInformation,
                data: JSON.stringify({
                    CardID: IDCode,
                    isDeath: 1
                })
            }, function (result) {
                objrow = result.data;
                var datas = [
                    result.data
                ];
                //返回数据加载到数据表格中
                dead_patinfo_table.reload({
                    data: datas
                });
            });
        });
        //监听行工具事件
        table.on('tool(basicDeathpatInfo_table)', function (obj) {
            var patinfo = obj.data //获得当前行数据
                ,
                layEvent = obj.event; //获得 lay-event 对应的值
            if (layEvent === 'SyncDeath') {
                // layer.msg('同步操作'+JSON.stringify(data));
                /***
                 * 首先调用根据患者Cd,同步国网的数据，调用国网同步接口
                 * @type {string}
                 */
                // ajax({
                //     api:.syncBasicInformation
                //     ,data:{
                //         Cd:data.Cd
                //     }
                // },function(result){
                //     if(result.code == "1") {

                //     } else {
                //         layer.msg("失败", Data.message);
                //     }
                // });
                /***
                 * code == 1表示更新患者信息成功调用患者随访更新接口同步患者随访数据
                 * 
                 */

                hdk.ajax({
                    api: appconfig.PatInfoServer.PatTurnDeathAndFollowup,
                    data: JSON.stringify({
                        PatInfoCd: patinfo.Cd,
                        ZoneCd: patinfo.ZoneCd,
                        OrganCd: patinfo.OrganCd,
                        causeOfDeath: patinfo.DeathCauseCd, //
                        dateOfDeath: patinfo.DeathDate, //死亡日期
                        DeadDetail: patinfo.DeadDetailCd, //躯体疾病
                        OperatorCd: tool.getInputbase().UserID
                    })
                }, function (result) {
                    if (result.code == "1") {
                        layer.msg("患者转死亡成功");
                    } else {
                        layer.msg("失败", result.message);
                    }
                });
            }
        });

    });



    exports('work/viewpatinfo', {})
});

