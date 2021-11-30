package com.example.pokimone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokimone.adapters.PokimaneAdapter;
import com.example.pokimone.databinding.ActivityFavouriteBinding;
import com.example.pokimone.model.PokimaneCharacter;
import com.example.pokimone.viewmodels.PokiListViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FavouriteActivity extends AppCompatActivity {
    private PokiListViewModel pokiListViewModel;
    //    private RecyclerView recyclerView;
    private PokimaneAdapter pokimaneAdapter;
    //    private Button mainButton;
    private ActivityFavouriteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavouriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        installViews();
        itemTouchHelperInstallation();

        pokiListViewModel.getPokiFavList();
        binding.recyclerFavView.setAdapter(pokimaneAdapter);


        pokiListViewModel.getFavList().observe(this, new Observer<List<PokimaneCharacter>>() {
            @Override
            public void onChanged(List<PokimaneCharacter> pokimaneCharacters) {
                pokimaneAdapter.getDiffer().submitList(pokimaneCharacters);

            }
        });

        binding.buttonMainActivity.setOnClickListener(view -> startActivity(new Intent(FavouriteActivity.this, MainActivity.class)));
    }


    private void installViews() {
//        mainButton = findViewById(R.id.button_main_activity);
//        recyclerView = findViewById(R.id.recycler_fav_view);
        pokimaneAdapter = new PokimaneAdapter();
        pokiListViewModel = new ViewModelProvider(this).get(PokiListViewModel.class);
    }

    private void itemTouchHelperInstallation() {
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int recyclerViewItemPosition = viewHolder.getBindingAdapterPosition();
                PokimaneCharacter pokiPosition = (PokimaneCharacter) pokimaneAdapter.getDiffer().getCurrentList().get(recyclerViewItemPosition);
                pokiListViewModel.deletePokiFavChar(pokiPosition.getName());
                Toast.makeText(FavouriteActivity.this, "pokimone has benn added deleted", Toast.LENGTH_SHORT).show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(binding.recyclerFavView);
    }
}