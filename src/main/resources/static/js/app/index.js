// var main 이라 쓰면 나중에 함수 스코프가 겹칠때에도 main 객체 안에서만 해당 function이 유효하기때문에 겹칠 위험 감소
var main = {
        init : function(){
            var _this = this;
            $('#btn-save').on('click', function(){
                _this.save();
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

    }
}
main.init();
