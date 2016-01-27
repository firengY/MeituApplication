package com.firengy.meituapplication.tools;

import com.firengy.meituapplication.R;

/**
 * Created by firengy
 * on 16-1-25.
 * Email: 18811372352@163.com
 */
public class ImageTool {
    public static int getDrawableId(String name) {
        int ret = 0;
        if (name != null) {
            Class<R.mipmap> mipmapClass = R.mipmap.class;
            try {
                ret = mipmapClass.getDeclaredField(name).getInt(null);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
}
