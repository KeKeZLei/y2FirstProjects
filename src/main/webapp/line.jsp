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
                        <label class="layui-form-label">出发点</label>
                        <div class="layui-input-block">
                            <select name="st">
                                <option>--请选择--</option>
                                <c:forEach items="${list}" var="list">
                                    <option>${list}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <p></p>
                    <div class="layui-form-item">
                        <label class="layui-form-label">终点站</label>
                        <div class="layui-input-block">
                            <select name="st1">
                                <option>--请选择--</option>
                                <c:forEach items="${list}" var="list">
                                    <option>${list}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                   </form>
            </div>
            <div id="updateDialog"
                 style="display: none; width: 420px; padding-top: 20px;">
                <form class="layui-form" action="">
                    <input type="hidden" name="userId"/>
                    <div class="layui-form-item">
                        <label class="layui-form-label">姓名</label>
                        <div class="layui-input-block">
                            <input type="text" name="userName" required
                                   lay-verify="required|question_content" placeholder="请输入姓名" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">性别</label>
                        <div class="layui-input-block">
                            <input type="text" name="sex" required lay-verify="required|answer"
                                   placeholder="请输入性别" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">年龄</label>
                        <div class="layui-input-block">
                            <input type="text" name="age" required lay-verify="required|answer"
                                   placeholder="请输入年龄" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">生日</label>
                        <div class="layui-input-block">
                            <input type="text" name="birthday" required
                                   lay-verify="required" placeholder="请输入生日" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">工资</label>
                        <div class="layui-input-block">
                            <input type="text" name="sal" required lay-verify="required|answer"
                                   placeholder="请输入工资" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">部门名称</label>
                        <div class="layui-input-block">
                            <input type="text" name="deptName" required lay-verify="required|answer"
                                   placeholder="请输入部门名称" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>
</div>
<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript">
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

                            var pname = $("#addDialog select[name=st]").val();
                            var pnames = $("#addDialog select[name=st1]").val();
                            $.ajax({
                                url: "<%=path%>/line_pro.action",
                                type: "post",
                                data: {

                                    pname: pname,
                                    pname: pnames,
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
                    $("#updateDialog input[name=userId]").val(rows[0].userId);
                    $("#updateDialog input[name=userName]").val(rows[0].userName);
                    $("#updateDialog input[name=sex]").val(rows[0].sex);
                    $("#updateDialog input[name=age]").val(rows[0].age);
                    $("#updateDialog input[name=birthday]").val(rows[0].birthday);
                    $("#updateDialog input[name=deptName]").val(rows[0].deptName);
                    $("#updateDialog input[name=sal]").val(rows[0].sal);
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
                                var userId = $("#updateDialog input[name=userId]").val();
                                var userName = $("#updateDialog input[name=userName]").val();
                                var sex = $("#updateDialog input[name=sex]").val();
                                var age = $("#updateDialog input[name=age]").val();
                                var birthday = $("#updateDialog input[name=birthday]").val();
                                var deptName = $("#updateDialog input[name=deptName]").val();
                                var sal = $("#updateDialog input[name=sal]").val();
                                $.post("<%=path%>/user_updateUserInfo.action",
                                    {
                                        userId: userId,
                                        userName: userName,
                                        sex: sex,
                                        age: age,
                                        birthday: birthday,
                                        deptName: deptName,
                                        sal: sal,
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
            }
        });
    });
</script>