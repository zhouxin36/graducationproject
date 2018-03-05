function formatMoney(num){
    num = num.toString().replace(/\$|\,/g,'');
    if(isNaN(num))
        num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num*100+0.50000000001);
    cents = num%100;
    num = Math.floor(num/100).toString();
    if(cents<10)
        cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
        num = num.substring(0,num.length-(4*i+3))+','+
            num.substring(num.length-(4*i+3));
    return (((sign)?'':'-') + num + '.' + cents);
}
function checkEmpty(id, name){
    var value = $("#"+id).val();
    if(value.length==0){

        $("#"+id)[0].focus();
        return false;
    }
    return true;
}


$(function(){


    $("a.productDetailTopReviewLink").click(function(){
        $("div.productReviewDiv").show();
        $("div.productDetailDiv").hide();
    });
    $("a.productReviewTopPartSelectedLink").click(function(){
        $("div.productReviewDiv").hide();
        $("div.productDetailDiv").show();
    });

    $("span.leaveMessageTextareaSpan").hide();
    $("img.leaveMessageImg").click(function(){

        $(this).hide();
        $("span.leaveMessageTextareaSpan").show();
        $("div.orderItemSumDiv").css("height","100px");
    });

    $("div#footer a[href$=#nowhere]").click(function(){
        alert("模仿天猫的连接，并没有跳转到实际的页面");
    });


    $("a.wangwanglink").click(function(){
        alert("模仿旺旺的图标，并不会打开旺旺");
    });
    $("a.notImplementLink").click(function(){
        alert("这个功能没做，蛤蛤~");
    });


});

var deleteOrderItem = false;
var deleteOrderItemid = 0;
$(function(){

    $("a.deleteOrderItem").click(function(){
        deleteOrderItem = false;
        var oiid = $(this).attr("oiid")
        deleteOrderItemid = oiid;
        $("#deleteConfirmModal").modal('show');
    });
    $("button.deleteConfirmButton").click(function(){
        deleteOrderItem = true;
        $("#deleteConfirmModal").modal('hide');
    });

    $('#deleteConfirmModal').on('hidden.bs.modal', function (e) {
        if(deleteOrderItem){
            var page="foredeleteOrderItem";
            $.post(
                page,
                {"oiid":deleteOrderItemid},
                function(result){
                    if("success"==result){
                        $("tr.cartProductItemTR[oiid="+deleteOrderItemid+"]").hide();
                    }
                    else{
                        location.href="login.jsp";
                    }
                }
            );

        }
    })

    $("img.cartProductItemIfSelected").click(function(){
        var selectit = $(this).attr("selectit")
        if("selectit"==selectit){
            $(this).attr("src","../images/noright.png");
            $(this).attr("selectit","false")
            $(this).parents("tr.cartProductItemTR").css("background-color","#fff");
        }
        else{
            $(this).attr("src","../images/right.png");
            $(this).attr("selectit","selectit")
            $(this).parents("tr.cartProductItemTR").css("background-color","#FFF8E1");
        }
        syncSelect();
        syncCreateOrderButton();
        calcCartSumPriceAndNumber();
    });
    $("img.selectAllItem").click(function(){
        var selectit = $(this).attr("selectit")
        if("selectit"==selectit){
            $("img.selectAllItem").attr("src","../images/noright.png");
            $("img.selectAllItem").attr("selectit","false")
            $(".cartProductItemIfSelected").each(function(){
                $(this).attr("src","../images/noright.png");
                $(this).attr("selectit","false");
                $(this).parents("tr.cartProductItemTR").css("background-color","#fff");
            });
        }
        else{
            $("img.selectAllItem").attr("src","../images/right.png");
            $("img.selectAllItem").attr("selectit","selectit")
            $(".cartProductItemIfSelected").each(function(){
                $(this).attr("src","../images/right.png");
                $(this).attr("selectit","selectit");
                $(this).parents("tr.cartProductItemTR").css("background-color","#FFF8E1");
            });
        }
        syncCreateOrderButton();
        calcCartSumPriceAndNumber();


    });

    $(".orderItemNumberSetting").keyup(function(){
        var pid=$(this).attr("pid");
        var stock= $("span.orderItemStock[pid="+pid+"]").text();
        var price= $("span.orderItemPromotePrice[pid="+pid+"]").text();

        var num= $(".orderItemNumberSetting[pid="+pid+"]").val();
        num = parseInt(num);
        if(isNaN(num))
            num= 1;
        if(num<=0)
            num = 1;
        if(num>stock)
            num = stock;

        syncPrice(pid,num,price);
    });

    $(".numberPlus").click(function(){

        var pid=$(this).attr("pid");
        var stock= $("span.orderItemStock[pid="+pid+"]").text();
        var price= $("span.orderItemPromotePrice[pid="+pid+"]").text();
        var num= $(".orderItemNumberSetting[pid="+pid+"]").val();

        num++;
        if(num>stock)
            num = stock;
        syncPrice(pid,num,price);
    });
    $(".numberMinus").click(function(){
        var pid=$(this).attr("pid");
        var stock= $("span.orderItemStock[pid="+pid+"]").text();
        var price= $("span.orderItemPromotePrice[pid="+pid+"]").text();

        var num= $(".orderItemNumberSetting[pid="+pid+"]").val();
        --num;
        if(num<=0)
            num=1;
        syncPrice(pid,num,price);
    });

    $("button.createOrderButton").click(function(){
        var params = "";
        $(".cartProductItemIfSelected").each(function(){
            if("selectit"==$(this).attr("selectit")){
                var oiid = $(this).attr("oiid");
                params += "&oiid="+oiid;
            }
        });
        params = params.substring(1);
        location.href="forebuy?"+params;
    });



})

function syncCreateOrderButton(){
    var selectAny = false;
    $(".cartProductItemIfSelected").each(function(){
        if("selectit"==$(this).attr("selectit")){
            selectAny = true;
        }
    });

    if(selectAny){
        $("button.createOrderButton").css("background-color","#C40000");
        $("button.createOrderButton").removeAttr("disabled");
    }
    else{
        $("button.createOrderButton").css("background-color","#AAAAAA");
        $("button.createOrderButton").attr("disabled","disabled");
    }

}
function syncSelect(){
    var selectAll = true;
    $(".cartProductItemIfSelected").each(function(){
        if("false"==$(this).attr("selectit")){
            selectAll = false;
        }
    });

    if(selectAll)
        $("img.selectAllItem").attr("src","../images/right.png");
    else
        $("img.selectAllItem").attr("src","../images/noright.png");



}
function calcCartSumPriceAndNumber(){
    var sum = 0;
    var totalNumber = 0;
    $("img.cartProductItemIfSelected[selectit='selectit']").each(function(){
        var oiid = $(this).attr("oiid");
        var price =$(".cartProductItemSmallSumPrice[oiid="+oiid+"]").text();
        price = price.replace(/,/g, "");
        price = price.replace(/￥/g, "");
        sum += new Number(price);

        var num =$(".orderItemNumberSetting[oiid="+oiid+"]").val();
        totalNumber += new Number(num);

    });

    $("span.cartSumPrice").html("￥"+formatMoney(sum));
    $("span.cartTitlePrice").html("￥"+formatMoney(sum));
    $("span.cartSumNumber").html(totalNumber);
}
function syncPrice(pid,num,price){
    $(".orderItemNumberSetting[pid="+pid+"]").val(num);
    var cartProductItemSmallSumPrice = formatMoney(num*price);
    $(".cartProductItemSmallSumPrice[pid="+pid+"]").html("￥"+cartProductItemSmallSumPrice);
    calcCartSumPriceAndNumber();

    var page = "forechangeOrderItem";
    $.post(
        page,
        {"pid":pid,"number":num},
        function(result){
            if("success"!=result){
                location.href="login.jsp";
            }
        }
    );

}
