package com.example.pokimone.adapters;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokimone.databinding.PokiCharacterItemViewBinding;
import com.example.pokimone.model.PokimaneCharacter;

public class PokimaneAdapter extends RecyclerView.Adapter<PokimaneAdapter.PokimoneViewHolder> {
    private static final String TAG = "PokimaneAdapter";
//    private ArrayList<PokimaneCharacter> list = new ArrayList<>();
//    private final Context mContext;

    private final AsyncListDiffer differ = new AsyncListDiffer(this, new DiffUtil.ItemCallback<PokimaneCharacter>() {
        @Override
        public boolean areItemsTheSame(@NonNull PokimaneCharacter oldItem, @NonNull PokimaneCharacter newItem) {
            Log.d(TAG, "areItemsTheSame: ");
            return oldItem.getName()== newItem.getName();
        }

        @Override
        public boolean areContentsTheSame(@NonNull PokimaneCharacter oldItem, @NonNull PokimaneCharacter newItem) {
            Log.d(TAG, "areContentsTheSame: ");
            return oldItem.getId()== newItem.getId();
        }
    });

//    public PokimaneAdapter(Context mContext) {
//        this.mContext = mContext;
//    }

    @NonNull
    @Override
    public PokimoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PokimoneViewHolder(PokiCharacterItemViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PokimoneViewHolder holder, int position) {
//        holder.pokiName.setText(list.get(position).getName());
//        Glide.with(mContext).load(list.get(position).getUrl())
//                .into(holder.pokiImage);
        PokimaneCharacter character = (PokimaneCharacter) differ.getCurrentList().get(position);
        holder.binding.pokiNameTextview.setText(character.getName());
        Glide.with(holder.binding.getRoot()).load(character.getUrl()).into(holder.binding.imagePoki);

    }


//    public PokimaneCharacter getPokiPosition(int position) {
//        return list.get(position);
//    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }

    public AsyncListDiffer getDiffer() {
        return differ;
    }

    class PokimoneViewHolder extends RecyclerView.ViewHolder {
        private final PokiCharacterItemViewBinding binding;

        public PokimoneViewHolder(@NonNull PokiCharacterItemViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
