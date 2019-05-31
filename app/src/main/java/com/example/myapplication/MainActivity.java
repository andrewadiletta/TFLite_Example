package com.example.myapplication;

import android.content.res.AssetFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("AJ: this works!");

        try {

            //Load the file without compressing!!!
            AssetFileDescriptor fileDescriptor = getAssets().openFd("model.tflite");

            FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
            FileChannel fileChannel = inputStream.getChannel();
            long startOffset = fileDescriptor.getStartOffset();
            long declaredLength = fileDescriptor.getDeclaredLength();

            ByteBuffer tfLiteFile = fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);

            //Create the interpreter
            Interpreter interpreter = new Interpreter(tfLiteFile);

            float[][] inputArray = {{0,0}, {1,0}, {0,1}, {1,1}};
            float[][] resultArray = {{0},{0},{0},{0}};
            interpreter.run(inputArray, resultArray);
            System.out.println("AJ:" + resultArray.length);
            for(float[] output : resultArray){
                System.out.println("AJ: "  + output[0]);
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("AJ: couldn't find file");
        }

    }
}
