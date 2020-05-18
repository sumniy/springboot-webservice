var index = {
   init : function () {
       var _this = this;
       $('#btn-save').on('click', function() {
           _this.save();
       });

       $('#btn-update').on('click', function () {
           _this.update();
       });

       $('#btn-delete').on('click', function () {
           _this.delete();
       });
   },
   save : function () {
       var data = {
           title: $('#title').val(),
           author: $('#author').val(),
           content: $('#content').val()
       };

       // api/v1/posts url로 post 메소드 방식으로 입력된 데이터들을 전송한다
       // done -> ajax 통신이 완료되면, 성공여부를 알려주고 성공 시 메인페이지로 이동
       $.ajax({
           type: 'POST',
           url: '/api/v1/posts',
           dataType: 'json',
           contentType: 'application/json; charset=utf-8',
           data: JSON.stringify(data)
           }).done(function() {
               alert('글이 등록되었습니다.');
               window.location.href = '/';
           }).fail(function (error) {
               alert(JSON.stringify(error));
           });
   },
    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 수정되었습니다. 글 번호:' + id);
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function () {
            alert('글이 삭제되었습니다. 글번호:'+id);
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

index.init();