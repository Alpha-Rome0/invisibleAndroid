package gteeny.invisiblecow;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class main extends Activity {
    long player;
    Firebase myFirebaseRef;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Mooer cow = Mooer.getInstance(getApplicationContext());

        //set to full screen 
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://invisiblecow.firebaseio.com/");
        myFirebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                player = (Long) snapshot.child("players").getValue();
                player++;
                myFirebaseRef.child("players").setValue(player);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        myFirebaseRef.child("players").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                player = (Long) dataSnapshot.getValue();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        Log.v("&&&&&&", Long.toString(player));
        setContentView(new GameSurface(this, cow, myFirebaseRef));
    }
    
}