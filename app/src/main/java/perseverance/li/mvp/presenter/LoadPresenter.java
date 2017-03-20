package perseverance.li.mvp.presenter;


import android.os.Handler;

import java.util.List;

import perseverance.li.mvp.base.IBase;
import perseverance.li.mvp.model.LoadShopDetailBiz;
import perseverance.li.mvp.model.OnShopLoadListener;
import perseverance.li.mvp.model.ShopDetail;
import perseverance.li.mvp.view.IShopDetailView;

/**
 * ---------------------------------------------------------------
 * Author: Perseverance.Li
 * Create: 17-3-20 11:51
 * ---------------------------------------------------------------
 * Describe:
 * ---------------------------------------------------------------
 * Changes:
 * ---------------------------------------------------------------
 * 17-3-20 11 : Create by Perseverance.Li
 * ---------------------------------------------------------------
 */

public class LoadPresenter implements IBase {

    private IShopDetailView mShopDetailView;
    private LoadShopDetailBiz mLoadShopDetailBiz;
    private Handler mHandler = new Handler();

    public LoadPresenter(IShopDetailView shopDetailView) {
        this.mShopDetailView = shopDetailView;
        this.mLoadShopDetailBiz = new LoadShopDetailBiz();
    }

    public void load() {
        mShopDetailView.showLoadDialog();
        mLoadShopDetailBiz.load(mShopDetailView.getLoadUrl(),
                mShopDetailView.getLoadStartIndex(),
                mShopDetailView.getLoadCount(), onShopLoadListener);
    }

    private OnShopLoadListener onShopLoadListener = new OnShopLoadListener() {
        @Override
        public void loadSuccess(final List<ShopDetail> shopList) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mShopDetailView.dismissLoadDialog();
                    mShopDetailView.refreshShopDatails(shopList);
                }
            });
        }

        @Override
        public void loadFailed() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mShopDetailView.dismissLoadDialog();
                    mShopDetailView.toLoadFailedActivity();
                }
            });
        }
    };

    @Override
    public void destory() {
        onShopLoadListener = null;
        mLoadShopDetailBiz.destory();
    }
}
