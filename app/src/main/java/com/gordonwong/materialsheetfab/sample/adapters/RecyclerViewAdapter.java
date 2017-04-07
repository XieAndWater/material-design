package com.gordonwong.materialsheetfab.sample.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gordonwong.materialsheetfab.sample.R;
import com.gordonwong.materialsheetfab.sample.models.Note;

/**
 * Created by Administrator on 2017/4/5.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private MyItemClickListener mItemClickListener;
    private Context mContext;
//    private List<String> mList;
    private Note[] notes;

    public RecyclerViewAdapter(Context context, int numNotes) {
        this.mContext = context;
        this.notes =generateNotes(context, numNotes);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.list_item_note, null);
        //将全局的监听传递给holder
        ViewHolder holder = new ViewHolder(view, mItemClickListener);
        return holder;
    }

    private Note[] generateNotes(Context context, int numNotes) {
        Note[] notes = new Note[numNotes];
        for (int i = 0; i < notes.length; i++) {
            notes[i] = Note.randomNote(context);
        }
        return notes;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //给空间赋值
        Note noteModel = notes[position];
        String title = noteModel.getTitle();
        String note = noteModel.getNote();
        String info = noteModel.getInfo();
        int infoImage = noteModel.getInfoImage();
        int color = noteModel.getColor();
        // Set text
        holder.titleTextView.setText(title);
        holder.noteTextView.setText(note);
        holder.infoTextView.setText(info);

        // Set image
        if (infoImage != 0) {
            holder.infoImageView.setImageResource(infoImage);
        }

        // Set visibilities
        holder.titleTextView.setVisibility(TextUtils.isEmpty(title) ? View.GONE : View.VISIBLE);
        holder.noteTextView.setVisibility(TextUtils.isEmpty(note) ? View.GONE : View.VISIBLE);
        holder.infoLayout.setVisibility(TextUtils.isEmpty(info) ? View.GONE : View.VISIBLE);

        // Set padding
        int paddingTop = (holder.titleTextView.getVisibility() != View.VISIBLE) ? 0
                : holder.itemView.getContext().getResources()
                .getDimensionPixelSize(R.dimen.note_content_spacing);
        holder.noteTextView.setPadding(holder.noteTextView.getPaddingLeft(), paddingTop,
                holder.noteTextView.getPaddingRight(), holder.noteTextView.getPaddingBottom());

        // Set background color
        ((CardView) holder.itemView).setCardBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return notes.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView titleTextView;
        public TextView noteTextView;
        public LinearLayout infoLayout;
        public TextView infoTextView;
        public ImageView infoImageView;
        private MyItemClickListener mListener;

        public ViewHolder(View itemView, MyItemClickListener myItemClickListener) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.note_title);
            noteTextView = (TextView) itemView.findViewById(R.id.note_text);
            infoLayout = (LinearLayout) itemView.findViewById(R.id.note_info_layout);
            infoTextView = (TextView) itemView.findViewById(R.id.note_info);
            infoImageView = (ImageView) itemView.findViewById(R.id.note_info_image);
            //将全局的监听赋值给接口
            this.mListener = myItemClickListener;
            itemView.setOnClickListener(this);
        }

        /**
         * 实现OnClickListener接口重写的方法
         * @param v
         */
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(v, getPosition());
            }

        }
    }

    /**
     * 创建一个回调接口
     */
    public interface MyItemClickListener {
        void onItemClick(View view, int position);
    }

    /**
     * 在activity里面adapter就是调用的这个方法,将点击事件监听传递过来,并赋值给全局的监听
     *
     * @param myItemClickListener
     */
    public void setItemClickListener(MyItemClickListener myItemClickListener) {
        this.mItemClickListener = myItemClickListener;
    }
}
