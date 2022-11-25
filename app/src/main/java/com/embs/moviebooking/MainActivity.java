package com.embs.moviebooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;

import com.embs.moviebooking._home.Home;
import com.embs.moviebooking._models.Movie;
import com.embs.moviebooking._models.User;
import com.embs.moviebooking._utils.DatabaseHelper;
import com.embs.moviebooking._utils.Helper;
import com.embs.moviebooking.front.front;

public class MainActivity extends AppCompatActivity {

    private User dummyUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dbHelper.dropDbs(new String[] {"movie", "ticket"});
        dbHelper.checkTableExist();
        dbHelper.truncateDbs(new String[] {"movie"});
        dummyUser = new User("louellagracechua@gmail.com", "Jervx", Helper.hashPassword("helloworld"));

        if(!dummyUser.checkIfAlreadyExist(dbHelper)){
            dummyUser.setState(0);
            dummyUser.saveState(getApplicationContext(), dbHelper, true);
        }

        Movie [] mv = new Movie[] {
                new Movie("mvc_jwkchpt2", "John Wick: Chapter 2", "Retired super-assassin John Wick's plans to resume a quiet civilian life are cut short when Italian gangster Santino D'Antonio shows up on his doorstep with a gold marker, compelling him to repay past favours. Ordered by Winston, the kingpin of secret assassin society The Continental, to respect the organisation's ancient code, Wick reluctantly accepts the assignment to travel to Rome to take out D'Antonio's sister, the ruthless capo atop the Italian Camorra crime syndicate.", "Cinema 1", "Dec 1, 2022", "10:30 AM", Helper.seatGenerator(1, 32), "", "Action", "2h 2m", 149),
                new Movie("mvc_encnto", "Encanto", "The Madrigals are an extraordinary family who live hidden in the mountains of Colombia in a charmed place called the Encanto. The magic of the Encanto has blessed every child in the family with a unique gift -- every child except Mirabel. However, she soon may be the Madrigals last hope when she discovers that the magic surrounding the Encanto is now in danger.", "Cinema 1", "Dec 1, 2022", "1:40 PM", Helper.seatGenerator(1, 32), "", "Musical/Family", "1h 49m", 200),
                new Movie("mvc_moana", "Moana", "An adventurous teenager sails out on a daring mission to save her people. During her journey, Moana meets the once-mighty demigod Maui, who guides her in her quest to become a master way-finder. Together they sail across the open ocean on an action-packed voyage, encountering enormous monsters and impossible odds. Along the way, Moana fulfills the ancient quest of her ancestors and discovers the one thing she always sought: her own identity.", "Cinema 2", "Dec 1, 2022", "10:30 AM", Helper.seatGenerator(1, 32), "", "Family", "1h 43m", 180),
                new Movie("mvc_tpgnmvrck", "Top Gun: Maverick", "After more than 30 years of service as one of the Navy's top aviators, Pete \"Maverick\" Mitchell is where he belongs, pushing the envelope as a courageous test pilot and dodging the advancement in rank that would ground him. Training a detachment of graduates for a special assignment, Maverick must confront the ghosts of his past and his deepest fears, culminating in a mission that demands the ultimate sacrifice from those who choose to fly it.", "Cinema 2", "Dec 1, 2022", "1:30 PM", Helper.seatGenerator(1, 32), "", "Action", "2h 11m", 230),
                new Movie("mvc_2thcntrygrl", "20th Century Girl", "In 1999, a teen girl keeps close tabs on a boy in school on behalf of her deeply smitten best friend, then she gets swept up in a love story of her own.", "Cinema 3", "Dec 2, 2022", "10:30 AM", Helper.seatGenerator(1, 32), "", "Romance", "1h 59", 180),
                new Movie("mvc_bbl", "Bubble", "Gravity-defying bubbles rain down, cutting off Tokyo from the rest of the world. The city skyline becomes a playground for young people competing in parkour team battles. Hibiki plummets into the sea but is saved by a girl with mysterious powers.", "Cinema 1", "Dec 2, 2022", "3:30 PM", Helper.seatGenerator(1, 32), "", "Anime", "1h 40m", 220),
                new Movie("mvc_dspcble2022", "Minions: The Rise of Gru", "In the 1970s, young Gru tries to join a group of supervillains called the Vicious 6 after they oust their leader -- the legendary fighter Wild Knuckles. When the interview turns disastrous, Gru and his Minions go on the run with the Vicious 6 hot on their tails. Luckily, he finds an unlikely source for guidance -- Wild Knuckles himself -- and soon discovers that even bad guys need a little help from their friends.", "Cinema 2", "Dec 3, 2022", "10:30 AM", Helper.seatGenerator(1, 32), "", "Adventure", "1h 30m", 220),
                new Movie("mvc_ywntbaln", "You Won't Be Alone", "In 19th-century Macedonia, a young girl is kidnapped and then transformed into a witch by an ancient spirit. Curious about life as a human, the witch accidentally kills a peasant in the nearby village and then takes her victim's shape to live life in her skin. Her curiosity ignited, she continues to wield this horrific power to understand what it means to be human.", "Cinema 1", "Dec 3, 2022", "12:30 PM", Helper.seatGenerator(1, 32), "", "Horror", "1h 49m", 290),
                new Movie("mvc_sncdhdghg2", "Sonic the Hedgehog 2", "After settling in Green Hills, Sonic is eager to prove that he has what it takes to be a true hero. His test comes when Dr. Robotnik returns with a new partner, Knuckles, in search of a mystical emerald that has the power to destroy civilizations. Sonic teams up with his own sidekick, Tails, and together they embark on a globe-trotting journey to find the emerald before it falls into the wrong hands.", "Cinema 1", "Dec 5, 2022", "10:30 AM", Helper.seatGenerator(1, 32), "", "Adventure", "2h 2m", 169),
                new Movie("mvc_myfthrsdrgn", "My Father's Dragon", "Elmer, who is having trouble adjusting to his new life, decides to set out to find a wild island and rescue a young dragon. His adventures will lead him to encounter ferocious beasts, discover a mysterious place and make a new friendship.", "Cinema 1", "Dec 6, 2022", "1:30 PM", Helper.seatGenerator(1, 32), "", "Animation/Comedy", "1h 39m", 210)
        };
        for(Movie m : mv){
            m.saveState(this, dbHelper, true);
            m.fetchSelf(dbHelper);
            System.out.println("IMG RESOURCE ID : " + m.getMovieCoverResID(this));
        }

        Cursor hasLoggedIn = dbHelper.execRawQuery("SELECT * FROM user where state=1", null);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(hasLoggedIn == null || hasLoggedIn.getCount() == 0){
                    hasLoggedIn.close();
                    Intent front = new Intent(getApplicationContext(), front.class);
                    startActivity(front);
                    finish();
                }else{
                    hasLoggedIn.moveToNext();
                    dummyUser = new User(hasLoggedIn.getString(2));
                    System.out.println("USER DESU");
                    System.out.println(dummyUser.toString());
                    dummyUser.fetchSelf(dbHelper);

                    Intent homeIntent = new Intent(getApplicationContext(), Home.class);
                    homeIntent.putExtra("currentUser", dummyUser);
                    startActivity(homeIntent);
                    finish();
                }
            }
        },2000);


    }

}