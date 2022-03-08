package cash.buddy.go.credit.bee.com.loan.activity

import android.content.Intent
import android.os.Bundle
import cash.buddy.go.credit.bee.com.loan.BaseActivity
import cash.buddy.go.credit.bee.com.loan.databinding.ActivityEmailBinding

class EmailActivity : BaseActivity() {





    private lateinit var binding: ActivityEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLoadIng("CUSTOMER SERVICE")
        binding.toEmail.setOnClickListener {
            val i = Intent(Intent.ACTION_SEND)
            i.type = "message/rfc822" // 真机上使用这行

            i.putExtra(Intent.EXTRA_EMAIL, arrayOf(binding.emailText.text.toString()))
            i.putExtra(Intent.EXTRA_SUBJECT, "")
            i.putExtra(Intent.EXTRA_TEXT, "")
            startActivity(
                Intent.createChooser(
                    i,
                    ""
                )
            )
        }

    }
}