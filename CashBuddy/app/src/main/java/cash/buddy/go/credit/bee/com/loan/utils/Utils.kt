package cash.buddy.go.credit.bee.com.loan.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import androidx.core.app.ActivityCompat
import java.io.File
import java.util.*

object Utils {

    /**
     * 申请权限
     */
    fun checkPermissionFirst(context: Context, requestCode: Int, permission: Array<String>): Boolean {
        val permissions = ArrayList<String>()
        for (per in permission) {
            val permissionCode = ActivityCompat.checkSelfPermission(context, per)
            if (permissionCode != PackageManager.PERMISSION_GRANTED) {
                permissions.add(per)
            }
        }
        return if (permissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(context as Activity, permissions.toTypedArray(), requestCode)
            false
        } else {
            true
        }
    }

    fun checkSelfPermission(context: Context?, permission: String?): Boolean {
        return ActivityCompat.checkSelfPermission(
            context!!,
            permission!!
        ) != PackageManager.PERMISSION_GRANTED
    }

     fun getDestinationUri(context: Context): Uri {
        val fileName = String.format("fr_crop_%s.jpg", System.currentTimeMillis())
        val cropFile = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName)
        return Uri.fromFile(cropFile)
    }

    fun getMapToString(map: Map<String?, Any?>?): String? {
       return ""
//        return try {
//            JSONObject.toJSONString(map)
//        } catch (e: Exception) {
//            e.printStackTrace()
//            ""
//        }
    }
}