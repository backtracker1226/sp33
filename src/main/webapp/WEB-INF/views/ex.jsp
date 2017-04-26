<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
 <title>Bootstrap Example</title>
 <meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
 <h2>Modal Example</h2>
 <!-- Trigger the modal with a button -->
 <button type="button" class="btn btn-info btn-lg" id="modalBtn">Open Modal</button>
 

 <!-- Modal -->
 <div class="modal fade" id="myModal" role="dialog">
   <div class="modal-dialog">
   
     <!-- Modal content-->
     <div class="modal-content">
       <div class="modal-header">
         <button type="button" class="close" data-dismiss="modal">&times;</button>  <!-- data-dismiss 때문에 닫힐 수 있다. -->
         <h4 class="modal-title">Modal Header</h4>
       </div>
       <div class="modal-body">
     
       
<style>
.fileDrop {
   width: 400px;
   height: 300px;
   background-color: gray;
}
</style>

<div class="fileDrop"></div>

   
       
      <!-- <form id="f1" action="upload2" method="post" enctype="multipart/form-data">
         <p>Some text in the modal.
             <input type="file" name="file[]">
         </p>
         <p>Some text in the modal.
             <input type="file" name="file[]">
         </p>
         <p>Some text in the modal.
             <input type="file" name="file[]">                        
         </p>
        </form> -->  
       
       </div>
       <div class="modal-footer">
       <button type="button" class="btn btn-danger saveBtn">Save</button>
         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
       </div>
     </div>
     
   </div>
 </div> <!-- end Modal -->
 
</div>
    <ul class = 'uploadedList'>
    
    </ul>

<script>

$(document).ready(function(){
    $("#modalBtn").click(function(){        
        $("#myModal").modal("toggle");        
    });
    
    $(".saveBtn").click(function(e){
        $("#f1").submit();        
    });
    
     $(".fileDrop").on("dragenter dragover", function(event) { // drag&drop 후의 이벤트를 방지한다.
        event.preventDefault();
    });        

    $(".fileDrop").on("drop", function(event) { // drag&drop 후의 이벤트를 방지한다.
        event.preventDefault();
    	
        var files = event.originalEvent.dataTransfer.files; // 파일데이타를 추려낸다.
       
        console.log(files); //복수의 파일이 배열로 나온다.
      
        var formData = new FormData();
       
        for(var i=0; i<files.length; i++){
        	
        	formData.append("file", files[i]); //input type file이다.
        }
       
        $.ajax({
       
            url: "/web/upload2",
            data: formData,
            processData: false,//이 2라인 때문에 ajax - 프로세스 상태바를 볼 수 있다. ajax progress data
            contentType: false,//이 2라인 때문에 ajax
            type: "post",
            success: function(result){
                console.log("upload complete..........")
                alert(result);
               
                for(var i=0; i<result.length; i++){
                var str = "<li><img class='view' src='display?fileName="+result[i]+"'><Button class='deleteBtn' value='"+result[i]+"'>Delete</Button></li>";
                        $(".uploadedList").append(str);
                        console.log("새로운 테스트");
                }
                
                $(".view").on("click", function(e){
                	console.dir(this);
                	var str = this.currentSrc;
                	var arr = str.split("=");
                	var imgstr = arr[1];
                	var iarr = imgstr.split("\"");
                	console.log(iarr[0]);
                	
                	
                });
                
                
                
                $(".deleteBtn").on("click",function(e){
                	e.stopPropagation();
                	
                	var that=$(this);
                	
                	var deleteFileName =$(this).val();
                	
                	
                	$.ajax({
                	
                		url:"deleteFile",
                		type:"post",
                		data:{fileName:$(this).val()},
                		dataType:"Text",
                		success:function(result){
                			that.parent("li").remove();
                			alert("지웠냐?");
                		}
                		
                	})
                	
                	
                	
                	
                });

            }

       

        });//ajax로 데이타 보내기
       
   
    alert("droped............");
   
    $("#myModal").modal("hide");
    
    });
    

});


</script>

</body>
</html>