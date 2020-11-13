/**

 @Name：layuiAdmin 公共业务
 @Author：贤心
 @Site：http://www.layui.com/admin/
 @License：LPPL
        
 */
 
layui.define(function(exports){
    var $ = layui.$
    ,layer = layui.layer
    ,laytpl = layui.laytpl
    ,setter = layui.setter
    ,view = layui.view
    ,admin = layui.admin
    ,appconfig = layui.appconfig



    //退出
    admin.events.logout = function(){
        layer.confirm('确认退出系统?', {
            btn: ['确认', '取消'],
            yes: function(index, layero) {
                //删除layui.data
                layui.data('health',null);
                location.href="../";
            }
        });
    };
    //跳转
    admin.events.passWord = function(){
        admin.urlenhash('password$view/title=修改密码');
    }

    //对外暴露的接口
    exports('common', {});
});