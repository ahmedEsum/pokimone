package com.example.pokimone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokimone.adapters.PokimaneAdapter;
import com.example.pokimone.databinding.ActivityMainBinding;
import com.example.pokimone.model.PokimaneCharacter;
import com.example.pokimone.viewmodels.PokiListViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private PokiListViewModel pokiListViewModel;
    //    private RecyclerView recyclerView;
    private ActivityMainBinding binding;
    private PokimaneAdapter pokimaneAdapter;
//    private Button favButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        installViews();
        binding.buttonFavActivity.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, FavouriteActivity.class)));
        pokiListViewModel.getPoki();
        binding.recyclerView.setAdapter(pokimaneAdapter);
        pokiListViewModel.getPokiList().observe(this, pokimaneCharacters -> pokimaneAdapter.getDiffer().submitList(pokimaneCharacters));


    }


    private void installViews() {
//        favButton=findViewById(R.id.button_fav_activity);
//        recyclerView = findViewById(R.id.recycler_view);
        pokimaneAdapter = new PokimaneAdapter();
        pokiListViewModel = new ViewModelProvider(this).get(PokiListViewModel.class);
        itemTouchHelperInstallation();
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
                pokiListViewModel.insertPokiChar(pokiPosition);
                Toast.makeText(MainActivity.this, "pokimone has benn added successfully", Toast.LENGTH_SHORT).show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(binding.recyclerView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}