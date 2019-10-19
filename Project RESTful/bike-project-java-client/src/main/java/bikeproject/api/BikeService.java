package bikeproject.api;

import bikeproject.api.model.Bike;
import bikeproject.api.model.BikeList;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BikeService {
   private ApiClient.BikeServiceRetrofit bikeServiceRetrofit;
    public BikeService(){
        bikeServiceRetrofit = ApiClient.getBikeService();
    }
    public List<Bike> getBikes() {
        List<Bike> bikeList = new ArrayList<>();
        try {
            Call<BikeList> bikeListCall = bikeServiceRetrofit.getBikes();
            Response<BikeList> bikeListResponse = bikeListCall.execute();
            bikeList = bikeListResponse.body().getBikeList();
        }
        catch (Exception e){
            System.out.println("No hay conexion con el servidor: getBikes()");
        }
        return bikeList;
    }
}
