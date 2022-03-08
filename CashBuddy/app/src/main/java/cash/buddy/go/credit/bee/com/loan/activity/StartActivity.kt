package cash.buddy.go.credit.bee.com.loan.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import cash.buddy.go.credit.bee.com.loan.BaseActivity
import cash.buddy.go.credit.bee.com.loan.databinding.ActivityStartBinding
import cash.buddy.go.credit.bee.com.loan.utils.StorePreferences


class StartActivity : BaseActivity() {



    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLoadIng("")
        val handler = Handler()
        handler.postDelayed({
            finish()

            when {
                StorePreferences.getInstance(this).user_xy.isEmpty() -> startActivity(Intent(this, PActivity::class.java))
                StorePreferences.getInstance(this).user_info.isEmpty() -> startActivity(Intent(this, LoginActivity::class.java))
                else -> startActivity(Intent(this, MainActivity::class.java))
            }
            overridePendingTransition(0, 0)


        }, 3014)
    }
}