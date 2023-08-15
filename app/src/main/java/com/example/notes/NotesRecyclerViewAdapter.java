package com.example.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesRecyclerViewAdapter extends RecyclerView.Adapter<NotesRecyclerViewAdapter.MyViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<NotesModel> notesModel;

    public NotesRecyclerViewAdapter(Context context, ArrayList<NotesModel> notesModel, RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.notesModel = notesModel;
        this.recyclerViewInterface = recyclerViewInterface;

    }

    @NonNull
    @Override
    public NotesRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.note, parent, false);
        return new NotesRecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesRecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.noteTitle.setText(notesModel.get(position).getTitle());
        holder.noteShortcut.setText(notesModel.get(position).getNoteShortcut());
    }

    @Override
    public int getItemCount() {
        return notesModel.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView noteTitle;
        TextView noteShortcut;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            noteTitle = itemView.findViewById(R.id.titleView);
            noteShortcut = itemView.findViewById(R.id.noteShortcut);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onNoteClick(pos);
                        }

                    }
                }
            });
        }
    }
}
