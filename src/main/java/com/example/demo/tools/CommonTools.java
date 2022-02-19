package com.example.demo.tools;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

public class CommonTools {
    public static Map makePage(Map map) {
        if(map.get("pageSize")==null){
            map.put("pageSize",10);
        }
        if(map.get("pageNumber")==null){
            map.put("pageNumber",1);
        }
        return map;
    }
    public static String fileUpload(MultipartFile file){
        //文件名后缀
        String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //设置文件存放路径
//        String uploadDir=request.getSession().getServletContext().getRealPath("/")+"upload/";
//        //上传文件名
//        String fileName= UUID.randomUUID()+suffix;
//        //服务端保存的文件对象
//        File saveFile=new File(uploadDir+fileName);
//        //将上传的文件写入到服务器端文件内
//        file.transferTo(saveFile);
        String uploadDir=file.getOriginalFilename();
        return "";
    }
    //集合遍历

}
