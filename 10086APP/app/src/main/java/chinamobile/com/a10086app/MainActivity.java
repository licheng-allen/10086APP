package chinamobile.com.a10086app;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chinamobile.com.a10086app.Adapter.BusinessAdapter;
import chinamobile.com.a10086app.Adapter.CardAdapter;
import chinamobile.com.a10086app.Adapter.NewsAdapter;
import chinamobile.com.a10086app.Bean.Business;
import chinamobile.com.a10086app.Bean.Card;
import chinamobile.com.a10086app.Bean.ImageCard;
import chinamobile.com.a10086app.Bean.News;
import chinamobile.com.a10086app.Bean.ProgressCard;
import chinamobile.com.a10086app.Bean.TextCard;
import chinamobile.com.a10086app.Interface.OnItemLongClickListener;
import chinamobile.com.a10086app.loader.GlideLoader;

public class MainActivity extends AppCompatActivity implements OnBannerListener {
    /**广告橱窗视图*/
    private Banner adBanner;
    private ArrayList<String> bannerPaths;
    private ArrayList<String> bannerTitles;
    /**中部业务网格视图*/
    private GridView gridViewBusiness;
    private ArrayList<Business> businessDataList;
    private BusinessAdapter businessAdapter;
    /**下部新闻视图*/
    private RecyclerView recycleViewNews;
    private RecyclerView recyclerViewCard;
    private News[] news;
    public static ArrayList<Card> cards;
    private static String[] TAGS={"progress","text","image"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        initRecycleViewNews();
        initBanner();
        initGrideViewBusiness();
        initCard();
    }

    private void initCard() {
        initCardData();
        initCardView();
    }

    private void initCardView() {
        recyclerViewCard=(RecyclerView) findViewById(R.id.recyclerview_card);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        //设置适配器管理器：LinearLayoutManager GridLayoutManager StaggeredGridLayoutManager(瀑布流)，
        recyclerViewCard.setLayoutManager(linearLayoutManager);
        CardAdapter cardAdapter=new CardAdapter(this, cards);
        cardAdapter.setmOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int data) {
                Intent intent=new Intent(MainActivity.this, CardActivity.class);
                ActivityOptions transitionActivityOptions;
                setTansitionActivityOptions();
                Log.d("DEBUG",data+"");
                ArrayList<Pair> pairs=new ArrayList<Pair>();
                pairs.add( Pair.create(findViewById(R.id.card_item_progress_layout),"progress"));
                //为点击的元素设置元素共享动画
                transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,
                        Pair.create(view,TAGS[cards.get(data).getCardType()]));
                CardActivity.defaultUsercards =cards;
                 intent.putExtra("cards", cards);
                startActivity(intent, transitionActivityOptions.toBundle());
            }
        });
        recyclerViewCard.setAdapter(cardAdapter);
        recyclerViewCard.setNestedScrollingEnabled(false);
    }

    private void initCardData() {
        if(cards!=null)
            return;
        int testbmp=  R.drawable.menu_find_blue;
        cards=new ArrayList<Card>();
        cards.add(new ProgressCard(100,80,"语音","剩余流量/GB"));
        cards.add(new ImageCard(testbmp,"添加亲密"));
        cards.add(new TextCard(testbmp,70,"当前积分/分","去兑换"));
        cards.add(new ProgressCard(100,40,"语音","剩余流量/GB"));
        cards.add(new TextCard(testbmp,70,"当前积分/分","去兑换"));
        cards.add(new TextCard(testbmp,70,"当前积分/分","去兑换"));
    }
   public void setTansitionActivityOptions(){

    }
    private void initRecycleViewNews() {
        initRecycleViewData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recycleViewNews = (RecyclerView) findViewById(R.id.recyclerview_news);
        //设置适配器管理器：LinearLayoutManager GridLayoutManager StaggeredGridLayoutManager(瀑布流)，
        recycleViewNews.setLayoutManager(linearLayoutManager);
        recycleViewNews.setAdapter(new NewsAdapter(this, news));
        recycleViewNews.setNestedScrollingEnabled(false);
        //添加分割线
        recycleViewNews.addItemDecoration(new TestDividerItemDecoration());
    }
    public class TestDividerItemDecoration extends RecyclerView.ItemDecoration {

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

//        //如果不是第一个，则设置top的值。
            if (parent.getChildAdapterPosition(view) != 0){
                //这里直接硬编码为1px
                outRect.top = 5;
            }
        }
    }
    private void initRecycleViewData() {
        Resources r = this.getResources();
        Bitmap testbmp= BitmapFactory.decodeResource(r, R.drawable.testimage1);
        Bitmap testbmp1= BitmapFactory.decodeResource(r, R.drawable.testimage);
        Bitmap testbmp2= BitmapFactory.decodeResource(r, R.drawable.testpic);
        ArrayList<Bitmap> bitmaps1=new ArrayList<Bitmap>();
        bitmaps1.add(testbmp);

        ArrayList<Bitmap> bitmaps2=new ArrayList<Bitmap>();
        bitmaps2.add(testbmp);
        bitmaps2.add(testbmp1);
        bitmaps2.add(testbmp2);

        news=new News[3];
        news[0]=new News("就在刚刚，中国移动在香港发布2018年度中期业绩！","今日头条","昨天16:09",1602550,bitmaps1);
        news[1]=new News("夏季是“养骨”的好时机！养好了身强体健，远离百病","中国移动","昨天16:09",4357635,bitmaps2);
        news[2]=new News("第一次有人把5G讲的这么简单明了！","今日头条","昨天16:09",5004305,bitmaps1);

    }

    private void initGrideViewBusiness() {
        gridViewBusiness = (GridView) findViewById(R.id.grideview_business);

        //初始化数据
        initGrideViewData();
        businessAdapter=new BusinessAdapter(this, businessDataList);
        gridViewBusiness.setAdapter(businessAdapter);
        gridViewBusiness.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
            }
        });
    }
    void initGrideViewData() {
        //图标
        int icno[] = { R.drawable.bill, R.drawable.surplus, R.drawable.click,
                R.drawable.recharge, R.drawable.add, R.drawable.red, R.drawable.reward,
                R.drawable.more };
        //图标下的文字
        String name[]={"话费账单","套餐余量","一键检测","话费充值","流量办理","3元1G包","每日领奖","更多"};
        businessDataList = new ArrayList<Business>();
        for (int i = 0; i <icno.length; i++) {
            Business business=null;
            if(i==5||i==7){
                business=new Business(icno[i],name[i],View.VISIBLE);
            }else{
                business=new Business(icno[i],name[i],View.INVISIBLE);
            }
            businessDataList.add(business);
        }
    }
    private void initBanner() {
        initBannerData();
        setBannerOption();
    }

    private void setBannerOption() {
        adBanner = (Banner) findViewById(R.id.banner);
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        adBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器，图片加载器在下方
        adBanner.setImageLoader(new GlideLoader());
        //设置图片网址或地址的集合
        adBanner.setImages(bannerPaths);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        adBanner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
        adBanner.setBannerTitles(bannerTitles);
        //设置轮播间隔时间
        adBanner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        adBanner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        adBanner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();
    }

    private void initBannerData() {
        //放图片地址的集合
        bannerPaths = new ArrayList<>();
        //放标题的集合
        bannerTitles = new ArrayList<>();

        bannerPaths.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        bannerPaths.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        bannerPaths.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        bannerPaths.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
        bannerTitles.add("好好学习");
        bannerTitles.add("天天向上");
        bannerTitles.add("热爱劳动");
        bannerTitles.add("不搞对象");
    }

    //轮播图的监听方法
    @Override
    public void OnBannerClick(int position) {
        Log.i("tag", "你点了第"+position+"张轮播图");
        Intent intent=new Intent(MainActivity.this, CardActivity.class);
        //ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, findViewById(R.id.card_item_progress_layout), "share");
       // startActivity(intent, transitionActivityOptions.toBundle());
        startActivity(intent);

    }
}
