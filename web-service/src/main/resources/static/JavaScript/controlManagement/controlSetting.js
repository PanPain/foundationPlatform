
var vm = new Vue({
    el: "#controlSetting",

    data: {
        queryCondition: {
            ruleName: "",
            ruleType: "",
            targetMac: "",
            status: ""
        },
        selectedItem: [
            { name: "布控ID", selected: false },
            { name: "布控名称", selected: true },
            { name: "布控区域", selected: false },

            { name: "创建时间", selected: true },
            { name: "失效时间", selected: true },
            { name: "布控类型", selected: true },
            { name: "目标Mac", selected: true },
            { name: "启停状态", selected: true },
            { name: "用户ID", selected: false },
            { name: "接收手机", selected: true },
            { name: "备注", selected: false }
        ],
        showTitle: [
            { name: "布控ID", show: false },
            { name: "布控名称", show: true },
            { name: "布控区域", show: false },

            { name: "创建时间", show: true },
            { name: "失效时间", show: true },
            { name: "布控类型", show: true },
            { name: "目标Mac", show: true },
            { name: "启停状态", show: true },
            { name: "用户ID", show: false },
            { name: "接收手机", show: true },
            { name: "备注", show: false }
        ],
        ruleMachine: [
            { macMachineNo: 1232, machineName: "dev-1" },
            { macMachineNo: 1232, machineName: "dev-2" },
            { macMachineNo: 1232, machineName: "dev-2" },
            { macMachineNo: 1232, machineName: "dev-2" },
            { macMachineNo: 1232, machineName: "dev-2" },
            { macMachineNo: 1232, machineName: "dev-2" }
        ],
        // 布控机具全选
        selectedAll: false,
        dataList: [{
            "ruleId": 11,
            "ruleName": "1",
            "ruleArea": "1",
            "ruleCreateTime": 1525088500000,
            "ruleExpireTime": 1525693307000,
            "ruleType": "1",
            "targetMac": "1",
            "status": "01",
            "userId": 1,
            "phone": "1",
            "remark": "1"
        }]
    },
    mounted: function () {
        //为productList添加select（是否选中）字段，初始值为true
        var _this = this;
        //为productList添加select（是否选中）字段，初始值为true
        this.ruleMachine.map(function (item) {
            _this.$set(item, 'select', false);
        })

        this.queryInfo(this.queryCondition);
    },
    watch: {
        selectedAll: function (newValue, oldValue) {
            this.ruleMachine.forEach(function (item, index, array) {
                item.select = newValue;
            });
        }
    },
    methods: {
        // 选择列事件
        clickLi: function (index) {
            this.selectedItem[index].selected = !this.selectedItem[index].selected;
            this.showTitle[index].show = this.selectedItem[index].selected;
        },
        // 布控机具全选/反选
        selectedAll: function () {
            for (let i = 0; i < this.ruleMachine.length; i++) {
                this.ruleMachine[i].select = true;
            }
        },
        showDetail: function (item) {

            var content = `<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
            <tr><td>布控名称</td><td>${item.ruleName}</td></tr>
            <tr><td>布控范围</td><td>${item.ruleArea}</td> </tr>
            <tr><td>创建时间</td><td>${item.ruleCreateTime}</td> </tr>
            <tr><td>失效时间</td><td>${item.ruleExpireTime}</td> </tr>
            <tr><td>布控类型</td><td>${item.ruleType}</td> </tr>
            <tr><td>目标mac</td><td>${item.targetMac}</td> </tr>
            <tr><td>布控状态</td><td>${item.status}</td> </tr>
            <tr><td>用户ID</td><td>${item.userId}</td> </tr>
            <tr><td>手机</td><td>${item.phone}</td> </tr>
            <tr><td>备注</td><td>${item.remark}</td> </tr>
          </table>`;
            $("#modal-table2 .modal-body").html(content);
        },
        // 新增
        addItemOrEdit: function () {
            console.log($("#rule-area").val());
            var timeString1 = $("#rule-expire-time").val()
            var timeString2 = $("#rule-create-time").val();
            // 将选择的日期转换成毫秒进行传参
            var createdatetime = (new Date(timeString2)).getTime();
            var expiredatetime = (new Date(timeString1)).getTime();

            // 布控机具参数
            var ruleMachineArg = [];
            for (let i = 0; i < this.ruleMachine.length; i++) {
                if(this.ruleMachine[i].select==true){
                    ruleMachineArg.push(this.ruleMachine[i].macMachineNo)
                }
            }
            console.log(ruleMachineArg.toString());
            var data = {
                "ruleName": $("#rule-name").val(),
                "ruleArea": ruleMachineArg.toString(),
                "ruleCreateTime": createdatetime,
                "ruleExpireTime": expiredatetime,
                "ruleType": $("#rule-type").val(),
                "targetMac": $("#target-mac").val(),
                "status": $("#dev-status").val(),
                "userId": $("#user-id").val() - 0,
                "phone": $("#phone").val(),
                "remark": $("#remark").val()
            };
            if ($("#dialog-title").html() === "新增") {

                console.log("新增提交的参数:"+JSON.stringify(data))

                $.ajax({
                    type: "post",
                    url: "/rule/addRule",
                    contentType: "application/json;charset=UTF-8;",
                    data: JSON.stringify(data),
                    success: function (response) {
                        if (response.meta.success) {
                            console.log("成功获取数据,后端返回的数据为：")
                            console.log(JSON.stringify(response));
                            alert("成功添加一条");
                            vm.queryInfo(vm.queryCondition)
                        } else {
                            alert("添加失败：" + response.meta.message)
                        }
                    }
                })
            } else if ($("#dialog-title").html() === "编辑") {
                // 编辑成功要提交了！
                $.ajax({
                    type: "post",
                    url: "/rule/updateRule",
                    contentType: "application/json;charset=UTF-8;",
                    data: JSON.stringify(data),
                    success: function (response) {
                        if (response.meta.success) {
                            console.log("成功获取数据,后端返回的数据为：")
                            console.log(JSON.stringify(response));
                            alert("成功修改一条数据");
                            vm.queryInfo(vm.queryCondition)
                        } else {
                            alert("修改失败：" + response.meta.message)
                        }
                    }
                })
            }

        },
        addShow: function () {
            $("#dialog-title").html("新增");
            $("#rule-name").val("");
            $("#rule-area").val("");
            // $("#rule-create-time").val("");
            $("#rule-expire-time").val("");
            $("#rule-type").val("");
            $("#target-mac").val("");
            $("#dev-status").val("");
            $("#user-id").val("");
            $("#phone").val("");
            $("#remark").val("");

            console.log("daozheli")
            //发送ajax获取所有设备列表
            $.ajax({
                type: "post",
                url: "/macMachine/getMacMachineInfo",
                data: JSON.stringify({}),
                success: function (response) {
                    console.log("请求设备信息成功")
                    if (response.meta.success) {
                        console.log(response.data.length)
                        vm.ruleMachine = response.data;
                    } else {
                        alert(response.meta.message)
                    }

                },
                error: function (XMLHttpRequest) {
                    console.log("请求失败")
                    console.log(XMLHttpRequest.status)
                }

            })
        },
        // 编辑
        editShow: function (item) {
            $("#dialog-title").html("编辑");
            // 填充各项
            $("#rule-name").val(item.ruleName);
            $("#rule-area").val(item.ruleArea);
            $("#rule-create-time").val(item.ruleCreateTime);
            $("#rule-expire-time").val(item.ruleExpireTime);
            $("#rule-type").val(item.ruleType);
            $("#target-mac").val(item.targetMac);
            $("#dev-status").val(item.status);
            $("#user-id").val(item.userId);
            $("#phone").val(item.phone);
            $("#remark").val(item.remark);
        },
        // 查询所有
        queryInfo: function (queryCondition) {
            console.log("点击了查询！")
            var data = {
                ruleName: queryCondition.ruleName,
                ruleType: queryCondition.ruleType,
                targetMac: queryCondition.targetMac,
                status: queryCondition.status,
            }
            var dataSend = {
                curPage: 1,
                PageNum: 20
            }
            for (var key in data) {
                if (data[key] !== "") {
                    dataSend[key] = data[key];
                }
            }
            $.ajax({
                type: "GET",
                url: "/rule/getRulesBySomeConditions",
                data: dataSend,
                success: function (response, textStatus) {
                    // data 由服务器返回，并根据dataType参数进行处理 可能是 xmlDoc;jsonObj(这里已经是Obj)
                    // textStatus 是描述请求状态的字符串：success/error/notmodified/timeout
                    console.log("后端返回了数据！response:" + response);
                    console.log("后端的数据：" + JSON.stringify(response));
                    console.log("textStatus:" + textStatus);
                    if (response.data === null || response.data.length === 0) {
                        alert("查询无数据！")
                    } else {
                        vm.dataList = response.data;
                    }

                },
                error: function (XMLHttpRequest, textStatus, errThrown) {
                    console.log("请求失败，状态码：" + XMLHttpRequest.status);
                    console.log("textStatus(错误信息):" + textStatus);
                    console.log("捕获的错误对象：" + errThrown);
                }
            })
        },

        deleteItem: function (item) {
            if (confirm("确定删除这条信息吗？")) {
                var id = item.ruleId;
                $.ajax({
                    type: "delete",
                    url: "/rule/deleteRule?ruleId=" + id,
                    success: function (response) {
                        if (response.meta.success) {
                            alert("删除成功");
                            // 删除之后刷新视图
                            vm.queryInfo(vm.queryCondition)
                        }
                    },
                    error: function (XMLHttpRequest) {
                        alert("删除失败" + XMLHttpRequest.status)
                    }
                })
            }

        }
    }
})