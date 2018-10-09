import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;
import java.util.List;

// DO NOT MODIFY!!!

/**
 * Created by Wolfgang Mühlbauer on 24.05.2017.
 */
public class BundesligaSeason {

    private static final String ROOT_URL = "https://www.openligadb.de/api/getmatchdata/";

    private static Retrofit getRetrofitInstance()
    {
        /**
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();
        */
        return new Retrofit.Builder().
          //    client(client).
                baseUrl(ROOT_URL).
                addConverterFactory(GsonConverterFactory.create())
                .build();
    }



    /**
     * obtain all matches of a given Bundesliga season
     * @param year Bundesliga season, example: 2016 means Bundesliga season 2016/2017
     * @return List of matches
     */

    public static List<Match> getAllMatchesOfSeason(int year)
    {
        MatchService api = getRetrofitInstance().create(MatchService.class);
        Call<List<Match>> call = api.getMyJSON("bl1/" + year);
        List<Match> matches = null;
        try {
            matches = call.execute().body();
        } catch (IOException e) {
            System.out.println("Error in JSON query");
            e.printStackTrace();
        }
        return matches;
    }

}