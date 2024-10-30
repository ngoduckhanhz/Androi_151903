package com.example.simplelist; // Đảm bảo rằng tên gói là chính xác

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Đảm bảo rằng tệp activity_main.xml đã được liên kết đúng cách
        setContentView(R.layout.activity_main);

        // Khởi tạo các thành phần giao diện từ tệp XML
        EditText editTextNumber = findViewById(R.id.editTextNumber);
        RadioButton radioEven = findViewById(R.id.radioEven);
        RadioButton radioOdd = findViewById(R.id.radioOdd);
        RadioButton radioSquare = findViewById(R.id.radioSquare);
        Button buttonShow = findViewById(R.id.buttonShow);
        ListView listViewResults = findViewById(R.id.listViewResults);
        TextView textViewError = findViewById(R.id.textViewError);

        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ EditText
                String input = editTextNumber.getText().toString();

                // Kiểm tra tính hợp lệ của dữ liệu đầu vào
                if (input.isEmpty() || Integer.parseInt(input) <= 0) {
                    textViewError.setText("Vui lòng nhập số nguyên dương hợp lệ");
                    textViewError.setVisibility(View.VISIBLE);
                    listViewResults.setAdapter(null);
                    return;
                }

                // Ẩn thông báo lỗi nếu đầu vào hợp lệ
                textViewError.setVisibility(View.GONE);
                int n = Integer.parseInt(input);
                List<Integer> resultList = new ArrayList<>();

                // Lựa chọn loại số dựa vào RadioButton được chọn
                if (radioEven.isChecked()) {
                    // Thêm số chẵn từ 0 đến n
                    for (int i = 0; i <= n; i += 2) {
                        resultList.add(i);
                    }
                } else if (radioOdd.isChecked()) {
                    // Thêm số lẻ từ 1 đến n
                    for (int i = 1; i <= n; i += 2) {
                        resultList.add(i);
                    }
                } else if (radioSquare.isChecked()) {
                    // Thêm số chính phương từ 0 đến n
                    int i = 0;
                    while (i * i <= n) {
                        resultList.add(i * i);
                        i++;
                    }
                } else {
                    textViewError.setText("Vui lòng chọn một loại số");
                    textViewError.setVisibility(View.VISIBLE);
                    return;
                }

                // Hiển thị kết quả trong ListView
                ArrayAdapter<Integer> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, resultList);
                listViewResults.setAdapter(adapter);
            }
        });
    }
}
