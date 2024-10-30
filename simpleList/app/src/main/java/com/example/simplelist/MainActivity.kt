import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.simplelist.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Đảm bảo rằng tệp activity_main.xml đã được liên kết đúng cách
        setContentView(R.layout.activity_main)

        // Khởi tạo các thành phần giao diện từ tệp XML
        val editTextNumber = findViewById<EditText>(R.id.editTextNumber)
        val radioEven = findViewById<RadioButton>(R.id.radioEven)
        val radioOdd = findViewById<RadioButton>(R.id.radioOdd)
        val radioSquare = findViewById<RadioButton>(R.id.radioSquare)
        val buttonShow = findViewById<Button>(R.id.buttonShow)
        val listViewResults = findViewById<ListView>(R.id.listViewResults)
        val textViewError = findViewById<TextView>(R.id.textViewError)

        buttonShow.setOnClickListener {
            // Lấy dữ liệu từ EditText
            val input = editTextNumber.text.toString()

            // Kiểm tra tính hợp lệ của dữ liệu đầu vào
            if (input.isEmpty() || input.toIntOrNull() == null || input.toInt() <= 0) {
                textViewError.text = "Vui lòng nhập số nguyên dương hợp lệ"
                textViewError.visibility = TextView.VISIBLE
                listViewResults.adapter = null
                return@setOnClickListener
            }

            // Ẩn thông báo lỗi nếu đầu vào hợp lệ
            textViewError.visibility = TextView.GONE
            val n = input.toInt()
            val resultList = mutableListOf<Int>()

            // Lựa chọn loại số dựa vào RadioButton được chọn
            when {
                radioEven.isChecked -> {
                    // Thêm số chẵn từ 0 đến n
                    for (i in 0..n step 2) {
                        resultList.add(i)
                    }
                }
                radioOdd.isChecked -> {
                    // Thêm số lẻ từ 1 đến n
                    for (i in 1..n step 2) {
                        resultList.add(i)
                    }
                }
                radioSquare.isChecked -> {
                    // Thêm số chính phương từ 0 đến n
                    var i = 0
                    while (i * i <= n) {
                        resultList.add(i * i)
                        i++
                    }
                }
                else -> {
                    textViewError.text = "Vui lòng chọn một loại số"
                    textViewError.visibility = TextView.VISIBLE
                    return@setOnClickListener
                }
            }

            // Hiển thị kết quả trong ListView
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultList)
            listViewResults.adapter = adapter
        }
    }
}
