package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kevoh.thepenguins.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import Model.CollegeHolder;

/**
 * Created by KeVoH on 1/25/2017.
 */
public class CollegesAdapter extends RecyclerView.Adapter<CollegesAdapter.NewsViewHolder>{

    public interface OnItemClickListener {

        void onItemClick(CollegeHolder item);

    }

    public List<CollegeHolder> list;
    public Context context;
    public AdapterView.OnItemClickListener onItemClickListener;

    public CollegesAdapter(List<CollegeHolder> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.college_card,parent,false);
        NewsViewHolder newsViewHolder = new NewsViewHolder(view);
        return newsViewHolder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {

           final CollegeHolder collegeHolder = list.get(position);
        if(!TextUtils.isEmpty(collegeHolder.getImage()))
        {
            Picasso.with(context).load(collegeHolder.getImage()).fit().into(holder.imageView);
        }

        //Setting textview title
        holder.name.setText(Html.fromHtml(collegeHolder.getName()));

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        }
        };
        holder.imageView.setOnClickListener(listener);
        holder.name.setOnClickListener(listener);
        holder.location.setText("Location");
        holder.contact.setText("Contact");

    }


    @Override
    public int getItemCount() {
        return (null != list?list.size():0);
    }

    public AdapterView.OnItemClickListener getOnItemClickListener()
    {
        return onItemClickListener;
    }
    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }

    class NewsViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imageView;
        private TextView name,location,contact;

        public NewsViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.IvCollege);
            name= (TextView) itemView.findViewById(R.id.tvCollegeName);
            location = (TextView) itemView.findViewById(R.id.tvCollegeLocation);
            contact = (TextView) itemView.findViewById(R.id.tvCollegeContact);

        }


    }

}
