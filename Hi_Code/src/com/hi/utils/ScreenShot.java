package com.hi.utils;

import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
   
import android.app.Activity;  
import android.graphics.Bitmap;  
import android.graphics.Rect;  
import android.os.Environment;
import android.view.View;  
   
public class ScreenShot {  
   
    private static Bitmap takeScreenShot(Activity activity) {  
        // View是你需要截图的View  
        View view = activity.getWindow().getDecorView();  
        view.setDrawingCacheEnabled(true);  
        view.buildDrawingCache();  
        Bitmap b1 = view.getDrawingCache();  
   
        // 获取状态栏高度  
        Rect frame = new Rect();  
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);  
        int statusBarHeight = frame.top;  
   
        // 获取屏幕长和高  
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();  
        int height = activity.getWindowManager().getDefaultDisplay()  
                .getHeight();  
        
        // 去掉标题栏  
//        Bitmap b = Bitmap.createBitmap(b1, 0, 0, width, height  
//                - statusBarHeight);  
        Bitmap b=Bitmap.createBitmap(b1);
        view.destroyDrawingCache();  
        return b;  
//        return b1;
        
    }  
   
    private static String savePic(Bitmap b) {  
    	if (checkSDCardAvailable()) {
			String filePath = Environment.getExternalStorageDirectory()
					.toString()+"/MeiMeiImg";
			File dir = new File(filePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(filePath+"/screen.png");
				if (null != fos) {
					b.compress(Bitmap.CompressFormat.PNG, 100, fos);
					fos.flush();
					fos.close();
				}
				return filePath+"/screen.png";
			} catch (FileNotFoundException e) {
				// e.printStackTrace();
			} catch (IOException e) {
				// e.printStackTrace();
			}
		}
    	return null;
    }  
   
    public static String shoot(Activity a) {  
        return ScreenShot.savePic(ScreenShot.takeScreenShot(a));  
    }  
    /**
	 * Check the SD card
	 * 
	 * @return
	 */
	public static boolean checkSDCardAvailable() {
		return android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
	}

}