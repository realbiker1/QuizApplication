package com.example.quiz.ViewModel;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quiz.Pokemon;
import com.example.quiz.R;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.PokemonHolder> {

    private List<Pokemon> pokemonList = new ArrayList<>();


    public void setPokemonList(List<Pokemon> pokemons) {
        pokemonList = pokemons;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return pokemonList == null ? 0 : pokemonList.size();
    }

    @NonNull
    @Override
    public PokemonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pokemon_list,parent,false);
        return new PokemonHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull PokemonHolder holder, int position) {
        Pokemon currentPokemon = pokemonList.get(position);
        holder.textViewTitle.setText(currentPokemon.getName());
        holder.imageView.setImageBitmap(convertByteToBitmap(currentPokemon.getBitmap()));
    }

    class PokemonHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private ImageView imageView;

        public PokemonHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.pokemon_row);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }

    public Bitmap convertByteToBitmap(byte [] picture){
        InputStream targetStream = new ByteArrayInputStream(picture);

        return BitmapFactory.decodeStream(targetStream);
    }
    }
