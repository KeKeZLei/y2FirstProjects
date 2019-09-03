<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yan
  Date: 2019/8/7
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>分页查询</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <script src="layui/DataTableExtend.js"></script>
    <style type="text/css">
        .layui-table-cell .layui-form-checkbox[lay-skin="primary"] {
            top: 50%;
            transform: translateY(-50%);
        }
    </style>
</head>
<body>
<div class="layui-tab">

    <div class="layui-tab-content" id="selectM">
        <div class="layui-tab-item layui-show">
            <div class="layui-row">
                <div class="layui-col-md10 layui-col-md-offset1">
                    <div class="layui-col-md3">
                        <label class="layui-form-label">线路编号</label>
                        <div class="layui-input-block">
                            <input type="text" name="linename" required
                                   lay-verify="required|question_content" placeholder="请输入姓名" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-col-md3">
                        <div class="layui-input-block">
                            <button id="search-btn" class="layui-btn layui-btn-normal">
                                <i class="layui-icon layui-icon-fire"></i>查询
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-container">
                <div class="layui-row">
                    <div class="layui-col-md10 layui-col-md-offset1">
                        <table id="table" lay-filter="userInfo"></table>
                    </div>
                </div>
            </div>
            <div id="addDialog"
                 style="display: none; width: 420px; padding-top: 20px;">
                <form class="layui-form" action="">
                    <div class="layui-form-item">
                        <label class="layui-form-label">线路编号</label>
                        <div class="layui-input-block">
                            <input type="text" name="lineno" required
                                   lay-verify="required|question_content" placeholder="请输入线路编号" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>

                    <div class="layui-form-item">
                    <label class="layui-form-label">从:</label>
                    <div class="layui-input-block">
                        <select name="lineStart" id="lineStart" lsay-verify="required" lay-filter="addprov1" style="width: 50px">
                            <option value="">请选择省份</option>
                        </select>

                    </div>
                </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">到:</label>
                        <div class="layui-input-block">
                            <select name="lineTo" id="lineTo" lsay-verify="required" lay-filter="addprov1" style="width: 50px">
                                <option value="">请选择省份</option>
                            </select>

                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">线路里程</label>
                        <div class="layui-input-block">
                            <input type="text" name="length" required
                                   lay-verify="required|question_content" placeholder="请输入线路里程" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                   </form>
            </div>
            <div id="updateDialog"
                 style="display: none; width: 420px; padding-top: 20px;">
                <form class="layui-form" action="">
                    <input type="hidden" name="userId"/>
                    <div class="layui-form-item">
                        <label class="layui-form-label">线路ID</label>
                        <div class="layui-input-block">
                            <input type="text" name="lineid" required
                                   lay-verify="required|question_content" placeholder="请输入线路ID" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">线路编号</label>
                        <div class="layui-input-block">
                            <input type="text" name="lineno" required lay-verify="required|answer"
                                   placeholder="请输入线路编号" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">线路名称</label>
                        <div class="layui-input-block">
                            <%--<input type="text" name="linename" required lay-verify="required|answer"--%>
                                   <%--placeholder="请输入线路名称" autocomplete="off" class="layui-input">--%>
                            <select name="linename" id="line_name" lay-verify="required" lay-filter="addprov1"> </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">线路里程</label>
                        <div class="layui-input-block">
                            <input type="text" name="length" required
                                   lay-verify="required" placeholder="请输入线路里程" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>
</div>
<script type="text/javascript" src="layui/layui.js"></script>
<script src="js/jquery.js"></script>
<script type="text/javascript">
    $(function () {
        //填充线路名称
        $.ajax({
            url:"line_getAllLineName.action",
            dataType:"json",
            success:function (reponse){
                var lineArr = reponse.lineList;
                for(var i in lineArr){
                    var line=lineArr[i];
                    var $opt=$("<option value='"+line.linename+"' name='line_id'>"+line.linename+"</option>");
                    $("#line_name").append($opt);
                }
            },
            error:function (response) {
                console.log("出现错误");
            }
        });
    });
        $(function () {
            //填充线路名称
            $.ajax({
                url:"line_getCity.action",
                dataType:"json",
                success:function (reponse){
                    var lineArr = reponse.listCity;
                    for(var i in lineArr){
                        var line=lineArr[i];
                        var $opt=$("<option value='"+line.pname+"' name='line_id'>"+line.pname+"</option>");
                        $("#lineStart").append($opt);
                        // $("#lineTo").append($opt);
                    }
                },
                error:function (response) {
                    console.log("出现错误");
                }
            });
        });
            $(function () {
                //填充线路名称
                $.ajax({
                    url:"line_getCity.action",
                    dataType:"json",
                    success:function (reponse){
                        var lineArr = reponse.listCity;
                        for(var i in lineArr){
                            var line=lineArr[i];
                            var $opt=$("<option value='"+line.pname+"' name='line_id'>"+line.pname+"</option>");
                            $("#lineTo").append($opt);
                        }
                    },
                    error:function (response) {
                        console.log("出现错误");
                    }
                });
    layui.use(["table", "jquery", "layer", 'form', "laydate", "element"], function () {
        var layer = layui.layer;
        var laydate = layui.laydate;
        var table = layui.table;
        var $ = layui.jquery;
        var form = layui.form;
        var element = layui.element;
        table.render({
            elem: "#table",
            url: "line_page.action",
            limits: [1, 5, 10, 15, 20],
            page: true,
            toolbar: "default",
            defaultToolbar: ['filter', 'print', 'exports'],
            cols: [[
                {field: 'lineid', title: '线路ID', width: 140, type: "checkbox", sort: true}
                , {field: 'lineno', title: '线路编号', width: 130}
                , {field: 'linename', title: '线路名称', width: 130}
                , {field: 'length', title: '线路里程', width: 130}

            ]]
        });
        $("#search-btn").bind("click",function(){
            table.reload("table",{
                where:{
                    userInfoName:$.trim($("#selectM input[name=linename]").val()),
                },
                page:{
                    curr:1
                }
            });
        });
        laydate.render({
            elem: "#birthday"
        });
        table.on("toolbar(userInfo)", function (object) {
            if (object.event == 'add') {
                layer.open({
                    type: 1,
                    title: "添加信息",
                    content: $("#addDialog"),
                    btn: ["保存", "取消"],
                    area: ["480px", "420px"],
                    success: function (layero, index) {
                        layero.addClass("layui-form");
                        layero.find(".layui-layer-btn0").attr("lay-filter", "add").attr("lay-submit", "");
                        form.render();
                    },
                    yes: function (layero, index) {
                        form.on('submit(add)', function (data) {
                            var lineno = $("#addDialog input[name=lineno]").val();
                            var linename = $("#addDialog select[name=lineStart]").val()+"-"+$("#addDialog select[name=lineTo]").val();
                            var lineStart = $("#addDialog select[name=lineStart]").val();
                            var lineTo = $("#addDialog select[name=lineTo]").val();
                            var length = $("#addDialog input[name=length]").val();
                            // var name = linename+"_"+length;
                            $.ajax({
                                url: "<%=path%>/line_AddProvince.action",
                                type: "post",
                                data: {
                                    lineno:lineno,
                                    linename:linename,
                                    length:length
                                },
                                dataType: "json",
                                success: function (data) {
                                    if (data.code == 0) {
                                        layer.alert(data.msg, {icon: 1});
                                        table.reload("table");
                                    } else {
                                        layer.alert(data.msg, {icon: 2});
                                    }
                                    layer.closeAll('page');
                                    form.render();
                                },
                                error: function () {
                                    layer.msg("保存失败...");
                                }
                            });
                        });
                    },
                    btn2: function (index) {
                        layer.close(index);
                        $('#addDialog input[type=text]').each(function (i, item) {
                            $(item).val("");
                        });
                        form.render();
                    },
                    end: function () {
                        $('#addDialog input[type=text]').each(function (i, item) {
                            $(item).val("");
                        });
                        form.render();
                    }
                });
            } else if (object.event == 'update') {
                var checkStatus = table.checkStatus('table');
                var rows = checkStatus.data;
                if (rows.length == 0) {
                    layer.alert("请选择需要修改的行", {icon: 2});
                } else if (rows.length > 1) {
                    layer.alert("只能选中一行进行修改", {icon: 2});
                } else {
                    $("#updateDialog input[name=lineid]").val(rows[0].lineid);
                    $("#updateDialog input[name=lineno]").val(rows[0].lineno);
                    $("#updateDialog select[name=linename]").val(rows[0].linename);
                    $("#updateDialog input[name=length]").val(rows[0].length);

                    layer.open({
                        type: 1,
                        title: "修改信息",
                        content: $("#updateDialog"),
                        btn: ["保存", "取消"],
                        area: ["480px", "420px"],
                        success: function (layero, index) {
                            layero.addClass("layui-form");
                            layero.find(".layui-layer-btn0").attr("lay-filter", "update").attr("lay-submit", "");
                            form.render();
                        },
                        yes: function (layero, index) {
                            form.on('submit(update)', function (data) {
                                var lineid = $("#updateDialog input[name=lineid]").val();
                                var lineno = $("#updateDialog input[name=lineno]").val();
                                var linename = $("#updateDialog select[name=linename]").val();
                                var length = $("#updateDialog input[name=length]").val();
                                $.post("<%=path%>/line_updateProvince.action",
                                    {
                                        lineid: lineid,
                                        lineno: lineno,
                                        linename: linename,
                                        length: length,

                                    }, function (data) {
                                        if (data.code == 0) {
                                            layer.alert(data.msg, {icon: 1});
                                            table.reload("table");
                                        } else {
                                            layer.alert(data.msg, {icon: 2});
                                        }
                                        layer.closeAll('page');
                                    }, "json");
                            });
                        },
                        btn2: function (index) {
                            layer.close(index);
                        }
                    });
                }
            }else if (object.event == 'delete') {
                var checkStatus = table.checkStatus('table');
                var rows = checkStatus.data;
                if (rows.length == 0) {
                    layer.alert("请选择需要删除的行", {icon: 2});
                } else {
                    var lineid = rows[0].lineid;
                    layer.confirm("确认要删除吗，删除后不能恢复", {title: "删除确认"}, function (index) {
                        $.post("{<%=path%>/line_deleteProvince.action", {lineid: lineid}, function (data) {
                            var num = data.code == 200 ? 1 : 2;
                            layer.alert(data.msg, {icon: num, shade: 0.3, offset: '40%', time: 2000});
                            setTimeout(function () {
                                //刷新
                                location.reload();
                            }, 1000);
                        });
                    })
                }
            }
        });
        });
    });
</script>