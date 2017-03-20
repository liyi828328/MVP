package perseverance.li.mvp.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import perseverance.li.mvp.R;
import perseverance.li.mvp.model.ShopDetail;
import perseverance.li.mvp.presenter.LoadPresenter;

/**
 * ---------------------------------------------------------------
 * Author: Perseverance.Li
 * Create: 17-3-20 11:46
 * ---------------------------------------------------------------
 * Describe:
 * ---------------------------------------------------------------
 * Changes:
 * ---------------------------------------------------------------
 * 17-3-20 11 : Create by Perseverance.Li
 * ---------------------------------------------------------------
 */

public class ShopDetailsActivity extends AppCompatActivity implements IShopDetailView, View.OnClickListener {

    private AlertDialog mDialog;
    private LoadPresenter mPresenter;
    private EditText mUrlEd;
    private EditText mStartIndexEd;
    private EditText mCountEd;
    private Button mLoadBtn;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_details_view);
        init();
    }

    private void init() {
        mPresenter = new LoadPresenter(this);
        mUrlEd = (EditText) findViewById(R.id.http_url);
        mStartIndexEd = (EditText) findViewById(R.id.start_index);
        mCountEd = (EditText) findViewById(R.id.count);
        mListView = (ListView) findViewById(R.id.listview);
        mLoadBtn = (Button) findViewById(R.id.load);
        mLoadBtn.setOnClickListener(this);
    }

    @Override
    public int getLoadStartIndex() {
        String startIndexTmp = mStartIndexEd.getText().toString();
        if (!TextUtils.isEmpty(startIndexTmp)) {
            try {
                return Integer.parseInt(startIndexTmp);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    @Override
    public int getLoadCount() {
        String countTmp = mCountEd.getText().toString();
        if (!TextUtils.isEmpty(countTmp)) {
            try {
                return Integer.parseInt(countTmp);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public String getLoadUrl() {
        return mUrlEd.getText().toString();
    }

    @Override
    public void refreshShopDatails(List<ShopDetail> shopDetails) {
        String[] array = new String[shopDetails.size()];
        for (int i = 0; i < shopDetails.size(); i++) {
            ShopDetail sd = shopDetails.get(i);
            array[i] = sd.getShopName() + "\r\n" + sd.getShopIntroduction();
        }
        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void showLoadDialog() {
        mDialog = new AlertDialog.Builder(this)
                .setTitle("提示")
                .setIcon(R.mipmap.ic_mvp)
                .setMessage("数据加载中...")
                .create();
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
    }

    @Override
    public void dismissLoadDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    @Override
    public void toLoadFailedActivity() {
        Intent errorIntent = new Intent(this, ErrorActivity.class);
        startActivity(errorIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.destory();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.load) {
            mPresenter.load();
        }
    }
}
