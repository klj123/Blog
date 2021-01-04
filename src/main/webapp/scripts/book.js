/**
 * 根据用户id查询笔记本信息
 */
function loadUserBooks() {
    //1.获取请求参数
    var uid = getCookie("uid");
    // alert(uid);
    // var userId = uid;
    //2.参数格式校验
    //为什么要判断为null?
    //防止没有登录就直接进入页面
    if (uid == null){
        window.location.href='log_in.html';
    }
    //3.发送Ajax
    $.ajax({
        url: base_path+"/book/loadbooks.do",
        type: "post",
        data: {"userId":uid},
        dataType: "json",
        success: function (result) {
            if (result.status == 0){
                var books = result.data;
                for (var i=0; i<books.length; i++){
                    var bookName = books[i].cn_notebook_name;
                    var bookId = books[i].cn_notebook_id;
                    createBookLi(bookId,bookName);
                }
            }
        },
        error: function () {
            alert("笔记本信息显示失败");
        }
    })
}
/*
* 创建笔记Li
* */
function createBookLi(bookId,bookName) {
    //     <!-- 动态生成笔记li元素 -->
    // <!-- 	<li class="online">
    //         <a  class='checked'>
    //         <i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> 使用Java操作符<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>
    //         </a>
    //         <div class="note_menu" tabindex='-1'>
    //         <dl>
    //         <dt><button type="button" class="btn btn-default btn-xs btn_move" title='移动至...'><i class="fa fa-random"></i></button></dt>
    //     <dt><button type="button" class="btn btn-default btn-xs btn_share" title='分享'><i class="fa fa-sitemap"></i></button></dt>
    //     <dt><button type="button" class="btn btn-default btn-xs btn_delete" title='删除'><i class="fa fa-times"></i></button></dt>
    //     </dl>
    //     </div>
    //     </li> -->
    var lis = '';
    lis += '<li class="online">';
    lis += '<a>';
    lis += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom">';
    lis += '</i>'+bookName+'</a></li>';
    //将js转化为jquery对象
    var $lis = $(lis);
    //给li绑定bookId数据
    $lis.data("bookId",bookId);
    //在book_ul后追加li
    $("#book_ul").append($lis);
}

/**
 * 笔记本的创建
 */
function addBook() {
    //1.获取请求参数
    /**
     * 笔记本名称，用户id
     */
    var bookName = $("#input_notebook").val().trim();
    var userId = getCookie("uid");
    //2.参数格式校验
    var ok = true;
    if (bookName == ""){
        ok = false;
        $("#notebook_span").html("笔记本名称为空");
    }
    if (userId == null){
        ok = false;
        window.location.href = 'log_in.html';
    }
    if (ok){
        $.ajax({
            url: base_path+"/book/add.do",
            type: "post",
            data: {"userId":userId,"bookName":bookName},
            dataType: "json",
            success: function (result) {
                //关闭弹窗
                closeAlertWindow();
                if (result.status == 0){
                    var bookId = result.data.cn_notebook_id;
                    //2.为笔记本列表追加新的元素
                    createBookLi(bookId,bookName);
                }
                //弹出错误提示框
                alert(result.msg);

            },
            error: function () {
                alert("创建笔记异常");
            }
        })
    }
    //3.发送Ajax
}
