package perseverance.li.mvp.model;

import perseverance.li.mvp.base.IBase;

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

public interface ILoadShopDetailBiz extends IBase {

    /**
     * 加载商品详情
     *
     * @param httpUrl    商品详情加载链接
     * @param startIndex 分页开始位置
     * @param count      加载个数
     * @param listener   数据加载回调
     */
    void load(String httpUrl, int startIndex, int count, OnShopLoadListener listener);

}
