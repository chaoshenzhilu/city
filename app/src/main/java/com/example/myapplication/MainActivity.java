package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button remove_ads_continue;
    private String isVip="5";
    static {
        System.loadLibrary("NDKLib");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        remove_ads_continue = findViewById(R.id.remove_ads_continue);
        remove_ads_continue.setOnClickListener(v->{
            startActivity(new Intent(this, NDKActivity.class));
        });

        onRemoveAdsContinue();
//        String a="simple.sleep.alarm.clock.timer.stopwatch.TIMER_NAVIGATE";
//        String b="simple.sleep.alarm.clock.timer.stopwatch.ALARM_NAVIGATE";
//        String c="simple.sleep.alarm.clock.timer.stopwatch.STOPWATCH_NAVIGATE";
//        String d="simple.sleep.alarm.clock.timer.stopwatch.TIMER_ALERT";
//        Log.e("aaaa.hashcode", "  "+a.hashCode());
//        Log.e("bbbb.hashcode", "  "+b.hashCode());
//        Log.e("cccc.hashcode", "  "+c.hashCode());
//        Log.e("dddd.hashcode", "  "+d.hashCode());
//
//        TextView text=findViewById(R.id.text);
//        String a2="com.alarmclock.xtreme.TIMER_NAVIGATE";
//        String b2="com.alarmclock.xtreme.ALARM_NAVIGATE";
//        String c2="com.alarmclock.xtreme.STOPWATCH_NAVIGATE";
//        String d2="com.alarmclock.xtreme.TIMER_ALERT";
//        Log.e("aaaa222.hashcode", "  "+a2.hashCode());
//        Log.e("bbbb222.hashcode", "  "+b2.hashCode());
//        Log.e("cccc222.hashcode", "  "+c2.hashCode());
//        Log.e("dddd222.hashcode", "  "+d2.hashCode());
//
//        String a3="simple.sleep.alarm.clock.timer.stopwatch.STOPWATCH_TICK_START";
//        String b3="simple.sleep.alarm.clock.timer.stopwatch.STOPWATCH_TICK_STOP";
//        String c3="simple.sleep.alarm.clock.timer.stopwatch.STOPWATCH_START";
//        String d3="simple.sleep.alarm.clock.timer.stopwatch.STOPWATCH_RESUME";
//        String e3="simple.sleep.alarm.clock.timer.stopwatch.STOPWATCH_RESET";
//        String f3="simple.sleep.alarm.clock.timer.stopwatch.STOPWATCH_LAP";
//        String g3="simple.sleep.alarm.clock.timer.stopwatch.STOPWATCH_PAUSE";
//        Log.e("aaaa333.hashcode", "  "+a3.hashCode());
//        Log.e("bbbb333.hashcode", "  "+b3.hashCode());
//        Log.e("cccc333.hashcode", "  "+c3.hashCode());
//        Log.e("dddd333.hashcode", "  "+d3.hashCode());
//        Log.e("eeee333.hashcode", "  "+e3.hashCode());
//        Log.e("ffff333.hashcode", "  "+f3.hashCode());
//        Log.e("gggg333.hashcode", "  "+g3.hashCode());
//
//
//
//        String ACTION_TAP_NOTIFICATION="simple.sleep.alarm.clock.timer.stopwatch.ACTION_TAP_NOTIFICATION";
//        String SHOW_NOTIFICATION="simple.sleep.alarm.clock.timer.stopwatch.SHOW_NOTIFICATION";
//        String DISMISS_NOTIFICATION="simple.sleep.alarm.clock.timer.stopwatch.DISMISS_NOTIFICATION";
//        String CANCEL_NOTIFICATION="simple.sleep.alarm.clock.timer.stopwatch.CANCEL_NOTIFICATION";
//        String TAP_NOTIFICATION="simple.sleep.alarm.clock.timer.stopwatch.TAP_NOTIFICATION";
//        Log.e("ACTION_TAP.hashcode", "  "+ACTION_TAP_NOTIFICATION.hashCode());
//        Log.e("SHOW.hashcode", "  "+SHOW_NOTIFICATION.hashCode());
//        Log.e("DISMISS.hashcode", "  "+DISMISS_NOTIFICATION.hashCode());
//        Log.e("CANCEL.hashcode", "  "+CANCEL_NOTIFICATION.hashCode());
//        Log.e("TAP.hashcode", "  "+TAP_NOTIFICATION.hashCode());

//        String STOP_COUNTDOWN="simple.sleep.alarm.clock.timer.stopwatch.STOP_COUNTDOWN";
//        String START_COUNTDOWN="simple.sleep.alarm.clock.timer.stopwatch.START_COUNTDOWN";
//        Log.e("STOP.hashcode", "  "+STOP_COUNTDOWN.hashCode());
//        Log.e("START.hashcode", "  "+START_COUNTDOWN.hashCode());

//        String START_ALARM="simple.sleep.alarm.clock.timer.stopwatch.START_ALARM";
//        String STOP_ALARM="simple.sleep.alarm.clock.timer.stopwatch.STOP_ALARM";
//        Log.e("START_ALARM.hashcode", "  "+START_ALARM.hashCode());
//        Log.e("STOP_ALARM.hashcode", "  "+STOP_ALARM.hashCode());

//        String RESUME_MUSIC="simple.sleep.alarm.clock.timer.stopwatch.RESUME_MUSIC";
//        String STOP_MUSIC="simple.sleep.alarm.clock.timer.stopwatch.STOP_MUSIC";
//        String START_MUSIC="simple.sleep.alarm.clock.timer.stopwatch.START_MUSIC";
//        String PAUSE_MUSIC="simple.sleep.alarm.clock.timer.stopwatch.PAUSE_MUSIC";
//        Log.e("RESUME_MUSIC.hashcode", "  "+RESUME_MUSIC.hashCode());
//        Log.e("STOP_MUSIC.hashcode", "  "+STOP_MUSIC.hashCode());
//        Log.e("START_MUSIC.hashcode", "  "+START_MUSIC.hashCode());
//        Log.e("PAUSE_MUSIC.hashcode", "  "+PAUSE_MUSIC.hashCode());

//        String UPCOMING_ALARM_DISABLED="simple.sleep.alarm.clock.timer.stopwatch.intent.action.UPCOMING_ALARM_DISABLED";
//        String UPCOMING_ALARM_SET="simple.sleep.alarm.clock.timer.stopwatch.intent.action.UPCOMING_ALARM_SET";
//        Log.e("DISABLED.hashcode", "  "+UPCOMING_ALARM_DISABLED.hashCode());
//        Log.e("ALARM_SET.hashcode", "  "+UPCOMING_ALARM_SET.hashCode());

//        String MUTE_ALARM="simple.sleep.alarm.clock.timer.stopwatch.MUTE_ALARM";
//        String SNOOZE_ALARM="simple.sleep.alarm.clock.timer.stopwatch.SNOOZE_ALARM";
//        String UNMUTE_ALARM="simple.sleep.alarm.clock.timer.stopwatch.UNMUTE_ALARM";
//        String REINITIALIZE_SNOOZE_ALARM="simple.sleep.alarm.clock.timer.stopwatch.REINITIALIZE_SNOOZE_ALARM";
//        String REINITIALIZE_ALARM="simple.sleep.alarm.clock.timer.stopwatch.REINITIALIZE_ALARM";
//        Log.e("MUTE_ALARM.hashcode", "  "+MUTE_ALARM.hashCode());
//        Log.e("SNOOZE_ALARM.hashcode", "  "+SNOOZE_ALARM.hashCode());
//        Log.e("UNMUTE_ALARM.hashcode", "  "+UNMUTE_ALARM.hashCode());
//        Log.e("REINITIALIZE1", "  "+REINITIALIZE_SNOOZE_ALARM.hashCode());
//        Log.e("REINITIALIZE2.hashcode", "  "+REINITIALIZE_ALARM.hashCode());

//        String STOPWATCH_LAP="simple.sleep.alarm.clock.timer.stopwatch.STOPWATCH_LAP";
//        String STOPWATCH_PAUSE="simple.sleep.alarm.clock.timer.stopwatch.STOPWATCH_PAUSE";
//        String STOPWATCH_RESET="simple.sleep.alarm.clock.timer.stopwatch.STOPWATCH_RESET";
//        String STOPWATCH_START="simple.sleep.alarm.clock.timer.stopwatch.STOPWATCH_START";
//        String STOPWATCH_RESUME="simple.sleep.alarm.clock.timer.stopwatch.STOPWATCH_RESUME";
//        Log.e("LAP.hashcode", "  "+STOPWATCH_LAP.hashCode());
//        Log.e("PAUSE.hashcode", "  "+STOPWATCH_PAUSE.hashCode());
//        Log.e("RESET.hashcode", "  "+STOPWATCH_RESET.hashCode());
//        Log.e("START", "  "+STOPWATCH_START.hashCode());
//        Log.e("RESUME.hashcode", "  "+STOPWATCH_RESUME.hashCode());

//        String ALARM_ALERT1="com.alarmclock.xtreme.ALARM_ALERT";
//        String ALARM_ALERT2="simple.sleep.alarm.clock.timer.stopwatch.ALARM_ALERT";
//        Log.e("ALARM_ALERT1.hashcode", "  "+ALARM_ALERT1.hashCode());
//        Log.e("ALARM_ALERT2.hashcode", "  "+ALARM_ALERT2.hashCode());
    }

    void onRemoveAdsContinue() {


    }
}
