package com.gyf.immersionbar;

import static com.gyf.immersionbar.Constants.IMMERSION_NAVIGATION_BAR_MODE_DEFAULT;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;

/**
 * 手势utils
 *
 * @author: ifan
 * @date: 8/13/21
 */
class GestureUtils {

    /**
     * 获取全面屏相关信息
     *
     * @param context Context
     * @return FullScreenBean
     */
    public static GestureBean getGestureBean(Context context) {
        GestureBean gestureBean = new GestureBean();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && context != null && context.getContentResolver() != null) {
            ContentResolver contentResolver = context.getContentResolver();
            NavigationBarType navigationBarType = NavigationBarType.UNKNOWN;
            int type = -1;
            boolean isGesture = false;
            boolean checkNavigation = false;
            if (type == -1) {
                type = Settings.Secure.getInt(contentResolver, IMMERSION_NAVIGATION_BAR_MODE_DEFAULT, -1);
                if (type == 0) {
                    navigationBarType = NavigationBarType.CLASSIC;
                    isGesture = false;
                } else if (type == 1) {
                    navigationBarType = NavigationBarType.DOUBLE;
                    isGesture = false;
                } else if (type == 2) {
                    navigationBarType = NavigationBarType.GESTURES;
                    isGesture = true;
                    checkNavigation = true;
                }
            }
            gestureBean.isGesture = isGesture;
            gestureBean.checkNavigation = checkNavigation;
            gestureBean.type = navigationBarType;
        }
        return gestureBean;
    }

    static class GestureBean {
        /**
         * 是否有手势操作
         */
        public boolean isGesture = false;
        /**
         * 需要校验导航栏高度，需要检查的机型，小米，三星，原生
         */
        public boolean checkNavigation = false;
        /**
         * 手势类型
         */
        public NavigationBarType type;

        @Override
        public String toString() {
            return "GestureBean{" +
                    "isGesture=" + isGesture +
                    ", checkNavigation=" + checkNavigation +
                    ", type=" + type +
                    '}';
        }
    }
}
