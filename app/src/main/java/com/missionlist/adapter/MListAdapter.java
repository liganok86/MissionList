package com.missionlist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.missionlist.model.Mission;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/10/7.
 */
public class MListAdapter extends BaseAdapter {
    private ArrayList<Mission> mList;
    private Context mContext;
    private LayoutInflater inflater = null;
    private List<Integer> mPositions;
    private List<String> mSections;

    public MListAdapter(Context mContext, ArrayList<Mission> mList){
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
        return null;
    }


























}