package com.myseoultrip.adapter;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myseoultrip.R;

import java.util.ArrayList;

public class DetailAdapter extends BaseAdapter {
    /* 아이템을 세트로 담기 위한 어레이 */
    private ArrayList<DetailItem> mItems = new ArrayList<>();

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public DetailItem getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int pos = position;
        Context context = parent.getContext();

        /* 'listview_custom' Layout을 inflate하여 convertView 참조 획득 */
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_detail_intro_item, parent, false);
        }

        /* 'listview_custom'에 정의된 위젯에 대한 참조 획득 */
        TextView viewKeyValue = (TextView) convertView.findViewById(R.id.detail_keyValue);
        viewKeyValue.setMovementMethod(new ScrollingMovementMethod());
        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        DetailItem detailItem = mItems.get(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        viewKeyValue.setText(detailItem.getKeyValue());
        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)  */


        return convertView;
    }

    /* 아이템 데이터 추가를 위한 함수. 자신이 원하는대로 작성 */
    public void addItem(String keyValue) {

        DetailItem detailItem = new DetailItem();

        /* MyItem에 아이템을 setting한다. */
        detailItem.setKeyValue(keyValue);

        Log.i("myLog", "추가내역: "+keyValue);
        /* mItems에 MyItem을 추가한다. */
        mItems.add(detailItem);

    }
}
