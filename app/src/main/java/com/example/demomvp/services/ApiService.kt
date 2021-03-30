package com.example.demomvp.services

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class ApiService{
    suspend fun httpGet(myURL: String?): String {
        return withContext(Dispatchers.IO) {
            val url = URL(myURL)
            val httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.connectTimeout = 15000
            httpURLConnection.readTimeout = 15000
            httpURLConnection.requestMethod = "GET"
            httpURLConnection.doOutput = true
            httpURLConnection.connect()

            val bufferedReader = BufferedReader(InputStreamReader(url.openStream()))
            val stringBuilder = StringBuilder()
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
            bufferedReader.close()
            httpURLConnection.disconnect()
            stringBuilder.toString()
        }
    }
}

