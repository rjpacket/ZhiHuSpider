package com.taovo.rjp.packagedemo;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * @Author：RJP on 2017/5/11 11:05
 */

public class Charles {
    public static void main(String[] args) {
        gen("D:\\SogouDownload\\charles.jar");
    }

    private static void gen(String path) {
        ClassPool classPool = ClassPool.getDefault();
        try {
            classPool.insertClassPath(path); //复制出来charles.jar的文件路径
            CtClass ctClass = classPool.get("com.xk72.charles.License");
            CtMethod ctMethod = ctClass.getDeclaredMethod("a", null);
            ctMethod.setBody("{return true;}");
            ctMethod = ctClass.getDeclaredMethod("b", null);
            ctMethod.setBody("{return \"Regisered Name\";}");
            ctClass.writeFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
