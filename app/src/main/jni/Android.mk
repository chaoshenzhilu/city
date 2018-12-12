
LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := NDKLib
LOCAL_SRC_FILES := NDKLib.cpp
LOCAL_LDLIBS := -lm -llog
include $(BUILD_SHARED_LIBRARY)