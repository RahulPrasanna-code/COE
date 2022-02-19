package com.example.coe_complaints;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

import io.realm.RealmResults;

public class ExamsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {


    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private OnLoadMoreListener onLoadMoreListener;

    private boolean isLoading;
    private Activity activity;
    private RealmResults<Exam> exams;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
        }
    }

    private class ExamViewHolder extends RecyclerView.ViewHolder {
        public TextView fees;
        public TextView course_name;
        public MaterialCardView cardExamination;
        public Button btnHallTicket;

        public ExamViewHolder(View view) {
            super(view);
            fees = (TextView) view.findViewById(R.id.txtFee);
            course_name = (TextView) view.findViewById(R.id.txtCourseName);
            cardExamination =  view.findViewById(R.id.cardExamination);
            btnHallTicket = (Button) view.findViewById(R.id.btnHallTicket);
        }
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }

    public ExamsAdapter(RecyclerView recyclerView, RealmResults<Exam> exams, Activity activity) {

        this.exams = exams;
        this.activity = activity;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return exams.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(activity).inflate(R.layout.exam_item_row, parent, false);
            return new ExamViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(activity).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ExamViewHolder) {
            Exam exam = exams.get(position);
            ExamViewHolder examViewHolder = (ExamViewHolder) holder;
            examViewHolder.course_name.setText(exam.getExamName());
            examViewHolder.fees.setText(exam.getFee());
            if(exam.isRegistered())
            {
                examViewHolder.btnHallTicket.setVisibility(View.VISIBLE);
            }
            else{
                examViewHolder.btnHallTicket.setVisibility(View.GONE);
            }

            examViewHolder.cardExamination.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(activity.getApplicationContext(),"clicked "+exam.getExamName(),Toast.LENGTH_SHORT).show();
                }
            });


        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    public void setExams(RealmResults<Exam> exams) {
        this.exams = exams;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return exams == null ? 0 : exams.size();
    }

    public void setLoaded() {
        isLoading = false;
    }
}
