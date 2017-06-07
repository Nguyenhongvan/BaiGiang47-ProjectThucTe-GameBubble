package com.vansuzy.baigiang47_projectthucte_gamebubble;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends ActionBarActivity {
    int score = 0;
    Random rd;
    TextView txtScore;
    ViewGroup.LayoutParams params;
    LinearLayout layoutBubble;
    Button btnCreateBubble;
    ObjectAnimator objectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnCreateBubble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i <= rd.nextInt(5); i++) {
                    ProcessAnim();
                }
            }
        });
    }

    private void ProcessAnim() {
        // Drawing a bubble
        ImageView img = getImageView();
        img.setBackground(getDrawable());
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutBubble.removeView(v); // khi chạm vào hình ảnh trái bóng bay lên thì layoutBubble sẽ xóa hình ảnh trái bóng này, khi remove thành công thì sẽ tăng điểm lên
                txtScore.setText("Score: "+(score+=1));
            }
        });

        objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(MainActivity.this, R.animator.bubble_animation);
        objectAnimator.setDuration(rd.nextInt(1000)+2000);
        objectAnimator.setTarget(img);  // gán hiệu ứng cho hình ảnh

        layoutBubble.addView(img, params);  // addView(): Adds a child view with the specified layout parameters.

        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                layoutBubble.removeView((View) ((ObjectAnimator) animation).getTarget());
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        objectAnimator.start();
    }

    // lấy ngẫu nhiên hình ảnh
    private Drawable getDrawable() {
        Drawable draw;
        int i = rd.nextInt(6);
        switch (i) {
            case 0:
                draw = getResources().getDrawable(R.drawable.n1);
                break;
            case 1:
                draw = getResources().getDrawable(R.drawable.n2);
                break;
            case 2:
                draw = getResources().getDrawable(R.drawable.n3);
                break;
            case 3:
                draw = getResources().getDrawable(R.drawable.n4);
                break;
            case 4:
                draw = getResources().getDrawable(R.drawable.n5);
                break;
            case 5:
                draw = getResources().getDrawable(R.drawable.n6);
                break;
            default:
                draw = getResources().getDrawable(R.drawable.n6);
                break;
        }
        return draw;
    }

    // hiển thị hình ảnh một cách ngẫu nhiên lên giao diện
    private ImageView getImageView() {
        ImageView img = new ImageView(MainActivity.this);
        img.setX(rd.nextInt(500));
        return img;
    }


    private void addControls() {
        txtScore = (TextView) findViewById(R.id.txtScore);
        rd = new Random();
        layoutBubble = (LinearLayout) findViewById(R.id.layoutBubble);
        params = new ViewGroup.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        btnCreateBubble = (Button) findViewById(R.id.btnCreateBubble);
    }
}
