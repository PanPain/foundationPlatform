
var vm = new Vue({
    el: "#controlAlert",
    data: {
        selectedItem: [
            { name: "布控名称", selected: true },
            { name: "设备编号", selected: true },
            { name: "接收位置", selected: true },
            { name: "目标Mac", selected: true },
            { name: "记录时间", selected: false },
            { name: "视频回放", selected: false }
        ],
        showTitle: [
            { name: "布控名称", show: true },
            { name: "设备编号", show: true },
            { name: "接收位置", show: true },
            { name: "目标Mac", show: true },
            { name: "记录时间", show: false },
            { name: "视频回放", show: false }
        ],
        dataList: [ {
            mfId: "id",
            mfName: "本地原始数据",
            mfOrgNo: "No",
            mfAddress: "address",
            mfContact: "contact",
            phone: "phone",
            email: "e-mail",
            remark: "remark"
        }]
    },
    methods: {
        // 选择列事件
        clickLi: function (index) {
            this.selectedItem[index].selected = !this.selectedItem[index].selected;
            this.showTitle[index].show = this.selectedItem[index].selected;
        },
        showDetail: function(item){
            console.log("clicked")
            // var detailDialog = $("#modal-table>.modal-body");
            // console.log($(".modal-body"))
            var content = `<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
            <tr><td>布控名称</td><td>${item.mfAddress}</td> </tr>
            <tr><td>接收手机</td><td>No</td> </tr>
            <tr><td>创建时间</td><td>address</td> </tr>
            <tr><td>布控范围</td><td>address</td> </tr>
            <tr><td>布控目标</td><td>address</td> </tr>
            <tr><td>启停状态</td><td>mac</td> </tr>
            <tr><td>备注</td><td>time</td> </tr>
          </table>`;
            $(".modal-body").html(content)
        },
        // 查询
        queryInfo: function (condition) {
            console.log("点击了查询！")
            $.ajax({
                type: "GET",
                url: "http:192.168.0.1:8080/manufacturer/queryManuByCond",
                data: {
                    controlName: $("#controlName").val()
                },
                success: function (response, textStatus) {
                    // data 由服务器返回，并根据dataType参数进行处理 可能是 xmlDoc;jsonObj(这里已经是Obj)
                    // textStatus 是描述请求状态的字符串：success/error/notmodified/timeout
                    console.log("后端返回了数据！response:" + response);
                    console.log("后端的数据：" + JSON.stringify(response));
                    console.log("textStatus:" + textStatus);
                    if (response.data === null || response.data.length === 0) {
                        vm.noData = true;
                        // vm.dataList.splice(1);
                    } else {
                        vm.noData = false;
                        vm.dataList = response.data;
                    }

                },
                error: function (XMLHttpRequest, textStatus, errThrown) {
                    console.log("请求失败，状态码：" + XMLHttpRequest.status);

                    console.log("textStatus(错误信息):" + textStatus);
                    console.log("捕获的错误对象：" + errThrown);

                }
            })
        }
    }

})