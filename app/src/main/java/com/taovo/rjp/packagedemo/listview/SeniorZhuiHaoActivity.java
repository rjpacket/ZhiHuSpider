package com.taovo.rjp.packagedemo.listview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.taovo.rjp.packagedemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class SeniorZhuiHaoActivity extends FragmentActivity implements SeniorInputView.OnInputChangeListener{
    SeniorInputView seniorInputView;
    ListView mListView;
    private List<SeniorZhuiHaoModel> models;
    private SeniorZhuiHaoAdapter adapter;
    private long amt;
    private int zhuiNum;

    public static final String LOTTERY_TYPE = "lottery_type";
    public static final String BETCODE = "betcode";
    public static final String PHASE = "phase";
    public static final String AMT = "amt";
    public static final String ZHUI_NUM = "zhui_num";
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_zhui_hao);
        ButterKnife.bind(this);
        initView();
    }

    protected void initView() {
        mContext = this;
        seniorInputView = (SeniorInputView) findViewById(R.id.senior_input_view);
        mListView = (ListView) findViewById(R.id.senior_list_view);
        Intent intent = getIntent();
        if (intent.hasExtra(LOTTERY_TYPE)) {
            amt = intent.getLongExtra(AMT, 0);
            zhuiNum = intent.getIntExtra(ZHUI_NUM, 1);
            seniorInputView.setInputNum(zhuiNum);
        }

        models = new ArrayList<>();
        adapter = new SeniorZhuiHaoAdapter();
        mListView.setAdapter(adapter);
        getYuShouQi(20);
        seniorInputView.setListener(this);
    }

    @Override
    public void onInputNumChange(Object tag, long num) {
        zhuiNum = (int) num;
        getYuShouQi(num);
    }

    /**
     * 获取预售期list
     *
     * @param
     */
    private void getYuShouQi(long num) {
        for (int i = 0; i < num; i++) {
            models.add(new SeniorZhuiHaoModel());
        }
        adapter.notifyDataSetChanged();
    }

    public class SeniorZhuiHaoAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return models.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_senior_list_view, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            SeniorZhuiHaoModel zhuiHaoModel = models.get(position);
            holder.imgCheck.setSelected(zhuiHaoModel.isSelect());
            holder.tvPhase.setText(zhuiHaoModel.getPhase() + "期");
            holder.tvMoney.setText(amt * zhuiHaoModel.getNum() + "元");

            holder.seniorInputView.setMaxInput(10000);
            holder.seniorInputView.setMinInput(1);
            holder.seniorInputView.setLeftTip("");
            holder.seniorInputView.setRightTip("倍");
            holder.seniorInputView.setTag(zhuiHaoModel);
            holder.seniorInputView.setInputNum(zhuiHaoModel.getNum());
            holder.seniorInputView.setListener(new SeniorInputView.OnInputChangeListener() {
                @Override
                public void onInputNumChange(Object tag, long num) {
                    if (tag != null && tag instanceof SeniorZhuiHaoModel) {
                        SeniorZhuiHaoModel tagModel = (SeniorZhuiHaoModel) tag;
                        long preNum = tagModel.getNum();
                        tagModel.setNum(num);
                        if (preNum != num) {
                            notifyDataSetChanged();
                        }
                    }
                }

            });

            holder.llLabel.setTag(zhuiHaoModel);
            holder.llLabel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Object tag = v.getTag();
                    if (tag instanceof SeniorZhuiHaoModel) {
                        SeniorZhuiHaoModel model = (SeniorZhuiHaoModel) tag;
                        model.setSelect(!model.isSelect());
                        notifyDataSetChanged();
                    }
                }
            });
            return convertView;
        }
    }

    public class ViewHolder {
        ImageView imgCheck;
        TextView tvPhase;
        SeniorInputView seniorInputView;
        TextView tvMoney;
        LinearLayout llLabel;

        public ViewHolder(View view) {
            imgCheck = (ImageView) view.findViewById(R.id.iv_senior_check);
            tvPhase = (TextView) view.findViewById(R.id.tv_phase);
            seniorInputView = (SeniorInputView) view.findViewById(R.id.senior_input_view);
            tvMoney = (TextView) view.findViewById(R.id.tv_money);
            llLabel = (LinearLayout) view.findViewById(R.id.ll_zhui_hao_label);
        }
    }
}
