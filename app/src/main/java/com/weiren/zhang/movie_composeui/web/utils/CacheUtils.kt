package com.weiren.zhang.movie_composeui.web.utils

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

object CacheUtils {

    fun getDirFile(context: Context, name: String): File {
        return if (FileUtil.isSDCardAlive()) {
            File(context.externalCacheDir, name).apply { mkdirs() }
        } else {
            File(context.cacheDir, name).apply { mkdirs() }
        }
    }

    fun getDirPath(context: Context, name: String): String {
        return getDirFile(context, name).absolutePath
    }

    suspend fun getTotalSize(context: Context): String {
        return withContext(Dispatchers.IO) {
            var cacheSize = FileUtil.getSize(context.cacheDir)
            if (FileUtil.isSDCardAlive()) {
                context.externalCacheDir?.apply {
                    cacheSize += FileUtil.getSize(this)
                }
            }
            FileUtil.formatSize(cacheSize.toDouble())
        }
    }

    fun clearAllCache(context: Context) {
        FileUtil.delete(context.cacheDir)
        if (FileUtil.isSDCardAlive()) {
            FileUtil.delete(context.externalCacheDir)
        }
    }

}