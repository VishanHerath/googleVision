package prsnl.vishanherath.vision;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

public class SecondActivity extends AppCompatActivity {

    ImageView imageView;
    TextView detectedText;
    Button detectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        imageView = findViewById(R.id.imageView1);
        detectedText = findViewById(R.id.detectTxt);
        detectBtn = findViewById(R.id.detectbtn);

        detectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detect();
            }
        });

    }

    private void detect() {
        //perform text detection here

        //TODO 1. define TextRecognizer
        TextRecognizer recognizer = new TextRecognizer.Builder(SecondActivity.this).build();

        //TODO 2. Get bitmap from imageview
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();

        //TODO 3. get frame from bitmap
        Frame frame = new Frame.Builder().setBitmap(bitmap).build();

        //TODO 4. get data from frame
        SparseArray<TextBlock> sparseArray =  recognizer.detect(frame);

        //TODO 5. set data on textview
        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0;i < sparseArray.size(); i++){
            TextBlock tx = sparseArray.get(i);
            String str = tx.getValue();
            stringBuilder.append(str);
        }

        detectedText.setText(stringBuilder);
    }

}