package com.quanlt.animatiorexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimationActivity extends AppCompatActivity {
    @BindView(R.id.image_android)
    ImageView mTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_scale)
    public void performmScale() {
        mTarget.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale));
    }

    @OnClick(R.id.button_alpha)
    public void performAlpha() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setFillAfter(true);
        mTarget.startAnimation(alphaAnimation);
    }

    @OnClick(R.id.button_translate_x)
    public void performTranslateX() {
        mTarget.startAnimation(AnimationUtils.loadAnimation(this, R.anim.translatex));

    }

    @OnClick(R.id.button_translate_y)
    public void perforTranslateY() {
        mTarget.startAnimation(AnimationUtils.loadAnimation(this, R.anim.translatey));

    }

    @OnClick(R.id.button_rotation)
    public void performRotation() {
        mTarget.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotation));
    }

    @OnClick(R.id.button_combine)
    public void performAnimationSet() {
        mTarget.startAnimation(AnimationUtils.loadAnimation(this, R.anim.combine));
    }
}
