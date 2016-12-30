package com.quanlt.animatiorexample;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimatorSetActivity extends AppCompatActivity {
    @BindView(R.id.image_target)
    ImageView mTarget;
    private int mScreenHeight;
    @BindView(R.id.image_cloud)
    ImageView mCloud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_set);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        mScreenHeight = displaymetrics.heightPixels;
        Log.d(getClass().getSimpleName(), "screen " + mScreenHeight);

    }

    @OnClick(R.id.image_target)
    public void animateByCode() {
        mCloud.setPivotX(0f);
        mCloud.setPivotY(0f);
        AnimatorSet animatorSet = new AnimatorSet();
        Animator dropAnimator = ObjectAnimator.ofFloat(mTarget, "translationY", 0, 600);
        dropAnimator.setDuration(500);
        dropAnimator.setInterpolator(new BounceInterpolator());
        dropAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mTarget.setImageResource(R.drawable.beated);
            }
        });
        Animator cloudDismiss = ObjectAnimator.ofFloat(mCloud, "scaleY", 1, 0);
        cloudDismiss.setDuration(400);
        Animator moveAnimator = ObjectAnimator.ofFloat(mTarget, "translationX", 0, 300);
        moveAnimator.setInterpolator(new AccelerateInterpolator());
        moveAnimator.setDuration(300);
        Animator rollAnimator = ObjectAnimator.ofFloat(mTarget, "rotation", 0, 360);
        rollAnimator.setDuration(150);
        rollAnimator.setInterpolator(new AccelerateInterpolator());
        animatorSet.play(cloudDismiss).before(dropAnimator);
        animatorSet.play(dropAnimator).before(moveAnimator);
        animatorSet.play(moveAnimator).with(rollAnimator);
        animatorSet.start();
    }

    @OnClick(R.id.image_cloud)
    public void animateByXML() {
        Animator set = AnimatorInflater.loadAnimator(this, R.animator.drop_and_roll);
        set.setTarget(mTarget);
        set.start();
    }
}
