package com.quanlt.animatiorexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_view_animation)
    public void goToViewAnimation() {
        startActivity(new Intent(this, AnimationActivity.class));
    }

    @OnClick(R.id.btn_property_animation)
    public void goToPropertyAnimation() {
        startActivity(new Intent(this, PropertyAnimationActivity.class));
    }

    @OnClick(R.id.btn_animation_set)
    public void goToAnimatorSet() {
        startActivity(new Intent(this, AnimatorSetActivity.class));
    }
}
