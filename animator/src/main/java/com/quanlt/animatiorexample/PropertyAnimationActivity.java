package com.quanlt.animatiorexample;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PropertyAnimationActivity extends AppCompatActivity {
    @BindView(R.id.image_android)
    ImageView mAndroid;
    @BindView(R.id.activity_animation)
    LinearLayout mAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_scale)
    public void performmScale() {
        Animator scaleX = ObjectAnimator.ofFloat(mAndroid, "scaleX", 0f, 2f);
        Animator scaleY = ObjectAnimator.ofFloat(mAndroid, "scaleY", 0f, 2f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleX).before(scaleY);
        animatorSet.start();
    }

    @OnClick(R.id.button_alpha)
    public void performAlpha() {
        ValueAnimator animator = ValueAnimator.ofFloat(1f, 0f);
        animator.setDuration(2000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAndroid.setAlpha((Float) animation.getAnimatedValue());
            }
        });
        animator.start();

    }

    @OnClick(R.id.button_translate_y)
    public void performTranslateY() {
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.drop);
        animator.setTarget(mAndroid);
        animator.start();
    }

    @OnClick(R.id.button_color)
    public void performColor() {
        ObjectAnimator animator = ObjectAnimator.ofObject(mAnimationView, "backgroundColor",
                new ArgbEvaluator(),
                ContextCompat.getColor(this, R.color.colorPrimary),
                ContextCompat.getColor(this, R.color.colorAccent));
        animator.setDuration(2000);
        animator.setRepeatCount(1);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.start();
    }

    @OnClick(R.id.button_keyframe)
    public void performKeyframe() {
        Keyframe keyframe = Keyframe.ofFloat(0f, 45f);
        Keyframe keyframe1 = Keyframe.ofFloat(0.5f, 360f);
        Keyframe keyframe2 = Keyframe.ofFloat(1f, 0f);
        keyframe1.setInterpolator(new AccelerateDecelerateInterpolator());
        keyframe2.setInterpolator(new BounceInterpolator());
        PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe("rotation", keyframe, keyframe1, keyframe2);
        ObjectAnimator rotationAnim = ObjectAnimator.ofPropertyValuesHolder(mAndroid, pvhRotation);
        rotationAnim.setDuration(5000);
        rotationAnim.start();
    }
}
