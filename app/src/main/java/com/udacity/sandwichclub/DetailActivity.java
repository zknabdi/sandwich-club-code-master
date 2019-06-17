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

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private Sandwich sandwich;
    // private ImageView imgViewLabel;
//    private TextView originTv;
//    private TextView descriptionTv;
//    private TextView ingredientsTv;
//    private TextView alsoKnownTv;
//    private ImageView imageTv;
//    private TextView detailAlsoKnownAsLabel;
//    private TextView detailIngredientsLabel;
//    private TextView detailPlaceOfOriginLabel;
//    private TextView detailDescriptionLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
//        originTv = (TextView) findViewById(R.id.origin_tv);
//        descriptionTv = (TextView) findViewById(R.id.description_tv);
//        ingredientsTv = (TextView) findViewById(R.id.ingredients_tv);
//        alsoKnownTv= (TextView) findViewById(R.id.also_known_tv);
//        imageTv = (ImageView) findViewById(R.id.image_iv);
//        detailAlsoKnownAsLabel = (TextView) findViewById(R.id.origin_tv);
//        detailIngredientsLabel = (TextView) findViewById(R.id.origin_tv);
//        detailPlaceOfOriginLabel = (TextView) findViewById(R.id.origin_tv);
//        detailDescriptionLabel = (TextView) findViewById(R.id.de)


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

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

       // populateUItv(sandwich.getDescription(), R.id.detail_description_label, R.id.description_tv);
    }

//    private void populateUItv(String detailsPassed, int labelID, int tvID){
//        TextView textViewTV = (TextView) findViewById(tvID);
//        if(detailsPassed.isEmpty()){
//            findViewById(labelID).setVisibility(View.GONE);
//            textViewTV.setVisibility(View.GONE);
//        }else{
//            textViewTV.setText(detailsPassed);
//        }
//    }
}
