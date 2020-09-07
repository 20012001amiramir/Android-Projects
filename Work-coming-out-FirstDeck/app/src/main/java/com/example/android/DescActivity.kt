package com.example.android

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_desc.*


class DescActivity : AppCompatActivity() {
    private var timeH: Int = 0
    private var timeM: Int = 0
    private var timeS: Int = 0
    private var timeMs: Int = 1000
    private var timeHL: Int = 0
    private var timeML: Int = 0
    private var timeSL: Int = 0
    private var timeMsL: Int = 0
    private var going: Boolean = false
    private var start: Boolean = false

    var image: ImageView? = null
    var title: TextView? = null
    var description: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desc)

        image = findViewById(R.id.ex_image)
        title = findViewById(R.id.ex_title)
        description = findViewById(R.id.ex_description)

        val name = intent?.extras?.getString(KEY_NAME) ?: "DEFAULT NAME"
        val desc = intent?.extras?.getString(KEY_DESC) ?: "DEFAULT DESC"
        val img = intent?.extras?.getInt(KEY_IMG) ?: R.mipmap.ic_launcher

        title?.setText(name)
        image?.setImageResource(img)
        description?.setText(desc)


        /*tv_desc_city_name.text = name
        tv_desc_city_desc.text = desc
        iv_desc_city.setImageResource(img)*/
        //еще варик
        //val id = Integer.parseInt(intent.extras?.getString("id").toString())
        //image?.setImageResource(img)
        //title?.text = name
        //description?.text = desc

        val actionBar = supportActionBar
        actionBar!!.title = "Exercise"

        actionBar.setDisplayHomeAsUpEnabled(true)

        /*TmBackButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }*/

        TmStartButton.setOnClickListener {
            going = true
        }

        TmStopButton.setOnClickListener {
            going = false
        }

        TmResetButton.setOnClickListener {
            timeS = timeSL
            timeM = timeML
            timeH = timeHL
            timeMsL = 0
            timeMs = 1000
            TmTimeView.text = ("$timeHL : $timeML : $timeSL . $timeMsL")
        }

        TmPUSButton.setOnClickListener {
            if (!going) {
                timeSL++
                if (timeSL >= 60) {
                    timeSL -= 60
                    timeML++
                }
                if (timeML >= 60) {
                    timeML -= 60
                    timeHL++
                }
                TmTimeView.text = ("$timeHL : $timeML : $timeSL . $timeMsL")
                timeS = timeSL
                timeM = timeML
                timeH = timeHL
            }
        }

        TmPTSButton.setOnClickListener {
            if (!going) {
                timeSL += 10
                if (timeSL >= 60) {
                    timeSL -= 60
                    timeML++
                }
                if (timeML >= 60) {
                    timeML -= 60
                    timeHL++
                }
                TmTimeView.text = ("$timeHL : $timeML : $timeSL . $timeMsL")
                timeS = timeSL
                timeM = timeML
                timeH = timeHL
            }
        }

        TmPUMButton.setOnClickListener {
            if (!going) {
                timeML++
                if (timeML >= 60) {
                    timeML -= 60
                    timeHL++
                }
                TmTimeView.text = ("$timeHL : $timeML : $timeSL . $timeMsL")
                timeS = timeSL
                timeM = timeML
                timeH = timeHL
            }
        }

        TmMUSButton.setOnClickListener {
            if (!going) {
                when {
                    timeSL > 0 -> {
                        timeSL--
                    }
                    timeML > 0 -> {
                        timeML--
                        timeSL += 59
                    }
                    timeHL > 0 -> {
                        timeHL--
                        timeML += 59
                        timeSL += 59
                    }
                }
                TmTimeView.text = ("$timeHL : $timeML : $timeSL . $timeMsL")
                timeS = timeSL
                timeM = timeML
                timeH = timeHL
            }
        }

        TmMTSButton.setOnClickListener {
            if (!going) {
                when {
                    timeSL >= 10 -> {
                        timeSL -= 10
                    }
                    timeML > 0 -> {
                        timeML--
                        timeSL += 50
                    }
                    timeHL > 0 -> {
                        timeHL--
                        timeML += 59
                        timeSL += 50
                    }
                }
                TmTimeView.text = ("$timeHL : $timeML : $timeSL . $timeMsL")
                timeS = timeSL
                timeM = timeML
                timeH = timeHL
            }
        }

        TmMUMButton.setOnClickListener {
            if (!going) {
                if (timeML > 0) {
                    timeML--
                } else if (timeHL > 0) {
                    timeHL--
                    timeML += 59
                }
                TmTimeView.text = ("$timeHL : $timeML : $timeSL . $timeMsL")
                timeS = timeSL
                timeM = timeML
                timeH = timeHL
            }
        }

        Thread {
            start = true
            TmTimeView.text = ("0 : 0 : 0 . 0")
            while (start) {
                Thread.sleep(10)
                while (going) {
                    Thread.sleep(7)
                    timeMs += 11
                    if (timeMs >= 1000) {
                        timeMs = 0
                        timeS--
                    }
                    timeMsL = 1000 - timeMs
                    if (timeS < 0) {
                        timeS = 59
                        timeM--
                    }
                    if (timeM < 0) {
                        timeM = 59
                        timeH--
                    }
                    if (timeH < 0) {
                        going = false
                        timeH = 0
                        timeM = 0
                        timeS = 0
                        timeMsL = 0
                        timeMs = 1000
                    }
                    TmTimeView.text = ("$timeH : $timeM : $timeS . $timeMsL")
                }
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        start = false
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    companion object {

        private const val KEY_NAME = "name"
        private const val KEY_DESC = "desc"
        private const val KEY_IMG = "img"

        fun createIntent(activity: Activity, name: String, desc: String, img: Int) =
                Intent(activity, DescActivity::class.java).apply {
                    putExtra(KEY_NAME, name)
                    putExtra(KEY_DESC, desc)
                    putExtra(KEY_IMG, img)
                }
    }
}