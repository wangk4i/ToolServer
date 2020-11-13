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


        laydate.render({
            elem: '#starttime'
            , type: 'datetime'
            , value: getRecentDay(-30),//获取前三天时间
        });
        laydate.render({
            elem: '#endtime'
            , type: 'datetime'
            , value: getRecentDay(0),
        });

        //患者信息重发
        var gwresend_patInfo_table = table.render({
            elem: '#resend_patInfo_table',
            page: true,
            limits: [10,20,50,100,200,500,1000],
            cols: [
                [{
                    type: 'checkbox'
                }, {
                    field: 'SyncStatus',
                    align: 'center',
                    title: '状态',
                    width: '4.8%', //77
                    minWidth: '60'
                }, {
                    field: 'SyncTime',
                    align: 'center',
                    title: '同步时间',
                    width: '11.5%' ,//184
                    sort: true,
                    minWidth: '118'
                }, {
                    field: 'SyncError',
                    align: 'center',
                    title: '同步错误',
                    width: '18.8%' ,//300
                    minWidth: '193'
                }, {
                    field: 'Cd',
                    align: 'center',
                    title: '患者主键',
                    width: '14.3%' ,//228
                    minWidth: '142'
                }, {
                    field: 'ZoneNam',
                    align: 'center',
                    title: '地区',
                    width: '6.4%', //102
                    minWidth: '77'
                }, {
                    field: 'ZoneCd',
                    align: 'center',
                    title: '地区编码',
                    hide: true
                }, {
                    field: 'OrganCd',
                    align: 'center',
                    title: '机构编码',
                    hide: true
                }, {
                    field: 'OrgNam',
                    align: 'center',
                    title: '机构' ,
                    width: '11.4%' ,//182
                    minWidth: '114'
                }, {
                    field: 'PatNam',
                    align: 'center',
                    title: '姓名',
                    width: '8.3%' , //133
                    minWidth: '102'
                }, {
                    field: 'IDCode',
                    align: 'center',
                    title: '身份证号',
                    width: '15.6%' , //250
                    minWidth: '145'
                }, {
                    field: '',
                    align: 'center',
                    title: '操作',
                    minWidth: '95',
                    toolbar: '#gwPatInfo_Resend_table_bar'
                }]
            ],
            data: []
        });
        var patResult=[];
        var existErrResult=[];
        //根据条件查询
        form.on('submit(gwresend_pat_query)', function (input) {
            hdk.ajax({
                api: appconfig.GWMsgResend.PatResend.PatInfoQuery,
                data: JSON.stringify({
                        starttime: input.field.starttime,
                        endtime: input.field.endtime,
                        actionType: input.field.actionType,
                        syncStatus: input.field.syncStatus,
                        IDCode: emptyConvert(input.field.IDCode),
                        SyncErr: emptyConvert(input.field.SyncErr)
                    }
                )
            }, function (result) {
                patResult=result.data.patList;
                existErrResult = result.data.existErr;
                initExistErrData();
                gwresend_patInfo_table.reload({data:patResult,page: {curr: 1, limit: 10}});
            });
            return false;
        });

        function initExistErrData(){
            $('#ExistSyncErr').empty();
            $.each(existErrResult, function (index, item) {
                $('#ExistSyncErr').append(new Option(item));
            })
            form.render();
        }

        //选择已有错误
        form.on('select(gwresend_err_select)', function (data) {
            $('#SyncErr').val($(this).text());
        })

        //批量重发
        form.on('submit(gwresend_pat_resend)', function (obj) {
            var checkStatus = table.checkStatus('resend_patInfo_table');
            var data = checkStatus.data;
            if (data.length===0){
                return false;
            }
            hdk.ajax({
                api: appconfig.GWMsgResend.PatResend.PatInfoBatchResend,
                data: JSON.stringify({
                    PatInfoList: data,
                    OperatorCd: tool.getInputbase().UserID
                })
            }, function (result) {
                if (result.code==1){
                    patResult = delSent(patResult, data, 'Cd');
                    gwresend_patInfo_table.reload({data:patResult});
                } else {
                    layer.msg(result.message);
                }
            });
            return false;
        });

        //单条重发按钮
        table.on('tool(resend_patInfo_table)', function (obj) {
            var data = obj.data;
            if (obj.event === 'resend') {
                hdk.ajax({
                    api: appconfig.GWMsgResend.PatResend.PatInfoResend,
                    data :JSON.stringify({
                        Cd: data.Cd,
                        ZoneCd: data.ZoneCd,
                        OrganCd: data.OrganCd,
                        OperatorCd: tool.getInputbase().UserID
                    })

                }, function (result) {
                    if (result.code ==1){
                        var rightIndex = tool.findIndex(patResult, data, 'Cd')
                        patResult.splice(rightIndex,1);
                        gwresend_patInfo_table.reload({data:patResult});
                    } else {
                        layer.msg(result.message);
                    }
                });
                return false;
            } else if(obj.event === 'reset') {
                hdk.ajax({
                    api: appconfig.GWMsgResend.PatResend.ResetInspectYear,
                    data :JSON.stringify({
                        Cd: data.Cd
                    })
                }, function (result) {
                    if (result.code ==1){
                        var rightIndex = tool.findIndex(patResult, data, 'Cd')
                        patResult.splice(rightIndex,1);
                        gwresend_patInfo_table.reload({data:patResult});
                    } else {
                        layer.msg(result.message);
                    }
                });
                return false;
            }
        });


        //====================================================================================================================================================================================================================================================================================================

        laydate.render({
            elem: '#followup_starttime'
            , type: 'datetime'
            , value: getRecentDay(-30),//获取前三天时间
        });
        laydate.render({
            elem: '#followup_endtime'
            , type: 'datetime'
            , value: getRecentDay(0),
        });

        //随访信息重发
        var gwresend_followupInfo_table = table.render({
            elem: '#resend_followupInfo_table',
            page: true,
            limits: [10,20,50,100,200,500,1000],
            cols: [
                [{
                    type: 'checkbox'
                }, {
                    field: 'SyncStatus',
                    align: 'center',
                    title: '状态',
                    width: '4.2%', //67
                    minWidth: '60'
                }, {
                    field: 'SyncTime',
                    align: 'center',
                    title: '同步时间',
                    width: '11.4%' ,//182
                    sort: true,
                    minWidth: '118'
                }, {
                    field: 'SyncError',
                    align: 'center',
                    title: '同步错误',
                    width: '18.6%' ,//298
                    minWidth: '193'
                }, {
                    field: 'Cd',
                    align: 'center',
                    title: '随访主键',
                    width: '14.4%' ,//230
                    minWidth: '142'
                }, {
                    field: 'ZoneNam',
                    align: 'center',
                    title: '地区',
                    width: '5.6%', //92
                    minWidth: '77'
                }, {
                    field: 'ZoneCd',
                    align: 'center',
                    title: '地区编码',
                    hide: true
                }, {
                    field: 'OrganCd',
                    align: 'center',
                    title: '机构编码',
                    hide: true
                }, {
                    field: 'OrgNam',
                    align: 'center',
                    title: '机构' ,
                    width: '12.5%' ,//200
                    minWidth: '114'
                }, {
                    field: 'PatInfoCd',
                    align: 'center',
                    title: '患者主键',
                    hide: true
                }, {
                    field: 'PatNam',
                    align: 'center',
                    title: '姓名',
                    width: '6.4%' , //102
                    minWidth: '102'
                }, {
                    field: 'IDCode',
                    align: 'center',
                    title: '身份证号',
                    width: '12.8%' , //204
                    minWidth: '145'
                }, {
                    field: 'InformedConsValue',
                    align: 'center',
                    title: '知情同意',
                    width: '5.5%' , //88
                }, {
                    field: '',
                    align: 'center',
                    title: '操作',
                    minWidth: '90',
                    toolbar: '#gwFollowupInfo_Resend_table_bar'
                }]
            ],
            data: []
        });

        var followupResult=[];
        var followupExistErrResult=[];
        //根据条件查询
        form.on('submit(gwresend_followup_query)', function (input) {
            hdk.ajax({
                api: appconfig.GWMsgResend.FollowupResend.FollowupInfoQuery,
                data: JSON.stringify({
                        starttime: input.field.followup_starttime,
                        endtime: input.field.followup_endtime,
                        actionType: input.field.followup_actionType,
                        syncStatus: input.field.followup_syncStatus,
                        IDCode: emptyConvert(input.field.followup_IDCode),
                        SyncErr: emptyConvert(input.field.followup_SyncErr)
                    }
                )
            }, function (result) {
                followupResult=result.data.followupList;
                followupExistErrResult = result.data.existErr;
                initFollowupExistErrData();
                gwresend_followupInfo_table.reload({data:followupResult,page: {curr: 1, limit: 10}});
            });
            return false;
        });

        function initFollowupExistErrData(){
            $('#followup_ExistSyncErr').empty();
            $.each(followupExistErrResult, function (index, item) {
                $('#followup_ExistSyncErr').append(new Option(item));
            })
            form.render();
        }

        //随访选择已有错误
        form.on('select(gwresend_followup_err_select)', function (data) {
            $('#followup_SyncErr').val($(this).text());
        })

        //批量重发
        form.on('submit(gwresend_followup_resend)', function (obj) {
            var checkStatus = table.checkStatus('resend_followupInfo_table');
            var data = checkStatus.data;
            if (data.length===0){
                return false;
            }
            hdk.ajax({
                api: appconfig.GWMsgResend.FollowupResend.FollowupInfoBatchResend,
                data: JSON.stringify({
                    FollowupInfoList: data,
                    OperatorCd: tool.getInputbase().UserID
                })
            }, function (result) {
                if (result.code==1){
                    followupResult = delSent(followupResult, data, 'Cd');
                    gwresend_followupInfo_table.reload({data:followupResult});
                } else {
                    layer.msg(result.message);
                }
            });
            return false;
        });

        //患者批量重发
        form.on('submit(gwresend_followPat_resend)', function (obj) {
            var checkStatus = table.checkStatus('resend_followupInfo_table');
            var data = checkStatus.data;
            if (data.length===0){
                return false;
            }
            hdk.ajax({
                api: appconfig.GWMsgResend.FollowupResend.FollowPatBatchResend,
                data: JSON.stringify({
                    FollowupInfoList: data,
                    OperatorCd: tool.getInputbase().UserID
                })
            }, function (result) {
                if (result.code==1){
           
                } else {
                    layer.msg(result.message);
                }
            });
            return false;
        });


        //单条重发按钮
        table.on('tool(resend_followupInfo_table)', function (obj) {
            var data = obj.data;
            if (obj.event === 'followup_resend') {
                hdk.ajax({
                    api: appconfig.GWMsgResend.FollowupResend.FollowupInfoResend,
                    data :JSON.stringify({
                        Cd: data.Cd,
                        ZoneCd: data.ZoneCd,
                        OrganCd: data.OrganCd,
                        OperatorCd: tool.getInputbase().UserID
                    })

                }, function (result) {
                    if (result.code ==1){
                        var rightIndex = tool.findIndex(followupResult, data, 'Cd')
                        followupResult.splice(rightIndex,1);
                        gwresend_followupInfo_table.reload({data:followupResult});
                    } else {
                        layer.msg(result.message);
                    }
                });
                return false;
            }
        });

        //============================================================================================================================================================================================================================================================
        //报告卡
        laydate.render({
            elem: '#report_starttime'
            , type: 'datetime'
            , value: getRecentDay(-30),//获取前三天时间
        });
        laydate.render({
            elem: '#report_endtime'
            , type: 'datetime'
            , value: getRecentDay(0),
        });
        //患者信息重发
        var gwresend_reportInfo_table = table.render({
            elem: '#resend_report_table',
            page: true,
            limits: [10,20,50,100,200,500,1000],
            cols: [
                [{
                    type: 'checkbox'
                }, {
                    field: 'SyncStatus',
                    align: 'center',
                    title: '状态',
                    width: '4.8%', //77
                    minWidth: '60'
                }, {
                    field: 'SyncTime',
                    align: 'center',
                    title: '同步时间',
                    width: '11.5%' ,//184
                    sort: true,
                    minWidth: '118'
                }, {
                    field: 'SyncError',
                    align: 'center',
                    title: '同步错误',
                    width: '18.8%' ,//300
                    minWidth: '193'
                }, {
                    field: 'Cd',
                    align: 'center',
                    title: '报告卡主键',
                    width: '14.3%' ,//228
                    minWidth: '142'
                }, {
                    field: 'ZoneNam',
                    align: 'center',
                    title: '地区',
                    width: '6.4%', //102
                    minWidth: '77'
                }, {
                    field: 'ZoneCd',
                    align: 'center',
                    title: '地区编码',
                    hide: true
                }, {
                    field: 'OrganCd',
                    align: 'center',
                    title: '机构编码',
                    hide: true
                }, {
                    field: 'OrgNam',
                    align: 'center',
                    title: '机构' ,
                    width: '11.4%' ,//182
                    minWidth: '114'
                }, {
                    field: 'PatNam',
                    align: 'center',
                    title: '姓名',
                    width: '8.3%' , //133
                    minWidth: '102'
                }, {
                    field: 'IDCode',
                    align: 'center',
                    title: '身份证号',
                    width: '15.6%' , //250
                    minWidth: '145'
                }, {
                    field: '',
                    align: 'center',
                    title: '操作',
                    minWidth: '95',
                    toolbar: '#gwReportInfo_Resend_table_bar'
                }]
            ],
            data: []
        });
        var reportResult=[];
        var reportExistErrResult=[];
        //根据条件查询
        form.on('submit(gwresend_report_query)', function (input) {
            hdk.ajax({
                api: appconfig.GWMsgResend.ReportResend.ReportInfoQuery,
                data: JSON.stringify({
                        starttime: input.field.report_starttime,
                        endtime: input.field.report_endtime,
                        actionType: input.field.report_actionType,
                        syncStatus: input.field.report_syncStatus,
                        IDCode: emptyConvert(input.field.report_IDCode),
                        SyncErr: emptyConvert(input.field.report_SyncErr)
                    }
                )
            }, function (result) {
                reportResult=result.data.reportList;
                reportExistErrResult = result.data.existErr;
                initReportExistErrData();
                gwresend_reportInfo_table.reload({data:reportResult,page: {curr: 1, limit: 10}});
            });
            return false;
        });

        function initReportExistErrData(){
            $('#report_ExistSyncErr').empty();
            $.each(reportExistErrResult, function (index, item) {
                $('#report_ExistSyncErr').append(new Option(item));
            })
            form.render();
        }

        //随访选择已有错误
        form.on('select(gwresend_report_err_select)', function (data) {
            $('#report_SyncErr').val($(this).text());
        })

        //批量重发
        form.on('submit(gwresend_report_resend)', function (obj) {
            var checkStatus = table.checkStatus('resend_report_table');
            var data = checkStatus.data;
            if (data.length===0){
                return false;
            }
            hdk.ajax({
                api: appconfig.GWMsgResend.ReportResend.ReportInfoBatchResend,
                data: JSON.stringify({
                    InfoList: data,
                    OperatorCd: tool.getInputbase().UserID
                })
            }, function (result) {
                if (result.code==1){
                    reportResult = delSent(reportResult, data, 'Cd');
                    gwresend_reportInfo_table.reload({data:reportResult});
                } else {
                    layer.msg(result.message);
                }
            });
            return false;
        });

        //单条重发按钮
        table.on('tool(resend_report_table)', function (obj) {
            var data = obj.data;
            if (obj.event === 'report_resend') {
                hdk.ajax({
                    api: appconfig.GWMsgResend.ReportResend.ReportInfoResend,
                    data :JSON.stringify({
                        Cd: data.Cd,
                        ZoneCd: data.ZoneCd,
                        OrganCd: data.OrganCd,
                        OperatorCd: tool.getInputbase().UserID
                    })

                }, function (result) {
                    if (result.code ==1){
                        var rightIndex = tool.findIndex(reportResult, data, 'Cd')
                        reportResult.splice(rightIndex,1);
                        gwresend_reportInfo_table.reload({data:reportResult});
                    } else {
                        layer.msg(result.message);
                    }
                });
                return false;
            }
        });

        //============================================================================================================================================================================================================================================================
        //出院单
        laydate.render({
            elem: '#discharge_starttime'
            , type: 'datetime'
            , value: getRecentDay(-30),//获取前三天时间
        });
        laydate.render({
            elem: '#discharge_endtime'
            , type: 'datetime'
            , value: getRecentDay(0),
        });
        //患者信息重发
        var gwresend_dischargeInfo_table = table.render({
            elem: '#resend_discharge_table',
            page: true,
            limits: [10,20,50,100,200,500,1000],
            cols: [
                [{
                    type: 'checkbox'
                }, {
                    field: 'SyncStatus',
                    align: 'center',
                    title: '状态',
                    width: '4.8%', //77
                    minWidth: '60'
                }, {
                    field: 'SyncTime',
                    align: 'center',
                    title: '同步时间',
                    width: '11.5%' ,//184
                    sort: true,
                    minWidth: '118'
                }, {
                    field: 'SyncError',
                    align: 'center',
                    title: '同步错误',
                    width: '18.8%' ,//300
                    minWidth: '193'
                }, {
                    field: 'Cd',
                    align: 'center',
                    title: '出院单主键',
                    width: '14.3%' ,//228
                    minWidth: '142'
                }, {
                    field: 'ZoneNam',
                    align: 'center',
                    title: '地区',
                    width: '6.4%', //102
                    minWidth: '77'
                }, {
                    field: 'ZoneCd',
                    align: 'center',
                    title: '地区编码',
                    hide: true
                }, {
                    field: 'OrganCd',
                    align: 'center',
                    title: '机构编码',
                    hide: true
                }, {
                    field: 'OrgNam',
                    align: 'center',
                    title: '机构' ,
                    width: '11.4%' ,//182
                    minWidth: '114'
                }, {
                    field: 'PatNam',
                    align: 'center',
                    title: '姓名',
                    width: '8.3%' , //133
                    minWidth: '102'
                }, {
                    field: 'IDCode',
                    align: 'center',
                    title: '身份证号',
                    width: '15.6%' , //250
                    minWidth: '145'
                }, {
                    field: '',
                    align: 'center',
                    title: '操作',
                    minWidth: '95',
                    toolbar: '#gwDischargeInfo_Resend_table_bar'
                }]
            ],
            data: []
        });
        var dischargeResult=[];
        var dischargeExistErrResult=[];
        //根据条件查询
        form.on('submit(gwresend_discharge_query)', function (input) {
            hdk.ajax({
                api: appconfig.GWMsgResend.DischargeResend.DischargeInfoQuery,
                data: JSON.stringify({
                        starttime: input.field.discharge_starttime,
                        endtime: input.field.discharge_endtime,
                        actionType: input.field.discharge_actionType,
                        syncStatus: input.field.discharge_syncStatus,
                        IDCode: emptyConvert(input.field.discharge_IDCode),
                        SyncErr: emptyConvert(input.field.discharge_SyncErr)
                    }
                )
            }, function (result) {
                dischargeResult=result.data.dischargeList;
                dischargeExistErrResult = result.data.existErr;
                initDischargeExistErrData();
                gwresend_dischargeInfo_table.reload({data:dischargeResult,page: {curr: 1, limit: 10}});
            });
            return false;
        });

        function initDischargeExistErrData(){
            $('#discharge_ExistSyncErr').empty();
            $.each(dischargeExistErrResult, function (index, item) {

                $('#discharge_ExistSyncErr').append(new Option(item));
            })
            form.render();
        }

        //出院单选择已有错误
        form.on('select(gwresend_discharge_err_select)', function (data) {
            $('#discharge_SyncErr').val($(this).text());
        })

        //批量重发
        form.on('submit(gwresend_discharge_resend)', function (obj) {
            var checkStatus = table.checkStatus('resend_discharge_table');
            var data = checkStatus.data;
            if (data.length===0){
                return false;
            }
            hdk.ajax({
                api: appconfig.GWMsgResend.DischargeResend.DischargeInfoBatchResend,
                data: JSON.stringify({
                    InfoList: data,
                    OperatorCd: tool.getInputbase().UserID
                })
            }, function (result) {
                if (result.code==1){
                    dischargeResult = delSent(dischargeResult, data, 'Cd');
                    gwresend_dischargeInfo_table.reload({data:dischargeResult});
                } else {
                    layer.msg(result.message);
                }
            });
            return false;
        });

        //单条重发按钮
        table.on('tool(resend_discharge_table)', function (obj) {
            var data = obj.data;
            if (obj.event === 'discharge_resend') {
                hdk.ajax({
                    api: appconfig.GWMsgResend.DischargeResend.DischargeInfoResend,
                    data :JSON.stringify({
                        Cd: data.Cd,
                        ZoneCd: data.ZoneCd,
                        OrganCd: data.OrganCd,
                        OperatorCd: tool.getInputbase().UserID
                    })

                }, function (result) {
                    if (result.code ==1){
                        var rightIndex = tool.findIndex(dischargeResult, data, 'Cd')
                        dischargeResult.splice(rightIndex,1);
                        gwresend_dischargeInfo_table.reload({data:dischargeResult});
                    } else {
                        layer.msg(result.message);
                    }
                });
                return false;
            }
        });

        //============================================================================================================================================================================================================================================================
        //应急处置
        laydate.render({
            elem: '#emergency_starttime'
            , type: 'datetime'
            , value: getRecentDay(-30),//获取前三天时间
        });
        laydate.render({
            elem: '#emergency_endtime'
            , type: 'datetime'
            , value: getRecentDay(0),
        });
        //患者信息重发
        var gwresend_emergencyInfo_table = table.render({
            elem: '#resend_emergency_table',
            page: true,
            limits: [10,20,50,100,200,500,1000],
            cols: [
                [{
                    type: 'checkbox'
                }, {
                    field: 'SyncStatus',
                    align: 'center',
                    title: '状态',
                    width: '4.8%', //77
                    minWidth: '60'
                }, {
                    field: 'SyncTime',
                    align: 'center',
                    title: '同步时间',
                    width: '11.5%' ,//184
                    sort: true,
                    minWidth: '118'
                }, {
                    field: 'SyncError',
                    align: 'center',
                    title: '同步错误',
                    width: '18.8%' ,//300
                    minWidth: '193'
                }, {
                    field: 'Cd',
                    align: 'center',
                    title: '应急处置主键',
                    width: '14.3%' ,//228
                    minWidth: '142'
                }, {
                    field: 'ZoneNam',
                    align: 'center',
                    title: '地区',
                    width: '6.4%', //102
                    minWidth: '77'
                }, {
                    field: 'ZoneCd',
                    align: 'center',
                    title: '地区编码',
                    hide: true
                }, {
                    field: 'OrganCd',
                    align: 'center',
                    title: '机构编码',
                    hide: true
                }, {
                    field: 'OrgNam',
                    align: 'center',
                    title: '机构' ,
                    width: '11.4%' ,//182
                    minWidth: '114'
                }, {
                    field: 'PatNam',
                    align: 'center',
                    title: '姓名',
                    width: '8.3%' , //133
                    minWidth: '102'
                }, {
                    field: 'IDCode',
                    align: 'center',
                    title: '身份证号',
                    width: '15.6%' , //250
                    minWidth: '145'
                }, {
                    field: '',
                    align: 'center',
                    title: '操作',
                    minWidth: '95',
                    toolbar: '#gwEmergencyInfo_Resend_table_bar'
                }]
            ],
            data: []
        });
        var emergencyResult=[];
        var emergencyExistErrResult=[];
        //根据条件查询
        form.on('submit(gwresend_emergency_query)', function (input) {
            hdk.ajax({
                api: appconfig.GWMsgResend.EmergencyResend.EmergencyInfoQuery,
                data: JSON.stringify({
                        starttime: input.field.emergency_starttime,
                        endtime: input.field.emergency_endtime,
                        actionType: input.field.emergency_actionType,
                        syncStatus: input.field.emergency_syncStatus,
                        IDCode: emptyConvert(input.field.emergency_IDCode),
                        SyncErr: emptyConvert(input.field.emergency_SyncErr)
                    }
                )
            }, function (result) {
                emergencyResult=result.data.emergencyList;
                emergencyExistErrResult = result.data.existErr;
                initEmergencyExistErrData();
                gwresend_emergencyInfo_table.reload({data:emergencyResult,page: {curr: 1, limit: 10}});
            });
            return false;
        });

        function initEmergencyExistErrData(){
            $('#emergency_ExistSyncErr').empty();
            $.each(emergencyExistErrResult, function (index, item) {
                $('#emergency_ExistSyncErr').append(new Option(item));
            })
            form.render();
        }

        //随访选择已有错误
        form.on('select(gwresend_emergency_err_select)', function (data) {
            $('#emergency_SyncErr').val($(this).text());
        })

        //批量重发
        form.on('submit(gwresend_emergency_resend)', function (obj) {
            var checkStatus = table.checkStatus('resend_emergency_table');
            var data = checkStatus.data;
            if (data.length===0){
                return false;
            }
            hdk.ajax({
                api: appconfig.GWMsgResend.EmergencyResend.EmergencyInfoBatchResend,
                data: JSON.stringify({
                    InfoList: data,
                    OperatorCd: tool.getInputbase().UserID
                })
            }, function (result) {
                if (result.code==1){
                    emergencyResult = delSent(emergencyResult, data, 'Cd');
                    gwresend_emergencyInfo_table.reload({data:emergencyResult});
                } else {
                    layer.msg(result.message);
                }
            });
            return false;
        });

        //单条重发按钮
        table.on('tool(resend_emergency_table)', function (obj) {
            var data = obj.data;
            if (obj.event === 'emergency_resend') {
                hdk.ajax({
                    api: appconfig.GWMsgResend.EmergencyResend.EmergencyInfoResend,
                    data :JSON.stringify({
                        Cd: data.Cd,
                        ZoneCd: data.ZoneCd,
                        OrganCd: data.OrganCd,
                        OperatorCd: tool.getInputbase().UserID
                    })

                }, function (result) {
                    if (result.code ==1){
                        var rightIndex = tool.findIndex(emergencyResult, data, 'Cd')
                        emergencyResult.splice(rightIndex,1);
                        gwresend_emergencyInfo_table.reload({data:emergencyResult});
                    } else {
                        layer.msg(result.message);
                    }
                });
                return false;
            }
        });

        //============================================================================================================================================================================================================================================================
        //输入框为空转为null
        function emptyConvert(index) {
            if (index === "" || index == null){
                index = null;
            }
            return index;
        }

        //删除已发送
        function delSent(list, sentList, attrNam) {
            for (let i=0; i<sentList.length; i++){
                let rightIndex = tool.findIndex(list, sentList[i], attrNam);
                list.splice(rightIndex,1);
            }
            return list;
        }

        //时间输入框赋初值
        function getRecentDay(day) {
            var today = new Date();
            var targetday_milliseconds = today.getTime() + 1000 * 60 * 60 * 24 * day;
            today.setTime(targetday_milliseconds);
            var tYear = today.getFullYear();
            var tMonth = today.getMonth();
            var tDate = today.getDate();
            var tHours = today.getHours();
            var tMinutes = today.getMinutes();
            var tSeconds = today.getSeconds();
            tMonth = doHandleMonth(tMonth + 1);
            tDate = doHandleMonth(tDate);
            tHours = doHandleMonth(tHours);
            tMinutes = doHandleMonth(tMinutes);
            tSeconds = doHandleMonth(tSeconds);

            return tYear + "-" + tMonth + "-" + tDate + ' ' + tHours + ':' + tMinutes + ':' + tSeconds;
        }
        function doHandleMonth(month) {
            var m = month;
            if (month.toString().length == 1) {
                m = "0" + month;
            }
            return m;
        }


    });

    exports('work/gwmsgresend', {})

});