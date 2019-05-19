package com.tjeit.baseballgame01.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tjeit.baseballgame01.R;
import com.tjeit.baseballgame01.datas.Chat;

import java.util.List;

public class ChatAdapter extends ArrayAdapter<Chat> {
    Context mContext;
    List<Chat> mList;
    LayoutInflater inf;

    public ChatAdapter(Context context, List<Chat> list) {
        super(context, R.layout.chat_list_item, list);

        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        if (row == null) {
            row = inf.inflate(R.layout.chat_list_item, null);
        }

        Chat data = mList.get(position);

        FrameLayout userMsgFramLayout = row.findViewById(R.id.userMessageFramLayout);
        FrameLayout comMsgFramLayout = row.findViewById(R.id.computerMessageFramLayout);
        TextView userMsgTxt = row.findViewById(R.id.userMessageTxt);
        TextView comMsgTxt = row.findViewById(R.id.computerMessageTxt);

        if(data.userSaid) {
            userMsgFramLayout.setVisibility(View.VISIBLE);
            comMsgFramLayout.setVisibility(View.GONE);

            userMsgTxt.setText(data.message);
        }
        else {
            userMsgFramLayout.setVisibility(View.GONE);
            comMsgFramLayout.setVisibility(View.VISIBLE);

            comMsgTxt.setText(data.message);
        }

        return row;
    }
}
