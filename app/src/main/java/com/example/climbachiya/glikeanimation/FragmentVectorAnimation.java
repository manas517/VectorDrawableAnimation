package com.example.climbachiya.glikeanimation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by C.limbachiya on 4/5/2016.
 */
public class FragmentVectorAnimation extends Fragment {

    ImageView imgTruck;
    RelativeLayout llRoot;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.vector_drawable_animated, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        llRoot = (RelativeLayout) view.findViewById(R.id.root);
        imgTruck = (ImageView) view.findViewById(R.id.imgTruck);

        changeBackground();

        /*AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(),
                R.animator.property_animator);
        set.setTarget(imgTruck);
        set.start();*/

        //loading xml from anim folder
        //Animation transAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.truck_tanslate);
        //You can now apply the animation to a view
        //imgTruck.startAnimation(transAnimation);

        /*RotateAnimation rAnim = new RotateAnimation(0, 359, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rAnim.setDuration(1000);
        imgTruck.startAnimation(rAnim);*/

        animateImageView(imgTruck);
    }

    private void changeBackground() {

        int colorFrom = Color.WHITE;
        int colorTo = ContextCompat.getColor(getActivity(), R.color.colorlightgreen);
        int duration = 1000;
        ObjectAnimator.ofObject(llRoot, "backgroundColor", new ArgbEvaluator(), colorFrom, colorTo)
                .setDuration(duration)
                .start();

    }

    private void animateImageView(final ImageView imgView) {

        final int color = ContextCompat.getColor(getActivity(), R.color.colorAccent);

        final ValueAnimator colorAnim = ObjectAnimator.ofFloat(0f, 1f);
        colorAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float mul = (Float) animation.getAnimatedValue();
                int alphaOrange = adjustAlpha(color, mul);
                imgView.setColorFilter(alphaOrange, PorterDuff.Mode.SRC_ATOP);
                if (mul == 0.0) {
                    imgView.setColorFilter(null);
                }
            }
        });

        colorAnim.setDuration(1500);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.setRepeatCount(-1);
        colorAnim.start();
    }

    public int adjustAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }
}
