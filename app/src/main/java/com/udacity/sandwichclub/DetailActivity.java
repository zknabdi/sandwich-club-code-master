package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private TextView originTv;
    private TextView descriptionTv;
    private TextView ingredientsTv;
    private TextView alsoKnownTv;
    private ImageView imageTv;
    private TextView detailAlsoKnownAsLabel;
    private TextView detailIngredientsLabel;
    private TextView detailPlaceOfOriginLabel;
    private TextView detailDescriptionLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageTv = findViewById(R.id.image_iv);
        originTv = (TextView) findViewById(R.id.origin_tv);
        descriptionTv = (TextView) findViewById(R.id.description_tv);
        ingredientsTv = (TextView) findViewById(R.id.ingredients_tv);
        alsoKnownTv = (TextView) findViewById(R.id.also_known_tv);

        detailAlsoKnownAsLabel = (TextView) findViewById(R.id.also_known_as_label);
        detailIngredientsLabel = (TextView) findViewById(R.id.ingredients_label);
        detailPlaceOfOriginLabel = (TextView) findViewById(R.id.origin_label);
        detailDescriptionLabel = (TextView) findViewById(R.id.description_label);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sWich) {
        // Picasso.with(this).load(sWich.getImage()).into(imageTv);
        Picasso.with(this)
                .load(sWich.getImage())
                .into(imageTv);

        setTitle(sWich.getMainName());
        populateUIStringView(sWich);

    }

    private void populateUIStringView(Sandwich sandwich) {
        if (sandwich.getPlaceOfOrigin().isEmpty()) {
            detailPlaceOfOriginLabel.setVisibility(View.GONE);
            originTv.setVisibility(View.GONE);

        } else {
            originTv.setText(sandwich.getPlaceOfOrigin());
        }

        if (sandwich.getDescription().isEmpty()) {
            detailDescriptionLabel.setVisibility(View.GONE);
            descriptionTv.setVisibility(View.GONE);

        } else {
            descriptionTv.setText(sandwich.getDescription());
        }

        if (sandwich.getAlsoKnownAs().isEmpty()) {
            detailAlsoKnownAsLabel.setVisibility(View.GONE);
            alsoKnownTv.setVisibility(View.GONE);

        } else {
            List<String> alsoListArray = sandwich.getAlsoKnownAs();
            for (int i = 0; i < alsoListArray.size(); i++) {
                alsoKnownTv.append(alsoListArray.get(i));
            }
        }

        if (sandwich.getIngredients().isEmpty()) {
            detailIngredientsLabel.setVisibility(View.GONE);
            ingredientsTv.setVisibility(View.GONE);

        } else {
            List<String> alsoListArray = sandwich.getIngredients();
            for (int i = 0; i < alsoListArray.size(); i++) {
                ingredientsTv.append(alsoListArray.get(i));
            }
        }


    }


}
