package com.dalong.wxtab.tab;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dalong.wxtab.R;

/**
 * Created by dalong on 2017/2/3.
 */

public class TabView extends RelativeLayout {
    private  Context mContext;
    //tab文字大小
    private  float mTabNameSize;
    //数字标签的文字大小
    private  float mTabLableTextSize;
    //数字标签的底色
    private  int mTabLableBackground;
    //数字标签文字颜色
    private  int mTabLableColor;
    //数字标签的文字
    private  String mTabLableText;
    //默认的图片
    private  Drawable mTabIcon;
    //选择的图片
    private  Drawable mTabSelectIcon;
    //tab的颜色
    private  int mTabColor;
    //tab选中颜色
    private  int mTabSelectColor;
    //tab的name
    private  String mTabName;
    //是否选中
    private  boolean isChecked;
    //tab 默认imageview
    private ImageView mTabIM;
    //tab 选中imageview
    private ImageView mTabSelectIM;
    //tab 默认tv
    private TextView mTabTv;
    //tab 选中tv
    private TextView mTabSelectTv;
    //消息数字
    private TextView mTabNum;
    //透明度
    private float mAlpha;
    private View mTabTip;

    public TabView(Context context) {
        this(context,null);
    }

    public TabView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        //获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabView);
        isChecked = typedArray.getBoolean(R.styleable.TabView_tabChecked, false);
        mTabColor = typedArray.getColor(R.styleable.TabView_tabNameColor, Color.GRAY);
        mTabSelectColor = typedArray.getColor(R.styleable.TabView_tabNameSelectColor, Color.parseColor("#45C01A"));

        mTabName= typedArray.getString(R.styleable.TabView_tabName);
        mTabNameSize= typedArray.getDimension(R.styleable.TabView_tabNameSize, 10);
        mTabIcon = typedArray.getDrawable(R.styleable.TabView_tabIcon);
        mTabSelectIcon = typedArray.getDrawable(R.styleable.TabView_tabIconSelect);


        mTabLableText=typedArray.getString(R.styleable.TabView_tabLableText);
        mTabLableColor = typedArray.getColor(R.styleable.TabView_tabLableColor, Color.WHITE);
        mTabLableBackground= typedArray.getColor(R.styleable.TabView_tabLablebackground, Color.RED);
        mTabLableTextSize= typedArray.getDimension(R.styleable.TabView_tabLableTextSize, 10);
        typedArray.recycle();
        mContext = context;


        initViews();
    }

    private void initViews() {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.tab_view, this);

        mTabIM=(ImageView)contentView.findViewById(R.id.tab_icon);
        mTabSelectIM=(ImageView)contentView.findViewById(R.id.tab_icon_select);

        mTabTv=(TextView)contentView.findViewById(R.id.tab_name);
        mTabSelectTv=(TextView)contentView.findViewById(R.id.tab_name_select);
        mTabNum=(TextView)contentView.findViewById(R.id.tab_num);
        mTabTip=contentView.findViewById(R.id.tab_tip);

        //设置数据，并刷新
        refreshData();
    }

    /**
     * 刷新数据
     */
    private void refreshData() {
        mAlpha = 1.0f;
        setNameData();
        setNameSelectData();
        setTabIconData();
        setTabIconSelectData();
    }
    /**
     * 设置默认状态下的icon的样式
     */
    private void setTabIconData() {
        mTabIM.setImageDrawable(mTabIcon);
        if(isChecked){
            mTabIM.setAlpha(1-mAlpha);
        } else {
            mTabIM.setAlpha(mAlpha);
        }
    }
    /**
     * 设置选中状态下的icon的样式
     */
    private void setTabIconSelectData() {
        mTabSelectIM.setImageDrawable(mTabSelectIcon);
        if(isChecked){
            mTabSelectIM.setAlpha(mAlpha);
        } else {
            mTabSelectIM.setAlpha(1-mAlpha);
        }
    }

    /**
     * 设置默认状态下的name的样式
     */
    private void setNameData() {
        mTabTv.setText(mTabName);
        mTabTv.setTextSize(TypedValue.COMPLEX_UNIT_PX,mTabNameSize);
        mTabTv.setTextColor(mTabColor);
        if(isChecked){
            mTabTv.setAlpha(1-mAlpha);
        } else {
            mTabTv.setAlpha(mAlpha);
        }
    }

    /**
     * 设置选中状态下的name的样式
     */
    private void setNameSelectData() {
        mTabSelectTv.setText(mTabName);
        mTabSelectTv.setTextSize(TypedValue.COMPLEX_UNIT_PX,mTabNameSize);
        mTabSelectTv.setTextColor(mTabSelectColor);
        if(isChecked){
            mTabSelectTv.setAlpha(mAlpha);
        } else {
            mTabSelectTv.setAlpha(1-mAlpha);
        }
    }


    /**
     * 设置选中状态
     * */
    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
        refreshData();
    }

    /**
     *  滑动时设置图标透明度以及文字透明度
     * @param alpha 透明度
     */
    public void onScrolling(float alpha){
        mAlpha = alpha;
        onScrollSetData();
    }


    /**
     * 滑动时设置图标透明度以及文字透明度
     */
    private void onScrollSetData(){
        //设置图标透明度
        mTabIM.setAlpha(1-mAlpha);
        mTabSelectIM.setAlpha(mAlpha);
        //设置标题透明度
        mTabTv.setAlpha(1-mAlpha);
        mTabSelectTv.setAlpha(mAlpha);
    }

    /**
     * 是否有新消息
     * @param hasNew
     */
    public void setHasNew(boolean hasNew){
        if (mTabTip != null) {
            mTabTip.setVisibility(hasNew ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 设置未读数量
     * @param count
     */
    public void setUnreadCount(int count) {
        mTabNum.setText(String.valueOf(count));
        mTabNum.setVisibility(count > 0 ? View.VISIBLE : View.GONE);
    }
}
