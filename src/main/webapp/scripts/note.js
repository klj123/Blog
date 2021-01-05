/**
 * 加载笔记本对应的笔记
 */
function loadBookNotes() {
    //1.获取请求参数
    //为什么要清空所有，就是note_ul只会加载一次，所以要清空所有
    $("#book_ul").find("a").removeClass("checked");
    var bookId = $(this).data("bookId");
    // alert(bookId);
    $(this).find("a").addClass("checked");
    //2.参数校验
    //3.发送Ajax
    $.ajax({
        url:base_path+"/note/loadNotes.do",
        type: "post",
        data: {"bookId":bookId},
        dataType: "json",
        success: function (result) {
            //
            $("#note_ul").empty();
            if (result.status == 0){
                var notes = result.data;
                for (var i=0; i<notes.length; i++){
                    var noteTitle = notes[i].cn_note_title;
                    var noteId = notes[i].cn_note_id;
                    createNote(noteId,noteTitle);
                }
            }
        },
        error: function () {
            alert("显示笔记异常");
        }
    })
}
function createNote(noteId,noteTitle) {
//     <!-- 动态生成笔记li元素 -->
//  	<li class="online">
//         <a class='checked'>
//         <i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> 使用Java操作符<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>
//         </a>
//         <div class="note_menu" tabindex='-1'>
//         <dl>
//         <dt><button type="button" class="btn btn-default btn-xs btn_move" title='移动至...'><i class="fa fa-random"></i></button></dt>
//     <dt><button type="button" class="btn btn-default btn-xs btn_share" title='分享'><i class="fa fa-sitemap"></i></button></dt>
//     <dt><button type="button" class="btn btn-default btn-xs btn_delete" title='删除'><i class="fa fa-times"></i></button></dt>
//     </dl>
//     </div>
//     </li>
    var lis = '';
    lis += '<li class="online">';
    lis += '<a>';
    lis += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
    lis += '</a>';
    lis += '<div class="note_menu" tabindex=\'-1\'>';
    lis += '<dl>';
    lis += '<dt><button type="button" class="btn btn-default btn-xs btn_move" title=\'移动至...\'><i class="fa fa-random"></i></button></dt>';
    lis += '<dt><button type="button" class="btn btn-default btn-xs btn_share" title=\'分享\'><i class="fa fa-sitemap"></i></button></dt>';
    lis += '<dt><button type="button" class="btn btn-default btn-xs btn_delete" title=\'删除\'><i class="fa fa-times"></i></button></dt>';
    lis += '</dl>';
    lis += '</div>';
    lis += '</li>';
    var $lis = $(lis);
    $lis.data("noteId",noteId);
    $("#note_ul").append($lis);
}

//显示笔记内容（根据笔记id，加载笔记）
function loadNote() {
    //1.请求参数
    $("#note_ul a").removeClass("checked");
    var noteId = $(this).data("noteId");
    //设置选中
    $(this).find("a").addClass("checked");
    // alert(noteId);
    //2.参数校验
    //3.发送Ajax
    $.ajax({
        url:base_path+"/note/loadNote.do",
        type: "post",
        data: {"noteId":noteId},
        dataType: "json",
        success: function (result) {
            if (result.status == 0){
                $("#input_note_title").val(result.data.cn_note_title);
                //使用富文本编辑器设置body
                um.setContent(result.data.cn_note_body);
            }
        },
        error: function () {
            alert("查询笔记异常");
        }
    })
}
//保存笔记
function updateNote() {
    //1.获取请求参数
    var title = $("#input_note_title").val().trim();
    var body = um.getContent();
    var $li = $("#note_ul a.checked").parent();
    var noteId = $li.data("noteId");
    //2.参数格式校验
    if ($li.length == 0){
        alert("请选择要保存的笔记");
    }else if (title == ""){
        $("#note_title_span").html("<font color='red'>标题不能为空</font>")
    }else {
        //3.发送Ajax
        $.ajax({
            url: base_path+"/note/update.do",
            type: "post",
            data: {"title":title,"body":body,"noteId":noteId},
            dataType: "json",
            success: function (result) {
                //更新笔记列表
                var lis = '';
                lis += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+title+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
                //将选中li元素的a标签的内容替换掉
                $li.find("a").html(lis);
                //提示保存信息
                alert(result.msg);
            },
            error: function () {
                alert("保存笔记异常");
            }
        })
    }
}

/**
 * 创建笔记
 */
function addNote() {
    //1.获取请求参数
    var userId = getCookie("uid");
    var $li = $("#book_ul a.checked").parent();
    var bookId = $li.data("bookId");
    var noteTitle = $("#input_note").val().trim();
    $("#note_span").html("");
    //2.参数格式校验
    var ok = true;
    if (userId == null){
        ok = false;
        window.location.href = 'log_in.html';
    }
    if (noteTitle == ""){
        ok = false;
        $("#note_span").html("笔记名为空");
    }
    if(ok){
        $.ajax({
            url: base_path+"/note/add.do",
            type: "post",
            data: {"userId":userId,"bookId":bookId,"noteTitle":noteTitle},
            dataType: "json",
            success: function (result) {
                //TODO
                closeAlertWindow();
                if (result.status == 0){
                    var note = result.data;
                    var noteId = note.cn_note_id;
                    createNote(noteId,noteTitle);
                }
                alert(result.msg);
            },
            error: function () {
                alert("创建笔记异常");
            }
        })
    }
    //3.发送Ajax
}

/**
 * 笔记菜单的显示
 */
function showMenu() {
    //进来先隐藏
    hideMenu();
    //$(this)指的是下拉菜单按钮
    var $menu = $(this).parent().next();
    $menu.slideDown(200);
    $("#note_ul a").removeClass("checked");
    $(this).parent().addClass("checked");
    //jQuery解决冒泡事件
    return false;
}

/**
 * 隐藏菜单
 */
function hideMenu() {
    //hidden是jquery的，hide是js的
    $("#note_ul div").hide();
}

/**
 * 删除笔记
 */
function deleteNote() {
    //1.获取请求参数
    var $li = $("#note_ul a.checked").parent();
    var noteId = $li.data("noteId");
    //2.参数格式校验
    //3.发送Ajax
    $.ajax({
        url: base_path+"/note/delete.do",
        type: "post",
        data: {"noteId":noteId},
        dataType: "json",
        success: function (result) {
            //关闭删除的对话框
            closeAlertWindow();
            if (result.status == 0){
                //删除对应的note的li元素
                $li.remove();
            }
            alert(result.msg);
        },
        error: function () {
            alert("删除笔记异常");
        }
    })
}