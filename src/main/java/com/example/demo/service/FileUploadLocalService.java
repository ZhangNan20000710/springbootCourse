package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileUploadLocalService {

    //文件上传
    public String upload(MultipartFile file,String name1,String name2) throws IOException {
        String filename=UUID.randomUUID()+getSuffix(Objects.requireNonNull(file.getOriginalFilename()));
        String filepath = getUploadCPath(name1,name2) + File.separator;
        String filepath2 = getUploadPath(name1,name2) + File.separator;

        BufferedOutputStream out = new BufferedOutputStream(
                new FileOutputStream(new File(filepath+filename)));
        BufferedOutputStream out1 = new BufferedOutputStream(
                new FileOutputStream(new File(filepath2+filename)));

        out.write(file.getBytes());
        out1.write(file.getBytes());
        out.flush();
        out1.flush();
        out.close();
        out1.close();
        return "/images/portrait/"+name1+"/"+name2+"/"+filename;
    }

    //添加测试文件
    public String uploadLocal(File file,String name1,String name2) throws IOException {
        String filename=UUID.randomUUID()+getSuffix(Objects.requireNonNull(file.getName()));

        String filepath = getUploadCPath(name1,name2) + File.separator;
        String filepath1 = getUploadPath(name1,name2) + File.separator;

        BufferedOutputStream out = new BufferedOutputStream(
                new FileOutputStream(new File(filepath+filename)));

        BufferedOutputStream out1 = new BufferedOutputStream(
                new FileOutputStream(new File(filepath1+filename)));

        /*获取要上传文件的字节*/
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        byte[] buffer = new byte[1024*30];
        in.read(buffer, 0, 1024*30);

        out.write(buffer);
        out1.write(buffer);

        out.flush();
        out1.flush();

        out.close();
        out1.close();
        return "/images/portrait/"+name1+"/"+name2+"/"+filename;
    }

    //获取当前路径
    private String getUploadCPath(String name1,String name2) {
        File path = null;
        try {
            //获取当前类路径
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert path != null;
        return getABSPath(path,name1,name2);
    }

    private String getUploadPath(String name1,String name2) {
        File path = null;
        try {
            //获取当前类路径
            path =new File(ResourceUtils.getURL("").getPath(),"/src/main/resources");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert path != null;
        return getABSPath(path,name1,name2);
    }

    private String getABSPath(File path,String name1,String name2){
        if (!path.exists()){
            path = new File("");
        }
        //生成文件上传的目标路径
        File upload = new File(path.getAbsolutePath(), "static/images/portrait/"+name1+"/"+name2+"/");
        if (!upload.exists()) {
            upload.mkdirs();
        }
        //返回绝对路径
        return upload.getAbsolutePath();
    }


    public String  Delete(String filePath) throws FileNotFoundException {
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        File file=new File(path.getAbsolutePath(),"static"+filePath);
        if (file.delete()) {
            return "删除成功";
        }else {
            return "删除失败";
        }
    }

    public String getSuffix(String name){
        String[] s = name.split("\\.");
        int length = s.length;
        return "."+s[length-1];
    }
}
