var vm = new Vue({
    el: "#safeVendor",
    data: {
        noData: false,
        editIndex: 0,
        queryCondition: {
            mfName: "",
            mfOrgNo: "",
            mfAddress: ""
        },
        selectedItem: [
            { name: "厂商名称", selected: true },
            { name: "厂商组织机构代码", selected: true },
            { name: "厂商地址", selected: true },
            { name: "联系人", selected: true },
            { name: "联系电话", selected: false },
            { name: "联系邮件", selected: false },
            { name: "备注", selected: false }
        ],
        showTitle: [
            { name: "厂商名称", show: true },
            { name: "厂商组织机构代码", show: true },
            { name: "厂商地址", show: true },
            { name: "联系人", show: true },
            { name: "联系电话", show: false },
            { name: "联系邮件", show: false },
            { name: "备注", show: false }
        ],
        dataList: [],
        editData: {}
    },
    mounted: function () {
        this.queryInfo(this.queryCondition);
    },
    methods: {
        // 选择列事件
        clickLi: function (index) {
            console.log("changged listtitle")
            this.selectedItem[index].selected = !this.selectedItem[index].selected;
            this.showTitle[index].show = this.selectedItem[index].selected;
        },
        // 明细
        doubleClicked: function (index) {

            var detailContent = ` <tr>
            <td>厂商名称：</td>
            <td>${this.dataList[index].mfName}
            </td>
          </tr>
          <tr>
            <td>组织机构代码:</td>
            <td>${this.dataList[index].mfOrgNo}</td>
          </tr>
          <tr>
            <td>厂商地址:</td>
            <td>${this.dataList[index].mfAddress}</td>
          </tr>
          <tr>
            <td>联系人:</td>
            <td>${this.dataList[index].mfContact}</td>
          </tr>
          <tr>
            <td>联系人电话:</td>
            <td>${this.dataList[index].phone}</td>
          </tr>
          <tr>
            <td>联系邮件:</td>
            <td>${this.dataList[index].email}</td>
          </tr>
          <tr>
            <td>备注:</td>
            <td>${this.dataList[index].remark}</td>
          </tr>`

            $("#detailDialog-table").html(detailContent);

            var diag = new Dialog();
            diag.Width = 400;
            diag.Height = 300;
            diag.Title = "当前厂商信息";
            diag.InvokeElementId = "detailDialog";
            diag.show();
        },
        // 新增
        addItem: function () {
            // 查询无结果之后再添加的话隐藏第一行查询无结果
            vm.noData = false;
            console.log("点击了新增按钮")
            // 向datalist头部增加一项，方便取值，添加后显示在最前面
            var content = `<label class="pull-right">厂商名称
            <input type="text" id="add-mfName"> </label>
          <label class="pull-right">厂商组织机构代码
            <input type="text" id="add-mfOrgNo"> </label>
          <label class="pull-right">厂商地址
            <input type="text" id="add-mfAddress"> </label>
          <label class="pull-right">联系人
            <input type="text" id="add-mfContact"> </label>
          <label class="pull-right">联系人电话
            <input type="text" id="add-phone"> </label>
          <label class="pull-right">联系邮件
            <input type="text" id="add-email"> </label>
          <label class="pull-right">备注
            <input type="text" id="add-remark"> </label>`

            $("#add-dialog").html(content);


            var diag = new Dialog();
            diag.Width = 400;
            diag.Height = 300;
            diag.Title = "新增安全厂商";
            diag.InvokeElementId = "addDialog";
            diag.CancelEvent = function () {
                vm.dataList.shift();
                diag.close();
            }
            diag.OKEvent = function () {
                console.log("点击了 确定按钮");
                $.ajax({
                    type: "POST",
                    url: "/manufacturer/addManu",
                    contentType: "application/json;charset=UTF-8;",
                    data: JSON.stringify({
                        mfName: $("#add-mfName").val(),
                        mfOrgNo: $("#add-mfOrgNo").val(),
                        mfAddress: $("#add-mfAddress").val(),
                        mfContact: $("#add-mfContact").val(),
                        phone: $("#add-phone").val(),
                        email: $("#add-email").val(),
                        remark: $("#add-remark").val(),
                    }),
                    success: function (data) {

                        console.log("您要添加的数据是：" + JSON.stringify(vm.dataList[0]))
                        console.log("新增请求返回数据成功");
                        console.log("后端返回的数据：" + JSON.stringify(data))
                        // var data = JSON.parse(response);
                        if (data.meta.success) {
                            diag.close();
                            alert("添加安全厂商成功");
                            console.log("更新ID成功：id；更新的数据：" + JSON.stringify(data.data))
                            // 更新完再次查询更新视图
                            vm.queryInfo({
                                mfName: "",
                                mfOrgNo: "",
                                mfAddress: ""
                            })


                        } else {
                            alert("添加失败：code:" + data.meta.code + ";message:" + data.meta.message);
                            vm.dataList.shift();
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errThrown) {
                        console.log("您要添加的数据是：" + JSON.stringify(vm.dataList[0]))
                        alert("添加失败，失败状态码：" + XMLHttpRequest.status);
                        console.log("添加失败了，readyState(状态)" + XMLHttpRequest.readyState)
                        console.log("错误信息：" + textStatus)
                    }
                })
                // 原生 js 实现 ajax 请求
                // var rq = new XMLHttpRequest();

                // rq.onreadystatechange = function () {
                //     if (rq.readyState == 4) {
                //         console.log("请求发送成功");
                //         if (rq.status === 200) {
                //             console.log("请求已被成功处理");
                //             var data = JSON.parse(rq.responseText);
                //             if (data.meta.success) {
                //                 dialog.close();
                //                 console.log("添加成功!");
                //             }
                //         } else {
                //             console.log("请求处理失败,http 状态码：" + rq.status)
                //         }
                //     } else {
                //         console.log("请求还在继续")
                //     }
                // };
                // rq.open("POST", "http:192.168.0.239:8080/manufacturer/addManu");
                // console.log("此次post发送的数据类型：" + typeof vm.dataList[0] + ",stringfy 后输出 :" + JSON.stringify(vm.dataList[0]))
                // rq.send(JSON.stringify(vm.dataList[0]));
            };
            diag.show();

        },
        // 编辑
        edit: function (item, index) {
            console.log("index:" + index)
            //之所以不直接在html里写好，是因为当datalist为空的时候，v-model 都绑定不到值
            var editDialogContent = `<label class="pull-right">厂商名称:
            <input type="text" id="edit-mfName" value=${item.mfName}> </label>
          <label class="pull-right">厂商组织机构代码:
            <input type="text" id="edit-mfOrgNo" value=${item.mfOrgNo}> </label>
          <label class="pull-right">厂商地址:
            <input type="text" id="edit-mfAddress" value=${item.mfAddress}> </label>
          <label class="pull-right">联系人:
            <input type="text" id="edit-mfContact" value=${item.mfContact}> </label>
          <label class="pull-right">联系人电话:
            <input type="text" id="edit-phone" value=${item.phone}> </label>
          <label class="pull-right">联系邮件:
            <input type="text" id="edit-email" value=${item.email}> </label>
          <label class="pull-right">备注:
            <input type="text" id="edit-remark" value=${item.remark}> </label>`

            $("#editDialog-content").html(editDialogContent);
            // 修改前的数据
            var drag = new Dialog();
            drag.Width = 400;
            drag.Height = 300;
            drag.Title = "编辑安全厂商";
            drag.InvokeElementId = "editDialog";
            drag.OKEvent = function () {
                //点击确定后的方法
                // 修改后数据v-model已经自动更新到data层了，直接获取，提示成功会自动更新视图
                var modifiedData = {
                    mfId: vm.dataList[index].mfId,
                    mfName: $("#edit-mfName").val(),
                    mfOrgNo: $("#edit-mfOrgNo").val(),
                    mfAddress: $("#edit-mfAddress").val(),
                    mfContact: $("#edit-mfContact").val(),
                    phone: $("#edit-phone").val(),
                    email: $("#edit-email").val(),
                    remark: $("#edit-remark").val(),
                }
                console.log("你修改的数据：" + JSON.stringify(modifiedData))
                //发送ajax请求
                $.ajax({
                    type: "POST",
                    url: "/manufacturer/modifyManufacturer",
                    data: JSON.stringify(modifiedData),
                    contentType: "application/json;charset=UTF-8",
                    success: function (data) {
                        // var data = JSON.parse(response);
                        if (data.meta.success) {
                            alert("修改成功");
                            // 更新完再次查询更新视图
                            vm.queryInfo({
                                mfName: "",
                                mfOrgNo: "",
                                mfAddress: ""
                            })
                            
                        } else {
                            alert("修改失败；code：" + data.meta.code + ";message:" + data.meta.message)
        
                        }

                    },
                    error: function (XMLHttpRequest) {
                        console.log("index:" + index)
                        console.log("您要修改的数据：" + JSON.stringify(modifiedData))

                        alert("修改失败，状态码：" + XMLHttpRequest.status);
                        console.log("修改失败，状态码：" + XMLHttpRequest.status);
                    }
                })
                drag.close();
            };
            drag.show();
        },
        // 删除
        deleteInfo: function (id, index) {

            console.log("要删除这条厂商的id为：" + id);
            var delResult = confirm("确定要删除这条安全厂商信息吗？");
            if (delResult == true) {
                $.ajax({
                    type: "DELETE",
                    url: "/manufacturer/deleteManu?manuId=" + id,
                    // get 请求会自动把data里的数据作为参数格式拼接到url后

                    dataType: "json",
                    success: function (dataResult) {
                        // 如果jq没有对data进行处理
                        // var dataResult = JSON.parse(data)
                        if (dataResult.meta.success) {
                            //删除成功
                            alert("删除成功");
                            //数据库删除了，视图也应该更新
                            vm.dataList.splice(index, 1);
                            //TODO
                        } else {
                            alert("删除失败，code：" + dataResult.meta.code + ";message：" + dataResult.meta.message);
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errThrown) {
                        console.log("请求未成功，状态码：" + XMLHttpRequest.status);
                    }
                })
            } else {
                console.log("clicked no:取消了这次删除")
            }
        },
        // 查询
        queryInfo: function (condition) {
            console.log("点击了查询！")
            $.ajax({
                type: "GET",
                url: "/manufacturer/queryManuByCond",
                data: {
                    mfName: condition.mfName,
                    mfOrgNo: condition.mfOrgNo,
                    mfAddress: condition.mfAddress,
                    pageNum: 50
                },
                success: function (response, textStatus) {
                    // data 由服务器返回，并根据dataType参数进行处理 可能是 xmlDoc;jsonObj(这里已经是Obj)
                    // textStatus 是描述请求状态的字符串：success/error/notmodified/timeout
                    console.log("后端返回了数据！response:" + response);
                    console.log("后端的数据：" + JSON.stringify(response));
                    console.log("textStatus:" + textStatus);
                    if (response.data === null || response.data.length === 0) {
                        vm.noData = true;
                        vm.dataList = [];
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
});
