package com.dongpakka.mqtt_ex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dongpakka.mqttkotlin.Mqtt
import com.dongpakka.mqttkotlin.R
import org.eclipse.paho.client.mqttv3.MqttMessage
import java.lang.Exception

const val SUB_TOPIC = "iot/#"
const val PUB_TOPIC = "iot/led"
const val SERVER_URI = "tcp://172.30.1.17:1883"

class MainActivity : AppCompatActivity() {
    val TAG = "MqttActivity"
    lateinit var mqttClient: Mqtt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("Mqtt", "On Create.....")
//        mqttClient = Mqtt(this, SERVER_URI)
        try{
//            mqttClient.setCallback{topic, message ->}
            mqttClient.setCallback(::onReceived)
            mqttClient.connect(arrayOf<String>(SUB_TOPIC))
            Log.e("Mqtt", "On Create..... connect")
        } catch(e: Exception){
            e.printStackTrace()
        }
    }
    fun onReceived(topic:String, message: MqttMessage) {
        //토픽 수신 처리
        val msg = String(message.payload)
        Log.i("Mqtt","수신 메세지: $msg")
    }

    fun publish() {
        mqttClient.publish(PUB_TOPIC, "1")
    }
}