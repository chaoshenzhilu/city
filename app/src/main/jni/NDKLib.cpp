//
// Created by 余显超 on 2018/12/6.
//
#include <stdio.h>
#include <jni.h>
#include "com_example_myapplication_JNITools.h"
#include <cstdio>
// 引入log头文件
#include  <android/log.h>
// log标签
#define  TAG    "JNI里面的LOG"
//LOG里面只能放char类型
//ANDROID_LOG_INFO：是日志级别；
//TAG：是要过滤的标签，可以在LogCat视图中过滤。
//__VA_ARGS__：是实际的日志内容。
//完成上面2步之后，我们就可以在.c/cpp文件中添加LOGI、LOGD、LOGE去打印信息！使用LOGI、LOGD、LOGE的方法和使用printf一样
// 定义info信息
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__)
// 定义debug信息
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)
// 定义error信息
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG,__VA_ARGS__)
JNIEXPORT void JNICALL Java_com_example_myapplication_JNITools_addNum(JNIEnv* env, jobject jobject,
                                                                      jint num1, jint num2,jstring msg) {
    const char * str;
    jboolean isCopy;
    str=env->GetStringUTFChars( msg, &isCopy);
    if(str==NULL){
        printf("msg == NULL");
        return;
    }

    printf("%s",str);
    env->ReleaseStringUTFChars(msg,str);

    jstring javaString;
    javaString=env->NewStringUTF(str);

    jint num=num1 + num2;
    jclass clazz=env->GetObjectClass(jobject);
//    jclass clazz=env->FindClass("com/example/myapplication/JNITools");
    if(clazz==NULL){
        printf("find Class Error");
        return;
    }
    jmethodID id=env->GetMethodID(clazz,"setText","(ILjava/lang/String;)V");
    if(id==NULL){
        printf("find Method Error");
        return;
    }
    env->CallVoidMethod(jobject,id,num,javaString);
}



//JNIEnv 概念 : 是一个线程相关的结构体, 该结构体代表了 Java 在本线程的运行环境 ;
//直接创建jstring 用NewStringUTF；把jstring转char 用GetStringUTFChars  用完之后记得释放
//c++调用java的方法时 无返回值的 CallVoidMethod
//        有返回值的如：String，object用 CallObjectMethod  其他基本数据类型 用CallBooleanMethod等等
//SetObjectField 用于给创建的类属性赋值
JNIEXPORT void JNICALL Java_com_example_myapplication_JNITools_caLLJavaMehodFormJni
        (JNIEnv *env, jobject object){
    jclass clazz=env->FindClass("com/example/myapplication/JNIHandle");
    jmethodID constructor =env->GetMethodID(clazz,"<init>","()V");
    jfieldID nameID=env->GetFieldID(clazz,"name","Ljava/lang/String;");
    jobject jniHandler=env->NewObject(clazz,constructor);
    env->SetObjectField(jniHandler,nameID,env->NewStringUTF("达达 大叔大婶多"));
    jmethodID getStringForJava=env->GetMethodID(clazz,"getStringForJava","(Lcom/example/myapplication/JNIHandle;)Ljava/lang/String;");
    jstring aaa=(jstring)env->CallObjectMethod(jniHandler, getStringForJava,jniHandler);
    const char  *bbb;
    bbb=env->GetStringUTFChars(aaa,0);
    LOGE(" %s",bbb);
    env->ReleaseStringUTFChars(aaa,bbb);
}

















