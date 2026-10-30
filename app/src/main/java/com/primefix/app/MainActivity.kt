package com.primefix.app

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.graphics.Color
import android.view.Gravity
import android.widget.*
import java.net.URLEncoder

class MainActivity : Activity() {

    private val blue = Color.rgb(13, 71, 161)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val scroll = ScrollView(this)
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(24, 24, 24, 24)
        layout.setBackgroundColor(Color.WHITE)

        scroll.addView(layout)

        val title = TextView(this)
        title.text = "PRIME FIX"
        title.textSize = 30f
        title.setTextColor(blue)
        title.gravity = Gravity.CENTER
        title.setPadding(10, 25, 10, 10)
        layout.addView(title)

        val subtitle = TextView(this)
        subtitle.text = "Your Trusted Home Service Partner"
        subtitle.textSize = 15f
        subtitle.gravity = Gravity.CENTER
        subtitle.setTextColor(Color.DKGRAY)
        layout.addView(subtitle)

        val heading = TextView(this)
        heading.text = "\nOur Services"
        heading.textSize = 23f
        heading.setTextColor(blue)
        layout.addView(heading)

        val services = listOf(
            "⚡ Electrical Service",
            "❄️ AC Service",
            "🧊 Refrigerator Service",
            "📹 CCTV Camera",
            "💧 Water Filter",
            "☎️ Intercom Service",
            "🚰 Plumbing Service",
            "🔋 IPS Service",
            "⚙️ Generator Service"
        )

        val spinner = Spinner(this)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            services
        )
        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        spinner.adapter = adapter

        for (service in services) {
            val item = TextView(this)
            item.text = service
            item.textSize = 17f
            item.setTextColor(blue)
            item.setPadding(15, 14, 15, 14)
            layout.addView(item)
        }

        val formTitle = TextView(this)
        formTitle.text = "\nBook Your Service"
        formTitle.textSize = 23f
        formTitle.setTextColor(blue)
        layout.addView(formTitle)

        fun input(hint: String): EditText {
            val edit = EditText(this)
            edit.hint = hint
            edit.textSize = 16f
            layout.addView(edit)
            return edit
        }

        val name = input("Customer Name")
        val phone = input("Mobile Number")
        val address = input("Full Address")

        val date = input("Preferred Service Date")

        val book = Button(this)
        book.text = "BOOK NOW"
        book.setBackgroundColor(blue)
        book.setTextColor(Color.WHITE)
        layout.addView(book)

        book.setOnClickListener {
            val message = """
                Prime Fix Service Booking
                Name: ${name.text}
                Phone: ${phone.text}
                Address: ${address.text}
                Date: ${date.text}
                Service: ${spinner.selectedItem}
            """.trimIndent()

            val url = "https://wa.me/8801830291935?text=" +
                URLEncoder.encode(message, "UTF-8")

            startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(url))
            )
        }

        val footer = TextView(this)
        footer.text = "\nContact Prime Fix\nWhatsApp: 01830291935"
        footer.textSize = 17f
        footer.gravity = Gravity.CENTER
        footer.setTextColor(blue)
        footer.setPadding(10, 25, 10, 25)
        layout.addView(footer)

        setContentView(scroll)
    }
}
