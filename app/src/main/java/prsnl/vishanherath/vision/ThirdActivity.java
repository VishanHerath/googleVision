package prsnl.vishanherath.vision;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.net.Uri;
import android.content.Intent;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

public class ThirdActivity extends AppCompatActivity {

    private final int REQUEST_CODE=10;
    ImageView image;
    Button importBtn, detectbtn;
    TextView txtViewThird;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        image = findViewById(R.id.imageView);
        importBtn = findViewById(R.id.importbtn);
        detectbtn = findViewById(R.id.txtDetectBtn);
        txtViewThird = findViewById(R.id.textViewThird);

        importBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(getImage,REQUEST_CODE);
            }
        });

        detectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detectThird();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==REQUEST_CODE && data != null && data.getData() != null) {
            Uri uri = data.getData();
            image.setImageURI(uri);
        }
    }

    private void detectThird() {
        //perform text detection here
        //TODO 1. define TextRecognizer
        TextRecognizer recognizer = new TextRecognizer.Builder(ThirdActivity.this).build();

        //TODO 2. Get bitmap from imageview
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();

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
        txtViewThird.setText(stringBuilder);
    }

}