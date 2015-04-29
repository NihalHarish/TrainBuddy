package com.example.nihal.maindriver;

/**
 * Created by nihal on 6/4/15.
 */
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
public class CustomAdapter extends ArrayAdapter {
    Model[] modelItems = null;
    Context context;

    public CustomAdapter(Context context, Model[] resource) {
        super(context, R.layout.row, resource);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.modelItems = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.row, parent, false);
        TextView name = (TextView) convertView.findViewById(R.id.textView1);
        TextView cost = (TextView) convertView.findViewById(R.id.textView2);
        CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox1);
        StringBuilder costString = new StringBuilder(' ');
        for(int i=0;i<(25-modelItems[position].getName().length());i++)
            costString.append(' ');
        costString.append("Cost:");
        name.setText(modelItems[position].getName());
        cost.setText(costString.append(modelItems[position].getCost()));
        if (modelItems[position].getValue() == 1){
            cb.setChecked(true);
//            MenuActivity.costText.setText("Cost:"+(Integer.parseInt(String.valueOf(MenuActivity.costText.getText()))+10)+"");
        }
        else{
            cb.setChecked(false);
//            MenuActivity.costText.setText(Integer.parseInt(String.valueOf(MenuActivity.costText.getText()))-10+"");
        }
        return convertView;
    }
}