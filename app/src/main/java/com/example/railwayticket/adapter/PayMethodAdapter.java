package com.example.railwayticket.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.railwayticket.R;
import com.example.railwayticket.model.PaymentMethod;

import java.util.List;

public class PayMethodAdapter extends BaseAdapter {
    private final Context context;
    private final int layout;
    private final List<PaymentMethod> MethodLst;

    public PayMethodAdapter(Context context, int layout, List<PaymentMethod> methodLst) {
        this.context = context;
        this.layout = layout;
        MethodLst = methodLst;
    }

    @Override
    public int getCount() {
        return MethodLst.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(layout,null);

        TextView tv = view.findViewById(R.id.tvpayMethod);
        ImageView iv = view.findViewById(R.id.ivImg);

         PaymentMethod payMethod = MethodLst.get(i);

         tv.setText(payMethod.getName());
         iv.setImageResource(payMethod.getImg());
        return view;
    }
}
