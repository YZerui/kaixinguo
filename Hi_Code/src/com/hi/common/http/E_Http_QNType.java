package com.hi.common.http;

/**
 * ��ţͼƬ�ϴ�����
 * User: Zerui
 * Time: 2015/3/15 13:59
 */
public enum E_Http_QNType {
    NORMAL("1"),
    LEAVEMSG("2"),
    IMMSG("3");
    private String type;

    private E_Http_QNType(String type) {
        this.type = type;
    }
}
