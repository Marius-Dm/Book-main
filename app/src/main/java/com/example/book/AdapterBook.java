package com.example.book;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class AdapterBook extends RecyclerView.Adapter<BookHolder> {
    private Context context;
    private List<Book> books;

    public AdapterBook(Context context, List<Book> books){
        this.books=books;
        this.context=context;
    }

    @Override
    public BookHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView= LayoutInflater.from(parent.getContext()).inflate(R.layout.book,null,false);
        RecyclerView.LayoutParams params=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(params);

        BookHolder book=new BookHolder((layoutView));
        return book;
    }

    @Override
    public void onBindViewHolder(BookHolder holder, @SuppressLint("RecyclerView") int position) {
        DB db=new DB(holder.itemView.getContext());

        holder.titlu.setText(books.get(position).getTitlu());
        holder.autor.setText(books.get(position).getAutor());
        holder.comentariu.setText(books.get(position).getComentariu());
        holder.rating.setText("Rating: "+books.get(position).getRating());
        holder.gen.setText("Gen: "+books.get(position).getGen());

        holder.sterge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.removeBook(books.get(position).getId());
                ViewGroup.LayoutParams layoutParams=holder.lay.getLayoutParams();
                layoutParams.height=0;
                holder.lay.setLayoutParams(layoutParams);
            }
        });
        holder.editeaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent((Activity)context, EditActivity.class);
                intent.putExtra("currentId",books.get(position).getId());
                intent.putExtra("currentTitlu",books.get(position).getTitlu());
                intent.putExtra("currentAutor",books.get(position).getAutor());
                intent.putExtra("currentComent",books.get(position).getComentariu());
                intent.putExtra("currentRating",books.get(position).getRating());
                intent.putExtra("currentGen",books.get(position).getGen());
                context.startActivity(intent);

            }
        });

        holder.editeaza.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                CharSequence text = "Știi că nu e nevoie să apeși?";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}
