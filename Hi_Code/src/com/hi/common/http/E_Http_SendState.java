package com.hi.common.http;

/**
 * 消息发送状态
 *
 * @author MM_Zerui
 */
public enum E_Http_SendState {
    HIDE(2),//隐藏
    SUCCESS(0),//成功
    NORMAL(1),//正常
    DELIVERED(3),//消息到达
    START(4),//开始发送
    FAIL(-1);//失败
    int value;

    private E_Http_SendState(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static E_Http_SendState get(int value) {
        switch (value) {
            case -1:
                return FAIL;
            case 2:
                return HIDE;
            case 0:
                return SUCCESS;
            case 1:
                return NORMAL;
            case 3:
                return DELIVERED;
            case 4:
                return START;
            default:
                return FAIL;
        }
    }
}
