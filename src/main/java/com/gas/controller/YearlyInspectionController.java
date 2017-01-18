package com.gas.controller;

import com.gas.common.ConfigProperties;
import com.gas.common.base.BaseController;
import com.gas.common.utils.DateUtils;
import com.gas.common.utils.IdGen;
import com.gas.entity.Result;
import com.gas.entity.YearlyInspection;
import com.gas.service.YearlyInspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 * Created by 刘维军 on 2017/01/18.
 */
@Controller
@RequestMapping(value = "/yearlyInspection")
public class YearlyInspectionController extends BaseController {
    @Autowired
    YearlyInspectionService service;

    private static final String filePaht= ConfigProperties.getConfig("filePath");

    @RequestMapping(value = "",method = RequestMethod.POST)
    public Object add(String gasNumber,@RequestParam("Filedata")CommonsMultipartFile file){
        Integer count=0;
        Result result=new Result();
        String imgUrlPath="";
        if (file!=null){
            String fileName="";
            String dirDate="";
            fileName=IdGen.getuuid()+".jpg";
            dirDate=DateUtils.getYear();
            Path path= Paths.get(filePaht,dirDate,fileName);
            File newFile=path.toFile();
            if (!newFile.getParentFile().exists()&&!newFile.getParentFile().isDirectory()){
                newFile.getParentFile().mkdir();
            }
            try{
                BufferedImage image = ImageIO.read(file.getInputStream());
                ImageIO.write(image, "jpg", newFile);
                imgUrlPath="/yearlyInspection/"+dirDate+"/"+fileName;
                YearlyInspection yl=new YearlyInspection();
                yl.setCreateTime(new Date());
                yl.setGasImg(imgUrlPath);
                yl.setGasNumber(gasNumber);
                yl.setYear(Integer.parseInt(dirDate));
                count= service.add(yl);
                if (count>0){
                    result.setSuccess(true);
                    result.setMessage("上传成功！");
                    result.setData(true);
                }
            } catch (IOException e) {
                e.printStackTrace();
                result.setSuccess(true);
                result.setMessage("上传失败！");
                result.setData(false);
            }
        }
        return  result;
    }

}
