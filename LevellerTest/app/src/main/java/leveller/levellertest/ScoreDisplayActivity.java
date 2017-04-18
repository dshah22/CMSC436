package leveller.levellertest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreDisplayActivity extends Activity {

    // Final Score
    TextView scoreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_display);

        String final_score = getIntent().getExtras().getString("final_score");

        scoreView = (TextView) findViewById(R.id.score_display);
        scoreView.setText("Score: " + final_score);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(ScoreDisplayActivity.this,MainActivity.class));
        finish();

    }
}
