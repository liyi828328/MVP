package perseverance.li.mvp.model;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ---------------------------------------------------------------
 * Author: Perseverance.Li
 * Create: 17-3-20 11:40
 * ---------------------------------------------------------------
 * Describe:
 * ---------------------------------------------------------------
 * Changes:
 * ---------------------------------------------------------------
 * 17-3-20 11 : Create by Perseverance.Li
 * ---------------------------------------------------------------
 */

public class LoadShopDetailBiz implements ILoadShopDetailBiz {

    @Override
    public void load(final String httpUrl, final int startIndex, final int count,
                     final OnShopLoadListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(httpUrl) || count <= 0 || startIndex < 0) {
                    listener.loadFailed();
                    return;
                }

                List<ShopDetail> shopDetails = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    ShopDetail sd = new ShopDetail();
                    sd.setShopName("商品名称: " + i);
                    sd.setShopIntroduction("商品描述: " + i);
                    shopDetails.add(sd);
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                listener.loadSuccess(shopDetails);
            }
        }).start();
    }

    @Override
    public void destory() {
        //TODO:销毁线程 task等,防止内存泄露
    }
}
