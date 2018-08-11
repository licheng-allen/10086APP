/*
package chinamobile.com.a10086app.Adapter;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.progresviews.ProgressWheel;

import chinamobile.com.a10086app.Bean.Card;
import chinamobile.com.a10086app.Bean.ChannelItem;
import chinamobile.com.a10086app.Bean.ImageCard;
import chinamobile.com.a10086app.Bean.ProgressCard;
import chinamobile.com.a10086app.Bean.TextCard;
import chinamobile.com.a10086app.R;

public class DragAdapter extends BaseAdapter {
	*/
/** TAG*//*

	private final static String TAG = "DragAdapter";
	*/
/** 是否显示底部的ITEM*//*

	private boolean isItemShow = false;
	private Context context;
	*/
/** 控制的postion*//*

	private int holdPosition;
	*/
/** 是否改变*//*

	private boolean isChanged = false;
	*/
/** 是否可见*//*

	boolean isVisible = true;
	*/
/** 可以拖动的列表（即用户选择的频道列表）*//*

	public List<ChannelItem> channelList;
	*/
/** TextView 频道内容*//*

	private TextView item_text;
	*/
/** 要删除的position*//*

	public int remove_position = -1;

	public DragAdapter(Context context, List<ChannelItem> channelList) {
		this.context = context;
		this.channelList = channelList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return channelList == null ? 0 : channelList.size();
	}

	@Override
	public ChannelItem getItem(int position) {
		// TODO Auto-generated method stub
		if (channelList != null && channelList.size() != 0) {
			return channelList.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = LayoutInflater.from(context).inflate(R.layout.function_category_item, null);
		item_text = (TextView) view.findViewById(R.id.text_item);
		ChannelItem channel = getItem(position);
		item_text.setText(channel.getName());
		if ((position == 0) || (position == 1)){
//			item_text.setTextColor(context.getResources().getColor(R.color.black));
			item_text.setEnabled(false);
		}
		if (isChanged && (position == holdPosition) && !isItemShow) {
			item_text.setText("");
			item_text.setSelected(true);
			item_text.setEnabled(true);
			isChanged = false;
		}
		if (!isVisible && (position == -1 + channelList.size())) {
			item_text.setText("");
			item_text.setSelected(true);
			item_text.setEnabled(true);
		}
		if(remove_position == position){
			item_text.setText("");
		}
		return view;
	}

	*/
/** 添加频道列表*//*

	public void addItem(ChannelItem channel) {
		channelList.add(channel);
		notifyDataSetChanged();
	}

	*/
/** 拖动变更频道排序*//*

	public void exchange(int dragPostion, int dropPostion) {
		holdPosition = dropPostion;
		ChannelItem dragItem = getItem(dragPostion);
		Log.d(TAG, "startPostion=" + dragPostion + ";endPosition=" + dropPostion);
		if (dragPostion < dropPostion) {
			channelList.add(dropPostion + 1, dragItem);
			channelList.remove(dragPostion);
		} else {
			channelList.add(dropPostion, dragItem);
			channelList.remove(dragPostion + 1);
		}
		isChanged = true;
		notifyDataSetChanged();
	}

	*/
/** 获取频道列表*//*

	public List<ChannelItem> getChannnelLst() {
		return channelList;
	}

	*/
/** 设置删除的position*//*

	public void setRemove(int position) {
		remove_position = position;
		notifyDataSetChanged();
	}

	*/
/** 删除频道列表*//*

	public void remove() {
		channelList.remove(remove_position);
		remove_position = -1;
		notifyDataSetChanged();
	}

	*/
/** 设置频道列表*//*

	public void setListDate(List<ChannelItem> list) {
		channelList = list;
	}

	*/
/** 获取是否可见*//*

	public boolean isVisible() {
		return isVisible;
	}

	*/
/** 设置是否可见*//*

	public void setVisible(boolean visible) {
		isVisible = visible;
	}
	*/
/** 显示放下的ITEM*//*

	public void setShowDropItem(boolean show) {
		isItemShow = show;
	}
	*/
/*private LayoutInflater mLayoutInflater;
	private Context context;
	private ArrayList<Card> cards;
	*//*
*/
/** 要删除的position *//*
*/
/*
	public int remove_position = -1;
	//建立枚举 2个item 类型
	public enum ITEM_TYPE {
		PROGRESS,//进度条样式
		TEXT,//文字样式
		IMAGE//单图模式
	}

	public DragAdapter(Context context,ArrayList<Card> cards) {
		this.cards = cards;
		this.context = context;
		mLayoutInflater = LayoutInflater.from(context);
	}

	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		if (viewType == CardAdapter.ITEM_TYPE.PROGRESS.ordinal()) {
			return new CardAdapter.ProgressCardHolder(mLayoutInflater.inflate(R.layout.card_item_progress, parent, false));
		} else if(viewType == CardAdapter.ITEM_TYPE.TEXT.ordinal()){
			return new CardAdapter.TextCardHolder(mLayoutInflater.inflate(R.layout.card_item_text, parent, false));
		}else {
			return new CardAdapter.ImageCardHolder(mLayoutInflater.inflate(R.layout.card_item_image, parent, false));
		}
	}

	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		if (holder instanceof CardAdapter.ProgressCardHolder) {
			ProgressCard card=(ProgressCard) cards.get(position);
			CardAdapter.ProgressCardHolder progressCardHolder =(CardAdapter.ProgressCardHolder) holder;
			progressCardHolder.progressWheeltitle.setText(card.getTitle());
			progressCardHolder.progressWheel.setDefText(card.getUnit());
			progressCardHolder.progressWheel.setPercentage(80);
			progressCardHolder.progressWheel.setStepCountText(card.getSurplusToString(2));
		} else if (holder instanceof CardAdapter.TextCardHolder) {
			TextCard card=(TextCard) cards.get(position);
			CardAdapter.TextCardHolder textCardHolder =(CardAdapter.TextCardHolder) holder;
			textCardHolder.textLogo.setImageBitmap(card.getLogo());
			textCardHolder.textTitle.setText(card.getNumberToString(1));
			textCardHolder.textUnit.setText(card.getUnit());
			textCardHolder.textButton.setText(card.getButtonText());
		}else if(holder instanceof CardAdapter.ImageCardHolder){
			ImageCard card=(ImageCard) cards.get(position);
			CardAdapter.ImageCardHolder imageCardHolder =(CardAdapter.ImageCardHolder) holder;
			imageCardHolder.logo.setImageBitmap(card.getLogo());
			imageCardHolder.textButton.setText(card.getButtonText());
		}
	}

	@Override
	public int getCount() {
		return cards == null ? 0 : cards.size();
	}

	@Override
	public Card getItem(int position) {
		if (cards != null && cards.size() != 0) {
			return cards.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return null;
	}
*//*
*/
/* 设置删除的position *//*
*/
/*
	public void setRemove(int position) {
		remove_position = position;
		notifyDataSetChanged();
	}

	*//*
*/
/** 删除频道列表 *//*
*/
/*
	public void remove() {
		cards.remove(remove_position);
		remove_position = -1;
		notifyDataSetChanged();
	}
	*//*
*/
/** 拖动变更频道排序 *//*
*/
/*
   *//*
*/
/* public void exchange(int dragPostion, int dropPostion) {
        holdPosition = dropPostion;
        ChannelItem dragItem = getItem(dragPostion);
        Log.d(TAG, "startPostion=" + dragPostion + ";endPosition=" + dropPostion);
        if (dragPostion < dropPostion) {
            channelList.add(dropPostion + 1, dragItem);
            channelList.remove(dragPostion);
        } else {
            channelList.add(dropPostion, dragItem);
            channelList.remove(dragPostion + 1);
        }
        isChanged = true;
        notifyDataSetChanged();
    }*//*
*/
/*


	public int getItemViewType(int position) {
		return cards.get(position).getCardType();
	}

	public int getItemCount() {
		return cards == null ? 0 : cards.size();
	}

	//圆形进度条 的ViewHolder
	public static class ProgressCardHolder extends RecyclerView.ViewHolder {
		TextView progressWheeltitle;
		ProgressWheel progressWheel;
		Button progressButton;

		public ProgressCardHolder(View itemView) {
			super(itemView);
			progressWheeltitle = (TextView) itemView.findViewById(R.id.card_progress_title);
			progressWheel=(ProgressWheel)itemView.findViewById(R.id.wheelprogress);
			progressButton=(Button)itemView.findViewById(R.id.card_progress_button);
		}
	}

	//文字的ViewHolder
	public static class TextCardHolder extends RecyclerView.ViewHolder {
		ImageView textLogo;
		TextView textTitle;
		TextView textUnit;
		Button textButton;
		public TextCardHolder(View itemView) {
			super(itemView);
			textLogo = (ImageView) itemView.findViewById(R.id.card_text_logo);
			textTitle= (TextView) itemView.findViewById(R.id.card_text_title);
			textUnit= (TextView) itemView.findViewById(R.id.card_text_unit);
			textButton=(Button) itemView.findViewById(R.id.card_text_button);
		}
	}
	//图片的ViewHolder
	public static class ImageCardHolder extends RecyclerView.ViewHolder {
		ImageView logo;
		Button textButton;
		public ImageCardHolder(View itemView) {
			super(itemView);
			logo = (ImageView) itemView.findViewById(R.id.card_image_logo);
			textButton=(Button) itemView.findViewById(R.id.card_image_button);
		}
	}*//*

}*/
package chinamobile.com.a10086app.Adapter;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.progresviews.ProgressWheel;

import org.w3c.dom.Text;

import chinamobile.com.a10086app.Bean.Card;
import chinamobile.com.a10086app.Bean.ChannelItem;
import chinamobile.com.a10086app.Bean.ImageCard;
import chinamobile.com.a10086app.Bean.ProgressCard;
import chinamobile.com.a10086app.Bean.TextCard;
import chinamobile.com.a10086app.R;

public class DragAdapter extends BaseAdapter {
	/** TAG*//*
	private final static String TAG = "DragAdapter";
	*//** 是否显示底部的ITEM *//*
	private boolean isItemShow = false;
	private Context context;
	*//** 控制的postion *//*
	private int holdPosition;
	*//** 是否改变 *//*
	private boolean isChanged = false;
	*//** 是否可见 *//*
	boolean isVisible = true;
	*//** 可以拖动的列表（即用户选择的频道列表） *//*
	public List<ChannelItem> channelList;
	*//** TextView 频道内容 *//*
	private TextView item_text;
	*//** 要删除的position *//*
	public int remove_position = -1;

	public DragAdapter(Context context, List<ChannelItem> channelList) {
		this.context = context;
		this.channelList = channelList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return channelList == null ? 0 : channelList.size();
	}

	@Override
	public ChannelItem getItem(int position) {
		// TODO Auto-generated method stub
		if (channelList != null && channelList.size() != 0) {
			return channelList.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = LayoutInflater.from(context).inflate(R.layout.function_category_item, null);
		item_text = (TextView) view.findViewById(R.id.text_item);
		ChannelItem channel = getItem(position);
		item_text.setText(channel.getName());
		if ((position == 0) || (position == 1)){
//			item_text.setTextColor(context.getResources().getColor(R.color.black));
			item_text.setEnabled(false);
		}
		if (isChanged && (position == holdPosition) && !isItemShow) {
			item_text.setText("");
			item_text.setSelected(true);
			item_text.setEnabled(true);
			isChanged = false;
		}
		if (!isVisible && (position == -1 + channelList.size())) {
			item_text.setText("");
			item_text.setSelected(true);
			item_text.setEnabled(true);
		}
		if(remove_position == position){
			item_text.setText("");
		}
		return view;
	}

	*//** 添加频道列表 *//*
	public void addItem(ChannelItem channel) {
		channelList.add(channel);
		notifyDataSetChanged();
	}

	*//** 拖动变更频道排序 *//*
	public void exchange(int dragPostion, int dropPostion) {
		holdPosition = dropPostion;
		ChannelItem dragItem = getItem(dragPostion);
		Log.d(TAG, "startPostion=" + dragPostion + ";endPosition=" + dropPostion);
		if (dragPostion < dropPostion) {
			channelList.add(dropPostion + 1, dragItem);
			channelList.remove(dragPostion);
		} else {
			channelList.add(dropPostion, dragItem);
			channelList.remove(dragPostion + 1);
		}
		isChanged = true;
		notifyDataSetChanged();
	}

	*//** 获取频道列表 *//*
	public List<ChannelItem> getChannnelLst() {
		return channelList;
	}

	*//** 设置删除的position *//*
	public void setRemove(int position) {
		remove_position = position;
		notifyDataSetChanged();
	}

	*//** 删除频道列表 *//*
	public void remove() {
		channelList.remove(remove_position);
		remove_position = -1;
		notifyDataSetChanged();
	}

	*//** 设置频道列表 *//*
	public void setListDate(List<ChannelItem> list) {
		channelList = list;
	}

	*//** 获取是否可见 *//*
	public boolean isVisible() {
		return isVisible;
	}

	*//** 设置是否可见 *//*
	public void setVisible(boolean visible) {
		isVisible = visible;
	}
	*//** 显示放下的ITEM *//*
	public void setShowDropItem(boolean show) {
		isItemShow = show;
	}*/
	/** TAG*/
	private final static String TAG = "DragAdapter";
	/** 是否显示底部的ITEM */
	private boolean isItemShow = false;
	private Context context;
	/** 控制的postion */
	private int holdPosition;
	/** 是否改变 */
	private boolean isChanged = false;
	/** 是否可见 */
	boolean isVisible = true;
	/** 可以拖动的列表（即用户选择的频道列表） */
	//public List<TextCard> channelList;
	/** TextView 频道内容 */
	private TextView item_text;
	private LayoutInflater mLayoutInflater;
	private ArrayList<Card> cards;
	/** 要删除的position */
	public int remove_position = -1;
	//建立枚举 2个item 类型
	public enum ITEM_TYPE {
		PROGRESS,//进度条样式
		TEXT,//文字样式
		IMAGE//单图模式
	}

	public DragAdapter(Context context,ArrayList<Card> cards) {
		this.cards = cards;
		this.context = context;
		mLayoutInflater = LayoutInflater.from(context);
	}

	/*public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		if (viewType == CardAdapter.ITEM_TYPE.PROGRESS.ordinal()) {
			return new CardAdapter.ProgressCardHolder(mLayoutInflater.inflate(R.layout.card_item_progress, parent, false));
		} else if(viewType == CardAdapter.ITEM_TYPE.TEXT.ordinal()){
			return new CardAdapter.TextCardHolder(mLayoutInflater.inflate(R.layout.card_item_text, parent, false));
		}else {
			return new CardAdapter.ImageCardHolder(mLayoutInflater.inflate(R.layout.card_item_image, parent, false));
		}
	}*/

	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		if (holder instanceof CardAdapter.ProgressCardHolder) {
			ProgressCard card=(ProgressCard) cards.get(position);
			CardAdapter.ProgressCardHolder progressCardHolder =(CardAdapter.ProgressCardHolder) holder;
			progressCardHolder.progressWheeltitle.setText(card.getTitle());
			progressCardHolder.progressWheel.setDefText(card.getUnit());
			progressCardHolder.progressWheel.setPercentage(80);
			progressCardHolder.progressWheel.setStepCountText(card.getSurplusToString(2));
		} else if (holder instanceof CardAdapter.TextCardHolder) {
			TextCard card=(TextCard) cards.get(position);
			CardAdapter.TextCardHolder textCardHolder =(CardAdapter.TextCardHolder) holder;
			textCardHolder.textLogo.setImageBitmap(card.getLogo());
			textCardHolder.textTitle.setText(card.getNumberToString(1));
			textCardHolder.textUnit.setText(card.getUnit());
			textCardHolder.textButton.setText(card.getButtonText());
		}else if(holder instanceof CardAdapter.ImageCardHolder){
			ImageCard card=(ImageCard) cards.get(position);
			CardAdapter.ImageCardHolder imageCardHolder =(CardAdapter.ImageCardHolder) holder;
			imageCardHolder.logo.setImageBitmap(card.getLogo());
			imageCardHolder.textButton.setText(card.getButtonText());
		}
	}

	@Override
	public int getCount() {
		return cards == null ? 0 : cards.size();
	}

	@Override
	public Card getItem(int position) {
		if (cards != null && cards.size() != 0) {
			return cards.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	/** 显示放下的ITEM */
	public void setShowDropItem(boolean show) {
		isItemShow = show;
	}
	/** 获取是否可见 */
	public boolean isVisible() {
		return isVisible;
	}

	/** 设置是否可见 */
	public void setVisible(boolean visible) {
		isVisible = visible;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		int type =  getItemViewType(position);
		//更改传进去的布局即可
		//final RecyclerView.ViewHolder viewHolder=getViewHolder(position, convertView, parent,type);

		View view = mLayoutInflater.inflate(R.layout.card_item_text, null);
		item_text = (TextView) view.findViewById(R.id.card_text_title);
		TextCard textCard = (TextCard) getItem(position);
		item_text.setText(textCard.getNumberToString(1));
		if ((position == 0) || (position == 1)){
//			item_text.setTextColor(context.getResources().getColor(R.color.black));
			item_text.setEnabled(false);
		}
		if (isChanged && (position == holdPosition) && !isItemShow) {
			item_text.setText("");
			item_text.setSelected(true);
			item_text.setEnabled(true);
			isChanged = false;
		}
		if (!isVisible && (position == -1 + cards.size())) {
			item_text.setText("");
			item_text.setSelected(true);
			item_text.setEnabled(true);
		}
		if(remove_position == position){
			item_text.setText("");
		}
		return view;
	}

	/*private RecyclerView.ViewHolder getViewHolder(int position, View convertView, ViewGroup parent, int type) {

	}*/

	/* 设置删除的position */
	public void setRemove(int position) {
		remove_position = position;
		notifyDataSetChanged();
	}

	/** 删除频道列表 */
	public void remove() {
		cards.remove(remove_position);
		remove_position = -1;
		notifyDataSetChanged();
	}
	/** 拖动变更频道排序 */
	public void exchange(int dragPostion, int dropPostion) {
		holdPosition = dropPostion;
		TextCard dragItem = (TextCard)getItem(dragPostion);
		Log.d(TAG, "startPostion=" + dragPostion + ";endPosition=" + dropPostion);
		if (dragPostion < dropPostion) {
			cards.add(dropPostion + 1, dragItem);
			cards.remove(dragPostion);
		} else {
			cards.add(dropPostion, dragItem);
			cards.remove(dragPostion + 1);
		}
		isChanged = true;
		notifyDataSetChanged();
	}


	public int getItemViewType(int position) {
		return cards.get(position).getCardType();
	}

	public int getItemCount() {
		return cards == null ? 0 : cards.size();
	}

	//圆形进度条 的ViewHolder
	public static class ProgressCardHolder extends RecyclerView.ViewHolder {
		TextView progressWheeltitle;
		ProgressWheel progressWheel;
		Button progressButton;

		public ProgressCardHolder(View itemView) {
			super(itemView);
			progressWheeltitle = (TextView) itemView.findViewById(R.id.card_progress_title);
			progressWheel=(ProgressWheel)itemView.findViewById(R.id.wheelprogress);
			progressButton=(Button)itemView.findViewById(R.id.card_progress_button);
		}
	}

	//文字的ViewHolder
	public static class TextCardHolder extends RecyclerView.ViewHolder {
		ImageView textLogo;
		TextView textTitle;
		TextView textUnit;
		Button textButton;
		public TextCardHolder(View itemView) {
			super(itemView);
			textLogo = (ImageView) itemView.findViewById(R.id.card_text_logo);
			textTitle= (TextView) itemView.findViewById(R.id.card_text_title);
			textUnit= (TextView) itemView.findViewById(R.id.card_text_unit);
			textButton=(Button) itemView.findViewById(R.id.card_text_button);
		}
	}
	//图片的ViewHolder
	public static class ImageCardHolder extends RecyclerView.ViewHolder {
		ImageView logo;
		Button textButton;
		public ImageCardHolder(View itemView) {
			super(itemView);
			logo = (ImageView) itemView.findViewById(R.id.card_image_logo);
			textButton=(Button) itemView.findViewById(R.id.card_image_button);
		}
	}
}