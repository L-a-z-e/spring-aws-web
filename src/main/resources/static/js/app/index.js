// var main 이라 쓰면 나중에 함수 스코프가 겹칠때에도 main 객체 안에서만 해당 function이 유효하기때문에 겹칠 위험 감소
var main = {
        init : function(){
            var _this = this;

            $('#btn-save').on('click', function(){
                _this.save();
            });

            $('#btn-update').on('click',function() {
                _this.update();
            });
            $('#btn-delete').on('click',function() {
                _this.delete();
            });
        },
    save : function(){
            var data = {
                title : $('#title').val(),
                author : $('#author').val(),
                content : $('#content').val(),
            };

            $.ajax({
                type : 'POST',
                url : '/api/v1/posts',
                dataType : 'json',
                contentType : 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function(){
                alert('등록');
                window.location.href = '/'
            }).fail(function (error){
                alert(JSON.stringify(error));
            })

    },
        update : function(){
            var data = {
                title : $('#title').val(),
                content : $('#content').val(),
            };

            var id = $('#id').val();

            $.ajax({
                type : 'PUT',
                url : '/api/v1/posts/' +id,
                dataType : 'json',
                contentType : 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function(){
                alert('글이 수정되었습니다.');
                window.location.href = '/'
            }).fail(function (error){
                alert(JSON.stringify(error));
            })
        },
    delete : function(){

        var id = $('#id').val();

        $.ajax({
            type : 'DELETE',
            url : '/api/v1/posts/' +id,
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
        }).done(function(){
            alert('글이 삭제되었습니다.');
            window.location.href = '/'
        }).fail(function (error){
            alert(JSON.stringify(error));
        })
    },
}
main.init();
