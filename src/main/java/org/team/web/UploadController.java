package org.team.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
   
   @GetMapping("/upload1")
   public void upload1(){
       
   }
   
   @GetMapping(name="/display", produces={"image/jpg"})
   @ResponseBody // Response시 내보내는 것
   public byte[] display (String fileName) throws Exception{
       
       InputStream fin = new FileInputStream("C:\\zzz\\upload\\" + fileName);
       
       byte[] arr = IOUtils.toByteArray(fin);
       
       return arr;
   }
   
   @ResponseBody
   @RequestMapping(value="/realSize", method=RequestMethod.POST)
   public String realSize(String fileName){
	   
	   System.out.println("얏호 크게 본다.");
	   System.out.println(fileName);//32개ㅑ
	   
	   String slName = fileName.substring(39,fileName.length());	// 섬네일 파일을 지우려면 fileName을 지우시오 이놈이 가지고 있소
	   
	   String realName = fileName.substring(0,37)+slName;			// 원본 파일 이름은 이놈이 가지고 있다
	   
	   return realName;
   }
   
   
   @ResponseBody
   @RequestMapping(value="/deleteFile", method=RequestMethod.POST)
   public ResponseEntity<String> deleteFile(String fileName){
	   
	   System.out.println("얏호 딜리트다");
	   System.out.println(fileName);//32개ㅑ
	   
	   String slName = fileName.substring(39,fileName.length());	// 섬네일 파일을 지우려면 fileName을 지우시오 이놈이 가지고 있소
	   
	   String realName = fileName.substring(0,37)+slName;			// 원본 파일 이름은 이놈이 가지고 있다
	   
	   if(slName != null && realName != null){
		   new File("C:\\zzz\\upload\\" + fileName).delete();
		   new File("C:\\zzz\\upload\\" + realName).delete();
	   }
	   
	   System.out.println(realName);
	   
	   System.out.println(slName);
	   
	   //String realName = fileName;
	   
	   
	   
	   return new ResponseEntity<String>("deleted", HttpStatus.OK);
   }
   
   
   
   
   
   
   @PostMapping("/upload2")
   @ResponseBody
   public String[] upload2Post(@RequestParam("file") MultipartFile[] files)throws Exception{ // 파일 여러개 올릴 때는 당연히 배열이 된다. 배열이니까 JSON으로 보내게 된다.
        
	   ArrayList<String> list = new ArrayList<>();
	   
       for (MultipartFile file : files) {
           
    	   UUID uid = UUID.randomUUID();
           
           String uidStr = uid.toString();
           String saveName = uidStr + "_" + file.getOriginalFilename(); //UUID와의 구분 _
           String thumbName = uidStr + "_s_" + file.getOriginalFilename(); //thumbName 구분 _s_
    	   
    	  
    	   //arr.add(file.getOriginalFilename());
           //System.out.println(file.getOriginalFilename());
           //System.out.println(file.getSize());    	   
    	   System.out.println(list.toString());
    	   IOUtils.copy(file.getInputStream(), new FileOutputStream("C:\\zzz\\upload\\" + saveName)); //예외 던지기, file.getInputStream() = inputstream
           
           BufferedImage src = ImageIO.read(file.getInputStream());
           
           BufferedImage thumb = Scalr.resize(src, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_HEIGHT, 100); //높이 고정으로 리사이즈
           
           ImageIO.write(thumb, "jpg", new FileOutputStream("C:\\zzz\\upload\\" + thumbName)); // 비동기 처리이기 때문에 시간이 좀 걸린다..
           
           list.add(thumbName);
    	   
            
        }
       
       


       return list.toArray(new String[list.size()]);
   }
   
   @PostMapping(name = "/upload1", produces="text/plain;charset=UTF-8") //응답 나갈 수 있는 mime 타입을 지정해주는 것이다.
   @ResponseBody // 한글처리 필요 - Content-Type: text/plain;charset=ISO-8859-1 => UTF-8로의 처리 필요 => 위의 produce 처리
   public String upload1post(@RequestParam("file") MultipartFile file) throws FileNotFoundException, IOException{ // import 주의 - import org.springframework.web.multipart.MultipartFile; // @RequestParam("file")이라고 명시 - 유지보수 용이
       System.out.println("upload post.........");
       System.out.println("getName: " + file.getName());//파일 정보 추출
       System.out.println("getOriginalFilename: " + file.getOriginalFilename());
       System.out.println("size: " + file.getSize());
       
       UUID uid = UUID.randomUUID();
       
       String uidStr = uid.toString();
       String saveName = uidStr + "_" + file.getOriginalFilename(); //UUID와의 구분 _
       String thumbName = uidStr + "_s_" + file.getOriginalFilename(); //thumbName 구분 _s_
       
               
       IOUtils.copy(file.getInputStream(), new FileOutputStream("C:\\zzz\\upload\\" + saveName)); //예외 던지기, file.getInputStream() = inputstream
       
       BufferedImage src = ImageIO.read(file.getInputStream());
       
       BufferedImage thumb = Scalr.resize(src, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_HEIGHT, 100); //높이 고정으로 리사이즈
       
       ImageIO.write(thumb, "jpg", new FileOutputStream("C:\\zzz\\upload\\" + thumbName)); // 비동기 처리이기 때문에 시간이 좀 걸린다..
       
       return thumbName;
       
       
       
   }

}