package vector.ijhh.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionStateChange;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //CREATE A CONNECTION
        PusherOptions options = new PusherOptions();
        options.setCluster("ap2");
        Pusher pusher = new Pusher("5a7750401700e8f11201", options);
        Channel channel = pusher.subscribe("my-channel");
        pusher.connect();

        pusher.connect(new ConnectionEventListener() {
            @Override
            public void onConnectionStateChange(ConnectionStateChange connectionStateChange) {
                System.out.println("State changed to " + connectionStateChange.getCurrentState() +
                        " from " + connectionStateChange.getPreviousState());
            }

            @Override
            public void onError(String s, String s1, Exception e) {
                System.out.println("There was a problem connecting!");


            }
        });

        channel.bind("my-event", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                System.out.println(data);
            }
        });

        pusher.connect();

    }
}
