package com.example.nirfrankclg;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CollegeAdapter extends RecyclerView.Adapter<CollegeAdapter.CollegeViewHolder> {

    private List<College> collegeList;

    public CollegeAdapter(List<College> collegeList) {
        this.collegeList = collegeList;
    }

    @Override
    public CollegeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_college, parent, false);
        return new CollegeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CollegeViewHolder holder, int position) {
        College college = collegeList.get(position);
        holder.nameTextView.setText(college.getName());
        holder.rankTextView.setText("Rank: " + college.getnirfrank());
        holder.domainTextView.setText("Domain: " + college.getDomain());
        holder.detailsTextView.setText(college.getDetails());
    }

    @Override
    public int getItemCount() {
        return collegeList.size();
    }
    public void updateCollegeList(List<College> filteredList) {
        this.collegeList = filteredList;
        notifyDataSetChanged(); // This tells the RecyclerView to refresh the data
    }


    public static class CollegeViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView rankTextView;
        TextView domainTextView;
        TextView detailsTextView;

        public CollegeViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            rankTextView = itemView.findViewById(R.id.rankTextView);
            domainTextView = itemView.findViewById(R.id.domainTextView);
            detailsTextView = itemView.findViewById(R.id.detailsTextView);
        }
    }
}
