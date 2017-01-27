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
import Model.ProgrammesHolder;

/**
 * Created by KeVoH on 1/25/2017.
 */
public class ProgrammesAdapter extends RecyclerView.Adapter<ProgrammesAdapter.NewsViewHolder>{

    public List<ProgrammesHolder> list;
    public Context context;
    public AdapterView.OnItemClickListener onItemClickListener;

    public ProgrammesAdapter(List<ProgrammesHolder> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.programme_card,parent,false);
        NewsViewHolder newsViewHolder = new NewsViewHolder(view);
        return newsViewHolder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {

        final ProgrammesHolder collegeHolder = list.get(position);
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

            imageView = (ImageView) itemView.findViewById(R.id.IvProgramme);
            name= (TextView) itemView.findViewById(R.id.tvProgrammeName);
            location = (TextView) itemView.findViewById(R.id.tvProgrammeLocation);
            contact = (TextView) itemView.findViewById(R.id.tvProgrammeContact);

        }


    }

}
