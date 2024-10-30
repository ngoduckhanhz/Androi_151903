package com.example.myapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private StudentAdapter studentAdapter;
    private List<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo danh sách sinh viên
        students = new ArrayList<>(Arrays.asList(
                new Student("Nguyễn Văn A", "123456"),
                new Student("Trần Thị B", "234567"),
                new Student("Lê Văn C", "345678"),
                new Student("Phạm Thị D", "456789"),
                new Student("Nguyễn Văn E", "567890")
        ));

        RecyclerView recyclerViewStudents = findViewById(R.id.recyclerViewStudents);
        recyclerViewStudents.setLayoutManager(new LinearLayoutManager(this));
        studentAdapter = new StudentAdapter(students);
        recyclerViewStudents.setAdapter(studentAdapter);

        EditText editTextSearch = findViewById(R.id.editTextSearch);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterStudents(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filterStudents(String query) {
        List<Student> filteredStudents;
        if (query.length() > 2) {
            filteredStudents = new ArrayList<>();
            for (Student student : students) {
                if (student.getName().toLowerCase().contains(query.toLowerCase()) ||
                        student.getMssv().toLowerCase().contains(query.toLowerCase())) {
                    filteredStudents.add(student);
                }
            }
        } else {
            filteredStudents = students;
        }
        studentAdapter.updateStudents(filteredStudents);
    }

    // Lớp Student
    public static class Student {
        private String name;
        private String mssv;

        public Student(String name, String mssv) {
            this.name = name;
            this.mssv = mssv;
        }

        public String getName() {
            return name;
        }

        public String getMssv() {
            return mssv;
        }
    }

    // Lớp StudentAdapter
    public static class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
        private List<Student> students;

        public StudentAdapter(List<Student> students) {
            this.students = students;
        }

        public static class StudentViewHolder extends RecyclerView.ViewHolder {
            public TextView textViewName;
            public TextView textViewMSSV;

            public StudentViewHolder(View view) {
                super(view);
                textViewName = view.findViewById(R.id.textViewName);
                textViewMSSV = view.findViewById(R.id.textViewMSSV);
            }
        }

        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
            return new StudentViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
            Student student = students.get(position);
            holder.textViewName.setText(student.getName());
            holder.textViewMSSV.setText(student.getMssv());
        }

        @Override
        public int getItemCount() {
            return students.size();
        }

        public void updateStudents(List<Student> newStudents) {
            this.students = newStudents;
            notifyDataSetChanged();
        }
    }
}
