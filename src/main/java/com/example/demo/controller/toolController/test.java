//package com.example.demo.controller.toolController;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectOutputStream;
//
//public class test {
//    //对象流的使用1.序列化：将内存中的Java对象保存到磁盘中或通过网络传输出去
//    public void testObjectOutputStream() {
//        ObjectOutputStream oos = null;
//        try {
//            oos = new ObjectOutputStream(new FileOutputStream("object.txt"));
//            oos.writeObject(new String("网络编程"));
//            oos.flush();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (oos!=null){
//                try {
//                    oos.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//}
