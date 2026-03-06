package com.ncorti.kotlin.template.app
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 定位到 QQfile_recv 文件夹
        val treeUri = "primary%3AAndroid%2Fdata%2Fcom.tencent.mobileqq"
        val documentUri = "primary%3AAndroid%2Fdata%2Fcom.tencent.mobileqq%2FTencent%2FQQfile_recv"
        val uriString = "content://com.android.externalstorage.documents/tree/$treeUri/document/$documentUri"
        val folderUri = Uri.parse(uriString)

        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(folderUri, "vnd.android.document/directory")
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
        }

        try {
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "打开失败：系统限制了原生文件管理器", Toast.LENGTH_LONG).show()
        }
        // 拉起文件夹后立刻关闭自己
        finish()
    }
}
