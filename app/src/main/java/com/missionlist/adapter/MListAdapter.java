package com.missionlist.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.missionlist.R;
import com.missionlist.model.Mission;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/10/7.
 */
public class MListAdapter extends BaseAdapter {
    private List<Mission> mList;
    private Context mContext;
    private LayoutInflater inflater = null;
    private List<Integer> mPositions;
    private List<String> mSections;
    private SimpleDateFormat formatter    =   new SimpleDateFormat("MM月dd日");

    public MListAdapter(Context mContext, List<Mission> mList){
        this.mContext = mContext;
        this.mList = mList;
        inflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return mList == null? 0: mList.size();
    }

    @Override
    public Mission getItem(int position) {
        if (mList != null && mList.size()!=0){
            return mList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder;
        View view = convertView;
        if (view == null){
            view = inflater.inflate(R.layout.list_item,null);
            mHolder = new ViewHolder();
            mHolder.itemImage = (ImageView)view.findViewById(R.id.item_image);
            mHolder.itemSyncFlag = (ImageView)view.findViewById(R.id.item_sync_flag);
            mHolder.itemTitle = (TextView)view.findViewById(R.id.item_title);
            mHolder.itemDescription = (TextView)view.findViewById(R.id.item_des);
            mHolder.itemPriority = (TextView)view.findViewById(R.id.item_priority);
            mHolder.itemDueDate = (TextView)view.findViewById(R.id.item_due_date);
            view.setTag(mHolder);
        }else {
            mHolder = (ViewHolder)view.getTag();
        }

        Mission mission = getItem(position);
        if (mission != null){
            mHolder.itemTitle.setText(mission.getTitle());
            mHolder.itemDescription.setText(mission.getDescription());
            if (mission.getStatus() == view.getResources().getIntArray(R.array.status)[2]){
                mHolder.itemImage.setImageResource(R.drawable.ic_done);
            }else {
                mHolder.itemImage.setImageResource(R.drawable.ic_todo);
            }
            if(mission.getDraft() == true){
                mHolder.itemSyncFlag.setImageResource(R.drawable.ic_sync_flag);
            }
            switch (mission.getPriority()){
                case -1:
                    mHolder.itemPriority.setText(mContext.getResources().getStringArray(R.array.priority)[0]);
                    break;
                case 0:
                    mHolder.itemPriority.setText(mContext.getResources().getStringArray(R.array.priority)[0]);
                    break;
                case 1:
                    mHolder.itemPriority.setText(mContext.getResources().getStringArray(R.array.priority)[1]);
                    break;
                case 2:
                    mHolder.itemPriority.setText(mContext.getResources().getStringArray(R.array.priority)[2]);
                    break;
            }

            if (mission.getDueDate() != null){
                mHolder.itemDueDate.setText(formatter.format(mission.getDueDate()));
            }
        }

        return view;
    }

    static class ViewHolder{
        ImageView itemImage;
        ImageView itemSyncFlag;
        TextView itemTitle;
        TextView itemDescription;
        TextView itemPriority;
        TextView itemDueDate;
    }


























}
