package gteeny.invisiblecow;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.Toast;

/**
 * Created by mzwan_000 on 11/15/2015.
 */
public class Mooer {
    private MediaPlayer[] cowsounds;
    private int delta = 50;
    private int counter = 0;

    private Mooer(Context c) {

        cowsounds = new MediaPlayer[10];
        int[] rawFiles = new int[] {R.raw.cow1, R.raw.cow2,
                R.raw.cow3, R.raw.cow4,
                R.raw.cow5, R.raw.cow6,
                R.raw.cow7, R.raw.cow8,
                R.raw.cow9, R.raw.cow10
        };

        for (int i=0; i < 10; i++) {
            cowsounds[i] = MediaPlayer.create(c, rawFiles[i]);
        }


    }
    public void moo(int distance){
        cowsounds[Math.min(9, Math.max(10-distance/delta, 3))].start();
    }


    private static Mooer me = null;

    public static Mooer getInstance(Context c) {
        if(me == null) {
            me = new Mooer(c);
        }
        return me;
    }
}
