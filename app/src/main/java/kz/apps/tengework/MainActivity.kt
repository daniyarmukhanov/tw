package kz.apps.tengework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        naRukiCheckBox.isChecked = true
        zarplataInEditText.doOnTextChanged { text, _, _, _ ->
            val checked = naRukiCheckBox.isChecked
            printZPDetails(text.toString().toIntOrNull() ?: 0, checked)
        }
        naRukiCheckBox.setOnCheckedChangeListener { _, b ->
            printZPDetails(zarplataInEditText.text.toString().toIntOrNull() ?: 0, b)
        }
    }

    private fun printZPDetails(zp: Int, checked: Boolean) {
        if (checked) {
            val gross = (zp - 4250) / 0.81
            val pens = gross / 10
            val tax = ((gross - (gross / 10)) - 42500) / 10
            resultText.text = ""
            resultText.append("Қолыңызға тиетін айлық: $zp тг\n")
            resultText.append("Салыққа: ${tax.toInt()} тг\n")
            resultText.append("Зейнетақы қорына: ${pens.toInt()} тг\n")
            resultText.append("Гросс айлығыңыз: ${gross.toInt()} тг")
        } else {
            val pens = zp / 10
            val tax = (zp - pens - 42500) / 10
            val net = zp - pens - tax
            resultText.text = ""
            resultText.append("Гросс айлығыңыз: $zp тг\n")
            resultText.append("Қолыңызға тиетін айлық: $net тг\n")
            resultText.append("Салыққа: $tax тг\n")
            resultText.append("Зейнетақы қорына: $pens тг")
        }
    }
}