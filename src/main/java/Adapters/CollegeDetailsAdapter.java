package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kevoh.thepenguins.R;

import java.util.ArrayList;

/**
 * Created by KeVoH on 1/26/2017.
 */
public class CollegeDetailsAdapter extends BaseAdapter {
    ArrayList arrayList = new ArrayList();
    Context context;
    LayoutInflater inflater;
   public String [] titles = {"Name:","Location:","Bio:","Contact:","Email:","Exam:","Fee:"};
    private String[]details;

    public CollegeDetailsAdapter(ArrayList arrayList, Context context,String[]details) {
        this.arrayList = arrayList;
        this.context = context;
        this.details = details;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
        public View getView(int position, View convertView, ViewGroup parent) {

        MyViewHolder myViewHolder;
        View v = inflater.inflate(R.layout.college_details,parent,false);
        if (convertView==null)
        {
            convertView = v;
            myViewHolder = new MyViewHolder(convertView);
            convertView.setTag(myViewHolder);
        }

        else
        {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        myViewHolder.a.setText(titles[position]);
        myViewHolder.b.setText(details[position]);


        return convertView;
    }

    private class MyViewHolder
    {
        private TextView a,b;
        public MyViewHolder(View view) {
            a = (TextView) view.findViewById(R.id.txt);
            b = (TextView) view.findViewById(R.id.texted);
        }
    }
}
