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
        element.render();

        //患者查询
        var InputobjPatInfo='';
        form.on('submit(gw_patinfo_query)', function (input) {
            InputobjPatInfo='';
            InputobjPatInfo = {
                basicInformationId: input.field.patCd
            }
            $.ajax({
                url: appconfig.GWInfoQuery.GetBasicInfoById,
                data: JSON.stringify(InputobjPatInfo),
                dataType: 'json', //服务器返回json格式数据
                type: 'post', //HTTP请求类型
                headers: {
                    'Content-Type': 'application/json'
                },
                success: function (Data) {
                    if (Data.code=='1'){
                        var getTpl = gwpatinfotpl.innerHTML
                            ,view = document.getElementById('gw_patinfo_view');
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


        //随访查询
        var InputobjFollowup='';
        var InputFollowPatCd='';
        form.on('submit(gw_followup_query)', function (input) {
            InputFollowPatCd=input.field.followPatCd;
            InputobjFollowup = {
                basicInformationId: InputFollowPatCd
            }
            $.ajax({
                url: appconfig.GWInfoQuery.GetBasicInfoById,
                data: JSON.stringify(InputobjFollowup),
                dataType: 'json', //服务器返回json格式数据
                type: 'post', //HTTP请求类型
                headers: {
                    'Content-Type': 'application/json'
                },
                success: function (Data) {

                    if(Data.code=='1'){

                            var follow_patinfo_html=''
                            var item = Data.data;
                            follow_patinfo_html +='<div class="layui-col-md12"><div class="layui-card"><div class="layui-card-body">'
                                +'<div class="layui-form">'
                                +'<div class="layui-inline"><label class="layui-form-label">患者主键：</label><div class="layui-input-inline" style="padding-top: 6px;min-width: 150px;">'+((item.ID == undefined) ? "" : item.ID)+'</div></div>'
                                +'<div class="layui-inline"><label class="layui-form-label">患者编号：</label><div class="layui-input-inline" style="padding-top: 6px;min-width: 150px;">'+((item.PatientNo == undefined) ? "" : item.PatientNo)+'</div></div>'
                                +'<div class="layui-inline"><label class="layui-form-label">管理地区：</label><div class="layui-input-inline" style="padding-top: 6px;min-width: 150px;">'+((item.ZoneCode == undefined) ? "" : item.ZoneCode)+'</div></div>'
                                +'<div class="layui-inline"><label class="layui-form-label">管理单位：</label><div class="layui-input-inline" style="padding-top: 6px;min-width: 150px;">'+((item.OrgCode == undefined) ? "" : item.OrgCode)+'</div></div>'
                                +'<div class="layui-inline"><label class="layui-form-label">姓名：</label><div class="layui-input-inline" style="padding-top: 6px;min-width: 150px;">'+((item.PatientName == undefined) ? "" : item.PatientName)+'</div></div>'
                                +'<div class="layui-inline"><label class="layui-form-label">性别：</label><div class="layui-input-inline" style="padding-top: 6px;min-width: 150px;">'+((item.GenderCode == undefined) ? "" : item.GenderCode)+'</div></div>'

                                +'<div class="layui-inline"><label class="layui-form-label">身份证号：</label><div class="layui-input-inline" style="padding-top: 6px;min-width: 150px;">'+((item.IdCard == undefined) ? "" : item.IdCard)+'</div></div>'
                                +'<div class="layui-inline"><label class="layui-form-label">出生日期：</label><div class="layui-input-inline" style="padding-top: 6px;min-width: 150px;">'+((item.BirthDate == undefined) ? "" : item.BirthDate)+'</div></div>'
                                +'<div class="layui-inline"><label class="layui-form-label">知情同意：</label><div class="layui-input-inline" style="padding-top: 6px;min-width: 150px;">'+((item.PsychogenyInformedCode == undefined) ? "" : item.PsychogenyInformedCode)+'</div></div>'
                                +'<div class="layui-inline"><label class="layui-form-label">知情同意书签字时间：</label><div class="layui-input-inline" style="padding-top: 6px;min-width: 150px;">'+((item.PsychogenyInformedDate == undefined) ? "" : item.PsychogenyInformedDate)+'</div></div>'

                                +'</div>'
                                +'</div></div></div>'
                            $(".gw-follow-patinfo-list").html(follow_patinfo_html);

                    } else {
                        layer.msg('请求失败: ' + Data.message);
                    }
                },
                error: function (xhr, type, errorThrown) {
                    console.log("错误");
                    console.log(errorThrown);
                }
            })

            loadFollowup();

            return false;
        });

        var this_follow_page = 1;
        function loadFollowup(){
            $.ajax({
                url: appconfig.GWInfoQuery.GetRelatedFollowupInfo,
                data: JSON.stringify(InputobjFollowup),
                dataType: 'json', //服务器返回json格式数据
                type: 'post', //HTTP请求类型
                headers: {
                    'Content-Type': 'application/json'
                },
                success: function (Data) {

                    if (Data.code=='1'){
                        if (null != Data.data.FollowupInfoList && Data.data.FollowupInfoList.length > 0) {
                            view_show("gw_followup_view");
                            var getTpl = gwfollowuptpl.innerHTML
                                , view = document.getElementById('gw_followup_view');
                            laytpl(getTpl).render(Data, function (html) {
                                view.innerHTML = html;
                            });
                            element.render();
                            if(Data.data.pages > this_follow_page){
                                more_onclick("followup_more_click");
                            }
                        } else {
                            // layer.msg('该患者未存在随访记录');
                        }
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

        function more_onclick(objId){
            document.getElementById(objId).style.display="block";
        }
        function more_close(objId){
            document.getElementById(objId).style.display="none";
        }

        function view_hide(objId){
            var text_1=document.getElementById(objId);
            text_1.setAttribute("hidden",true);
        }

        function view_show(objId){
            var text_2=document.getElementById(objId);
            text_2.removeAttribute("hidden");
        }

        //随访more按钮
        form.on('submit(gw_followup_more)', function (input) {
            hdk.ajax({
                api: appconfig.GWInfoQuery.GetRelatedFollowupInfo,
                data: JSON.stringify({
                        basicInformationId: InputFollowPatCd,
                        pageNum: ++this_follow_page
                    })
            }, function (result) {
                var getTpl = gwfollowuptpl.innerHTML;
                laytpl(getTpl).render(result, function(html){
                    $('#gw_followup_view').append(html);
                });
                element.render();
                if(result.data.pages === this_follow_page){
                    more_close("followup_more_click");
                }

            });
            return false;
        });

        //报告卡查询
        var InputobjCaseReport='';
        form.on('submit(gw_casereport_query)', function (input) {
            InputobjCaseReport='';
            InputobjCaseReport = {
                newCaseReportId: input.field.reportCd
            }
            $.ajax({
                url: appconfig.GWInfoQuery.GetNewCaseReportById,
                data: JSON.stringify(InputobjCaseReport),
                dataType: 'json', //服务器返回json格式数据
                type: 'post', //HTTP请求类型
                headers: {
                    'Content-Type': 'application/json'
                },
                success: function (Data) {
                    if (Data.code=='1'){
                        var getTpl = gwreporttpl.innerHTML
                            ,view = document.getElementById('gw_report_view');
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

        //根据患者主键查报告卡
        var InputReportPatCd='';
        var this_report_page= 1;
        form.on('submit(gw_casereport_queryByPat)', function (input) {
            InputReportPatCd = input.field.report_patCd;
            $.ajax({
                url: appconfig.GWInfoQuery.GetRelatedNewCaseReport,
                data: JSON.stringify({
                    basicInformationId: InputReportPatCd
                }),
                dataType: 'json', //服务器返回json格式数据
                type: 'post', //HTTP请求类型
                headers: {
                    'Content-Type': 'application/json'
                },
                success: function (Data) {
                    if (Data.code=='1'){
                        if (null != Data.data.DiseaseInfoList && Data.data.DiseaseInfoList.length > 0) {
                            view_show('gw_report_pat_view');
                            var getTpl = gwreportpattpl.innerHTML
                                , view = document.getElementById('gw_report_pat_view');
                            laytpl(getTpl).render(Data, function (html) {
                                view.innerHTML = html;
                            });
                            element.render();
                            if (Data.data.pages > this_report_page) {
                                more_onclick("report_more_click");
                            }
                        } else {
                            layer.msg('该患者未存在报告卡记录');
                        }
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
        //报告卡more按钮
        form.on('submit(gw_report_more)', function (input) {
            hdk.ajax({
                api: appconfig.GWInfoQuery.GetRelatedNewCaseReport,
                data: JSON.stringify({
                    basicInformationId: InputReportPatCd,
                    pageNum: ++this_report_page
                })
            }, function (result) {
                var getTpl = gwreportpattpl.innerHTML;
                laytpl(getTpl).render(result, function(html){
                    $('#gw_report_pat_view').append(html);
                });
                element.render();
                if(result.data.pages === this_report_page){
                    more_close("report_more_click");
                }

            });
            return false;
        });

        //出院单查询
        var InputobjDischarge='';
        form.on('submit(gw_discharge_query)', function (input) {
            InputobjDischarge = {
                dischargeInformationId: input.field.dischargeCd
            }
            $.ajax({
                url: appconfig.GWInfoQuery.GetDischargeInfoById,
                data: JSON.stringify(InputobjDischarge),
                dataType: 'json', //服务器返回json格式数据
                type: 'post', //HTTP请求类型
                headers: {
                    'Content-Type': 'application/json'
                },
                success: function (Data) {
                    if (Data.code=='1'){
                        var getTpl = gwdischargetpl.innerHTML
                            ,view = document.getElementById('gw_discharge_view');
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

        //根据患者主键查出院单
        var InputDischargePatCd='';
        var this_discharge_page= 1;
        form.on('submit(gw_discharge_queryByPat)', function (input) {
            InputDischargePatCd = input.field.discharge_patCd;
            $.ajax({
                url: appconfig.GWInfoQuery.GetRelatedDischargeInformation,
                data: JSON.stringify({
                    basicInformationId: InputDischargePatCd
                }),
                dataType: 'json', //服务器返回json格式数据
                type: 'post', //HTTP请求类型
                headers: {
                    'Content-Type': 'application/json'
                },
                success: function (Data) {
                    if (Data.code=='1'){
                        if (null != Data.data.TreatmentInfoList && Data.data.TreatmentInfoList.length > 0) {
                            view_show('gw_discharge_pat_view');
                            var getTpl = gwdischargepattpl.innerHTML
                                , view = document.getElementById('gw_discharge_pat_view');
                            laytpl(getTpl).render(Data, function (html) {
                                view.innerHTML = html;
                            });
                            element.render();
                            if (Data.data.pages > this_discharge_page) {
                                more_onclick("discharge_more_click");
                            }
                        } else {
                            layer.msg('该患者未存在出院信息记录');
                        }
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
        //出院单more按钮
        form.on('submit(gw_discharge_more)', function (input) {
            hdk.ajax({
                api: appconfig.GWInfoQuery.GetRelatedDischargeInformation,
                data: JSON.stringify({
                    basicInformationId: InputDischargePatCd,
                    pageNum: ++this_discharge_page
                })
            }, function (result) {
                var getTpl = gwdischargepattpl.innerHTML;
                laytpl(getTpl).render(result, function(html){
                    $('#gw_discharge_pat_view').append(html);
                });
                element.render();
                if(result.data.pages === this_discharge_page){
                    more_close("discharge_more_click");
                }

            });
            return false;
        });

        //应急处置查询
        var InputobjEmerDeal='';
        form.on('submit(gw_emerdeal_query)', function (input) {
            InputobjEmerDeal = {
                emergencyId: input.field.emerDealCd
            }
            $.ajax({
                url: appconfig.GWInfoQuery.GetEmerDealById,
                data: JSON.stringify(InputobjEmerDeal),
                dataType: 'json', //服务器返回json格式数据
                type: 'post', //HTTP请求类型
                headers: {
                    'Content-Type': 'application/json'
                },
                success: function (Data) {
                    if (Data.code=='1'){
                        var getTpl = gwemerdealtpl.innerHTML
                            ,view = document.getElementById('gw_emerdeal_view');
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

        //根据患者身份证查应急处置
        var InputEmerDealPatCd='';
        var this_emerdeal_page= 1;
        form.on('submit(gw_emerDeal_queryByPat)', function (input) {
            InputEmerDealPatCd = input.field.emerDeal_patCd;
            $.ajax({
                url: appconfig.GWInfoQuery.GetRelatedEmergencyInformation,
                data: JSON.stringify({
                    IdCard: InputEmerDealPatCd
                }),
                dataType: 'json', //服务器返回json格式数据
                type: 'post', //HTTP请求类型
                headers: {
                    'Content-Type': 'application/json'
                },
                success: function (Data) {
                    if (Data.code=='1'){
                        if (null != Data.data.EmergencyInfoList && Data.data.EmergencyInfoList.length > 0) {
                            view_show('gw_emerdeal_pat_view');
                            var getTpl = gwemerdealpattpl.innerHTML
                                , view = document.getElementById('gw_emerdeal_pat_view');
                            laytpl(getTpl).render(Data, function (html) {
                                view.innerHTML = html;
                            });
                            element.render();
                            if (Data.data.pages > this_emerdeal_page) {
                                more_onclick("emerdeal_more_click");
                            }
                        } else {
                            layer.msg('该患者未存在应急处置记录')
                        }
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
        //应急处置more按钮
        form.on('submit(gw_emerdeal_more)', function (input) {
            hdk.ajax({
                api: appconfig.GWInfoQuery.GetRelatedEmergencyInformation,
                data: JSON.stringify({
                    IdCard: InputEmerDealPatCd,
                    pageNum: ++this_emerdeal_page
                })
            }, function (result) {
                var getTpl = gwemerdealpattpl.innerHTML;
                laytpl(getTpl).render(result, function(html){
                    $('#gw_emerdeal_pat_view').append(html);
                });
                element.render();
                if(result.data.pages === this_emerdeal_page){
                    more_close("emerdeal_more_click");
                }

            });
            return false;
        });


        //基层直报查询
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
                $('#gw_province').append(new Option(item.Nam, item.Cd));
            });
            layui.form.render();
        }

        //机构有无基层直报员
        /**
         * 获取省级下拉框的数据加载市级数据
         */
        form.on('select(gw_province_select)', function (data) {

            $("#gw_city").empty();
            $("#gw_county").empty();
            $("#gw_organ").empty();
            hdk.ajax({
                api: appconfig.BasicInfoQuery.QueryZoneByLv,
                data: tool.buildJson({
                    ParCd: data.value
                })
            }, function (result) {
                $.each(result.data, function (index, item) {
                    $('#gw_city').append(new Option(item.Nam, item.Cd));
                })
                layui.form.render();
            });
        });


        /**
         * 获取市下拉框的数据加载区县级数据
         */ //countyfiletr
        form.on('select(gw_city_select)', function (data) {
            $("#gw_county").empty();

            hdk.ajax({
                api: appconfig.BasicInfoQuery.QueryZoneByLv,
                data: tool.buildJson({
                    ParCd: data.value,
                })
            }, function (result) {
                $.each(result.data, function (index, item) {
                    $('#gw_county').append(new Option(item.Nam, item.Cd));
                })
                layui.form.render();
            });
        });

        /**
         * 获取区县下拉框的数据加载机构数据
         */
        form.on('select(gw_county_select)', function (data) {
            $("#gw_organ").empty();
            hdk.ajax({
                api: appconfig.BasicInfoQuery.QueryManOrgByZone,
                data: tool.buildJson({
                    ZoneCd: data.value,
                })
            }, function (result) {
                console.log(result);
                $.each(result.data, function (index, item) {
                    $('#gw_organ').append(new Option(item.Nam, item.OrgCode));
                })
                layui.form.render();
            });
        });

        $("#gw_reporter_query").on('click',function() {
            /***
             * 首先判断机构cd是否为空
             */
            var organCd = $("#gw_organ").val();  //.val();
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
                    table.reload('gw_hasreporter_table', {
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
            elem: '#gw_hasreporter_table',
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



    });


    exports('work/gwinfoquery', {})
});