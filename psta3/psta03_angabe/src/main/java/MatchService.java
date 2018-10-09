import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

import java.util.List;

/**
 * Created by Wolfgang Mühlbauer on 26.05.2017.
 */
public interface MatchService {

    @GET
    Call<List<Match>> getMyJSON(@Url String url);
}