package com.hi.common.http;

/**
 * ��Ϣ����״̬
 *
 * @author MM_Zerui
 */
public enum E_Http_SendState {
    HIDE(2),//����
    SUCCESS(0),//�ɹ�
    NORMAL(1),//����
    DELIVERED(3),//��Ϣ����
    START(4),//��ʼ����
    FAIL(-1);//ʧ��
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
