package com.taovo.rjp.packagedemo.listview;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taovo.rjp.packagedemo.R;

/**
 * @author Gimpo create on 2017/6/9 16:47
 * @email : jimbo922@163.com
 */

public class SeniorInputView extends LinearLayout implements View.OnClickListener {

    private TextView tvLeft;
    private TextView tvRight;
    private int MAX_INPUT = 10000;
    private int MIN_INPUT = 1;
    private EditText etInput;
    private TextView tvMinus;
    private TextView tvPlus;
    private long mInputNum;
    private boolean resetInput = false;
    private OnInputChangeListener listener;

    public SeniorInputView(Context context) {
        super(context);
        initView(context);
    }

    public SeniorInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SeniorInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!resetInput) {
                if (!TextUtils.isEmpty(s.toString())) {
                    mInputNum = Long.parseLong(s.toString());
                    if (mInputNum > MAX_INPUT) {
                        setEditText(MAX_INPUT);
                    } else {
                        resetInput = true;
                        setEditText(mInputNum);
                    }
                } else {
                    mInputNum = MIN_INPUT;
                    tvMinus.setEnabled(false);
                }
            } else {
                resetInput = false;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_senior_input_view, this);
        tvLeft = (TextView) findViewById(R.id.tv_left_tip);
        tvRight = (TextView) findViewById(R.id.tv_right_tip);
        tvMinus = (TextView) findViewById(R.id.tv_minus);
        tvPlus = (TextView) findViewById(R.id.tv_plus);
        etInput = (EditText) findViewById(R.id.et_input);
        etInput.addTextChangedListener(textWatcher);
//        etInput.setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (listener != null) {
//                    listener.onTouchFocusOn(getTag());
//                }
//                return false;
//            }
//        });
        tvMinus.setOnClickListener(this);
        tvPlus.setOnClickListener(this);
        //设置一个默认的值
        setInputNum(1);
    }

    public void setLeftTip(String leftTip) {
        tvLeft.setText(leftTip);
    }

    public void setRightTip(String rightTip) {
        tvRight.setText(rightTip);
    }

    public void setMaxInput(int maxInput) {
        MAX_INPUT = maxInput;
    }

    public void setMinInput(int minInput) {
        MIN_INPUT = minInput;
    }

    public void setInputNum(long inputNum) {
        mInputNum = inputNum;
        if(mInputNum <= MIN_INPUT){
            mInputNum = MIN_INPUT;
        }
        etInput.setText(String.valueOf(mInputNum));
        etInput.setSelection(etInput.length());
        tvPlus.setEnabled(inputNum < MAX_INPUT);
        tvMinus.setEnabled(inputNum > MIN_INPUT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_minus:
                mInputNum--;
                setEditText(mInputNum);
                break;
            case R.id.tv_plus:
                String num = etInput.getText().toString();
                if (TextUtils.isEmpty(num)) {
                    mInputNum = 0;
                }
                mInputNum++;
                setEditText(mInputNum);
                break;
        }
    }

    private void setEditText(long inputNum) {
        setInputNum(inputNum);
        if (listener != null) {
            listener.onInputNumChange(getTag(), inputNum);
        }
    }

    public void setListener(OnInputChangeListener listener) {
        this.listener = listener;
    }

    public void setEditTextEnabled(boolean enable) {
        etInput.setEnabled(enable);
    }

    public void setFocus(boolean focus) {
        if(focus) {
            etInput.requestFocus();
        }else{
            etInput.clearFocus();
        }
    }

    public interface OnInputChangeListener {
        void onInputNumChange(Object tag, long num);
    }
}
