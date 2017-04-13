package com.ninghoo.beta17ma27.weydio2.WeiboDocument.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ninghoo.beta17ma27.weydio2.R;
import com.ninghoo.beta17ma27.weydio2.WeiboDocument.Activity.PhotoViewActivity;
import com.ninghoo.beta17ma27.weydio2.WeiboDocument.Activity.RepostActivity;
import com.ninghoo.beta17ma27.weydio2.WeiboDocument.Model.CircleTransform;
import com.ninghoo.beta17ma27.weydio2.WeiboDocument.Model.ParameterKeySet;
import com.ninghoo.beta17ma27.weydio2.WeiboDocument.Model.PicUrlsEntity;
import com.ninghoo.beta17ma27.weydio2.WeiboDocument.Model.StatusEntity;
import com.ninghoo.beta17ma27.weydio2.WeiboDocument.Utils.RichTextUtils;
import com.ninghoo.beta17ma27.weydio2.WeiboDocument.Utils.TimeFormatUtils;

import java.util.List;

/**
 * Created by ningfu on 17-4-13.
 */

public class HomepageListAdapter extends RecyclerView.Adapter {
    private List<StatusEntity> mDataSet;
    private OnItemClickListener mOnItemClickListener;
    private Context mContext;

    public HomepageListAdapter(List<StatusEntity> dataSet, Context context) {
        mDataSet = dataSet;
        mContext = context;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weibo_content, parent, false);
        return new HomepageViewHolder(view);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HomepageViewHolder) {
            HomepageViewHolder homepageViewHolder = (HomepageViewHolder) holder;
            final StatusEntity entity = mDataSet.get(position);
            homepageViewHolder.tvUserName.setText(entity.user.screen_name);
            homepageViewHolder.tvTime.setText(TimeFormatUtils.parseToYYMMDD(entity.created_at));
            homepageViewHolder.tvContent.setText(RichTextUtils.getRichText(mContext, entity.text));
            homepageViewHolder.tvContent.setMovementMethod(LinkMovementMethod.getInstance());
            homepageViewHolder.tvSource.setText(Html.fromHtml(entity.source).toString());
            final StatusEntity reStatus = entity.retweeted_status;
            Glide.with(mContext).load(entity.user.profile_image_url).transform(new CircleTransform(mContext)).error(R
                    .mipmap.ic_default_header)
                    .placeholder(R.mipmap.ic_launcher).into
                    (homepageViewHolder.ivHeader);
            List<PicUrlsEntity> pics = entity.pic_urls;

            homepageViewHolder.tvComment.setText(String.valueOf(entity.comments_count));
            homepageViewHolder.tvLike.setText(String.valueOf(entity.attitudes_count));
            homepageViewHolder.tvRetween.setText(String.valueOf(entity.reposts_count));
            homepageViewHolder.tvRetween.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, RepostActivity.class);
                    intent.putExtra(ParameterKeySet.ID, entity.id);
                    if(null!=reStatus){
                        intent.putExtra(ParameterKeySet.STATUS, entity.text);
                    }
                    intent.setAction("REPOST");
                    mContext.startActivity(intent);
                }
            });
            homepageViewHolder.tvComment.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, RepostActivity.class);
                    intent.putExtra(ParameterKeySet.ID, entity.id);
                    intent.setAction("COMMENT");
                    mContext.startActivity(intent);
                }
            });


            if(null!=pics&&pics.size()>0){
                final PicUrlsEntity pic = pics.get(0);
                pic.original_pic = pic.thumbnail_pic.replace("thumbnail","large");
                pic.bmiddle_pic = pic.thumbnail_pic.replace("thumbnail","bmiddle");
                homepageViewHolder.ivContent.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(pic.bmiddle_pic).asBitmap().into(homepageViewHolder.ivContent);
                homepageViewHolder.ivContent.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, PhotoViewActivity.class);
                        intent.putExtra(PicUrlsEntity.class.getSimpleName(),pic);
                        mContext.startActivity(intent);
                    }
                });
            }
            else {
                homepageViewHolder.ivContent.setVisibility(View.GONE);
            }
            if (null != reStatus) {
                String reContent =  "@"+reStatus.user.screen_name+":"+reStatus.text;
                homepageViewHolder.llRe.setVisibility(View.VISIBLE);
                homepageViewHolder.tvReContent.setText(RichTextUtils.getRichText(mContext,reContent));
                homepageViewHolder.tvReContent.setMovementMethod(LinkMovementMethod.getInstance());
                List<PicUrlsEntity> rePics = reStatus.pic_urls;
                if(null!=rePics&&rePics.size()>0){
                    final PicUrlsEntity pic = rePics.get(0);
                    homepageViewHolder.ivReContent.setVisibility(View.VISIBLE);
                    pic.original_pic = pic.thumbnail_pic.replace("thumbnail","large");
                    pic.bmiddle_pic = pic.thumbnail_pic.replace("thumbnail","bmiddle");
                    Glide.with(mContext).load(pic.bmiddle_pic).asBitmap().into(homepageViewHolder.ivReContent);
                    homepageViewHolder.ivReContent.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, PhotoViewActivity.class);
                            intent.putExtra(PicUrlsEntity.class.getSimpleName(), pic);
                            mContext.startActivity(intent);
                        }
                    });
                }
                else {
                    homepageViewHolder.ivReContent.setVisibility(View.GONE);
                }
            } else {
                homepageViewHolder.llRe.setVisibility(View.GONE);
            }
        }


    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public int getItemCount() {
        return mDataSet.size();
    }


    class HomepageViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivHeader;
        private TextView tvUserName;
        private TextView tvTime;
        private TextView tvSource;
        private TextView tvContent;
        private TextView tvReContent;
        private LinearLayout llRe;
        private ImageView ivContent, ivReContent;
        private TextView tvRetween,tvComment,tvLike;
        public HomepageViewHolder(View itemView) {
            super(itemView);
            initialize(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(v, getLayoutPosition());
                    }
                }
            });
        }

        private void initialize(View v) {

            ivHeader = (ImageView) v.findViewById(R.id.ivHeader);
            tvUserName = (TextView) v.findViewById(R.id.tvUserName);
            tvTime = (TextView) v.findViewById(R.id.tvTime);
            tvSource = (TextView) v.findViewById(R.id.tvSource);
            tvContent = (TextView) v.findViewById(R.id.tvContent);
            tvReContent = (TextView) v.findViewById(R.id.tvReContent);
            llRe = (LinearLayout) v.findViewById(R.id.llRe);
            ivContent = (ImageView) v.findViewById(R.id.ivContent);
            ivReContent = (ImageView) v.findViewById(R.id.ivReContent);
            tvRetween = (TextView) v.findViewById(R.id.tvRetweet);
            tvComment = (TextView) v.findViewById(R.id.tvComment);
            tvLike = (TextView) v.findViewById(R.id.tvLike);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }
}
