package perseverance.li.mvp.view;

import java.util.List;

import perseverance.li.mvp.model.ShopDetail;

/**
 * ---------------------------------------------------------------
 * Author: Perseverance.Li
 * Create: 17-3-20 11:47
 * ---------------------------------------------------------------
 * Describe:
 * ---------------------------------------------------------------
 * Changes:
 * ---------------------------------------------------------------
 * 17-3-20 11 : Create by Perseverance.Li
 * ---------------------------------------------------------------
 */

public interface IShopDetailView {

    int getLoadStartIndex();

    int getLoadCount();

    String getLoadUrl();

    void refreshShopDatails(List<ShopDetail> shopDetails);

    void showLoadDialog();

    void dismissLoadDialog();

    void toLoadFailedActivity();
}
