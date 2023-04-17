package com.example.book;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class BookHolder extends RecyclerView.ViewHolder  {
    public TextView titlu;
    public TextView autor;
    public TextView comentariu;
    public TextView rating;
    public TextView gen;


    public Button sterge;
    public Button editeaza;
    public LinearLayout lay;
    public BookHolder(View itemView) {
        super(itemView);

        titlu=(TextView) itemView.findViewById(R.id.titlu);
        autor=(TextView) itemView.findViewById(R.id.autor);
        comentariu=(TextView) itemView.findViewById(R.id.comentariu);
        rating=(TextView) itemView.findViewById(R.id.rating);
        gen=(TextView) itemView.findViewById(R.id.gen);
        sterge=(Button) itemView.findViewById(R.id.sterge);
        editeaza=(Button) itemView.findViewById(R.id.editeaza);
        lay=(LinearLayout) itemView.findViewById(R.id.lay);
    }
}
