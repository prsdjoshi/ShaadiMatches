package com.exercise.shaadicarddemo.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.exercise.shaadicarddemo.R;
import com.exercise.shaadicarddemo.model.response.User;
import com.exercise.shaadicarddemo.model.roomdatabase.AppDatabase;
import com.exercise.shaadicarddemo.model.roomdatabase.DatabaseClient;
import com.exercise.shaadicarddemo.model.roomdatabase.UserTable;
import com.exercise.shaadicarddemo.utils.StringHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private final AppDatabase appDatabase;
    private Context context;
    private List<UserTable> userDetailsList;
    StringHelper stringHelper;
    public UserAdapter(Context context,List<UserTable> userDetailsList) {
        this.context = context;
        this.userDetailsList = userDetailsList;
        stringHelper =new StringHelper(context);
        appDatabase = DatabaseClient.getInstance(context).getAppDatabase();

    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shaadi_match_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
            holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        if (userDetailsList != null && userDetailsList.size() > 0) {
            return userDetailsList.size();
        } else {
            return 0;
        }
    }
    public class ViewHolder extends BaseViewHolder {

        ImageView ivThumbnail;
        TextView tv_status,txtdecline,txtconnect,tv_created_date,tv_location, tv_email,tv_name_age;
        FloatingActionButton fab_decline,fab_connect;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_created_date = itemView.findViewById(R.id.tv_created_date);
            tv_location = itemView.findViewById(R.id.tv_location);
            tv_email = itemView.findViewById(R.id.tv_email);
            tv_name_age = itemView.findViewById(R.id.tv_name_age);
            ivThumbnail = itemView.findViewById(R.id.iv_profile_image);
            fab_connect = itemView.findViewById(R.id.fab_connect);
            fab_decline = itemView.findViewById(R.id.fab_decline);
            txtdecline = itemView.findViewById(R.id.txtdecline);
            txtconnect = itemView.findViewById(R.id.txtconnect);
            tv_status = itemView.findViewById(R.id.tv_status);


        }

        @Override
        protected void clear() {
            ivThumbnail.setImageDrawable(null);
            tv_created_date.setText("");
            tv_location.setText("");
            tv_email.setText("");
            tv_name_age.setText("");
        }

        @Override
        public void onBind(int position) {

            super.onBind(position);

            UserTable currentUser = userDetailsList.get(position);
            if (currentUser.getPicture() != null) {
                Glide.with(itemView.getContext())
                        .load(currentUser.getPicture())
                        .into(ivThumbnail);
            }
            if (currentUser.getFullNameAge() != null) {
                tv_name_age.setText(currentUser.getFullNameAge());
            }
            if (currentUser.getLocation() != null) {

                tv_location.setText(currentUser.getLocation());
            }
            if (currentUser.getEmail() != null) {
                tv_email.setText(currentUser.getEmail());
            }
            if (currentUser.getRegistrationPeriod() != null) {
                tv_created_date.setText(currentUser.getRegistrationPeriod());
            }

            if(currentUser.getStatus().equalsIgnoreCase(""))
            {
                //not accepted or declined
               setNothing();
            }else if(currentUser.getStatus().equalsIgnoreCase("0"))
            {
                //declined
               setUserDeclined();

            }else if (currentUser.getStatus().equalsIgnoreCase("1"))
            {
                //accepted
              setUserConnected();
            }
            fab_connect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        currentUser.setStatus("1");
                        appDatabase.userDao().update(currentUser);
                        //setUserConnected();
                         notifyDataSetChanged();
                }
            });
            fab_decline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentUser.setStatus("0");
                    appDatabase.userDao().update(currentUser);
                    //setUserDeclined();
                    notifyDataSetChanged();
                }
            });

        }
        public void setUserConnected()
        {
            tv_status.setVisibility(View.VISIBLE);
            txtconnect.setVisibility(View.GONE);
            txtdecline.setVisibility(View.GONE);
            fab_decline.setVisibility(View.GONE);
            fab_connect.setVisibility(View.GONE);
            tv_status.setText(context.getResources().getString(R.string.connected_msg));

        }
        public void setUserDeclined()
        {
            tv_status.setVisibility(View.VISIBLE);
            txtconnect.setVisibility(View.GONE);
            txtdecline.setVisibility(View.GONE);
            fab_decline.setVisibility(View.GONE);
            fab_connect.setVisibility(View.GONE);
            tv_status.setText(context.getResources().getString(R.string.declined_msg));

        }
        private void setNothing() {
            tv_status.setVisibility(View.GONE);
            txtconnect.setVisibility(View.VISIBLE);
            txtdecline.setVisibility(View.VISIBLE);
            fab_decline.setVisibility(View.VISIBLE);
            fab_connect.setVisibility(View.VISIBLE);
        }
    }


}

abstract class BaseViewHolder extends RecyclerView.ViewHolder {
    private int mCurrentPosition;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }
    protected abstract void clear();
    public void onBind(int position)
    {
        mCurrentPosition = position;
        clear();
    }
    public int getCurrentPosition() {
        return mCurrentPosition;
    }
}
