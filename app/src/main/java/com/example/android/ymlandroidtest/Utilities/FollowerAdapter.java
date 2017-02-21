package com.example.android.ymlandroidtest.Utilities;

        import android.content.Context;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.example.android.ymlandroidtest.Entities.Follower;
        import com.example.android.ymlandroidtest.R;
        import com.squareup.picasso.Callback;
        import com.squareup.picasso.Picasso;

        import java.util.List;

/**
 * Created by User on 2/20/2017.
 */

public class FollowerAdapter extends RecyclerView.Adapter<FollowerAdapter.MyViewholder> {


    private static final String TAG = "Adapter";
    private List<Follower> followersList;


    Context context;
    OnItemClickListener onItemClickListener;


    public FollowerAdapter(List<Follower> contactList, Context context) {
        this.followersList = contactList;
        this.context = context;
    }

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.follower_row_data,parent,false);

        return new MyViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {

        holder.followerTextview.setText(followersList.get(position).getLogin());




        Picasso.with(context).load(followersList.get(position).getAvatarUrl()).transform(new ShapeTransformation())
                .into(holder.avatarImageview, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });

    }

    @Override
    public int getItemCount() {
        return followersList.size();
    }

    public void setClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public class MyViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView avatarImageview;
        TextView followerTextview;


        public MyViewholder(View itemView) {
            super(itemView);

            avatarImageview = (ImageView) itemView.findViewById(R.id.imageView_follower);
            followerTextview = (TextView) itemView.findViewById(R.id.textView_follower);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            onItemClickListener.onClick(view, getAdapterPosition());

        }
    }

}
