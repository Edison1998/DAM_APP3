package mx.edu.tesoem.isc.eijd.dam_app3;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
/*
 * ------------NOTA------------------------------------
 * para que se puedan reproducir archivos GIF.
 * es necesario agregar dependencias en los archivos:
 * build.grandle(Project)
 * build.grandle(Module)
 * cambiar en activity_main.xml ImageView por GifImageView
 * */
public class MainActivity extends AppCompatActivity {

    ImageView imagen;
    TextView letrero;
    Button btn;
    MediaPlayer mp;
    Vibrator vibrator;
    int sr = 0;
    int temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ASOCIAMOS ATRIBUTOS, ID, LAYOUT
        imagen = (ImageView) findViewById(R.id.imageView);
        letrero = (TextView) findViewById(R.id.textView);
        btn = (Button) findViewById(R.id.button);

        mostrarRand();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarRand();
            }
        });
    }

    //Mostrar valores de aleatorio
    public void mostrarRand() {
        // randoms();
        temp=sr;
        sr = ThreadLocalRandom.current().nextInt(0,7);
        while(temp==sr){
            sr = ThreadLocalRandom.current().nextInt(0,7);
        }
        imagen.setImageResource(randArray[sr].getImagen());
        letrero.setText(randArray[sr].getAleatorio());

        //generar patron de vibracion
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        Random rand = new Random();
        int n = rand.nextInt(1000);
        long[] pattern = {n,n,n,n,n,n,n};
        vibrator.vibrate(pattern, -1);

        stopPlying();
        mp = MediaPlayer.create(this,randArray[sr].getSonido());
        mp.start();
    }

    aleatorio i1 = new aleatorio(R.drawable.uno,    "img1", R.raw.sound1);
    aleatorio i2 = new aleatorio(R.drawable.dos,    "img2", R.raw.sound2);
    aleatorio i3 = new aleatorio(R.drawable.tres,   "img3", R.raw.sound3);
    aleatorio i4 = new aleatorio(R.drawable.cuatro, "img4", R.raw.sound4);
    aleatorio i5 = new aleatorio(R.drawable.cinco,  "img5", R.raw.sound5);
    aleatorio i6 = new aleatorio(R.drawable.seis,   "img6", R.raw.sound6);
    aleatorio i7 = new aleatorio(R.drawable.siete,  "img7", R.raw.sound7);

    //LLenando array
    aleatorio[] randArray = new aleatorio[]{ i1, i2, i3, i4, i5, i6, i7  };

    public void randoms(){
        Collections.shuffle(Arrays.asList(randArray));
    }

    // METODO PARA DETENER E INICIAR UNA REPRODUCCION
    private void stopPlying(){
        if(mp != null){
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
