layui.define(['sysconfig'],function(exports){
    var sysconfig=layui.sysconfig;

    var appconfig = {
        Zone:{
            QueryProvince:sysconfig.apiroot+"/LocalData/QueryProvince",
            queryzonebylv:sysconfig.apiroot+"/LocalData/QueryZoneByLv",
            querymanorgbyzone: sysconfig.apiroot+"/LocalData/QueryManOrgByZone",
        },
        Base:{
            queryPatInspectYear:sysconfig.apiroot+"/LocalData/queryPatInspectYear",
            queryPatInspectYearDetails:sysconfig.apiroot+"/LocalData/queryPatInspectYearDetails",
            queryPatInspectYearDelete:sysconfig.apiroot+"/LocalData/queryPatInspectYearDelete",
            queryPatInspectYearDeath:sysconfig.apiroot+"/LocalData/queryPatInspectYearDeath",
        },
        GW:{  // c#
            CompareZoneDataByZoneCode:sysconfig.apiroot+"/Base2/CompareZoneDataByZoneCode",
            CompareOrganDataByZoneCode:sysconfig.apiroot+"/Base2/CompareOrganDataByZoneCode",
            handleOrganiseChange:sysconfig.apiroot+"/Base2/handleOrganiseChange",
            queryhandleOrganiseChange:sysconfig.apiroot+"/Base2/queryhandleOrganiseChange",
            SyncOrgan : sysconfig.apiroot +"/Base2/SyncOrg",
            //同步患者基本信息
            syncBasicInformation :sysconfig.apiroot+"/Base2/SyncBasicInformation",
            //患者信息删除
            deleteBasicInformation :sysconfig.apiroot+"/Base2/deleteBasicInformation",
            //患者信息恢复
            recoveryBasicInformation : sysconfig.apiroot+"/Base2/recoveryBasicInformation",
            //患者重复迁移
            //获取重复患者信息
            GetRepeatInfoByIdcode :sysconfig.apiroot + "/MoveInAndOut2/GetRepeatInfoByIdcode",
            //获取基础直报人员联系方式
            SyncgetUserInformationByOrgcodeResponse:sysconfig.apiroot+"/Base2/SyncgetUserInformationByOrgcodeResponse",
            //查看患者档案数据
            QueryPatInfoMana:sysconfig.apiroot+"/Base2/QueryPatInfoMana",
            //根据身份证ID查询患者信息
            queryBasicInformation :sysconfig.apiroot+"/Base2/queryBasicInformation",
            //根据身份证ID查询患者流转信息
            queryMoveOutInformation :sysconfig.apiroot+"/Base2/queryMoveOutInformation",
            callBackMove2:sysconfig.apiroot+"/MoveInAndOut/callBackMove2",
            //患者转死亡同步患者的随访信息+调用转死亡接口
            PatTurnDeathAndFollowup : sysconfig.apiroot+"/MoveInAndOut/PatTurnDeath",
            //查询跨省迁出
            GetMoveOutResult:sysconfig.apiroot+"/Base2/GetMoveOutResult",
            //根据患者身份证查询患者的应急处置信息
            getRelatedEmergencyInformation :sysconfig.apiroot+"/Base2/GetgEmergencyResult",
            QueryOutPatForList : sysconfig.apiroot+"/SyncPatFollowup/QueryOutPatForList",
            QueryPatInfoByCdForCard : sysconfig.apiroot+"/SyncPatFollowup/QueryPatInfoByCdForCard",
            QueryPatFollowupByCdForCard : sysconfig.apiroot+"/SyncPatFollowup/QueryPatFollowupByCdForCard",
            SyncPatFollowupByKey : sysconfig.apiroot+"/SyncPatFollowup/SyncPatFollowupByKey",
            DelPatFollowupByKey : sysconfig.apiroot+"/SyncPatFollowup/DelPatFollowupByKey",
            queyrPatInfoByMoveCd:sysconfig.apiroot+"/MoveInAndOut2/queyrPatInfoByMoveCd"
        },
		
		Zone2:{
			QueryProvince:sysconfig.toolapiroot+"/BasicInfoQuery/QueryProvince",
			Queryzonebylv:sysconfig.apiroot+"/BasicInfoQuery/QueryZoneByLv",
			Querymanorgbyzone: sysconfig.apiroot+"/BasicInfoQuery/QueryManOrgByZone"
		},

        Login:{
            UserLogin : sysconfig.toolapiroot+"/Login/UserLogin",
        },

		PatInfoServer:{
			//获取重复患者信息
			GetRepeatInfoByIdcode : sysconfig.toolapiroot + "/PatInfoServer/GetRepeatInfoByIdcode",
			//患者信息删除
			DeleteBasicInformation :sysconfig.toolapiroot+"/PatInfoServer/DeleteBasicInformation",
			//患者信息恢复
			RecoveryBasicInformation : sysconfig.toolapiroot+"/PatInfoServer/RecoveryBasicInformation",
			//根据身份证ID查询患者信息
			QueryBasicInformation :sysconfig.toolapiroot+"/PatInfoServer/QueryBasicInformation",
			//同步患者基本信息
			SyncBasicInformation :sysconfig.toolapiroot+"/PatInfoServer/SyncBasicInformation",
			//查看患者档案数据
			QueryPatInfoMana:sysconfig.toolapiroot+"/PatInfoServer/QueryPatInfoMana",
			//患者转死亡 同步患者的随访信息+调用转死亡接口
			PatTurnDeathAndFollowup : sysconfig.toolapiroot+"/PatInfoServer/PatTurnDeath",
		},
		BasicInfoQuery:{
			QueryProvince:sysconfig.toolapiroot+"/BasicInfoQuery/QueryProvince",
			QueryZoneByLv:sysconfig.toolapiroot+"/BasicInfoQuery/QueryZoneByLv",
			QueryManOrgByZone: sysconfig.toolapiroot+"/BasicInfoQuery/QueryManOrgByZone",
			GetUserInformationByOrgcode : sysconfig.toolapiroot+"/BasicInfoQuery/GetUserInformationByOrgcode"
		},
		MoveInAndOut:{
			//查询跨省迁出
			GetMoveOutResult:sysconfig.toolapiroot+"/MoveInAndOut/GetMoveOutResult",
			//根据身份证ID查询患者流转信息
			QueryMoveOutInformation :sysconfig.toolapiroot+"/MoveInAndOut/QueryMoveOutInformation",
			//上挂撤回
			CallBackMove:sysconfig.toolapiroot+"/MoveInAndOut/CallBackMove",
			//获取正迁入到本省的患者信息
			QueyrPatInfoByMoveCd:sysconfig.toolapiroot+"/MoveInAndOut/QueyrPatInfoByMoveCd"
		},
		YearInspectQuery:{
            queryPatInspectYear:sysconfig.toolapiroot+"/YearInspectQuery/queryPatInspectYear",
            queryPatInspectYearDetails:sysconfig.toolapiroot+"/YearInspectQuery/queryPatInspectYearDetails",
            queryPatInspectYearDeath:sysconfig.toolapiroot+"/YearInspectQuery/queryPatInspectYearDeath",
            queryPatInspectYearDelete:sysconfig.toolapiroot+"/YearInspectQuery/queryPatInspectYearDelete",
		},
        SyncPatFollowup:{
            //跨省迁出患者查询
            QueryOutPatForList : sysconfig.toolapiroot+"/SyncPatFollowup/QueryOutPatForList",
            //患者信息查询
            QueryPatInfoByCdForCard : sysconfig.toolapiroot+"/SyncPatFollowup/QueryPatInfoByCdForCard",
            //患者随访信息查询
            QueryPatFollowupByCdForCard : sysconfig.toolapiroot+"/SyncPatFollowup/QueryPatFollowupByCdForCard",
            //随访信息同步
            SyncPatFollowupByKey : sysconfig.toolapiroot+"/SyncPatFollowup/SyncPatFollowupByKey",
            //随访信息删除
            DelPatFollowupByKey : sysconfig.toolapiroot+"/SyncPatFollowup/DelPatFollowupByKey"

        },
        GWInfoQuery:{  //根据患者主键查询相关
            //患者基本信息
            GetBasicInfoById : sysconfig.toolapiroot+"/GWInfoQuery/GetBasicInfoById",
            //病例报告卡信息
            GetNewCaseReportById : sysconfig.toolapiroot+"/GWInfoQuery/GetNewCaseReportById",
            //出院信息
            GetDischargeInfoById : sysconfig.toolapiroot+"/GWInfoQuery/GetDischargeInfoById",
            //应急处置信息
            GetEmerDealById : sysconfig.toolapiroot+"/GWInfoQuery/GetEmerDealById",
            //随访信息
            GetRelatedFollowupInfo : sysconfig.toolapiroot+"/GWInfoQuery/GetRelatedFollowupInfo",

            //根据患者主键查询报告卡信息
            GetRelatedNewCaseReport : sysconfig.toolapiroot+"/GWInfoQuery/GetRelatedNewCaseReport",
            //根据患者主键查询出院信息
            GetRelatedDischargeInformation : sysconfig.toolapiroot+"/GWInfoQuery/GetRelatedDischargeInformation",
            //根据患者身份证号查询应急处置信息
            GetRelatedEmergencyInformation : sysconfig.toolapiroot+"/GWInfoQuery/GetRelatedEmergencyInformation",
        },
        GWMsgResend:{//国网消息重发
            //患者信息重发
            PatResend:{
                //患者信息查询
                PatInfoQuery : sysconfig.toolapiroot+"/GWMsgResend/PatInfoQuery",
                //患者信息批量重发
                PatInfoBatchResend : sysconfig.toolapiroot+"/GWMsgResend/PatInfoBatchResend",
                //患者信息单条重发
                PatInfoResend : sysconfig.toolapiroot+"/GWMsgResend/PatInfoResend",
                //患者重置年审
                ResetInspectYear : sysconfig.toolapiroot+"/GWMsgResend/ResetInspectYear",
            },
            //随访信息重发
            FollowupResend:{
                //随访信息查询
                FollowupInfoQuery : sysconfig.toolapiroot+"/GWMsgResend/FollowupInfoQuery",
                //随访信息批量重发
                FollowupInfoBatchResend : sysconfig.toolapiroot+"/GWMsgResend/FollowupInfoBatchResend",
                //随访信息单条重发
                FollowupInfoResend : sysconfig.toolapiroot+"/GWMsgResend/FollowupInfoResend",
                //批量重发随访患者
                FollowPatBatchResend : sysconfig.toolapiroot+"/GWMsgResend/FollowPatBatchResend",
            },
            //报告卡消息重发
            ReportResend:{
                //报告卡信息查询
                ReportInfoQuery : sysconfig.toolapiroot+"/GWMsgResend/ReportInfoQuery",
                //报告卡信息批量重发
                ReportInfoBatchResend : sysconfig.toolapiroot+"/GWMsgResend/ReportInfoBatchResend",
                //报告卡信息单条重发
                ReportInfoResend : sysconfig.toolapiroot+"/GWMsgResend/ReportInfoResend"
            },
            //出院单消息重发
            DischargeResend:{
                //出院单信息查询
                DischargeInfoQuery : sysconfig.toolapiroot+"/GWMsgResend/DischargeInfoQuery",
                //出院单信息批量重发
                DischargeInfoBatchResend : sysconfig.toolapiroot+"/GWMsgResend/DischargeInfoBatchResend",
                //出院单信息单条重发
                DischargeInfoResend : sysconfig.toolapiroot+"/GWMsgResend/DischargeInfoResend"
            },
            //应急处置消息重发
            EmergencyResend:{
                //应急处置信息查询
                EmergencyInfoQuery : sysconfig.toolapiroot+"/GWMsgResend/EmergencyInfoQuery",
                //应急处置信息批量重发
                EmergencyInfoBatchResend : sysconfig.toolapiroot+"/GWMsgResend/EmergencyInfoBatchResend",
                //应急处置信息单条重发
                EmergencyInfoResend : sysconfig.toolapiroot+"/GWMsgResend/EmergencyInfoResend"
            },
        },
        //消息实时监控
        MsgMonitor:{
            //监控消息文件概况
            GetMsgOverview : sysconfig.toolapiroot+"/MsgMonitor/GetMsgOverview",
            //消息文件查询
            GetMsgLocation : sysconfig.toolapiroot+"/MsgMonitor/GetMsgLocation"
        },

        //历史消息跟踪
        HistoryMsg:{
            //查询历史消息
            HistoricalMsgQuery : sysconfig.toolapiroot+"/HistoryMsg/HistoricalMsgQuery",
            //获取消息静态资源文件
            HistoricalMsgFileGet : sysconfig.toolapiroot+"/HistoryMsg/HistoricalMsgFileGet",
        }
		
    };
    exports('appconfig', appconfig);
});