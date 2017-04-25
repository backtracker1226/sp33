<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

    <form method="post" enctype="multipart/form-data">
        <p>
            <input type="text" name="p1" value="AAAA">
        </p>
        <p>
            <input type="file" name="file">
        </p>
        <p>
            <button>Save</button>
        </p>
    </form>

    <hr />
    <hr />
    <hr />
    

    <style>
.fileDrop {
    width: 400px;
    height: 300px;
    background-color: gray;
}
</style>

    <div class="fileDrop"></div>
    
    <ul class = 'uploadedList'>
    
    </ul>

    <script src="https://code.jquery.com/jquery-3.2.1.min.js"
        integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
        crossorigin="anonymous"></script>

    <script>
    $(document).ready(function() {

        $(".fileDrop").on("dragenter dragover", function(event) { // drag&drop 후의 이벤트를 방지한다.
            event.preventDefault();
        });

        $(".fileDrop").on("drop", function(event) { // drag&drop 후의 이벤트를 방지한다.
            event.preventDefault();
            console.log("droped.........");
            
            console.log("시발 혹시 너?");
            
            var files = event.originalEvent.dataTransfer.files; // 파일데이타를 추려낸다.
            
            console.log(files); //복수의 파일이 배열로 나온다.
            
            var file = files[0];
            
            var formData = new FormData();
            
            formData.append("file", file); //input type file이다.
            
            $.ajax({
            
                url: "/web/upload1",
                data: formData,
                processData: false,//이 2라인 때문에 ajax - 프로세스 상태바를 볼 수 있다. ajax progress data
                contentType: false,//이 2라인 때문에 ajax
                type: "post",
                success: function(result){
                    console.log("upload complete..........");
                    alert(result);
                    var str = "<li><img src='display?fileName="+result+"'></li><button class='del"+files+"'>Test</button>";
                    $(".uploadedList").append(str);
/*                 	$(".del").on("click", function(event){
                		var tagName = result;
                		console.log(result);
                		alert(event); */
                	
                }
                
            });//ajax로 데이타 보내기
        });
    });
</script>

</body>
</html>