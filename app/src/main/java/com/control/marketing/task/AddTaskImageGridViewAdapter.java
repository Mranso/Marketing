package com.control.marketing.task;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.control.marketing.R;
import com.control.marketing.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * author: yuantian
 * date: 2017/5/25
 * version: 2.0.0
 */

public class AddTaskImageGridViewAdapter extends BaseAdapter {

    public static final String DEFAULT_IMAGE_URL = "DEFAULT_IMAGE_URL";

    private Context context;
    private List<String> imageList = new ArrayList<>();

    public AddTaskImageGridViewAdapter(Context context) {
        this.context = context;
    }

    public void refreshData(List<String> imageList) {
        this.imageList = imageList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int i) {
        return imageList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        AddTaskImageViewHolder addTaskImageViewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.add_task_image_grid_view_item_layout, viewGroup, false);
            addTaskImageViewHolder = new AddTaskImageViewHolder();
            addTaskImageViewHolder.addTaskImage = (ImageView) view.findViewById(R.id.add_task_image_grid_item_image);
            addTaskImageViewHolder.addTaskImageDelete = (ImageView) view.findViewById(R.id.add_task_image_grid_item_delete);
            view.setTag(addTaskImageViewHolder);
        } else {
            addTaskImageViewHolder = (AddTaskImageViewHolder) view.getTag();
        }
        final String imageUrl = imageList.get(i);
        if (DEFAULT_IMAGE_URL.equals(imageUrl)) {
            Resources resources = context.getResources();
            Drawable drawable = resources.getDrawable(R.drawable.add_task_add_image);
            addTaskImageViewHolder.addTaskImage.setImageDrawable(drawable);
            addTaskImageViewHolder.addTaskImageDelete.setVisibility(View.GONE);
        } else {
            ImageUtils.setImage(context, imageUrl, addTaskImageViewHolder.addTaskImage);
            addTaskImageViewHolder.addTaskImageDelete.setVisibility(View.VISIBLE);
        }

        addTaskImageViewHolder.addTaskImageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageList.remove(imageUrl);
                if (!DEFAULT_IMAGE_URL.equals(imageList.get(imageList.size() - 1))) {
                    imageList.add(DEFAULT_IMAGE_URL);
                }
                notifyDataSetChanged();
            }
        });

        return view;
    }

    private class AddTaskImageViewHolder {
        private ImageView addTaskImage;
        private ImageView addTaskImageDelete;
    }
}
