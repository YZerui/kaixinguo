package com.hi.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import com.hi.module.base.application.AppContextApplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.widget.ImageView;
import android.widget.Toast;

public class ViewHandleUtils {
	/**
	 * �������� recycle
	 * 
	 * @throws
	 */
	public static void recycle(Bitmap bitmap) {
		// ���ж��Ƿ��Ѿ�����
		if (bitmap != null && !bitmap.isRecycled()) {
			// ���ղ�����Ϊnull
			bitmap.recycle();
			bitmap = null;
		}
		System.gc();
	}

	/**
	 * ת��ͼƬ��Բ��
	 * 
	 * @param bitmap
	 *            ����Bitmap����
	 * @return
	 */
	public static Bitmap toRoundBitmap(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float roundPx;
		float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
		if (width <= height) {
			roundPx = width / 2;

			left = 0;
			top = 0;
			right = width;
			bottom = width;

			height = width;

			dst_left = 0;
			dst_top = 0;
			dst_right = width;
			dst_bottom = width;
		} else {
			roundPx = height / 2;

			float clip = (width - height) / 2;

			left = clip;
			right = width - clip;
			top = 0;
			bottom = height;
			width = height;

			dst_left = 0;
			dst_top = 0;
			dst_right = height;
			dst_bottom = height;
		}

		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final Paint paint = new Paint();
		final Rect src = new Rect((int) left, (int) top, (int) right,
				(int) bottom);
		final Rect dst = new Rect((int) dst_left, (int) dst_top,
				(int) dst_right, (int) dst_bottom);
		final RectF rectF = new RectF(dst);

		paint.setAntiAlias(true);// ���û����޾��

		canvas.drawARGB(0, 0, 0, 0); // �������Canvas

		// ���������ַ�����Բ,drawRounRect��drawCircle
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);// ��Բ�Ǿ��Σ���һ������Ϊͼ����ʾ���򣬵ڶ��������͵����������ֱ���ˮƽԲ�ǰ뾶�ʹ�ֱԲ�ǰ뾶��
		// canvas.drawCircle(roundPx, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));// ��������ͼƬ�ཻʱ��ģʽ,�ο�http://trylovecatch.iteye.com/blog/1189452
		canvas.drawBitmap(bitmap, src, dst, paint); // ��Mode.SRC_INģʽ�ϲ�bitmap���Ѿ�draw�˵�Circle

		return output;
	}

	/**
	 * Բ��ͼƬ
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int roundPx) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		// final float roundPx = 30;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;

	}

	// public static Bitmap getHttpCacheBitmap(ImageView imageView,String
	// imgUrl,int imgPixel) {
	//
	// Context context = AppContextApplication.getInstance();
	// ImageCacheManager cacheMgr = new ImageCacheManager(context);
	// AsyncImageLoader imageLoader = new AsyncImageLoader(context,
	// cacheMgr.getMemoryCache(), cacheMgr.getPlacardFileCache());
	// return imageLoader.loadBitmap(imageView,imgUrl, true,imgPixel);
	// }

	/**
	 * ��ͨ��Toast
	 */
	public static void toastNormal(String str) {
		Toast.makeText(AppContextApplication.getInstance(), str,
				Toast.LENGTH_LONG).show();
	}

	/**
	 * ��ͨ��Toast
	 */
	public static void toastNormal(String str, Context context) {
		Toast.makeText(context, str, Toast.LENGTH_LONG).show();
	}

	public static void pathToImageView(ImageView imageView, String path) {
		// BitmapFactory.Options options = new BitmapFactory.Options();
		// options.inJustDecodeBounds = true;
		// BitmapFactory.decodeFile(path, options);
		// int inSampleSize = 1;
		// int maxSize = 3000;
		// if (options.outWidth > maxSize || options.outHeight > maxSize) {
		// int widthScale = (int) Math.ceil(options.outWidth * 1.0 / maxSize);
		// int heightScale = (int) Math
		// .ceil(options.outHeight * 1.0 / maxSize);
		// inSampleSize = Math.max(widthScale, heightScale);
		// }
		// options.inJustDecodeBounds = false;
		// options.inSampleSize = inSampleSize;
		// Bitmap bitmap = BitmapFactory.decodeFile(path, options);
		// int w = bitmap.getWidth();
		// int h = bitmap.getHeight();
		// int newW = w;
		// int newH = h;
		// if (w > maxSize || h > maxSize) {
		// if (w > h) {
		// newW = maxSize;
		// newH = (int) (newW * h * 1.0 / w);
		// } else {
		// newH = maxSize;
		// newW = (int) (newH * w * 1.0 / h);
		// }
		// }
		// Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, newW, newH,
		// false);
		// if (newBitmap != null) {
		// imageView.setImageBitmap(newBitmap);
		// }
		File file = new File(path);
		if (file.exists()) {
			Bitmap bm = BitmapFactory.decodeFile(path);
			imageView.setImageBitmap(bm);
//			recycle(bm);
		}

	}

	public static String compressImage(String path, String newPath) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
		int inSampleSize = 1;
		int maxSize = 2000;
		if (options.outWidth > maxSize || options.outHeight > maxSize) {
			int widthScale = (int) Math.ceil(options.outWidth * 1.0 / maxSize);
			int heightScale = (int) Math
					.ceil(options.outHeight * 1.0 / maxSize);
			inSampleSize = Math.max(widthScale, heightScale);
		}
		options.inJustDecodeBounds = false;
		options.inSampleSize = inSampleSize;
		Bitmap bitmap = BitmapFactory.decodeFile(path, options);
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		int newW = w;
		int newH = h;
		if (w > maxSize || h > maxSize) {
			if (w > h) {
				newW = maxSize;
				newH = (int) (newW * h * 1.0 / w);
			} else {
				newH = maxSize;
				newW = (int) (newH * w * 1.0 / h);
			}
		}
		Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, newW, newH, false);
		// recycle(bitmap);

		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(newPath);
			newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		recycle(newBitmap);
		recycle(bitmap);
		return newPath;
	}
}
