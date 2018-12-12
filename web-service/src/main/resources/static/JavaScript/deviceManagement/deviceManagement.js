var vm = new Vue({
    el: "#deviceManagement",
    data: {
        queryCondition: {
            devAddress: "",
            placeNo: "",
            devNo: "",
            devName: "",
            vendorName: "",
            devType: "",
            policeOfficeName: "",
            devStatus: "",
        },
        allSelected: false,
        selectedItem: [
            { name: "设备状态", selected: true },
            { name: "场所编号", selected: false },
            { name: "场所组织机构代码", selected: false },
            { name: "安全厂商名称", selected: false },
            { name: "设备编号", selected: true },
            { name: "设备名称", selected: true },
            { name: "安装位置", selected: true },
            { name: "设备朝向", selected: false },
            { name: "百度坐标经度", selected: false },
            { name: "百度坐标纬度", selected: false },
            { name: "所属单位", selected: false },
            { name: "场所经营性质", selected: true },
            { name: "ip地址", selected: true },
            { name: "最近活动时间", selected: false },
            { name: "最近递交数据时间", selected: false },
            { name: "审核状态", selected: false }
        ],
        showTitle: [
            { name: "设备状态", show: true },
            { name: "场所编号", show: false },
            { name: "场所组织机构代码", show: false },
            { name: "安全厂商名称", show: false },
            { name: "设备编号", show: true },
            { name: "设备名称", show: true },
            { name: "安装位置", show: true },
            { name: "设备朝向", show: false },
            { name: "百度坐标经度", show: false },
            { name: "百度坐标纬度", show: false },
            { name: "所属单位", show: false },
            { name: "场所经营性质", show: true },
            { name: "ip地址", show: true },
            { name: "最近活动时间", show: false },
            { name: "最近递交数据时间", show: false },
            { name: "审核状态", show: false }
        ],
        policeOfficeList: [
            {policeOfficeName: "单位1"},
            {policeOfficeName: "单位2"}

        ],
        dataList: [
            {
                "macMachineId": 1,
                "macMachineNo": "111",
                "status": "0",
                "macMachineName": "123",
                "machineAddress": "123",
                "type": "01",
                "ip": "111",
                "property": "111"
            }
            // { "macMachineId":1,
            //  "macMachineNo":"111",
            //  "status":"0",
            //  "fkPlaceId":1,
            //  "macMachineName":"123",
            //  "longitude":"01",
            //  "latitude":"11",
            //  "macMachineAddress":"111",
            //  "type":"1",
            //  "fkMfId":1,
            //  "timeInterval":1,
            //  "radius":1,
            //  "bikeNo":"111",
            //  "metroLine":"111",
            //  "metroVehicle":"111",
            //  "carriageNo":"111",
            //  "platform":"111",
            //  "ip":"111",
            //  "lastConncetionTime": (new Date()).toLocaleDateString,
            //  "fkPoliceOfficeId":1 }
        ]
    },
    mounted: function () {
        this.query(this.queryCondition);
        $.ajax({
            type: "post",
            url: "http://192.168.0.1:8080/policeOffice/getPoliceOfficeInfo",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify({}),
            success: function(response){
                if(response.meta.success){
                    console.log("所属单位请求成功");
                    vm.policeOfficeList = response.data;
                }
            }
        })
    },

    methods: {
        itemSelected: function (item, index) {
            if (item === 'all') {
                console.log("all")
                for (let i = 0; i < this.selectedItem.length; i++) {
                    this.selectedItem[i].selected = !this.allSelected;
                    this.showTitle[i].show = !this.allSelected;
                }
            }
            else {
                item.selected = !item.selected;
                console.log("here")
                this.showTitle[index].show = !item.selected;
            }

        },
        query: function (queryCondition) {
            console.log("clicked query")
            var queryData = {
                machineAddress: queryCondition.devAddress,
                macMachineNo: queryCondition.devNo,
                machineName: queryCondition.devName,
                type: queryCondition.devType,
                status: queryCondition.devStatus,
                placeNo: queryCondition.placeNo,
                mfName: queryCondition.vendorName,
                policeOfficeName: queryCondition.policeOfficeName,
                curPage: 1,
                tagNum: 50
            };

            var queryArgu = {};
            for(var  key in queryData){
                if(queryData[key]!==""){
                    queryArgu[key] = queryData[key];
                }
            }

            console.log("查询条件："+JSON.stringify(queryArgu))
            $.ajax({
                type: "get",
                url: "http://192.168.0.224:8080/macMachine/getMacMachineInfo",
                data: queryArgu,
                // contentType: "application/json;charset=UTF-8",
                // dataType: "json",
                success: function (response) {
                    console.log("后端返回数据：")
                    console.log(JSON.stringify(response))
                    // console.log("请求成功，后端返回的数据条数为：" + response.data.length);
                    vm.dataList = response.data;
                }
            })
        },
        addItem: function () {
            var addItem = {
                "macMachineId": "",
                "macMachineNo": $("#dev-no").val(),
                "status": $("#dev-status").val(),
                "fkPlaceId": 1,
                "macMachineName": $("#dev-name").val(),
                "longitude": "135.234",
                "latitude": "432.433",
                "macMachineAddress": $("#dev-address").val(),
                "type": $("#dev-type").val(),
                "fkMfId": 1,
                "timeInterval": 1,
                "radius": 1,
                "bikeNo": "default",
                "metroLine": "default",
                "metroVehicle": "default",
                "carriageNo": "default",
                "platform": "default",
                "ip": $("#ip-address").val(),
                "lastConncetionTime": (new Date()).getTime(),
                "fkPoliceOfficeId": 1,
                "orgNum": "default",
                "placeNo": "",
            };
            $.ajax({
                type: "POST",
                url: "http://192.168.1:8080/macMachine/addMacMachineInfo",
                data: JSON.stringify(addItem),
                contentType: "application/json;charset=UTF-8",
                dataType: "json",
                success: function (response) {
                    console.log("添加请求成功，后端返回的数据：" + JSON.stringify(response));
                    if (response.meta.success) {
                        alert("成功添加一条设备");
                        //!!!!! 重新更新查询~
                        vm.query(vm.queryCondition);
                    } else {
                        alert("添加失败；message：" + response.meta.message);
                    }
                },
                error: function (XMLHttpRequest) {
                    console.log("请求失败,状态码：" + XMLHttpRequest.status);
                }
            })
        },
        // showDetail
        showDetail: function(item){
            var id = item.macMachineId
            $.ajax({
                type: "get",
                url: "http://192.168.1:8080/macMachine/getMacMachineDetailInfo",
                data: {
                    macMachineId: id
                },
                success: function(response){
                    console.log("请求成功,后端返回的数据如下");
                    console.log(JSON.stringify(response));
                    // 更新 dialog 视图
                    // 传入数据
                    if(response.meta.success){
                        let data = response.data;
                        $(".dev-no").html(data.macMachineNo);
                        $(".dev-name").html(data.macMachineName);
                        $(".dev-status").html(data.status);
                        $(".dev-address").html(data.macMachineAddress);
                        $(".dev-longitude").html(data.longitude);
                        $(".dev-lantitude").html(data.latitude);
                        $(".dev-type").html(data.type);
                        $(".dev-radius").html(data.radius);
                        $(".place-no").html(data.placeNo);
                        $(".dev-ip-address").html(data.ip);
                        $(".time-interval").html(data.timeInterval);
                        $(".dev-platform").html(data.platform);
                        $(".org-name").html(data.orgNum);
                        $(".last-connection-time").html(data.lastConncetionTime)
                    }else{
                        alert("查看明细失败："+response.meta.message)
                    }
                }
            })
        },

        showEditDetail: function(item){
            $("#edit-dev-no").val(item.macMachineNo);
            $("#edit-dev-name").val(item.machineName);
            $("#edit-dev-address").val(item.machineAddress);
            $("#edit-ip-address").val(item.ip);
            $("#edit-dev-status").val(item.status);
            $("#edit-dev-type").val(item.type);
        },
        edit: function(){
            var editItem = {
                "macMachineId": "",
                "macMachineNo": $("#edit-dev-no").val(),
                "status": $("#edit-dev-status").val(),
                "fkPlaceId": 1,
                "macMachineName": $("#edit-dev-name").val(),
                "longitude": "135.234",
                "latitude": "432.433",
                "macMachineAddress": $("#edit-dev-address").val(),
                "type": $("#edit-dev-type").val(),
                "fkMfId": 1,
                "timeInterval": 1,
                "radius": 1,
                "bikeNo": "default",
                "metroLine": "default",
                "metroVehicle": "default",
                "carriageNo": "default",
                "platform": "default",
                "ip": $("#edit-ip-address").val(),
                "lastConncetionTime": (new Date()).getTime(),
                "fkPoliceOfficeId": 1,
                "orgNum": "default",
                "placeNo": "",
            };
            $.ajax({
                type: "POST",
                url: "http://192.168.1:8080/macMachine/updateMacMachineInfo",
                data: JSON.stringify(editItem),
                contentType: "application/json;charset=UTF-8",
                dataType: "json",
                success: function (response) {
                    console.log("添加请求成功，后端返回的数据：" + JSON.stringify(response));
                    if (response.meta.success) {
                        alert("成功修改一条信息");
                        //!!!!! 重新更新查询~
                        this.query(this.queryCondition);
                    } else {
                        alert("添加失败；message：" + response.meta.message);
                    }
                },
                error: function (XMLHttpRequest) {
                    console.log("请求失败,状态码：" + XMLHttpRequest.status);
                }
            })
        },

        deleteItem: function (item) {
            var id = item.macMachineId;
            if (confirm("确定要删除这条数据吗？")) {
                $.ajax({
                    type: "get",
                    url: "http://192.168.1:8080/macMachine/deleteMacMachineInfo",
                    data: {
                        macMachineId: id
                    },
                    success: function (response) {
                        console.log("请求成功");
                        if (response.meta.success) {
                            alert("删除成功")
                        }else{
                            alert("删除失败："+ response.meta.message)
                        }
                    }
                })
            }
        }

    }
})