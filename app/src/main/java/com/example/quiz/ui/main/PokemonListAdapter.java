package com.example.quiz.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quiz.Pokemon;
import com.example.quiz.R;

import java.util.ArrayList;
import java.util.List;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder> {


    private int productItemLayout;
    private List<Pokemon> pokemonList;

    @Override
    public int getItemCount() {
        return pokemonList == null ? 0 : pokemonList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()).inflate(productItemLayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        TextView item = holder.item;
        item.setText(pokemonList.get(listPosition).getName());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView item;
        ViewHolder(View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.image_view);
            item = itemView.findViewById(R.id.pokemon_row);

        }
    }
    public void setPokemons(List<Pokemon> pokemons){
        this.pokemonList= pokemons;
        notifyDataSetChanged();
    }
}
