package chaitanya.testingbitmap;

import android.content.Intent;
import android.graphics.Bitmap;

import android.graphics.Canvas;

import android.graphics.Paint;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int SPEECH_REQUEST_CODE = 0;
    private static final String TAG="chaitanya.testingbitmap";
    public int width;// = this.getResources().getDisplayMetrics().widthPixels;
    public int height;// = (int)this.getResources().getDisplayMetrics().heightPixels/2;
    public int x,y;
    Bitmap bmp;
    Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView imageView=(ImageView)findViewById(R.id.imageView);
        paint=new Paint();

        bmp=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        width=this.getResources().getDisplayMetrics().widthPixels;
        height=(int)this.getResources().getDisplayMetrics().heightPixels/2;
        x=(int)bmp.getWidth()/2;
        y=(int)bmp.getHeight()/2;


        /* Inside a View use
        int width = getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        int height = getApplicationContext().getResources().getDisplayMetrics().heightPixels;
         */

/*      ImageView imageView=(ImageView)findViewById(R.id.imageView);
        Paint paint=new Paint();

        Bitmap bmp;
        bmp=Bitmap.createBitmap(width,height/2, Bitmap.Config.ARGB_8888);

        Canvas canvas=new Canvas(bmp);
        canvas.drawCircle(bmp.getWidth()/2, bmp.getHeight()/2, 100, paint);

        imageView.setImageBitmap(bmp);
*/



        bmp=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);

        Canvas canvas=new Canvas(bmp);
        canvas.drawCircle(x, y, 100, paint);

        imageView.setImageBitmap(bmp);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG,"Intent speaking");
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);
            Log.i(TAG, spokenText);
            if(spokenText.equals("left"))
            {
                Toast.makeText(this, "move left", Toast.LENGTH_SHORT).show();
                x-=10;
            }
            else if(spokenText.equals("right"))
            {
                Toast.makeText(this, "move right", Toast.LENGTH_LONG).show();
                x+=10;
            }
            else if(spokenText.equals("up"))
            {
                Toast.makeText(this, "move up", Toast.LENGTH_SHORT).show();
                y-=10;
            }
            else if(spokenText.equals("down"))
            {
                Toast.makeText(this, "move down", Toast.LENGTH_SHORT).show();
                y+=10;
            }
            else
            {
                Toast.makeText(this, "invalid command",Toast.LENGTH_SHORT).show();
            }

            // Do something with spokenText
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void voicecommand(View v)
    {
        Log.i(TAG, "Into voicecommand");
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        // Start the activity, the intent will be populated with the speech text
        startActivityForResult(intent, SPEECH_REQUEST_CODE);

        ImageView imageView=(ImageView)findViewById(R.id.imageView);
        paint=new Paint();

        bmp=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);

        Canvas canvas=new Canvas(bmp);
        canvas.drawCircle(x, y, 100, paint);

        imageView.setImageBitmap(bmp);
    }



}
