var index = 0;
//事件绑定

//点击新增按钮
$('#usraddRow').click(function (e) {
    var addR = $('#usrmodal-table2');
    console.log(addR);
    addR.css('display', 'block');
    addR.attr('class', 'modal fade in');
});


//点击编辑弹框中的取消按钮和×按钮，关闭弹窗
$('.usrdelEdit').click(function (e) {
    var addR = $('#usrmodal-table');
    console.log(addR);
    addR.css('display', 'none');
    addR.attr('class', 'modal fade');
});
//点击新增弹框中的取消按钮和×按钮，关闭弹窗
$('.usrdelAdd').click(function (e) {
    var addR = $('#usrmodal-table2');
    console.log(addR);
    addR.css('display', 'none');
    addR.attr('class', 'modal fade');
});
//点击明细弹框中的取消按钮和×按钮，关闭弹窗
$('.usrdelView').click(function (e) {
    var addR = $('#usrmodal-table3');
    console.log(addR);
    addR.css('display', 'none');
    addR.attr('class', 'modal fade');
});

//点击选择列按钮，阻止默认事件的冒泡
$('.lbl').click(function (e) {
    e.stopPropagation();
    var target = $(e.currentTarget);
    var key = target.attr('id');
    if (key != 'undefined') {
        var goals1 = $('td.' + key);
        var goals2 = $('th.' + key);
        goals1.toggle();
        goals2.toggle();
    }
});

//点击全选/反选按钮，更改复选框的状态:因为上述点击事件阻止冒泡行为，所以此处略微复杂
$('input.all').click(function (e) {
    var all = $(e.currentTarget).get(0);
    var items = $('input.items');
    var uncheckItems = $('input.items').not('input:checked');
    console.log(all.checked);
    console.log('所有子项：', items.length);
    console.log('未被选中的子项：', uncheckItems.length);
    var allLbl = $('span.tblItems');
    var length = allLbl.length;
    console.log('子项的数目：', allLbl.length);
    if (all.checked) {
        uncheckItems.click();
        //全选
        for (var i = 0; i < length; i++) {
            var target = allLbl.eq(i);
            var key = target.attr('id');
            if (key != 'undefined') {
                var goals1 = $('td.' + key);
                var goals2 = $('th.' + key);
                goals1.show();
                goals2.show();
            }
        }
    }
    else {
        all.checked = false;
        items.attr('checked', false);
        //全不选
        for (var i = 0; i < length; i++) {
            var target = allLbl.eq(i);
            var key = target.attr('id');
            if (key != 'undefined') {
                var goals1 = $('td.' + key);
                var goals2 = $('th.' + key);
                goals1.hide();
                goals2.hide();
            }
        }
    }
});
//生成表格序号
$(function () {
    //$('table tr:not(:first)').remove();
    var len = $('table tr').length;
    for (var i = 1; i < len; i++) {
        $('table tr:eq(' + i + ') td:nth-child(2)').text(i);
    }

});

//添加单个用户
function addPeople(arr, index) {
    var str = '<tr id="myTr" ><td class="center checkboxTbl"><label class="pos-rel" style="display:block;">' +
        '<input type="checkbox" class="ace chk" name="chk" data-id="' + arr.userId + '" data-index="' + index + '" data-account="' + arr.userAccount + '" data-name="' + arr.userRealName + '" data-phone="' + arr.userPhone + '" data-office="' + arr.policeOfficeName + '" data-role="' + arr.roleName + '"/>' + '<span class="lbl"></span></label></td><td>' + index + '</td>';

    var str1 = str + '<td class="usrnumber11 ">' + arr.userAccount + '</td>' + '<td class="usrname11 ">' + arr.userRealName + '</td>' + '<td class="usroffice11 ">' + arr.policeOfficeName + '</td>' + '<td class="usrrole11 ">' + arr.roleName + '</td></tr>';
    // var a=$("#myTr").data("id");



    return str1;
}

//循环添加所有用户信息
function addAllPeople(arr) {
    var str = '';
    var dataArr = arr['data'];
    var meta = arr['meta'];
    var a = meta['success'];

    if (!meta['success']) {
        return -1;
    }
    else {
        var len = dataArr.length;
        var index = 1;
        for (var i = 0; i < len; i++ , index++) {
            str += addPeople(dataArr[i], index);
        }
        return str;
    }
}




//获取到数据之后执行的回调函数
function updateTable(data) {
    var result = JSON.stringify(data);
    allPeopleTable = JSON.parse(result);
    //在执行之前先删除表格中的所有元素，重新进行动态添加
    var tby = $('#usrdynamic-table tbody').eq(0);
    var trs = tby.children('tr');
    var str = addAllPeople(allPeopleTable);
    if (str == -1) {
        alert('数据请求失败！');
    }
    else {
        console.log('数据请求成功，正在更新表格！');
        tby.empty();
        tby.html(str);
    }
}

$(function () {
    //jQuery的入口函数
    //跨域请求服务端的数据，等待数据返回，更新页面的表格数据
    //1. 第一种方法：原生的ajax请求
    //getResources();
    //2. 使用jQuery的ajax跨域方式
    //getResourcesjQuery();
    //使用jQuery的ajax请求，用于获取所有的人员信息

    //拼接参数：获取所有的人员不需要传递参数
    // var params='';
    var URL = 'http://192.168.0.200:8888/user/getAllUserInfo';    //对应到接口的位置
    $.ajax({
        type: 'GET',
        url: URL,
        dataType: 'json',       //指定服务器返回的数据类型
        data: {},
        success: function (response) {//allPeople换成response
            console.log('成功调用！');
            console.log(JSON.stringify(response));
            updateTable(response);
        },
        error: function (err) {
            console.log('Exception:' + err.text);
        },
    });
});
// //添加页面点击确定按钮
$('.page-content').on('click', '#usraddUser', function (e) {
    e.stopPropagation();
    // alert($("input#account").val());
    // alert((document.getElementById("officename")).value);
    if (!($("input#usraccount").val()) || (!$("input#usrpassword").val()) || !(document.getElementById("usrofficename")).value || !(document.getElementById("usrrole")).value)
    // if(!($("#NewNumber").val()))
    {
        alert("除用户姓名和电话号码外，其他各项为必填项");
    } else {
        var newUserObject = {
            "dat": [{
                "userAccount": $("input#usraccount").val(),
                "userPassword": $("input#usrpassword").val(),
                "policeOfficeName": document.getElementById("usrofficename").value,
                "roleName":  document.getElementById("usrrole").value,
                "userRealName": $("input#usrusername").val(),
                "userPhone": $("input#usrphone").val(),
            }],
        }
        var newUser = newUserObject['dat'][0];
        // alert(newUser);
        console.log(newUser);
        //向后端传送数据保存
        $.ajax({
            type: "POST",
            url: "http://192.168.0.200:8888/user/addUser",
            data: JSON.stringify(newUser),
            contentType: "application/json;charset=UTF-8",
            success: function (response) {
                if (response.meta.success) {
                    // alert("yyyyyyyyy" + response.meta.message);
                    location.reload();
                } else {
                    // alert("11111111111" + response.meta.message + "qqqqq" + response.meta.code + response.meta.success);
                }
            },
            error: function (XmlHttpRequest, textStatus, errThrown) {
                console.log(XmlHttpRequest.status + "uuyyuuyyuuyy");
            }
        });
    }
});
//全选
$("#usrcheckAll").click(function () {
    if ($("#usrcheckAll").is(":checked")) {
        $("input[type='checkbox']").prop("checked", true);
    } else {
        $("input[type='checkbox']").prop("checked", false);
    }
});

//页面详情
$("#usrviewRow").click(function () {
    var checks = document.getElementsByName("chk");
    n = 0;
    for (i = 0; i < checks.length; i++) {
        if (checks[i].checked)
            n++;
    }
    // alert(n);
    if (n < 1) {
        alert('请选择要查看的数据！');
        return false;
    } else if (n == 1) {
        var addR = $('#usrmodal-table3');
        console.log(addR);
        addR.css('display', 'block');
        addR.attr('class', 'modal fade in');

        var id = $("td :checked").attr("data-id");
        var index = $("td :checked").attr("data-index");
        var account = $("td :checked").attr("data-account");
        var name = $("td :checked").attr("data-name");
        var phone = $("td :checked").attr("data-phone");
        var office = $("td :checked").attr("data-office");
        var role = $("td :checked").attr("data-role");

        document.getElementById("usrmnum").innerHTML = index;
        document.getElementById("usrmacc").innerHTML = account;
        document.getElementById("usrmname").innerHTML = name;
        document.getElementById("usrmoffice").innerHTML = office;
        document.getElementById("usrmphone").innerHTML = phone;
        document.getElementById("usrmrole").innerHTML = role;


    } else {
        alert('只能选择一条数据！');
        return false;
    };


    //		return false;		

    return false;
});


//点击编辑按钮 data-id="' + arr.userId + '" data-index="' + index + '" data-account="' + arr.userAccount + '" data-name="' + arr.userRealName + '" data-phone="' + arr.userPhone + '" data-office="' + arr.policeOfficeName + '" data-role="' + arr.roleName
$('#usreditRow').click(function (e) {
    var checks = document.getElementsByName("chk");
    n = 0;
    for (i = 0; i < checks.length; i++) {
        if (checks[i].checked)
            n++;
    }
    if (n < 1) {
        alert('请选择编辑数据！');
        return false;
    } else if (n > 1) {
        alert("只能编辑一条数据");
    } else {
        if (confirm('确认编辑？')) {
            var addR = $('#usrmodal-table');
            console.log(addR + "huasgbuf");
            addR.css('display', 'block');
            addR.attr('class', 'modal fade in');
            editId = $("td :checked").attr('data-id');//为什么不能写成 var editId？
            editAccount = $("td :checked").attr('data-account');
            var editName = $("td :checked").attr('data-name');
            var editPho = $("td :checked").attr('data-phone');
            var editOffice = $("td :checked").attr('data-office');
            var editRole = $("td :checked").attr('data-role');
            // alert(editId);
            // alert(editAccount);
            $("input#usrename").val(editName);
            $("input#usrephone").val(editPho);
            // $("input#eoffice").val(editOffice);
            // $("input#erole").val(editRole);
            $("select#usreoffice").val(editOffice);
            $("select#usrerole").val(editRole);

        }
    }
})
//点击编辑按钮中的保存按钮
$('#usrsaveEdit').click(function (e) {

    e.stopPropagation();
    // alert($("input#ename").val());
    if (!document.getElementById("usreoffice").value || !document.getElementById("usrerole").value)
    // if(!($("#NewNumber").val()))
    {
        alert("这些项为必填项");
    } else {
        var editUserObject = {
            "dat": [{
                "userId": editId,
                "userAccount": editAccount,
                "userPhone": $("input#usrephone").val(),
                "userRealName": $("input#usrename").val(),
                "policeOfficeName": document.getElementById("usreoffice").value,
                "roleName":document.getElementById("usrerole").value,
            }],
        }
        // alert(document.getElementById("usrerole").value);
        var editUser1 = editUserObject['dat'][0];
        // alert(editUser1);
        // alert("usdycbjcdghjsd"+editUser1);
        //向后端传送数据保存
        $.ajax({
            type: "POST",
            url: "http://192.168.0.200:8888/user/modifyUserInfo",
            data: JSON.stringify(editUser1),
            contentType: "application/json;charset=UTF-8",
            success: function (response) {
                if (response.meta.success) {
                    alert(response.meta.message);
                    location.reload();
                } else {
                    alert(response.meta.message);
                }
            },
            error: function (XmlHttpRequest, textStatus, errThrown) {
                console.log(XmlHttpRequest.status);
            }
        });
    }

})
// //删除人员
$('#usrdelRow').click(function (e) {
    //alert(this);
    var checks = document.getElementsByName("chk");
    n = 0;
    for (i = 0; i < checks.length; i++) {
        if (checks[i].checked)
            n++;
    }
    if (n < 1) {
        alert('请选择要删除的数据！');
        return false;
    } else {
        e.stopPropagation();
        var rsu = confirm('确定要删除该用户吗？');
        if (rsu) {
            var delId = $("td :checked").attr('data-id');
            $.ajax({
                type: "get",
                url: "http://192.168.0.200:8888/user/deleteUser",
                data: {"userId":delId},
                contentType: "application/json;charset=UTF-8",
                success: function (response) {
                    if (response.meta.success) {
                        alert('删除成功');
                        //alert(this);
                        $($("td :checked").parent().parent().parent()).remove();
                        window.location.reload();
                    }
                }
            });
            // alert('删除成功！');
        }
        else {
            alert('撤销操作！');
        }
    }
});
//点击查询按钮
$('#usrchaxun1').click(function (e) {
    var index=1;
    // alert(this);
    var account = $("#usrsearchText").val();
    var data1={};
    if(account != "" && account != null){
   
    var tby=$('#usrdynamic-table tbody').eq(0);
    //向后端传送数据保存 
    $.ajax({
    type: "get",
    url:"http://192.168.0.200:8888/user/getAllUserInfo",
    data:{},
    contentType: "application/json;charset=UTF-8",
    success: function(response){
    if(response.meta.success){
    var usData = response.data;
    var arrQuery = [];
    //console.log('后端返回了数据：'+usData);
    var len=usData.length;
    var i,j;
    for ( i = 0,j = 0; i < len ; i++,j++) {
                    if (response.data[i]['userAccount'] == account) {
                         arrQuery[j] = response.data[i];
                        // console.log(arrQuery[j]+"yyyyyyy");
                        
                    }else{
                        j--;
                    }

                }
                // alert("sadffdads");
                // console.log(arrQuery[j]+"aaaaaaa");
                // var result = JSON.stringify(arrQuery);
                // arrQuery1 = JSON.parse(result);
    if(arrQuery.length==0){
        alert("查询不到此帐号。");
        return false;
    }
    else{
    // alert(arrQuery.length);
    var strr='';
    for(var i=0;i<arrQuery.length;i++,index++){
    strr+=addPeople(arrQuery[i],index);
    }
    console.log(strr);
    tby.empty();
    tby.html(strr);
}
    }
    }
    });
}else{
    var URL = 'http://192.168.0.200:8888/user/getAllUserInfo';    //对应到接口的位置
    $.ajax({
        type: 'GET',
        url: URL,
        dataType: 'json',       //指定服务器返回的数据类型
        data: {},
        success: function (response) {//allPeople换成response
            console.log('成功调用！');
            console.log(JSON.stringify(response));
            updateTable(response);
        },
        error: function (err) {
            console.log('Exception:' + err.text);
        },
    });
}

})
