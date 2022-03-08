package cash.buddy.go.credit.bee.com.loan.activity

import android.content.Intent
import android.os.Bundle
import cash.buddy.go.credit.bee.com.loan.BaseActivity
import cash.buddy.go.credit.bee.com.loan.databinding.ActivityIdCardRuleBinding
import cash.buddy.go.credit.bee.com.loan.utils.StorePreferences


class IDCardRuleActivity : BaseActivity() {



    private lateinit var binding: ActivityIdCardRuleBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdCardRuleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLoadIng("VERIFICATION")

        binding.next.setOnClickListener {
            if (StorePreferences.getInstance(this).user_number == -1) {
                finish()
                startActivity(Intent(this,IDCardOneActivity::class.java))
            }else if (StorePreferences.getInstance(this).user_number == 1) {
                finish()
                startActivity(Intent(this,IDCardTwoActivity::class.java))
            }
        }
//        loadingProgressBar.visibility = View.VISIBLE
    }
}