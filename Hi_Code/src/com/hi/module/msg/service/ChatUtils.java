package com.hi.module.msg.service;

import com.hi.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * User: Zerui
 * Time: 2015/3/9 19:53
 */
public class ChatUtils {
    public static String convid(String myId, String otherId) {
        List<String> ids;
        ids = new ArrayList<String>();
        ids.add(myId);
        ids.add(otherId);
        return convid(ids);
    }

    public static String convid(List<String> peerIds) {
        Collections.sort(peerIds);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < peerIds.size(); i++) {
            if (i != 0) {
                sb.append(":");
            }
            sb.append(peerIds.get(i));
        }
        return Utils.md5(sb.toString());
    }
}
