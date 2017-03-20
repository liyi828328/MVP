package perseverance.li.mvp.model;

import java.util.List;

/**
 * ---------------------------------------------------------------
 * Author: Perseverance.Li
 * Create: 17-3-20 11:36
 * ---------------------------------------------------------------
 * Describe:
 * ---------------------------------------------------------------
 * Changes:
 * ---------------------------------------------------------------
 * 17-3-20 11 : Create by Perseverance.Li
 * ---------------------------------------------------------------
 */

public interface OnShopLoadListener {

    void loadSuccess(List<ShopDetail> shopList);

    void loadFailed();

}
