package com.example.demo.controller.admin;


import com.example.demo.bean.MyFile;
import com.example.demo.groups.user.add;
import com.example.demo.untils.ResultReturn;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/admin")
public class FileController {

    /**
     * 文件上传
     * 文件夹按照图片视频音频文本其他+年月日创建
     * @param request
     * @param file
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "文件上传",notes="文件夹按照图片视频音频文本其他+年月日创建")
    @ApiImplicitParam(name = "file", value = "文件", required = true, dataType = "file")
    @PostMapping("/upload")
    public String upload(HttpServletRequest request, MultipartFile file) throws IOException {
        //指定文件上传目录
//        String uploadDir=request.getSession().getServletContext().getRealPath("/")+"upload/";
        String uploadDir="G:/桌面/upload/";
        //文件名后缀
        String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
        //获取年月日
        Date date = new Date();
        DateFormat format=new SimpleDateFormat("yyyyMMdd");
        String time =format.format(date);
        String[] picSuffic={"png","jpeg","gif","bmp"};
        String[] vedioSuffix={"mp4","avi","wmv","mpg","mpeg","mov","rm","ram","swf","flv"};
        String[] txtSuffix={"txt","doc","pdf","xls","xlsx","docx","pptx"};
        Collection picColl= Arrays.asList(picSuffic);
        Collection vedioColl= Arrays.asList(vedioSuffix);
        Collection txtColl= Arrays.asList(txtSuffix);
        if(picColl.contains(suffix)){
            uploadDir=uploadDir+"pic/"+time+"/";
        }else if(vedioColl.contains(suffix)){
            uploadDir=uploadDir+"vedio/"+time+"/";
        }else if(txtColl.contains(suffix)){
            uploadDir=uploadDir+"txt/"+time+"/";
        }else{
            uploadDir=uploadDir+"other/"+time+"/";
        }
        //判断目录是否存在，如果不存在则构建目录
        File dir=new File(uploadDir);
        if(!dir.mkdirs()){
            dir.mkdirs();
        }
        //上传文件名
//        String fileName= UUID.randomUUID()+suffix;
        String fileName= file.getOriginalFilename();
        //服务端保存的文件对象
        File saveFile=new File(uploadDir+fileName);
        //将上传的文件写入到服务器端文件内
        file.transferTo(saveFile);
        return uploadDir;
    }
    //文件添加
    @PostMapping("/file-add")
    public ResultReturn fileAdd(@RequestBody @Validated(value= add.class) MyFile file){

        return ResultReturn.success("");
    }
}
